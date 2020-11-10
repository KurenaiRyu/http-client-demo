package io.github.natsusai.httpclient.demo.request.core;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.natsusai.httpclient.demo.request.AbstractPageRequest;
import io.github.natsusai.httpclient.demo.request.NotVoidRequest;
import io.github.natsusai.httpclient.demo.response.PageResponse;
import io.github.natsusai.httpclient.demo.response.core.BrandResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * 品牌列表请求对象
 *
 * @author Kurenai
 * @since 2020-11-11 11:00
 */

@Getter
@Setter
public class BrandListRequest extends AbstractPageRequest implements
    NotVoidRequest<PageResponse<BrandResponse>> {

  /**
   * 编码
   */
  private String code;
  /**
   * 名称
   */
  private String name;

  @Override
  public String getUri() {
    return "/api/core/brand/list";
  }

  @Override
  public TypeReference<PageResponse<BrandResponse>> getTypeReference() {
    return new TypeReference<>() {
    };
  }
}
