package com.example.Second_Lab;

import java.io.*;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@MultipartConfig
@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Controller ready!";
        this.getServletContext().setAttribute("Collection", new LinkedList<Lane>());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String onload = request.getParameter("ONLOAD");
        if (!onload.equals("true")) {
            response.setContentType("text/html");

            String[] X = request.getParameterValues("X[]");
            String Y = request.getParameter("Y");
            String R = request.getParameter("R");


            PrintWriter out = response.getWriter();

//            out.println("<html><body>");


            if (X == null) {
                message = "No X parameter set";
                out.println(message);
//                out.println("</body></html>");
                return;
            }

            for (String i : X) {
                try {
                    Double.parseDouble(i);
                } catch (NumberFormatException e) {
                    message = "Not a number in X!";
                    out.println(message);
//                    out.println("</body></html>");
                    return;
                }
            }

            try {
                Double.parseDouble(Y);
            } catch (NumberFormatException e) {
                message = "Not a number in Y!";
                out.println(message);
//                out.println("</body></html>");
                return;
            }

            try {
                Double.parseDouble(R);
            } catch (NumberFormatException e) {
                message = "Not a number in R!";
                out.println(message);
//                out.println("</body></html>");
                return;
            }

            message = "Controller ready!";
//            out.println(message);
//            out.println("</body></html>");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/area-check-servlet");
            requestDispatcher.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            LinkedList<Lane> collection = (LinkedList<Lane>) this.getServletContext().getAttribute("Collection");

            if (collection.size()!=0) {
                out.println("[");

                for (int i = 0; i < collection.size(); i++) {
                    if (i != collection.size() - 1) {
                        out.println(collection.get(i) + ",");
                    } else {
                        out.println(collection.get(i));
                    }

                }
                out.println("]");
            } else {
                out.println("[");
                out.println("   {\n" +
                        "       \"result\": \"none\"\n" +
                        "}\n");
                out.println("]");
            }
        }
    }



    public void destroy() {
    }
}
