package today.wivel.root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.security.core.Authentication;
import today.wivel.root.data.domain.User;
import today.wivel.root.security.KeycloakAuthenticatorDecorator;
import today.wivel.root.security.SecurityEvaluationContextExtension;

@SpringBootApplication
@EnableMongoAuditing
public class RootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }

    @Bean
    public AuditorAware<User> auditor() {
        return () -> {
            final KeycloakAuthenticatorDecorator keycloakAuthenticatorDecorator = new KeycloakAuthenticatorDecorator();
            final Authentication authentication = keycloakAuthenticatorDecorator.getAuthentication();
            return new User(keycloakAuthenticatorDecorator.getPrincipal().getId(), authentication.getName());
        };
    }

    @Bean
    EvaluationContextExtension securityExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(mongoDbFactory, converter);
    }
}

/*
$ export TOKEN=`curl -ss --data "grant_type=password&client_id=wivel-root&username=mart&password=password" http://localhost:9001/auth/realms/wivel/protocol/openid-connect/token | jq -r .access_token`

curl -H "Authorization: bearer $TOKEN" http://localhost:8081/admin/hello

curl -H "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ4UGZSNWM4TVlKU25CSy00dThncWVicTlSMzJNTEhqUTRXd3pEcjllaXZBIn0.eyJqdGkiOiJiOGFlODFmOS0xODg0LTQxNTYtYjhkNS1mM2FkZDNhY2JkYzQiLCJleHAiOjE1MTkyNDU0NjksIm5iZiI6MCwiaWF0IjoxNTE5MjQ1MTY5LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODIvYXV0aC9yZWFsbXMvd2l2ZWwiLCJhdWQiOiJ3aXZlbC1wdWJsaWMiLCJzdWIiOiI0MTkwM2FhZi1kZTFjLTQ4MzctYjBiYy03MzA5YmRkOGZhMjQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ3aXZlbC1wdWJsaWMiLCJhdXRoX3RpbWUiOjAsInNlc3Npb25fc3RhdGUiOiI3MzM3YmZkMC1kNmEzLTRkZmMtYTMzMC1mMmU1NzU3MjIxY2QiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MSJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiYXV0aG9yIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJtYXJ0In0.O32i9s6MnLEoJWloPQ0tF669H4ffv_OYSEXQ8-aBzpeEBpI5KPM7naTqJzyTnT3LzTJv7I9i5udWi8ew4NE2uFlHa9zlIg2YwA-ykksKhvnrfxwrSrbLqycJz7t-LfZgJC20klcgDdxMRol_ZsABbgxuE37uz4JgLSMS5gAKHllp9dvnriKaUDv93-qGwqjd9cZJOqISq9L84iYe4O-AMhaYfJqE97S2S81AlJEHoYj72s19rb1gRMb7KqqbUx3-7mI2o4FxWkCReEsh6X2PnjuQoYRKJ_ZKvChXV1aY87STJ8SGIhQaHGBPpWmC-yuvGuc_fE6CXLNoOCFjXm9i0w" http://localhost:8081/user/hello


 */