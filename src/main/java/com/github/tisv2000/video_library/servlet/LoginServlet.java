package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.UserReceiveDto;
import com.github.tisv2000.video_library.service.UserService;
import com.github.tisv2000.video_library.util.JspHelper;
import com.github.tisv2000.video_library.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static com.github.tisv2000.video_library.util.UrlPath.LOGIN;
import static com.github.tisv2000.video_library.util.UrlPath.MOVIES;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(LOGIN)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("email"), req.getParameter("password"))
                .ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp),
                        () -> onLoginFailure(req, resp)
                );
    }

    @SneakyThrows
    private void onLoginFailure(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(LOGIN + "?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(UserReceiveDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(MOVIES);
    }


}
