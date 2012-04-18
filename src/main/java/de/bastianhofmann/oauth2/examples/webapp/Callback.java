package de.bastianhofmann.oauth2.examples.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.http.GenericUrl;

public class Callback  extends AbstractAuthorizationCodeCallbackServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
        throws ServletException, IOException {
      resp.sendRedirect("/StatusNet");
    }

    @Override
    protected void onError(
        HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
        throws ServletException, IOException {
      // handle error
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
