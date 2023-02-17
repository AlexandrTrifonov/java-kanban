package http;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVTaskClient {

    HttpClient client;
    String url;
    String apiToken;

    public KVTaskClient (String url) {
        client = HttpClient.newHttpClient();
        this.url = url;
        this.apiToken = null;
    //    url = "http://localhost:8078/";
        URI uriR = URI.create(url + "register");
        HttpRequest apiR = HttpRequest.newBuilder().uri(uriR).GET().build();
        try {
            HttpResponse<String> response = client.send(apiR, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonElement jsonElement = JsonParser.parseString(response.body());
                this.apiToken = jsonElement.getAsString();
                System.out.println("Получили Токен " + this.apiToken);
            } else {
                System.out.println("Ошибка при получении apiToken");
            }
        } catch (NullPointerException | IOException | InterruptedException ex) {
            System.out.println("Ошибка при получении apiToken");
        }
    }

    public String load(String key) {

        HttpClient client = HttpClient.newBuilder().build();
        URI uri = URI.create(url + "load/" + key + "?API_TOKEN=" + apiToken);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) {
            System.out.println("Ошибка загрузки.");
        }

        if (response != null) {
            return response.body();
        }

        return null;
    }

    public void put(String key, String json) {

        System.out.println("put " + key);
        URI uri = URI.create(url + "save/" + key + "?API_TOKEN=" + apiToken);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString(json)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Менеджер сохранен на сервер");
            } else {
                System.out.println("Ошибка сохранения менеджера");
            }
        } catch (NullPointerException | IOException | InterruptedException ex) {
            System.out.println("Ошибка сохранения менеджера");
        }
    }
}
