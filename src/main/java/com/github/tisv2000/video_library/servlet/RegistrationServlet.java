package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.dto.CreateUserDto;
import com.github.tisv2000.video_library.entity.Gender;
import com.github.tisv2000.video_library.entity.Role;
import com.github.tisv2000.video_library.service.ImageService;
import com.github.tisv2000.video_library.service.UserService;
import com.github.tisv2000.video_library.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024*1024)
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.setAttribute("genders", Gender.values());
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .image(req.getPart("image").getSubmittedFileName())
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .gender(req.getParameter("gender"))
                .build();
        userService.create(userDto);
        imageService.upload("/users/user" + userDto.getId() + ".jpeg", req.getPart("image").getInputStream());
        resp.sendRedirect("/login");
    }
}
