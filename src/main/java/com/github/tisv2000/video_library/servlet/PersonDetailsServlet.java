package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.service.PersonService;
import com.github.tisv2000.video_library.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/persons/*")
public class PersonDetailsServlet extends HttpServlet {

    private static PersonService personService = PersonService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var personId = Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/")+1));
        var person = personService.findById(personId);

        req.setAttribute("person", person);
        req.getRequestDispatcher(JspHelper.getPath("personDetails")).forward(req, resp);
    }
}
