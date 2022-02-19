package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.UserReceiveDto;
import com.github.tisv2000.video_library.service.ReviewService;
import com.github.tisv2000.video_library.service.UserService;
import com.github.tisv2000.video_library.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/reviews")
public class Reviews extends HttpServlet {

    private ReviewService reviewService = ReviewService.getInstance();
    private UserService userService = UserService.getInstance();

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
        req.getRequestDispatcher(JspHelper.getPath("/reviews")).forward(req, resp);
    }

    @SneakyThrows
    private void filterReviews(HttpServletRequest req, HttpServletResponse resp) {
        var email = req.getParameter("email");
        var user = userService.findByEmail(email);
        // проверка на ошибку тут?
        var reviews = reviewService.findAllWithUserId(user.get().getId());
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher(JspHelper.getPath("/reviews")).forward(req, resp);
    }


}
