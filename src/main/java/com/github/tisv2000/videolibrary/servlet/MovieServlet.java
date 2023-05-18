package com.github.tisv2000.videolibrary.servlet;

import com.github.tisv2000.videolibrary.dto.MovieCreatedDto;
import com.github.tisv2000.videolibrary.dto.MovieReceivedDto;
import com.github.tisv2000.videolibrary.dto.MovieFilterDto;
import com.github.tisv2000.videolibrary.service.GenreService;
import com.github.tisv2000.videolibrary.service.ImageService;
import com.github.tisv2000.videolibrary.service.MovieService;
import com.github.tisv2000.videolibrary.util.JspHelper;
import com.github.tisv2000.videolibrary.validator.CreateMovieValidator;
import com.github.tisv2000.videolibrary.validator.MovieFilterValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

import static com.github.tisv2000.videolibrary.util.UrlPath.MOVIES;

@MultipartConfig(fileSizeThreshold = 1024*1024)
@WebServlet(MOVIES)
public class MovieServlet extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();
    private final GenreService genreService = GenreService.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final MovieFilterValidator movieFilterValidator = MovieFilterValidator.getInstance();
    private final CreateMovieValidator createMovieValidator = CreateMovieValidator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameterMap().isEmpty()) {
            filterMovie(req, resp);
        } else {
            getMovieList(req, resp);
        }
    }

    @SneakyThrows
    private void filterMovie(HttpServletRequest req, HttpServletResponse resp) {
        MovieFilterDto movieFilterDto = MovieFilterDto.builder()
                .title(req.getParameter("filterTitle"))
                .year(req.getParameter("filterYear"))
                .country(req.getParameter("filterCountry"))
                .genre(req.getParameter("filterGenre"))
                .build();
        var validationResult = movieFilterValidator.isValid(movieFilterDto);

        if (!validationResult.isValid()) {
            req.setAttribute("filterMovieErrors", validationResult.getErrors());
            redirectToAllFoundMovies(req, resp);
        } else {
            forwardToAllFilteredMovies(req, resp, movieFilterDto);
        }
    }

    private void getMovieList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectToAllFoundMovies(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var movieCreateDto = MovieCreatedDto.builder()
                .title(req.getParameter("title"))
                .year(req.getParameter("year"))
                .image(req.getPart("image").getSubmittedFileName())
                .country(req.getParameter("country"))
                .genre(req.getParameter("genre"))
                .description(req.getParameter("description"))
                .build();
        var validationResult = createMovieValidator.isValid(movieCreateDto);

        if (!validationResult.isValid()) {
            req.setAttribute("addMovieErrors", validationResult.getErrors());
            redirectToAllFoundMovies(req, resp);
        } else {
            var movieId = movieService.createMovie(movieCreateDto);
            imageService.upload("/movies/" + movieCreateDto.getImage() + ".jpeg", req.getPart("image").getInputStream());
            resp.sendRedirect(MOVIES + "/" + movieId);
        }

    }

    private void forwardToAllFilteredMovies(HttpServletRequest req, HttpServletResponse resp, MovieFilterDto movieFilterDto) throws ServletException, IOException {
        req.setAttribute("movies", movieService.findAllByFilters(movieFilterDto));
        req.setAttribute("genres", genreService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("movies")).forward(req, resp);
    }

    private void redirectToAllFoundMovies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieReceivedDto> movies = movieService.findAll();
        req.setAttribute("movies", movies);
        req.setAttribute("genres", genreService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("movies")).forward(req, resp);
    }
}
