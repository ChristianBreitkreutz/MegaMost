package de.christianbreitkreutz;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class MegaMostClientBuilder {

    public Client newClient() {
        return ClientBuilder.newClient();
    }

}
