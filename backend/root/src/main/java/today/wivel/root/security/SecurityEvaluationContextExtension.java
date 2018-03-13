package today.wivel.root.security;

import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;

public class SecurityEvaluationContextExtension extends EvaluationContextExtensionSupport {
    @Override
    public String getExtensionId() {
        return "security";
    }

    @Override
    public SecurityExpressionRoot getRootObject() {
        final KeycloakAuthenticatorDecorator keycloakAuthenticatorDecorator = new KeycloakAuthenticatorDecorator();
        final Authentication authentication = keycloakAuthenticatorDecorator.getAuthentication();
        return new SecurityExpressionRoot(authentication) {};
    }
}
