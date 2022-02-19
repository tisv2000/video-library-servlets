package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.PersonCreateDto;
import com.github.tisv2000.video_library.dto.PersonDto;
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
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;

import static com.github.tisv2000.video_library.util.UrlPath.PERSONS;

@WebServlet(PERSONS)
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
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .build();
        ValidationResult result = createPersonValidator.isValid(personFilterDto);
        List<PersonDto> persons = null;
        if (!result.isValid()) {
            req.setAttribute("errors", result.getErrors());
        } else {
            persons = personService.findAllByFilter(personFilterDto);
        }
        // TODO проверить с папой
        if (persons == null) {
            req.setAttribute("persons", personService.findAll());
        } else {
            req.setAttribute("persons", persons);
        }
        req.getRequestDispatcher(JspHelper.getPath("/persons")).forward(req, resp);
    }

    private void getPersonsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("persons", personService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("/persons")).forward(req, resp);
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
            req.setAttribute("persons", personService.findAll());
            req.getRequestDispatcher(JspHelper.getPath("/persons")).forward(req, resp);
        } else {
            var personId = personService.createPerson(personCreatedDto);
            resp.sendRedirect(PERSONS + "/" + personId);
        }
    }
}
