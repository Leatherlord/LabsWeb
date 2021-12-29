package msg.rs;

import msg.rs.Resources.PointsResource;
import msg.rs.Resources.AuthorizationResource;
import msg.rs.Resources.RegistrationResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class APIApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(PointsResource.class);
        set.add(RegistrationResource.class);
        set.add(AuthorizationResource.class);
        return set;
    }
}