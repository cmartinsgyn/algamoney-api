package com.algaworks.algamoney.api.cors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private String originPermitida = "http://localhost:8000";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse reponse = (HttpServletResponse) resp;

        reponse.setHeader("Access-Control-Allow-Origin", originPermitida);
        reponse.setHeader("Access-Control-Allow-Credentials", "true");

        if (("OPTIONS").equals(request.getMethod()) && originPermitida.equals(request.getHeader("Origin"))) {
            reponse.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
            reponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            reponse.setHeader("Access-Control-Max-Age", "3600");

            reponse.setStatus(HttpServletResponse.SC_OK);

        }else {
            chain.doFilter(req, resp);

        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
