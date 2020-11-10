package io.github.natsusai.httpclient.demo;

import io.github.natsusai.httpclient.demo.exception.ClientException;
import io.github.natsusai.httpclient.demo.request.NotVoidRequest;
import io.github.natsusai.httpclient.demo.request.Request;
import io.github.natsusai.httpclient.demo.response.AbstractResponse;

/**
 * Client
 *
 * @author Kurenai
 * @since 2020-11-10 15:38
 */

public interface Client {

  <T extends AbstractResponse> T execute(NotVoidRequest<T> request) throws ClientException;

  void execute(Request request) throws ClientException;


}
