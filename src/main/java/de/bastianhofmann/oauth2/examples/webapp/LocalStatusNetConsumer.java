package de.bastianhofmann.oauth2.examples.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

public class LocalStatusNetConsumer extends AbstractAuthorizationCodeServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String accessToken = this.getCredential().getAccessToken();
        NetHttpTransport transport =  new NetHttpTransport();
        HttpRequestFactory requestFactory =
                transport.createRequestFactory(new HttpRequestInitializer() {

                  public void initialize(HttpRequest request) throws IOException {
                    request.setInterceptor(new HttpExecuteInterceptor() {
                      public void intercept(HttpRequest request) throws IOException {
                      }
                    });
                  }
                });
            // make request
            HttpRequest httpRequest = requestFactory.buildGetRequest(new GenericUrl("http://dev.status.net:8080/index.php/api/statuses/home_timeline.json?oauth_token=" + accessToken));
            HttpResponse httpresponse = httpRequest.execute();
            if (httpresponse.isSuccessStatusCode()) {
                response.getWriter().write(httpresponse.parseAsString());
            }
    }

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
      GenericUrl url = new GenericUrl(req.getRequestURL().toString());
      url.setRawPath("/Callback");
      return url.build();
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        return AuthorizationCodeFlowProvider.get();
    }

    @Override
    protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
      return "1";
    }
  }