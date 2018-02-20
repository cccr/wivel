package today.wivel.root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}


/*


$ export TOKEN=`curl -ss --data "grant_type=password&client_id=wivel-root&username=mart&password=password" http://localhost:9001/auth/realms/wivel/protocol/openid-connect/token | jq -r .access_token`

curl -H "Authorization: bearer $TOKEN" http://localhost:8081/admin/hello

curl -H "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJXNE5jRl9qNm1HaE9lVTlrQ1pScWFGQjFjNXIzT3VnSXU2OXJrR1JHUlUwIn0.eyJqdGkiOiI1YjI2MDMxMy0wY2YwLTQzMDYtYTIzMC0wYWEwZTI0ZTlhMWQiLCJleHAiOjE1MTkxNjI0MTcsIm5iZiI6MCwiaWF0IjoxNTE5MTYyMTE3LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjMyNzY5L2F1dGgvcmVhbG1zL3dpdmVsIiwiYXVkIjoid2l2ZWwtcHVibGljIiwic3ViIjoiZGIwM2RjNTgtYmIyZS00OTZiLTlkZDUtZjkyMjY3YzU3NTE4IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoid2l2ZWwtcHVibGljIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiODY4MTNiNTQtYjgxMy00OTZmLWJkMWEtMjBjYmQ3ZGQxNmM2IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6W10sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJhdXRob3IiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInByZWZlcnJlZF91c2VybmFtZSI6Im1hcnQifQ.NdwUUvC07sdne5NDpEQ3SMDXHUNsgpb3IiT7tGDNMDL7QqEj5wU4290OOi9nwW0slS4gwLR5XAw89Akykk_yyFf9uVpUB8X63z-UCNYkjuzibiCMWOFe6zA8L_Y208SyBepHhp1BTgnFZyzV8weZmbUIk1weQDwgxEwmHzm0GmA7a3ahfZ98s_l851-NyrIXj4eUvespsEVc3RY3Yr_3p-KCJnhYRzL-2gGPb-oD8BypefN_fJ-weRjZ850f-WrpnY_GtsXu6n_t_zrbsjB2zYqf3pn0c6PtznvGUQG8SEhtBRYouUEeA68tFMa4YU9dUeUB0ena52EXxHX4rCr7rw" http://localhost:8081/user/hello




 */