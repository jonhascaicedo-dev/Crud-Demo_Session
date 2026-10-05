package cruddemo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author johans caicedo
 */
public class EmailService {

    

        public static void enviarCorreo(String destinatario, String codigo) {
            String apiKey = ""; // Tu API Key de Resend

            // JSON del cuerpo del correo
            String jsonPayload = "{"
                    + "\"from\": \"adminapp@domain.com\","
                    + "\"to\": [\"" + destinatario + "\"],"
                    + "\"subject\": \"Restablecer contraseña\","
                    + "\"html\": \"<p>Tu código de recuperación es: <strong>" + codigo + "</strong></p>\""
                    + "}";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.resend.com/emails"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .exceptionally(e -> {
                        e.printStackTrace();
                        return null;
                    });
        }
    }


