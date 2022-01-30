package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.service.MovieService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/movies")
public class MovieServlet extends HttpServlet {

    private final MovieService movieService = MovieService.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>List of all movies</h1>");
            printWriter.write("<table>");
            printWriter.write("<tr>");
            printWriter.write("<th>Title</th>");
            printWriter.write("<th>Year</th>");
            printWriter.write("<th>Country</th>");
            printWriter.write("<th>Genre</th>");
            printWriter.write("</tr>");
            movieService.findAll().forEach(movieDto -> {
                printWriter.write("""
                                <tr>
                                    <td>
                                        <a href="/movies?movieId=%d">%s</a>
                                    </td>
                                    <td>%s</td>
                                    <td>%s</td>
                                    <td>%s</td>
                                </tr>
                                """.formatted(
                                movieDto.getId(),
                                movieDto.getTitle(),
                                movieDto.getYear(),
                                movieDto.getCountry(),
                                movieDto.getGenre()
                        )
                );
            });
            printWriter.write("</table>");
        }
    }
}
