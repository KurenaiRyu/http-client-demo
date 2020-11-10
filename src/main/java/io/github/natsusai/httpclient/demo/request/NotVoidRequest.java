package io.github.natsusai.httpclient.demo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.natsusai.httpclient.demo.response.AbstractResponse;

/**
 * 请求接口
 *
 * @author Kurenai
 * @since 2020-11-10 15:45
 */

public interface NotVoidRequest<T extends AbstractResponse> extends Request {

  @JsonIgnore
  TypeReference<T> getTypeReference();

}
