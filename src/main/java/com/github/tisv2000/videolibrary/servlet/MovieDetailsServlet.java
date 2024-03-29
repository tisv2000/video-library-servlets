package com.github.tisv2000.videolibrary.servlet;

import com.github.tisv2000.videolibrary.dto.MoviePersonCreatedDto;
import com.github.tisv2000.videolibrary.dto.MovieReceivedDto;
import com.github.tisv2000.videolibrary.dto.ReviewCreatedDto;
import com.github.tisv2000.videolibrary.dto.UserReceivedDto;
import com.github.tisv2000.videolibrary.service.*;
import com.github.tisv2000.videolibrary.util.JspHelper;
import com.github.tisv2000.videolibrary.validator.CreateMoviePersonValidator;
import com.github.tisv2000.videolibrary.validator.ReviewValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;

import static com.github.tisv2000.videolibrary.util.UrlPath.MOVIES;

@WebServlet(MOVIES + "/*")
public class MovieDetailsServlet extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();
    private final ReviewService reviewService = ReviewService.getInstance();
    private final PersonService personService = PersonService.getInstance();
    private final ReviewValidator reviewValidator = ReviewValidator.getInstance();
    private final PersonRoleService personRoleService = PersonRoleService.getInstance();
    private final MoviePersonService moviePersonService = MoviePersonService.getInstance();
    private final CreateMoviePersonValidator createMoviePersonValidator = CreateMoviePersonValidator.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("addMoviePersonMode") != null) {
            addMoviePerson(req, resp);
        } else {
            addReview(req, resp);
        }
    }

    @SneakyThrows
    private void addMoviePerson(HttpServletRequest req, HttpServletResponse resp) {
        var movieId = req.getParameter("movieId");
        var moviePersonDto = MoviePersonCreatedDto.builder()
                .movieId(movieId)
                .personId(req.getParameter("person"))
                .roleId(req.getParameter("role"))
                .build();
        var validationResult = createMoviePersonValidator.isValid(moviePersonDto);

        if (!validationResult.isValid()) {
            req.setAttribute("addMoviePersonErrors", validationResult.getErrors());
        } else {
            moviePersonService.addMoviePerson(moviePersonDto);
        }

        var movie = movieService.findById(Integer.parseInt(movieId)).orElse(null);
        checkAndRedirectToMovieDetails(movie, resp, req);
    }

    @SneakyThrows
    private void addReview(HttpServletRequest req, HttpServletResponse resp) {
        UserReceivedDto user = (UserReceivedDto) req.getSession().getAttribute("user");
        var movieId = req.getParameter("movieId");
        var reviewCreateDto = ReviewCreatedDto.builder()
                .movieId(movieId)
                .userId(user.getId().toString())
                .text(req.getParameter("text"))
                .rate(req.getParameter("rate"))
                .build();
        var validationResult = reviewValidator.isValid(reviewCreateDto);

        if (!validationResult.isValid()) {
            req.setAttribute("addReviewErrors", validationResult.getErrors());
        } else {
            reviewService.createReview(reviewCreateDto);
        }

        var movie = movieService.findById(Integer.parseInt(movieId)).orElse(null);
        checkAndRedirectToMovieDetails(movie, resp, req);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var movieId = Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1));
        var movie = movieService.findById(movieId).orElse(null);
        checkAndRedirectToMovieDetails(movie, resp, req);
    }


    private void checkAndRedirectToMovieDetails(MovieReceivedDto movie, HttpServletResponse resp, HttpServletRequest req) throws IOException {
        if (movie == null) {
            resp.sendError(404);
        } else {
            redirectToMovieDetailsWithAllAttributes(req, resp, movie);
        }
    }

    @SneakyThrows
    private void redirectToMovieDetailsWithAllAttributes(HttpServletRequest req, HttpServletResponse resp, MovieReceivedDto movie) {
        var reviews = reviewService.findAllWithMovieId(movie.getId());
        var moviePersons = moviePersonService.findAllMoviePersonsByMovieId(movie.getId());
        var persons = personService.findAll();
        var roles = personRoleService.findAll();

        req.setAttribute("reviews", reviews);
        req.setAttribute("movie", movie);
        req.setAttribute("moviePersons", moviePersons);
        req.setAttribute("persons", persons);
        req.setAttribute("roles", roles);

        req.getRequestDispatcher(JspHelper.getPath("movieDetails")).forward(req, resp);
    }
}
