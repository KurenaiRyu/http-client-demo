package io.github.natsusai.httpclient.demo.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.natsusai.httpclient.demo.request.core.SkuListRequest;
import org.junit.Test;

public class NotVoidRequestTest {

  private ObjectMapper mapper = new ObjectMapper();

  @Test
  public void test() throws JsonProcessingException {
    String out = mapper.writeValueAsString(new SkuListRequest());
    System.out.println(out);
  }
}