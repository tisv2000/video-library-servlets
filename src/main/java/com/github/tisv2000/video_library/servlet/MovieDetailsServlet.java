package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/movies/*")
public class MovieDetailsServlet extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var movieId = Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/")+1));
        var movie = movieService.findById(movieId);

        req.setAttribute("movie", movie);
        req.getRequestDispatcher("/WEB-INF/jsp/movieDetails.jsp").forward(req, resp);

    }
}
