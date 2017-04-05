package de.christianbreitkreutz;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(MockitoJUnitRunner.class)
public class MegaMostTest {

    final static int WIREMOCK_PORT = 8089;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WIREMOCK_PORT);

    @Test
    public void testStuff() throws MegaMostExeption {
        stubFor(post(urlEqualTo("/hooks/matterMostID123")).willReturn(aResponse().withStatus(200)));

        // MegaMost sut = Builder;
        MegaMost sut = new MegaMost.Builder("localhost", "matterMostID123")//
                .icon("iconUrl")//
                .port(WIREMOCK_PORT)//
                .scheme(UriScheme.HTTP)//
                .useName("hans")//
                .build();

        sut.sendMessage("ExampleMessage");

        verify(
                postRequestedFor(urlMatching(".*hooks.*"))//
                .withUrl("/hooks/matterMostID123")
                .withRequestBody( containing("username%22%3A%22hans") )
                .withRequestBody( containing("icon_url%22%3A%22iconUrl") )
                .withRequestBody( containing("text%22%3A%22ExampleMessage") )
                
        );
    }
}