package nz.ac.vuw.jenz;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final String DEFAULT_URL = "http://localhost:11434";
    private static final String DEFAULT_MODEL = "qwen3.5:latest";
    private static final String DEFAULT_OUTPUT = "output.zip";

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder("s").longOpt("spec").hasArg().argName("file")
                .desc("Path to the .md specification file (required)").build());
        options.addOption(Option.builder("o").longOpt("output").hasArg().argName("file")
                .desc("Output .zip file path (default: " + DEFAULT_OUTPUT + ")").build());
        options.addOption(Option.builder("u").longOpt("url").hasArg().argName("url")
                .desc("Ollama API base URL (default: " + DEFAULT_URL + ")").build());
        options.addOption(Option.builder("m").longOpt("model").hasArg().argName("model")
                .desc("Ollama model name (default: " + DEFAULT_MODEL + ")").build());
        options.addOption(Option.builder("h").longOpt("help")
                .desc("Print this help message").build());

        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        try {
            cmd = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("ollama-package-gen", options, true);
            System.exit(1);
            return;
        }

        if (cmd.hasOption("h")) {
            formatter.printHelp("ollama-package-gen", options, true);
            return;
        }

        if (!cmd.hasOption("s")) {
            System.err.println("Missing required option: -s/--spec");
            formatter.printHelp("ollama-package-gen", options, true);
            System.exit(1);
            return;
        }

        String specPath = cmd.getOptionValue("s");
        String outputPath = cmd.getOptionValue("o", DEFAULT_OUTPUT);
        String url = cmd.getOptionValue("u", DEFAULT_URL);
        String model = cmd.getOptionValue("m", DEFAULT_MODEL);

        if ("?".equals(model)) {
            try {
                model = new ModelSelector(url).selectModel();
            } catch (Exception e) {
                log.error("Failed to select model from Ollama server", e);
                System.exit(1);
                return;
            }
        }

        try {
            new PackageGenerator(url, model).generate(specPath, outputPath);
        } catch (Exception e) {
            log.error("Package generation failed", e);
            System.exit(1);
        }
    }
}
