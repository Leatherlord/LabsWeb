package com.example.Second_Lab;

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

        ArrayList<Lane> collection = (ArrayList<Lane>) this.getServletContext().getAttribute("Collection");
        ArrayList<Lane> list = new ArrayList<>();
        for (String i : X) {
            Lane lane = new Lane(
                    Double.parseDouble(i),
                    Double.parseDouble(Y),
                    Double.parseDouble(R),
                    isInArea(Double.parseDouble(i),
                            Double.parseDouble(Y),
                            Double.parseDouble(R)
                    ),
                    new Date(),
                    false,
                    ""
            );

            collection.add(lane);
            list.add(lane);
        }

        out.println(JSONParser.toJSON(list));

    }

    private boolean isInArea(double x, double y, double r) {
        return !(x < -r) && !(y > r / 2) && (!(x > 0) || !(y > 0)) && !(y < x - r / 2) && ((!(x * x + y * y > (r / 2) * (r / 2))) || (!(x < 0) || !(y < 0)));
    }


    public void destroy() {
    }
}