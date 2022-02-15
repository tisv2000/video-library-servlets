package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.PersonCreateDto;
import com.github.tisv2000.video_library.dto.PersonFilterDto;
import com.github.tisv2000.video_library.service.PersonService;
import com.github.tisv2000.video_library.util.JspHelper;
import com.github.tisv2000.video_library.validator.CreatePersonValidator;
import com.github.tisv2000.video_library.validator.ValidationResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/persons")
public class PersonServlet extends HttpServlet {

    private static PersonService personService = PersonService.getInstance();
    private static CreatePersonValidator createPersonValidator = CreatePersonValidator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getParameterMap().isEmpty()) {
            filterPersons(req, resp);
        } else {
            getPersonsList(req, resp);
        }
    }

    @SneakyThrows
    private void filterPersons(HttpServletRequest req, HttpServletResponse resp) {
        var personFilterDto = PersonFilterDto.builder()
                .name(req.getParameter("name").trim())
                .birthday(req.getParameter("birthday").trim())
                .build();
        ValidationResult result = createPersonValidator.isValid(personFilterDto);
        if (!result.isValid()) {
            req.setAttribute("errors", result.getErrors());
        } else {
            var persons = personService.findAllByFilter(personFilterDto);
            req.setAttribute("persons", persons);
            req.getRequestDispatcher(JspHelper.getPath("persons")).forward(req, resp);
        }
    }

    private void getPersonsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("persons", personService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("persons")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var personCreatedDto = PersonCreateDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .build();
        ValidationResult result = createPersonValidator.isValid(personCreatedDto);
        if (!result.isValid()) {
            req.setAttribute("errors", result.getErrors());
        } else {
            var personId = personService.createPerson(personCreatedDto);
            resp.sendRedirect("persons/" + personId);
        }
    }
}
