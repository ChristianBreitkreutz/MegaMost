package de.christianbreitkreutz;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class MegaMost {
    // http://{your-mattermost-site}/hooks/xxx-generatedkey-xxx
    private static UriBuilder endpointUri;
    private static MegaMost target;

    private MegaMost(final UriBuilder url) {
        endpointUri = url;
    }

    public static MegaMost create(final String host, final String mattermostKey) {
        if (target == null) {
            UriBuilder uirBuider = UriBuilder.fromPath(host).path(mattermostKey);
            target = new MegaMost(uirBuider);
        }
        return target;
    }

    public void sendMessage(final String message) throws MegaMostExeption {

        Response response = buildRequest().post(payload(message));

        //TODO: log request
        if (response == null || response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new MegaMostExeption(message //
                    , response.getStatus()//
                    , response.getHeaders().toString() //
                    , response.readEntity(String.class)//
            );
        }
        System.out.println(response.readEntity(String.class));
    }

    private Builder buildRequest() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(endpointUri);
        Builder builder = webTarget.request(MediaType.APPLICATION_JSON + "; charset=utf-8")//
                .acceptEncoding("utf-8");
        return builder;
    }

    private Entity<String> payload(final String message) {
        return Entity.json("{text: " + message + "}");
    }
}
