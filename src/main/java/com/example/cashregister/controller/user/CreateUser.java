package com.example.cashregister.controller.user;


import com.example.cashregister.security.UserSession;
import com.example.cashregister.dao.UserDao;
import com.example.cashregister.dao.impl.UserDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.cashregister.security.PasswordEncryptionService.generateSalt;
import static com.example.cashregister.security.PasswordEncryptionService.getEncryptedPassword;
import static com.example.cashregister.security.UserSession.getLoginedUser;

/**
 * Create user servlet
 */
@WebServlet(name = "createUser", value = "/create/user")
public class CreateUser extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateUser.class);
    private final UserDao userDao;

    public CreateUser() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");
        if (getLoginedUser(req.getSession()).getId() != 0) {
            log.info("user has already logined");
            req.getSession().setAttribute("errormessage", "You are already had an account");
            resp.sendRedirect("/");
        } else {
            log.warn("user is not logined");
            getServletContext().getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("doPost");
        byte[] salt = generateSalt();
        byte[] password = getEncryptedPassword(req.getParameter("password"), salt);
        int id = userDao.createUser(
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                password, salt,
                Integer.parseInt(req.getParameter("roleid")));
        if (id != 0) {
            log.info("User was created with id: " + id);
            req.getSession().setAttribute("message", "User was created");

        } else {
            log.warn("user wasn't created");
            req.getSession().setAttribute("errormessage", "User was not created");
        }
        resp.sendRedirect("/login");
    }
}
