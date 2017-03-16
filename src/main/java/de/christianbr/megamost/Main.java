package de.christianbr.megamost;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Main {

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://pm.epages.com/rs/shops/ulf");
        Response response = target.request().get();
        System.out.println("Status: " + response.getStatus());
        String content = response.readEntity(String.class);
        System.out.println(content);
    }

}
