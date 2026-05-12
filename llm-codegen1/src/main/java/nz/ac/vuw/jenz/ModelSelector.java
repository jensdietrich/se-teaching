package nz.ac.vuw.jenz;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ModelSelector {

    private static final Logger log = LoggerFactory.getLogger(ModelSelector.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String baseUrl;

    public ModelSelector(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<String> listAvailableModels() throws Exception {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/api/tags"))
                .GET()
                .timeout(Duration.ofMinutes(1))
                .build();

        log.info("Querying Ollama for available models at {}", baseUrl);
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Ollama API returned HTTP " + response.statusCode() + ": " + response.body());
        }

        JsonNode root = MAPPER.readTree(response.body());
        JsonNode modelsNode = root.path("models");
        List<String> names = new ArrayList<>();
        if (modelsNode.isArray()) {
            for (JsonNode m : modelsNode) {
                String name = m.path("name").asText(null);
                if (name != null && !name.isEmpty()) {
                    names.add(name);
                }
            }
        }
        return names;
    }

    public String selectModel() throws Exception {
        List<String> models = listAvailableModels();
        if (models.isEmpty()) {
            throw new RuntimeException("No models available on the Ollama server at " + baseUrl);
        }

        System.out.println("Available models:");
        for (int i = 0; i < models.size(); i++) {
            System.out.printf("  [%d] %s%n", i + 1, models.get(i));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        while (true) {
            System.out.printf("Select a model [1-%d]: ", models.size());
            System.out.flush();
            String line = reader.readLine();
            if (line == null) {
                throw new RuntimeException("No model selection provided (end of input)");
            }
            line = line.trim();
            try {
                int choice = Integer.parseInt(line);
                if (choice >= 1 && choice <= models.size()) {
                    return models.get(choice - 1);
                }
            } catch (NumberFormatException ignored) {
                // fall through to retry
            }
            System.out.println("Invalid selection, please try again.");
        }
    }
}
