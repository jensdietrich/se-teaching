package nz.ac.vuw.jenz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PackageGenerator {

    private static final Logger log = LoggerFactory.getLogger(PackageGenerator.class);

    private static final String PROMPT_TEMPLATE = """
            You are a software project generator. Based on the specification below, generate a complete, runnable software project.

            Rules:
            - Output EVERY file needed for the project to build and run from the root directory.
            - For a Java/Maven project that means: pom.xml, all Java source files, and any resources.
            - Use EXACTLY the following format for each file — no text outside these blocks:

            <<< FILE: relative/path/to/file >>>
            file content here
            <<< END FILE >>>

            Specification:
            %s
            """;

    private final OllamaClient client;
    private final ResponseParser parser;

    public PackageGenerator(String baseUrl, String model) {
        this.client = new OllamaClient(baseUrl, model);
        this.parser = new ResponseParser();
    }

    public void generate(String specPath, String outputPath) throws Exception {
        log.info("Reading specification from {}", specPath);
        String spec = Files.readString(Path.of(specPath));

        String prompt = String.format(PROMPT_TEMPLATE, spec);

        log.info("Requesting project generation from Ollama");
        String response = client.generate(prompt);

        log.info("Parsing model response");
        Map<String, String> files = parser.parse(response);

        if (files.isEmpty()) {
            Path rawDump = Path.of("response.txt");
            Files.writeString(rawDump, response);
            log.error("No files extracted — raw model response saved to {}", rawDump.toAbsolutePath());
            throw new RuntimeException("Model response contained no parseable file blocks");
        }

        log.info("Packaging {} file(s) into {}", files.size(), outputPath);
        writeZip(files, outputPath);
        log.info("Done — package written to {}", outputPath);
    }

    private void writeZip(Map<String, String> files, String outputPath) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputPath))) {
            for (Map.Entry<String, String> entry : files.entrySet()) {
                zos.putNextEntry(new ZipEntry(entry.getKey()));
                zos.write(entry.getValue().getBytes());
                zos.closeEntry();
            }
        }
    }
}
