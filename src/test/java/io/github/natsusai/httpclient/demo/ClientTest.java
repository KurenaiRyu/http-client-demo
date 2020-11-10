package io.github.natsusai.httpclient.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.github.natsusai.httpclient.demo.exception.ClientException;
import io.github.natsusai.httpclient.demo.request.NotVoidRequest;
import io.github.natsusai.httpclient.demo.request.core.SkuListRequest;
import io.github.natsusai.httpclient.demo.util.StringUtil;
import io.github.natsusai.httpclient.demo.util.constant.StringPool;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientTest {

  ObjectMapper mapper = new ObjectMapper();

  @Before
  public void setUp() throws Exception {
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

  @Test
  public void testGet() throws ClientException {
    String param = buildParam(new SkuListRequest());
    System.out.println("param = " + param);
  }

  private String buildParam(NotVoidRequest<?> notVoidRequest) throws ClientException {
    try {
      Map<String, String> map = mapper.readValue(mapper.writeValueAsBytes(notVoidRequest),
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
}