package org.example.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
@WebFilter(urlPatterns = {"/createTeam", "/team", "/myTeams"})
*/
public class UserWebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Integer id = (Integer) request.getSession().getAttribute("id");
        System.out.println(id);
        if (id == null){
            System.out.println(2);
            /*((HttpServletResponse) servletResponse).sendRedirect("http://localhost:8080/Site/authorization");*/
            response.sendRedirect("http://localhost:8080/Site/authorization");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
