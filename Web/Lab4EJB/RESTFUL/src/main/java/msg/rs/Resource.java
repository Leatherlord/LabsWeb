package msg.rs;

import msg.ejb.ControllerBean;
import msg.ejb.PointsEntity;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Map;

@Path("/resource")
public class Resource {

    @EJB
    private ControllerBean controller;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response response(Map<String, String> map) {
        ArrayList<PointsEntity> result = new ArrayList<>();
        if (map.get("operation").equals("login")) {
            if (controller.isRegistered(map.get("login"), map.get("password"))) {
                PointsEntity tech = new PointsEntity();
                tech.setResult(true);
                result.add(tech);
                result.addAll(controller.getPoints());
            } else {
                PointsEntity tech = new PointsEntity();
                tech.setResult(false);
                result.add(tech);
            }
        } else if (map.get("operation").equals("data")) {
            double x = Double.parseDouble(map.get("X"));
            double y = Double.parseDouble(map.get("Y"));
            double r = Double.parseDouble(map.get("R"));
            PointsEntity point = new PointsEntity();
            point.setX(x);
            point.setY(y);
            point.setR(r);
            controller.addPoint(point);
            result.add(point);
        }
        return Response
                .ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(JSONParser.toJSON(result))
                .build();
    }


}
