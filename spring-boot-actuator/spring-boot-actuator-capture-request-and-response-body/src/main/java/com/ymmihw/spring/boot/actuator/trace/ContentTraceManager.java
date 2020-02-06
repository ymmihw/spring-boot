package com.ymmihw.spring.boot.actuator.trace;

import java.io.UnsupportedEncodingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import lombok.extern.slf4j.Slf4j;

@Component
@RequestScope
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
@Slf4j
public class ContentTraceManager {

  private ContentTrace trace;

  public void updateBody(ContentCachingRequestWrapper wrappedRequest,
      ContentCachingResponseWrapper wrappedResponse) {

    String requestBody = getRequestBody(wrappedRequest);
    getTrace().setRequestBody(requestBody);

    String responseBody = getResponseBody(wrappedResponse);
    getTrace().setResponseBody(responseBody);
  }

  protected String getRequestBody(ContentCachingRequestWrapper wrappedRequest) {
    try {
      if (wrappedRequest.getContentLength() <= 0) {
        return null;
      }
      return new String(wrappedRequest.getContentAsByteArray(), 0,
          wrappedRequest.getContentLength(), wrappedRequest.getCharacterEncoding());
    } catch (UnsupportedEncodingException e) {
      log.error("Could not read cached request body: " + e.getMessage());
      return null;
    }

  }

  protected String getResponseBody(ContentCachingResponseWrapper wrappedResponse) {

    try {
      if (wrappedResponse.getContentSize() <= 0) {
        return null;
      }
      return new String(wrappedResponse.getContentAsByteArray(), 0,
          wrappedResponse.getContentSize(), wrappedResponse.getCharacterEncoding());
    } catch (UnsupportedEncodingException e) {
      log.error("Could not read cached response body: " + e.getMessage());
      return null;
    }

  }

  public ContentTrace getTrace() {
    if (trace == null) {
      trace = new ContentTrace();
    }
    return trace;
  }
}
