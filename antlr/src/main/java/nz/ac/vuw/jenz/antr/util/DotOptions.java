package nz.ac.vuw.jenz.antr.util;

public class DotOptions {

    public static final DotOptions DEFAULT = new Builder().build();

    public static final String DEFAULT_PARAMETERS = null;
    public static final String DEFAULT_LEXER_RULE_SHAPE = "box";
    public static final String DEFAULT_PARSER_RULE_SHAPE = "ellipse";

    public static class Builder {

        private String parameters = DEFAULT_PARAMETERS;
        private String lexerRuleShape = DEFAULT_LEXER_RULE_SHAPE;
        private String parserRuleShape = DEFAULT_PARSER_RULE_SHAPE;

        public Builder withParameters(String parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder withLexerRuleShape(String lexerRuleShape) {
            this.lexerRuleShape = lexerRuleShape;
            return this;
        }

        public Builder withParserRuleShape(String parserRuleShape) {
            this.parserRuleShape = parserRuleShape;
            return this;
        }

        public DotOptions build() {

            if (lexerRuleShape == null)
                throw new IllegalStateException("lexerRuleShape == null");

            if (parserRuleShape == null)
                throw new IllegalStateException("parserRuleShape == null");

            return new DotOptions(parameters, lexerRuleShape, parserRuleShape);
        }
    }

    public final String parameters;
    public final String lexerRuleShape;
    public final String parserRuleShape;

    private DotOptions(String parameters, String lexerRuleShape, String parserRuleShape) {
        this.parameters = parameters;
        this.lexerRuleShape = lexerRuleShape;
        this.parserRuleShape = parserRuleShape;
    }
}
