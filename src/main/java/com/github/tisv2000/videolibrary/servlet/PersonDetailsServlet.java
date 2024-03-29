package com.github.tisv2000.videolibrary.servlet;

import com.github.tisv2000.videolibrary.service.GenreService;
import com.github.tisv2000.videolibrary.service.MovieService;
import com.github.tisv2000.videolibrary.service.PersonService;
import com.github.tisv2000.videolibrary.util.JspHelper;
import com.github.tisv2000.videolibrary.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.PERSONS + "/*")
public class PersonDetailsServlet extends HttpServlet {

    private static final PersonService personService = PersonService.getInstance();
    private static final MovieService movieService = MovieService.getInstance();
    private static final GenreService genreService = GenreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var personId = Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1));
        var maybePerson = personService.findById(personId);

        if (maybePerson.isEmpty()) {
            resp.sendError(404);
        } else {
            req.setAttribute("person", maybePerson.get());
            req.setAttribute("genres", genreService.findAll());
            req.setAttribute("movies", movieService.findByMoviePersonId(personId));
            req.getRequestDispatcher(JspHelper.getPath("personDetails")).forward(req, resp);
        }
    }
}
