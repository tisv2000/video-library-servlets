package com.github.tisv2000.videolibrary.servlet;

import com.github.tisv2000.videolibrary.dto.PersonCreatedDto;
import com.github.tisv2000.videolibrary.dto.PersonFilterDto;
import com.github.tisv2000.videolibrary.service.ImageService;
import com.github.tisv2000.videolibrary.service.PersonService;
import com.github.tisv2000.videolibrary.util.JspHelper;
import com.github.tisv2000.videolibrary.validator.CreatePersonValidator;
import com.github.tisv2000.videolibrary.validator.PersonFilterValidator;
import com.github.tisv2000.videolibrary.validator.ValidationResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static com.github.tisv2000.videolibrary.util.UrlPath.PERSONS;

@WebServlet(PERSONS)
public class PersonServlet extends HttpServlet {

    private static final PersonService personService = PersonService.getInstance();
    private static final CreatePersonValidator createPersonValidator = CreatePersonValidator.getInstance();
    private static final PersonFilterValidator personFilterValidator = PersonFilterValidator.getInstance();
    private static final ImageService imageService = ImageService.getInstance();

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
                .build();
        ValidationResult result = personFilterValidator.isValid(personFilterDto);

        if (!result.isValid()) {
            req.setAttribute("filterPersonErrors", result.getErrors());
            req.setAttribute("persons", personService.findAll());
        } else {
            req.setAttribute("persons", personService.findAllByFilter(personFilterDto));
        }

        req.getRequestDispatcher(JspHelper.getPath("persons")).forward(req, resp);
    }

    private void getPersonsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        redirectToPersonsWithAllAttributes(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var personCreatedDto = PersonCreatedDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .image(req.getPart("image").getSubmittedFileName())
                .build();
        ValidationResult result = createPersonValidator.isValid(personCreatedDto);

        if (!result.isValid()) {
            req.setAttribute("addPersonErrors", result.getErrors());
            redirectToPersonsWithAllAttributes(req, resp);
        } else {
            var personId = personService.createPerson(personCreatedDto);
            imageService.upload("/persons/" + personCreatedDto.getImage() + ".jpeg", req.getPart("image").getInputStream());
            resp.sendRedirect(PERSONS + "/" + personId);
        }
    }

    private void redirectToPersonsWithAllAttributes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("persons", personService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("persons")).forward(req, resp);
    }
}
