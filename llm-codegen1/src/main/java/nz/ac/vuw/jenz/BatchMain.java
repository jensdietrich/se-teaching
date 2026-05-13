package nz.ac.vuw.jenz;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BatchMain {

    private static final Logger log = LoggerFactory.getLogger(BatchMain.class);

    private static final String DEFAULT_URL = "http://localhost:11434";
    private static final String SPEC_EXTENSION = ".md";

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder("s").longOpt("specs").hasArg().argName("dir")
                .desc("Directory containing .md specification files (required)").build());
        options.addOption(Option.builder("r").longOpt("results").hasArg().argName("dir")
                .desc("Directory in which results will be written (required)").build());
        options.addOption(Option.builder("u").longOpt("url").hasArg().argName("url")
                .desc("Ollama API base URL (default: " + DEFAULT_URL + ")").build());
        options.addOption(Option.builder("h").longOpt("help")
                .desc("Print this help message").build());

        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        try {
            cmd = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("ollama-package-gen-batch", options, true);
            System.exit(1);
            return;
        }

        if (cmd.hasOption("h")) {
            formatter.printHelp("ollama-package-gen-batch", options, true);
            return;
        }

        if (!cmd.hasOption("s") || !cmd.hasOption("r")) {
            System.err.println("Missing required option: -s/--specs and/or -r/--results");
            formatter.printHelp("ollama-package-gen-batch", options, true);
            System.exit(1);
            return;
        }

        Path specsDir = Path.of(cmd.getOptionValue("s"));
        Path resultsDir = Path.of(cmd.getOptionValue("r"));
        String url = cmd.getOptionValue("u", DEFAULT_URL);

        if (!Files.isDirectory(specsDir)) {
            System.err.println("Specs path is not a directory: " + specsDir);
            System.exit(1);
            return;
        }

        try {
            run(url, specsDir, resultsDir);
        } catch (Exception e) {
            log.error("Batch processing failed", e);
            System.exit(1);
        }
    }

    private static void run(String url, Path specsDir, Path resultsDir) throws Exception {
        List<Path> specs = listSpecs(specsDir);
        if (specs.isEmpty()) {
            log.warn("No {} specification files found in {}", SPEC_EXTENSION, specsDir);
            return;
        }
        log.info("Found {} specification(s) in {}", specs.size(), specsDir);

        List<String> models = new ModelSelector(url).listAvailableModels();
        if (models.isEmpty()) {
            log.warn("No models available on Ollama server at {}", url);
            return;
        }
        log.info("Found {} model(s) on Ollama server: {}", models.size(), models);

        Files.createDirectories(resultsDir);

        for (String model : models) {
            Path modelDir = resultsDir.resolve(sanitiseForFilename(model));
            Files.createDirectories(modelDir);
            log.info("Processing model {} -> {}", model, modelDir);

            for (Path spec : specs) {
                String baseName = stripExtension(spec.getFileName().toString());
                Path outputZip = modelDir.resolve(baseName + ".zip");
                Path errorLog = modelDir.resolve(baseName + ".error.log");

                try {
                    new PackageGenerator(url, model, false).generate(spec.toString(), outputZip.toString());
                    log.info("Generated {}", outputZip);
                } catch (Exception e) {
                    log.error("Generation failed for model={}, spec={}", model, spec, e);
                    writeErrorLog(errorLog, model, spec, e);
                }
            }

            try {
                new OllamaClient(url, model).unload();
            } catch (Exception e) {
                log.error("Failed to unload model {}", model, e);
            }
        }

        log.info("Batch run complete; results in {}", resultsDir);
    }

    private static List<Path> listSpecs(Path specsDir) throws IOException {
        try (Stream<Path> stream = Files.list(specsDir)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().toLowerCase().endsWith(SPEC_EXTENSION))
                    .sorted(Comparator.comparing(p -> p.getFileName().toString()))
                    .toList();
        }
    }

    private static void writeErrorLog(Path errorLog, String model, Path spec, Exception e) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            pw.println("Model: " + model);
            pw.println("Spec:  " + spec);
            pw.println("Error: " + e.getClass().getName() + ": " + e.getMessage());
            pw.println();
            e.printStackTrace(pw);
        }
        try {
            Files.writeString(errorLog, sw.toString());
        } catch (IOException ioe) {
            log.error("Could not write error log to {}", errorLog, ioe);
        }
    }

    private static String stripExtension(String filename) {
        int dot = filename.lastIndexOf('.');
        return dot > 0 ? filename.substring(0, dot) : filename;
    }

    private static String sanitiseForFilename(String name) {
        return name.replaceAll("[:/\\\\?*\"<>|]", "_");
    }
}
