package de.christianbreitkreutz;

import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.json.simple.JSONObject;

public class MegaMost {
    private final UriBuilder endpointUri;
    private final String userName;
    private final String iconUrl;

    public MegaMost(final Builder builder) {
        this.userName = builder.userName;
        this.iconUrl = builder.iconUrl;

        endpointUri = UriBuilder.fromUri("{scheme}://{host}:{port}/hooks/{mattermostKey}")
                .resolveTemplate("scheme", builder.scheme)//
                .resolveTemplate("host", builder.host)//
                .resolveTemplate("port", builder.port)//
                .resolveTemplate("mattermostKey", builder.mattermostKey//
        );
    }

    public void sendMessage(final String message) throws MegaMostExeption {

        Response response = buildRequest().post(payload(message));

        if (response == null || response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            throw new MegaMostExeption(message //
                    ,response.getStatus()//
                    ,response.getHeaders().toString()//
                    ,response.readEntity(String.class)//
            );
        }
    }

    private javax.ws.rs.client.Invocation.Builder buildRequest() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(endpointUri);
        javax.ws.rs.client.Invocation.Builder builder = webTarget
                .request(MediaType.APPLICATION_JSON + "; charset=utf-8")//
                .acceptEncoding("utf-8");
        return builder;
    }

    private Entity<Form> payload(final String message) {
        MultivaluedMap<String, String> formMap = new MultivaluedStringMap();
        formMap.add("payload", createPayload(message));
        return Entity.form(formMap);
    }

    @SuppressWarnings("unchecked")
    private String createPayload(final String message) {
        HashMap<String, String> jsonMap = new HashMap<>();
        jsonMap.put("text", message);

        if (iconUrl != null) {
            jsonMap.put("icon_url", iconUrl);
        }
        if (userName != null) {
            jsonMap.put("username", userName);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(jsonMap);
        return jsonObject.toJSONString();
    }

    public static class Builder {
        private final String host;
        private final String mattermostKey;
        private String scheme = UriScheme.HTTPS;
        private int port = UriPort.HTTPS;
        private String iconUrl;
        private String userName;

        public Builder(final String host, final String mattermostKey) {
            this.host = host;
            this.mattermostKey = mattermostKey;
        }

        public Builder scheme(final String scheme) {
            this.scheme = scheme;
            return this;
        }

        public Builder port(final int port) {
            this.port = port;
            return this;
        }

        public Builder icon(final String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }

        public Builder useName(final String userName) {
            this.userName = userName;
            return this;
        }

        public MegaMost build() {
            return new MegaMost(this);
        }
    }
}
