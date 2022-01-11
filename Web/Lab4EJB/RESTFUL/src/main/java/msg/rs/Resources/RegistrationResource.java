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
        if (controller.isRegistered(user.getLogin(), user.getPassword())) {
            return Response.status(403)
                    .entity("User already exists")
                    .build();
        }
        try {
            controller.addUser(user);
            return Response.ok()
                    .entity(JSONParser.toJSON(result))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("Could not add a user")
                    .build();
        }

    }
}
