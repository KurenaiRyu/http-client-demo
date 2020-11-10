package io.github.natsusai.httpclient.demo.exception;


import io.github.natsusai.httpclient.demo.util.constant.StringPool;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Kurenai
 * @since 2020/3/23 13:10
 */

@Getter
@Setter
@ToString
public class ErrorMessage {

  private String errorCode;
  private String message;
  private String detailMsg;

  public ErrorMessage() {
  }

  public ErrorMessage(String errorCode, String message) {
    this(errorCode, message, StringPool.EMPTY);
  }

  public ErrorMessage(String errorCode, String message, String detailMsg) {
    this.errorCode = errorCode;
    this.message = message;
    this.detailMsg = detailMsg;
  }
}
