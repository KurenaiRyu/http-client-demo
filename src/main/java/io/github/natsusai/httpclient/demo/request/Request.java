package io.github.natsusai.httpclient.demo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.natsusai.httpclient.demo.util.constant.HttpMethod;

/**
 * @author Kurenai
 * @since 2020-11-11 13:49
 */

public interface Request {


  @JsonIgnore
  HttpMethod getHttpMethod();

  @JsonIgnore
  String getUri();

}
