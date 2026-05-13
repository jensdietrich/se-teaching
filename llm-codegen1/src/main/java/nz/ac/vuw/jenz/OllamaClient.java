package nz.ac.vuw.jenz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class OllamaClient {

    private static final Logger log = LoggerFactory.getLogger(OllamaClient.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String baseUrl;
    private final String model;
    private final boolean unloadAfterUse;
    private final HttpClient httpClient;

    public OllamaClient(String baseUrl, String model) {
        this(baseUrl, model, false);
    }

    public OllamaClient(String baseUrl, String model, boolean unloadAfterUse) {
        this.baseUrl = baseUrl;
        this.model = model;
        this.unloadAfterUse = unloadAfterUse;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
    }

    public String generate(String prompt) throws Exception {
        ObjectNode body = MAPPER.createObjectNode();
        body.put("model", model);
        body.put("prompt", prompt);
        body.put("stream", false);
        if (unloadAfterUse) {
            body.put("keep_alive", 0);
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(MAPPER.writeValueAsString(body)))
                .timeout(Duration.ofMinutes(10))
                .build();

        log.info("Sending prompt to Ollama (model={})", model);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Ollama API returned HTTP " + response.statusCode() + ": " + response.body());
        }

        JsonNode node = MAPPER.readTree(response.body());
        return node.get("response").asText();
    }
}
