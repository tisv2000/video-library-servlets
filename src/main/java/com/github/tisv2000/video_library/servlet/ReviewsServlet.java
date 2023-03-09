package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.service.ReviewService;
import com.github.tisv2000.video_library.service.UserService;
import com.github.tisv2000.video_library.util.JspHelper;
import com.github.tisv2000.video_library.validator.Error;
import com.github.tisv2000.video_library.validator.ReviewFilterValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

@WebServlet("/reviews")
public class ReviewsServlet extends HttpServlet {

    private final ReviewService reviewService = ReviewService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final ReviewFilterValidator reviewFilterValidator = ReviewFilterValidator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameterMap().isEmpty()) {
            filterReviews(req, resp);
        } else {
            showFilterForm(req, resp);
        }
    }

    @SneakyThrows
    private void showFilterForm(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher(JspHelper.getPath("reviews")).forward(req, resp);
    }

    @SneakyThrows
    private void filterReviews(HttpServletRequest req, HttpServletResponse resp) {
        var email = req.getParameter("email");
        var validationResult = reviewFilterValidator.isValid(email);
        if (!validationResult.isValid()) {
            req.setAttribute("errors", validationResult.getErrors());
        } else {
            var user = userService.findByEmail(email);
            if (user.isEmpty()) {
                req.setAttribute("errors", List.of(Error.of("error.user.notfound")));
            } else {
                var reviews = reviewService.findAllWithUserId(user.get().getId());
                req.setAttribute("reviews", reviews);
            }
        }
        req.getRequestDispatcher(JspHelper.getPath("reviews")).forward(req, resp);
    }
}
