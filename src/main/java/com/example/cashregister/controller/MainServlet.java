package com.example.cashregister.controller;

import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.cashregister.Service.extra.Notifications.setMessage;

/**
 * Welcome  servlet
 */
@WebServlet(name = "mainServlet", value = "/cashregister/")
public class MainServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}