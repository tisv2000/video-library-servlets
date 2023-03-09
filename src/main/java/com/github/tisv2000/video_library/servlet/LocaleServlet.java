package com.github.tisv2000.video_library.servlet;

import com.github.tisv2000.video_library.util.LocaleBundleUtils;
import com.github.tisv2000.video_library.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.github.tisv2000.video_library.util.UrlPath.LOCALE_URL;

@WebServlet(LOCALE_URL)
public class LocaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var userLocaleName = req.getParameter("lang");
        resp.addCookie(new Cookie("userLocaleName", userLocaleName));
        LocaleBundleUtils.setLocaleName(userLocaleName);

        var prevPage = req.getHeader("referer");
        var page = prevPage != null ? prevPage : UrlPath.LOGIN;
        resp.sendRedirect(page);

    }
}
