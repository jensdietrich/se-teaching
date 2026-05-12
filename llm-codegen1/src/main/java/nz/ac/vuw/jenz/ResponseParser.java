package nz.ac.vuw.jenz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseParser {

    private static final Logger log = LoggerFactory.getLogger(ResponseParser.class);

    private static final Pattern FILE_BLOCK = Pattern.compile(
            "<<<\\s*FILE:\\s*([^>\\n]+?)\\s*>>>\\s*\\n(.*?)\\n?<<<\\s*END FILE\\s*>>>",
            Pattern.DOTALL
    );

    public Map<String, String> parse(String response) {
        Map<String, String> files = new LinkedHashMap<>();
        Matcher matcher = FILE_BLOCK.matcher(response);
        while (matcher.find()) {
            String path = matcher.group(1).trim();
            String content = matcher.group(2);
            files.put(path, content);
            log.info("Extracted file: {}", path);
        }
        if (files.isEmpty()) {
            log.warn("No <<< FILE: ... >>> blocks found in model response");
        }
        return files;
    }
}
