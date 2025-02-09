package com.rouvsen.examples.slackalertmanager.http.filter;

import com.rouvsen.examples.slackalertmanager.http.StatusCaptureResponseWrapper;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class HttpErrorMetricFilter implements Filter {

    private final Counter httpErrorCounter;

    public HttpErrorMetricFilter(MeterRegistry meterRegistry) {
        this.httpErrorCounter = meterRegistry.counter("custom.http.errors", "type", "server");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        StatusCaptureResponseWrapper responseWrapper = new StatusCaptureResponseWrapper(httpServletResponse);
        chain.doFilter(request, responseWrapper);
        if (responseWrapper.getStatus() >= 500) {
            httpErrorCounter.increment();
        }
    }
}

