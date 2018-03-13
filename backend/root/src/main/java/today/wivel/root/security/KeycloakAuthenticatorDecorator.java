package today.wivel.root.security;

import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.security.auth.Subject;
import java.util.Collection;

public class KeycloakAuthenticatorDecorator implements Authentication {
    private final Authentication authentication;
    private final KeycloakPrincipalDecorator keycloakPrincipalDecorator;
    private final AccessToken accessToken;

    public KeycloakAuthenticatorDecorator() {
        this(SecurityContextHolder.getContext());
    }

    private KeycloakAuthenticatorDecorator(SecurityContext securityContext) {
        authentication = securityContext.getAuthentication();
        keycloakPrincipalDecorator = new KeycloakPrincipalDecorator(authentication.getPrincipal());
        accessToken = keycloakPrincipalDecorator.getKeycloakSecurityContext().getToken();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authentication.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return authentication.getCredentials();
    }

    @Override
    public Object getDetails() {
        return authentication.getDetails();
    }

    @Override
    public KeycloakPrincipalDecorator getPrincipal() {
        return keycloakPrincipalDecorator;
    }

    @Override
    public boolean isAuthenticated() {
        return authentication.isAuthenticated();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authentication.setAuthenticated(isAuthenticated);
    }

    @Override
    public String getName() {
        return authentication.getName();
    }

    @Override
    public boolean implies(Subject subject) {
        return authentication.implies(subject);
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public Authentication getAuthentication() {
        return this;
    }

    public KeycloakPrincipalDecorator getKeycloakPrincipalDecorator() {
        return keycloakPrincipalDecorator;
    }
}
