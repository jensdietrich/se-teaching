# Programmatic Code Generation with Local LLM

This shows how to interact with an LLM programmatically from a Java program.

The program send a specification to generate code (`.md` file) to a locally running [Ollama](https://ollama.com) instance and 
downloads the generated code as a `.zip` archive.

## Prerequisites

**Java version required:** Java 17 (see `pom.xml`).
Verify this by running `java -version`.

[ollama](https://ollama.com/) must be installed locally, and the API must be exposed on port `11434`.
Verify this by running `curl http://localhost:11434`.
This URL is hardcoded in `Main::DEFAULT_URL` and can be changed there.


## Build

```bash
mvn package
```

This produces a self-contained executable JAR at `target/ollama-package-gen-1.0-SNAPSHOT.jar`.

## Computing the classpath

```bash
mvn dependency:build-classpath -Dmdep.outputFile=classpath.txt
```

The classpath is written to `classpath.txt`. You can use it directly:

```bash
java -cp target/classes:<classpath> nz.ac.vuw.jenz.Main [options]
```

Or use the shaded JAR (no classpath needed):

```bash
java -jar target/ollama-package-gen-1.0-SNAPSHOT.jar [options]
```

## Main — `nz.ac.vuw.jenz.Main`

Reads a Markdown specification file, submits it to a local Ollama model, and writes the generated project files to a `.zip` archive.

**Prerequisites:** start Ollama with the desired model before running, e.g.:

```bash
ollama run qwen3.5:latest
```

**Usage:**

```
usage: ollama-package-gen [-h] [-m <model>] [-o <file>] [-s <file>] [-u <url>] [--unload-after-use]
 -h,--help               Print this help message
 -m,--model <model>      Ollama model name (default: qwen3.5:latest) - if the model is set to ? a selector of models will appear
 -o,--output <file>      Output .zip file path (default: output.zip)
 -s,--spec <file>        Path to the .md specification file (required)
 -u,--url <url>          Ollama API base URL (default: http://localhost:11434)
    --unload-after-use   Unload the model from memory after the request (sends keep_alive=0). Default: false.
```

**Example:**

```bash
java -jar target/ollama-package-gen-1.0-SNAPSHOT.jar \
  --spec my-project-spec.md \
  --output my-project.zip
```

If the model response cannot be parsed (no `<<< FILE: ... >>>` blocks found), the raw response is saved to `response.txt` for inspection.

## Specification format

Write a plain Markdown file describing the software to be generated. The model determines the project type, language, and structure from the specification. For example:

```markdown
# Hello World CLI

Generate a Java 17 Maven project with a single class `App` that prints "Hello, World!" to stdout.
The project should be buildable with `mvn package` and runnable with `java -jar <jar-file-generated-by-build>` .
See `examples/input.md` for example input.
```
