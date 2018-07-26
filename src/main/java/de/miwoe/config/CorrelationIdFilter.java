package de.miwoe.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by mwoelm on 18.09.17.
 */
@Component
public class CorrelationIdFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Nothing to init here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String correlationId = httpServletRequest.getHeader(HTTPHeaderConstants.CORRELATION_ID);
        String requestTraceInfo = httpServletRequest.getHeader(HTTPHeaderConstants.REQUEST_TRACE_INFO);
        log.info("Filter {}: {} executed. CorrelationId = {}, OriginService = {}",
                httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), correlationId, requestTraceInfo);
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();

        }
        if (requestTraceInfo == null) {
            requestTraceInfo = "";
        }
        requestTraceInfo = requestTraceInfo + " -> corrid-test:" + httpServletRequest.getMethod()+":"+ httpServletRequest.getRequestURI();

        InnerCallsConfig.correlationId.set(UUID.fromString(correlationId));
        InnerCallsConfig.requestTraceInfo.set(requestTraceInfo);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Nothing to destroy here
    }
}
