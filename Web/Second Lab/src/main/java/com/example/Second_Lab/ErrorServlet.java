package com.example.Second_Lab;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

@MultipartConfig
@WebServlet(name = "errorServlet", value = "/error-servlet")
public class ErrorServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Error servlet ready!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        message = (String) request.getAttribute("message");
        PrintWriter out = response.getWriter();
        ArrayList<Lane> error = new ArrayList<>();
        Lane errorLane = new Lane(0, 0, 0, false, new Date(), true, message);
        error.add(errorLane);
        out.println(JSONParser.toJSON(error));
    }


    public void destroy() {
    }
}