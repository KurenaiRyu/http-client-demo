package io.github.natsusai.httpclient.demo.request.core;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.natsusai.httpclient.demo.request.AbstractPageRequest;
import io.github.natsusai.httpclient.demo.request.NotVoidRequest;
import io.github.natsusai.httpclient.demo.response.PageResponse;
import io.github.natsusai.httpclient.demo.response.core.SkuResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * sku列表请求对象
 *
 * @author Kurenai
 * @since 2020-11-11 11:00
 */

@Getter
@Setter
public class SkuListRequest extends AbstractPageRequest implements
    NotVoidRequest<PageResponse<SkuResponse>> {

  /**
   * 款id
   */
  private Long prodId;
  /**
   * 款号
   */
  private String prodCode;
  /**
   * 条码
   */
  private String barcode;

  @Override
  public String getUri() {
    return "/api/core/sku/list";
  }

  @Override
  public TypeReference<PageResponse<SkuResponse>> getTypeReference() {
    return new TypeReference<>() {
    };
  }
}
