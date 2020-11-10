package io.github.natsusai.httpclient.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.github.natsusai.httpclient.demo.exception.ClientException;
import io.github.natsusai.httpclient.demo.exception.ErrorMessage;
import io.github.natsusai.httpclient.demo.request.ArrayRequest;
import io.github.natsusai.httpclient.demo.request.NotVoidRequest;
import io.github.natsusai.httpclient.demo.request.Request;
import io.github.natsusai.httpclient.demo.response.AbstractResponse;
import io.github.natsusai.httpclient.demo.util.StringUtil;
import io.github.natsusai.httpclient.demo.util.constant.HttpStatus;
import io.github.natsusai.httpclient.demo.util.constant.RequestHead;
import io.github.natsusai.httpclient.demo.util.constant.StringPool;
import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Kurenai
 * @since 2020-11-10 15:52
 */


public class DefaultClient implements Client {

  public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

  private final String serverUrl;
  private final String token;
  private final ObjectMapper mapper = new ObjectMapper();
  //TODO: 账号密码？

  public DefaultClient(String serverUrl, String token) {
    this.serverUrl = serverUrl;
    String tokenPrefix = "Bearer ";
    this.token = StringUtil.startsWith(token, tokenPrefix) ? token : tokenPrefix + token;
    buildJackson();
  }

  @Override
  public <T extends AbstractResponse> T execute(NotVoidRequest<T> entityRequest)
          throws ClientException {
    OkHttpClient client = new OkHttpClient();
    okhttp3.Request request = buildRequest(entityRequest);
    try (Response response = client.newCall(request).execute()) {
      ResponseBody responseBody = response.body();
      if (responseBody == null || responseBody.contentLength() == 0) {
        return null;
      }
      resolveException(response);
      try {
        return mapper.readValue(responseBody.bytes(), entityRequest.getTypeReference());
      } catch (IOException e) {
        throw new ClientException("响应对象转换失败！", e);
      }
    } catch (IOException e) {
      throw new ClientException("请求失败，请重试！", e);
    }
  }

  @Override
  public void execute(Request entityRequest) throws ClientException {
    OkHttpClient client = new OkHttpClient();
    okhttp3.Request request = buildRequest(entityRequest);
    try (Response response = client.newCall(request).execute()) {
      resolveException(response);
    } catch (IOException e) {
      throw new ClientException("请求失败，请重试！", e);
    }
  }

  private void resolveException(Response response) throws ClientException {
    if (response.code() == 200) {
      return;
    }

    ResponseBody body = response.body();
    if (body != null) {
      byte[] bytes;
      try {
        bytes = body.bytes();
        try {
          ErrorMessage errorMessage = mapper.readValue(bytes, ErrorMessage.class);
          throw new ClientException(Objects.requireNonNull(HttpStatus.resolve(response.code())),
              errorMessage);
        } catch (IOException e) {
          throw new ClientException(Objects.requireNonNull(HttpStatus.resolve(response.code())),
              new String(bytes, StandardCharsets.UTF_8));
        }
      } catch (IOException e) {
        throw new ClientException(Objects.requireNonNull(HttpStatus.resolve(response.code())));
      }
    }
  }

  private okhttp3.Request buildRequest(Request request) throws ClientException {
    RequestBody body;
    try {
      String json;
      if (request instanceof ArrayRequest) {
        json = mapper.writeValueAsString(((ArrayRequest) request).getData());
      } else {
        json = mapper.writeValueAsString(request);
      }
      body = RequestBody.create(json, JSON);
    } catch (JsonProcessingException e) {
      throw new ClientException("请求转换json失败！", e);
    }
    Builder builder = new Builder()
        .header(RequestHead.AUTHORIZATION, token)
        .header(RequestHead.ACCEPT_LANGUAGE, "zh-CN");
    switch (request.getHttpMethod()) {
      case GET:
        return builder
            .url(serverUrl + request.getUri() + buildParam(request))
            .get().build();
      case DELETE:
        return builder
            .url(serverUrl + request.getUri())
            .delete(body).build();
      case PUT:
        return builder
            .url(serverUrl + request.getUri())
            .put(body).build();
      default:
        return builder
            .url(serverUrl + request.getUri())
            .post(body).build();
    }
  }

  private String buildParam(Request request) throws ClientException {
    try {
      Map<String, String> map = mapper.readValue(mapper.writeValueAsBytes(request),
          new TypeReference<Map<String, String>>() {
          });
      List<String> params = new ArrayList<>();
      map.forEach((k, v) -> {
        if (StringUtil.areNotEmpty(k, v)) {
          params.add(k + "=" + v);
        }
      });
      return StringPool.QUESTION_MARK + String.join(StringPool.AMPERSAND, params);
    } catch (IOException e) {
      throw new ClientException("构建get参数失败！", e);
    }
  }

  private void buildJackson() {
    //配置jackson
    String dateFormat = "yyyy-MM-dd HH:mm:ss";
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addDeserializer(LocalDateTime.class,
        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
    javaTimeModule.addSerializer(LocalDateTime.class,
        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormat)));

    mapper.registerModule(javaTimeModule);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

}
