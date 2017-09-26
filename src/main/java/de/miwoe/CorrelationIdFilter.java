package de.miwoe;

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

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String correlationId = httpServletRequest.getHeader("correlationId");
        String serviceName = httpServletRequest.getHeader("serviceName");
        log.info("Filter " + httpServletRequest.getMethod()+":"+ httpServletRequest.getRequestURI()+" executed. CorrelationId = {}, OriginService = {}", correlationId, serviceName);
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();

        }
        if (serviceName == null) {
            serviceName = "";
        }
        serviceName = serviceName + " -> corrid-test:" + httpServletRequest.getMethod()+":"+ httpServletRequest.getRequestURI();

        InnerCallsConfig.correlationId.set(UUID.fromString(correlationId));
        InnerCallsConfig.service.set(serviceName);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
