package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.MovieCreateDto;
import com.github.tisv2000.video_library.dto.MovieReceiveDto;
import com.github.tisv2000.video_library.dto.MovieFilterDto;
import com.github.tisv2000.video_library.exception.ValidationException;
import com.github.tisv2000.video_library.service.GenreService;
import com.github.tisv2000.video_library.service.ImageService;
import com.github.tisv2000.video_library.service.MovieService;
import com.github.tisv2000.video_library.util.JspHelper;
import com.github.tisv2000.video_library.validator.CreateMovieFilterValidator;
import com.github.tisv2000.video_library.validator.MovieFilterValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

import static com.github.tisv2000.video_library.util.UrlPath.MOVIES;

@MultipartConfig(fileSizeThreshold = 1024*1024)
@WebServlet(MOVIES)
public class MovieServlet extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();
    private final GenreService genreService = GenreService.getInstance();
    private final MovieFilterValidator movieFilterValidator = MovieFilterValidator.getInstance();
    private final CreateMovieFilterValidator createMovieFilterValidator = CreateMovieFilterValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();

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
                .title(req.getParameter("title"))
                .year(req.getParameter("year"))
                .country(req.getParameter("country"))
                .genre(req.getParameter("genre"))
                .build();
        var validationResult = movieFilterValidator.isValid(movieFilterDto);
        if (!validationResult.isValid()) {
            // не нужно бросать тут исключение?
            throw new ValidationException(validationResult.getErrors());
        }

        List<MovieReceiveDto> movies = movieService.findAllByFilters(movieFilterDto);

        req.setAttribute("movies", movies);
        req.setAttribute("genres", genreService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("movies")).forward(req, resp);
    }

    private void getMovieList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieReceiveDto> movies = movieService.findAll();
        req.setAttribute("movies", movies);
        req.setAttribute("genres", genreService.findAll());
        req.getRequestDispatcher(JspHelper.getPath(MOVIES)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // разница между getAttribute и getParams
        var movieCreateDto = MovieCreateDto.builder()
                .title(req.getParameter("title"))
                .year(req.getParameter("year"))
                .image(req.getPart("image").getSubmittedFileName())
                .country(req.getParameter("country"))
                .genre(req.getParameter("genre"))
                .description(req.getParameter("description"))
                .build();
        var validationResult = createMovieFilterValidator.isValid(movieCreateDto);
        if (!validationResult.isValid()) {
            req.setAttribute("errors", validationResult.getErrors());
        }
        var movieId = movieService.createMovie(movieCreateDto);

        // TODO improve logic to save pictures - make unique names, so that they are not get overwritten
        imageService.upload("/movies/" + movieCreateDto.getImage() + ".jpeg", req.getPart("image").getInputStream());

        resp.sendRedirect(MOVIES + "/" + movieId);
    }
}
