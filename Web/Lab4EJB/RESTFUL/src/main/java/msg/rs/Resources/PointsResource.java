package msg.rs.Resources;

import com.google.common.hash.Hashing;
import msg.ejb.ControllerBean;
import msg.ejb.PointsEntity;
import msg.ejb.UsersEntity;
import msg.rs.JSONParser;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@Path("/points")
public class PointsResource {

    @EJB
    private ControllerBean controller;

    @POST
    @Path("/add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addPoint(Map<String, String> data) {
        if (controller.isRegistered(data.get("login"), Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString())) {
            ArrayList<PointsEntity> result = new ArrayList<>();
            double x = Double.parseDouble(data.get("X"));
            double y = Double.parseDouble(data.get("Y"));
            double r = Double.parseDouble(data.get("R"));
            PointsEntity point = new PointsEntity();
            point.setX(x);
            point.setY(y);
            point.setR(r);
            controller.addPoint(point, data.get("login"));
            result.add(point);
            return Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(JSONParser.toJSON(result))
                    .build();
        } else {
            return Response.serverError()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity("User is not confirmed")
                    .build();
        }
    }

    @POST
    @Path("/get")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response getAllPoints(Map<String, String> data) {
        if (controller.isRegistered(data.get("login"), Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString())) {
            return Response.ok()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(JSONParser.toJSON(controller.getPoints(data.get("login"))))
                    .build();
        } else {
            return Response.serverError()
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity("User is not confirmed")
                    .build();
        }
    }
}
