package de.christianbreitkreutz;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class Megatech {
	private final UriBuilder endpointUri;
	private final String userName;
	private final String iconUrl;

	private Megatech(final Builder builder) {
		this.userName = builder.userName;
		this.iconUrl = builder.iconUrl;
		endpointUri = UriBuilder//
				.fromPath(builder.host) //
				.scheme(builder.scheme)//
				.port(builder.port)//
				.path("hooks/"+builder.mattermostKey);//
	}

	public void sendMessage(final String message) throws MegaMostExeption {

		Response response = buildRequest().post(payload(message));

		if (response == null || response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
			throw new MegaMostExeption(message //
					, response.getStatus()//
					, response.getHeaders().toString() //
					, response.readEntity(String.class)//
			);
		}
		System.out.println(response.readEntity(String.class));
	}

	private javax.ws.rs.client.Invocation.Builder buildRequest() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(endpointUri);
		javax.ws.rs.client.Invocation.Builder builder = webTarget
				.request(MediaType.APPLICATION_JSON + "; charset=utf-8")//
				.acceptEncoding("utf-8");
		return builder;
	}

	private Entity<String> payload(final String message) {
		//TODO: add Icon and username only if defined
		return Entity.json("{text: " + message + ",icon: "+ iconUrl +", user:"+ userName + "}");
	}

	public static class Builder {
		private final String host;
		private final String mattermostKey;
		private String scheme = UriScheme.HTTPS;
		private int port = 443;
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

		public Megatech build() {
			return new Megatech(this);
		}
	}
}
