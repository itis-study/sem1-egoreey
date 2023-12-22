package org.example.filters;

import org.example.entity.Team;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
@WebFilter(urlPatterns = {"/team"})
*/
public class TeamWebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Integer team_id = (Integer) request.getSession().getAttribute("team_id");
        if (team_id.equals(null)){
            ((HttpServletResponse) servletResponse).sendRedirect("/Site/myTeams");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
