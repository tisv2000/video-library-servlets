package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.UserReceiveDto;
import com.github.tisv2000.video_library.service.ReviewService;
import com.github.tisv2000.video_library.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/myReviews")
public class MyReviewsServlet extends HttpServlet {

    private ReviewService reviewService = ReviewService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserReceiveDto) req.getSession().getAttribute("user");
        var reviews = reviewService.findAllWithUserId(user.getId());
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher(JspHelper.getPath("/myReviews")).forward(req, resp);
    }
}
