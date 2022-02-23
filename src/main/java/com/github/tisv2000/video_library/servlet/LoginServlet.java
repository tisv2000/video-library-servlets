package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.LoginDto;
import com.github.tisv2000.video_library.dto.UserReceivedDto;
import com.github.tisv2000.video_library.service.UserService;
import com.github.tisv2000.video_library.util.JspHelper;
import com.github.tisv2000.video_library.validator.Error;
import com.github.tisv2000.video_library.validator.LoginValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

import static com.github.tisv2000.video_library.util.UrlPath.LOGIN;
import static com.github.tisv2000.video_library.util.UrlPath.MOVIES;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final LoginValidator loginValidator = LoginValidator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(LOGIN)).forward(req, resp);
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
            req.getRequestDispatcher(JspHelper.getPath(LOGIN)).forward(req, resp);
        } else {
            userService.login(loginDto)
                    .ifPresentOrElse(
                            user -> onLoginSuccess(user, req, resp),
                            () -> onLoginFailure(req, resp)
                    );
        }
    }

    // TODO зачем этот ?error&email
    // TODO почему не отображается ошибка??
    @SneakyThrows
    private void onLoginFailure(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("errors", List.of(Error.of("login.unsuccessful", "Login information is not correct")));
        resp.sendRedirect(LOGIN + "?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void onLoginSuccess(UserReceivedDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(MOVIES);
    }


}
