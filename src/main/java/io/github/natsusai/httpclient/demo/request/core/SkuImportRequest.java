package io.github.natsusai.httpclient.demo.request.core;

import io.github.natsusai.httpclient.demo.request.AbstractRequest;
import io.github.natsusai.httpclient.demo.request.ArrayRequest;
import io.github.natsusai.httpclient.demo.request.Request;
import io.github.natsusai.httpclient.demo.util.constant.HttpMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 尺码组列表请求对象
 *
 * @author Kurenai
 * @since 2020-11-11 11:00
 */

@Getter
@Setter
public class SkuImportRequest extends AbstractRequest implements Request, ArrayRequest {

  List<SkuImport> data;

  @Getter
  @Setter
  static class SkuImport {

    /**
     * 款号
     */
    private String prodCode;
    /**
     * 款名称
     */
    private String prodName;
  }

  @Override
  public HttpMethod getHttpMethod() {
    return HttpMethod.POST;
  }

  @Override
  public String getUri() {
    return "/api/core/sku/imports";
  }
}
