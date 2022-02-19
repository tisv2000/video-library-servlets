package com.github.tisv2000.video_library.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.github.tisv2000.video_library.util.UrlPath.LOGIN;
import static com.github.tisv2000.video_library.util.UrlPath.LOGOUT;

@WebServlet(LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(LOGIN); // возвращается 302, браузер делает второй запрос. эта url передается в 302 как заголовок
//        req.getRequestDispatcher(JspHelper.getPath("/login")).forward(req, resp); - будет /logout в строке запроса в браузере
    }
}
