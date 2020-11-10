package io.github.natsusai.httpclient.demo.request;


import io.github.natsusai.httpclient.demo.util.constant.HttpMethod;

/**
 * 请求
 * @author Kurenai
 * @since 2020-11-10 15:51
 */

public abstract class AbstractRequest {

  public HttpMethod getHttpMethod() {
    return HttpMethod.GET;
  }

}
