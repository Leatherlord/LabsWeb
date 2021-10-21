package com.example.Second_Lab;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@MultipartConfig
@WebServlet(name = "onloadServlet", value = "/onload-servlet")
public class OnloadServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Onload ready!";
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();
        ArrayList<Lane> collection = (ArrayList<Lane>) this.getServletContext().getAttribute("Collection");
        out.println(JSONParser.toJSON(collection));

    }

    public void destroy() {
    }
}