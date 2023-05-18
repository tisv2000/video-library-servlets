package com.github.tisv2000.videolibrary.servlet;

import com.github.tisv2000.videolibrary.dto.LoginDto;
import com.github.tisv2000.videolibrary.dto.UserReceivedDto;
import com.github.tisv2000.videolibrary.service.UserService;
import com.github.tisv2000.videolibrary.util.JspHelper;
import com.github.tisv2000.videolibrary.util.LocaleBundleUtils;
import com.github.tisv2000.videolibrary.validator.Error;
import com.github.tisv2000.videolibrary.validator.LoginValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.github.tisv2000.videolibrary.util.UrlPath.LOGIN;
import static com.github.tisv2000.videolibrary.util.UrlPath.MOVIES;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final LoginValidator loginValidator = LoginValidator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = JspHelper.getPath("login");
        LocaleBundleUtils.setLocaleName(Arrays.stream(req.getCookies())
                .filter((cookie) -> cookie.getName().equals("userLocaleName"))
                .map(cookie -> cookie.getValue())
                .findFirst()
                .orElse(LocaleBundleUtils.DEFAULT_LOCALE));
        req.getRequestDispatcher(login).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var loginDto = LoginDto.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        var validationResult = loginValidator.isValid(loginDto);
        if (!validationResult.isValid()) {
            req.setAttribute("errors", validationResult.getErrors());
            req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
        } else {
            userService.login(loginDto)
                    .ifPresentOrElse(
                            user -> onLoginSuccess(user, req, resp),
                            () -> onLoginFailure(req, resp)
                    );
        }
    }

    @SneakyThrows
    private void onLoginFailure(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("errors", List.of(Error.of( "error.login.unsuccessful")));
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @SneakyThrows
    private void onLoginSuccess(UserReceivedDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(MOVIES);
    }
}
