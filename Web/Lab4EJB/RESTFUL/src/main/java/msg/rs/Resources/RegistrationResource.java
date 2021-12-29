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

@Path("/registration")
public class RegistrationResource {

    @EJB
    private ControllerBean controller;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response registration(Map<String, String> data) {
        ArrayList<PointsEntity> result = new ArrayList<>();
        UsersEntity user = new UsersEntity();
        user.setLogin(data.get("login"));
        user.setPassword(Hashing.sha256().hashString(data.get("password"), StandardCharsets.UTF_8).toString());
        for (UsersEntity registered: controller.getUsers()) {
            if (registered.getLogin().equals(user.getLogin())) {
                return Response.serverError()
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                        .header("Access-Control-Allow-Credentials", "true")
                        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                        .header("Access-Control-Max-Age", "1209600")
                        .entity("User already exists")
                        .build();
            }
        }
        controller.addUser(user);
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(JSONParser.toJSON(result))
                .build();
    }
}
