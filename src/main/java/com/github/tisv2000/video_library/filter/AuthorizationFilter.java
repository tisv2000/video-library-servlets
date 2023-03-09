package com.github.tisv2000.video_library.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.github.tisv2000.video_library.util.UrlPath.LOCALE_URL;
import static com.github.tisv2000.video_library.util.UrlPath.LOGIN;
import static com.github.tisv2000.video_library.util.UrlPath.MOVIES;
import static com.github.tisv2000.video_library.util.UrlPath.REGISTRATION;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, LOCALE_URL);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var uri = req.getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(req)) {
            if (uri.equals("/")) {
                ((HttpServletResponse) response).sendRedirect(MOVIES);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            var prevPage = req.getHeader("referer");
            ((HttpServletResponse) response).sendRedirect(prevPage != null ? prevPage : LOGIN);
        }
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isUserLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }
}
