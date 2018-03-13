package today.wivel.root.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;

public class KeycloakPrincipalDecorator implements Principal, Serializable {
    private final KeycloakPrincipal keycloakPrincipal;

    KeycloakPrincipalDecorator(Object keycloakPrincipal) {
        super();
        this.keycloakPrincipal = (KeycloakPrincipal) keycloakPrincipal;
    }

    public KeycloakSecurityContext getKeycloakSecurityContext() {
        return keycloakPrincipal.getKeycloakSecurityContext();
    }

    public String getId() {
        final AccessToken accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();
        return (String) accessToken.getOtherClaims().get("user_id");
    }

    @Override
    public String getName() {
        return keycloakPrincipal.getName();
    }

    @Override
    public boolean implies(Subject subject) {
        return keycloakPrincipal.implies(subject);
    }
}
