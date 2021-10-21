package com.example.Second_Lab;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@MultipartConfig
@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Controller ready!";
        this.getServletContext().setAttribute("Collection", new ArrayList<Lane>());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String onload = request.getParameter("ONLOAD");
        if (!onload.equals("true")) {
            response.setContentType("text/html");

            String[] X = request.getParameterValues("X[]");
            String Y = request.getParameter("Y");
            String R = request.getParameter("R");

            if (X == null) {
                request.setAttribute("message", "No X parameter set!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error-servlet");
                requestDispatcher.forward(request, response);
                return;
            }

            for (String i : X) {
                try {
                    Double.parseDouble(i);
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Not a number in X!");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error-servlet");
                    requestDispatcher.forward(request, response);
                    return;
                }
            }

            try {
                Double.parseDouble(Y);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Not a number in Y!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error-servlet");
                requestDispatcher.forward(request, response);
                return;
            }

            try {
                Double.parseDouble(R);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Not a number in R!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error-servlet");
                requestDispatcher.forward(request, response);
                return;
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/area-check-servlet");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/onload-servlet");
            requestDispatcher.forward(request, response);
        }
    }


    public void destroy() {
    }
}