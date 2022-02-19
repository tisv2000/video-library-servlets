package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.ReviewCreateDto;
import com.github.tisv2000.video_library.dto.ReviewReceiveDto;
import com.github.tisv2000.video_library.dto.UserReceiveDto;
import com.github.tisv2000.video_library.service.MovieService;
import com.github.tisv2000.video_library.service.ReviewService;
import com.github.tisv2000.video_library.util.JspHelper;
import com.github.tisv2000.video_library.validator.ReviewValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.github.tisv2000.video_library.util.UrlPath.MOVIES;

@WebServlet(MOVIES + "/*")
public class MovieDetailsServlet extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();
    private ReviewValidator reviewValidator = ReviewValidator.getInstance();
    private ReviewService reviewService = ReviewService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserReceiveDto user = (UserReceiveDto) req.getSession().getAttribute("user");
        var movieId = req.getParameter("movieId");
        var reviewCreateDto = ReviewCreateDto.builder()
                .movieId(movieId)
                .userId(user.getId().toString())
                .text(req.getParameter("text"))
                .rate(req.getParameter("rate"))
                .build();
        var validationResult = reviewValidator.isValid(reviewCreateDto);

        if (!validationResult.isValid()) {
            req.setAttribute("errors", validationResult.getErrors());
        } else {
            reviewService.createReview(reviewCreateDto);
        }
//        req.getRequestDispatcher("/WEB-INF/jsp/movieDetails.jsp").forward(req, resp);
        resp.sendRedirect(MOVIES + "/" + movieId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var movieId = Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/")+1));
        var movie = movieService.findById(movieId);
        List<ReviewReceiveDto> reviews = reviewService.findAllForMovieWithId(movieId);

        req.setAttribute("reviews", reviews);
        req.setAttribute("movie", movie);
        req.getRequestDispatcher(JspHelper.getPath("/movieDetails")).forward(req, resp);
    }
}
