package com.example.Second_Lab;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@MultipartConfig
@WebServlet(name = "areaCheckServlet", value = "/area-check-servlet")
public class AreaCheckServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Area Checker ready!";
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");

        String[] X = req.getParameterValues("X[]");
        String Y = req.getParameter("Y");
        String R = req.getParameter("R");

        PrintWriter out = resp.getWriter();

        LinkedList<Lane> collection = (LinkedList<Lane>) this.getServletContext().getAttribute("Collection");

        for (String i: X) {
                collection.add(
                        new Lane(
                                Double.parseDouble(i),
                                Double.parseDouble(Y),
                                Double.parseDouble(R),
                                isInArea(Double.parseDouble(i),
                                        Double.parseDouble(Y),
                                        Double.parseDouble(R)
                                ),
                                new Date()
                        )
                );
        }

//        out.println("<html><body>");
//        out.println("<table border='2'><tr><td>X</td><td>Y</td><td>R</td><td>Result</td></tr>");
        out.println("[");

        for (int i = collection.size() - X.length; i < collection.size(); i++) {
            if (i != collection.size()-1) {
                out.println(collection.get(i) + ",");
            } else {
                out.println(collection.get(i));
            }

        }

//        for (Lane i : collection) {
////            echo "<tr><td>".$stats[0]."</td><td>".$stats[1]."</td><td>".$stats[2]."</td><td>".$stats[3]."</td><td>".$stats[4].$stats[5]."</td></tr>\n";
////            out.println("<tr><td>" + i.getX() + "</td><td>" + i.getY() + "</td><td>" + i.getR() + "</td><td>" + i.isInArea() + "</td></tr>");
//            out.println(i + ",");
//        }
        out.println("]");

//        out.println("</body></html>");


    }

    private boolean isInArea(double x, double y, double r) {
        if (x < -r || y > r / 2 || (x > 0 && y > 0) || y < x - r/2 || ((x*x + y*y > (r/2)*(r/2)) && (x < 0 && y < 0) )) {
            return false;
        }
        return true;
    }


    public void destroy() {
    }
}
