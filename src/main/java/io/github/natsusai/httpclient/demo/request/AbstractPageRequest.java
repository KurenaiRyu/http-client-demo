package io.github.natsusai.httpclient.demo.request;


import io.github.natsusai.httpclient.demo.util.constant.OrderBy;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求
 *
 * @author Kurenai
 * @since 2020-11-10 15:51
 */

@Getter
@Setter
public abstract class AbstractPageRequest extends AbstractRequest {
  private boolean enablePage = true;
  private int currentPage = 1;
  private int pageSize = 20;
  private String  orderByField;
  private OrderBy orderByMethod = OrderBy.DESCEND;
}
