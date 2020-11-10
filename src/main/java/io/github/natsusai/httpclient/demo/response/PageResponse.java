package io.github.natsusai.httpclient.demo.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kurenai
 * @since 2020-11-10 15:49
 */

@Getter
@Setter
public class PageResponse<T extends AbstractResponse> extends AbstractResponse {

  private List<T> data = new ArrayList<>();
  private long currentPage = 1;
  private long pageSize = 20;
  private boolean hasNext = false;

}
