package io.github.natsusai.httpclient.demo.response.core;

import io.github.natsusai.httpclient.demo.response.AbstractResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Kurenai
 * @since 2020-11-11 11:02
 */

@Getter
@Setter
public class BrandResponse extends AbstractResponse {

  /**
   * 编码
   */
  private String code;
  /**
   * 创建时间
   */
  private LocalDateTime created;
  /**
   * 是否停用
   */
  private boolean disabled;
  /**
   * Pk
   */
  private Long id;
  /**
   * 修改时间
   */
  private LocalDateTime modified;
  /**
   * 名称
   */
  private String name;
  /**
   * 备注
   */
  private String remark;

}
