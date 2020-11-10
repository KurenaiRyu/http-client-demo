package io.github.natsusai.httpclient.demo.exception;

import io.github.natsusai.httpclient.demo.util.constant.HttpStatus;
import io.github.natsusai.httpclient.demo.util.constant.StringPool;
import lombok.Getter;

/**
 * @author Kurenai
 * @since 2020-11-10 17:15
 */

@Getter
public class ClientException extends Exception {

  private ErrorMessage errorMessage;

  private static final long serialVersionUID = -2381286082241474853L;

  public ClientException() {
  }

  public ClientException(HttpStatus httpStatus, ErrorMessage errorMessage) {
    super(
        "Response " + httpStatus.value() + StringPool.LEFT_SQ_BRACKET + httpStatus.getReasonPhrase()
            + StringPool.RIGHT_SQ_BRACKET + StringPool.SPACE + errorMessage.getMessage()
            + StringPool.CRLF
            + errorMessage.getDetailMsg());
    this.errorMessage = errorMessage;
  }

  public ClientException(HttpStatus httpStatus, String message) {
    super(
        "Response " + httpStatus.value() + StringPool.LEFT_SQ_BRACKET + httpStatus.getReasonPhrase()
            + StringPool.RIGHT_SQ_BRACKET + StringPool.SPACE + message);
  }

  public ClientException(String message) {
    super(message);
  }

  public ClientException(String message, Throwable cause) {
    super(message, cause);
  }

  public ClientException(Throwable cause) {
    super(cause);
  }

  public ClientException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ClientException(HttpStatus httpStatus) {
    super(
        "Response " + httpStatus.value() + StringPool.LEFT_SQ_BRACKET + httpStatus.getReasonPhrase()
            + StringPool.RIGHT_SQ_BRACKET);
  }
}
