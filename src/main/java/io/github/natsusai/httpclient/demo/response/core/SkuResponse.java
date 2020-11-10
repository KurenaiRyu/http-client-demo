package io.github.natsusai.httpclient.demo.response.core;

import io.github.natsusai.httpclient.demo.response.AbstractResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 尺码组响应对象
 *
 * @author Kurenai
 * @since 2020-11-11 11:02
 */

@Getter
@Setter
public class SkuResponse extends AbstractResponse {

  /**
   * 条码ID
   */
  private Long id;
  /**
   * 条码
   */
  private String barcode;
  /**
   * 条码名称
   */
  private String name;
  /**
   * 是否已停用
   */
  private Boolean disabled;
  /**
   * 创建时间
   */
  private LocalDateTime created;
  /**
   * 修改时间
   */
  private LocalDateTime modified;
  /**
   * 款ID
   */
  private Long prodId;
  /**
   * 款号
   */
  private String prodCode;
  /**
   * 款名称
   */
  private String prodName;
  /**
   * 颜色ID
   */
  private Long colorId;
  /**
   * 颜色编码
   */
  private String colorCode;
  /**
   * 颜色名称
   */
  private String colorName;
  /**
   * 尺码ID
   */
  private Long sizeId;
  /**
   * 尺码编码
   */
  private String sizeCode;
  /**
   * 尺码名称
   */
  private String sizeName;
  /**
   * 规格ID
   */
  private Long specId;
  /**
   * 规格编码
   */
  private String specCode;
  /**
   * 规格名称
   */
  private String specName;

}
