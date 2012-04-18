package de.bastianhofmann.oauth2.examples.webapp;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

public class AuthorizationCodeFlowProvider {
    public static AuthorizationCodeFlow get() {
        return new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
                new NetHttpTransport(),
                new JacksonFactory(),
                new GenericUrl("http://dev.status.net:8080/index.php/api/oauth2/access_token"),
                new BasicAuthentication("45943ff6ad985da7eec267608ff1c1d2", "6272de38d2550cba4d81f778fe29e436"),
                "45943ff6ad985da7eec267608ff1c1d2",
                "http://dev.status.net:8080/index.php/api/oauth2/authorize").setCredentialStore(
                        MemoryCredentialStoreFactory.get()).setScopes("")
                .build();
    }
}
