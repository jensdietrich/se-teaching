/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.log4j.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A source for global configuration properties.
 *
 * @since 2.10.0
 */
public interface PropertySource {

    /**
     * Returns the order in which this PropertySource has priority. A higher value means that the source will be
     * applied later so as to take precedence over other property sources.
     *
     * @return priority value
     */
    int getPriority();

    /**
     * Iterates over all properties and performs an action for each key/value pair.
     *
     * @param action action to perform on each key/value pair
     */
    void forEach(BiConsumer<String, String> action);

    /**
     * Converts a list of property name tokens into a normal form. For example, a list of tokens such as
     * "foo", "bar", "baz", might be normalized into the property name "log4j2.fooBarBaz".
     *
     * @param tokens list of property name tokens
     * @return a normalized property name using the given tokens
     */
    CharSequence getNormalForm(Iterable<? extends CharSequence> tokens);

    /**
     * Comparator for ordering PropertySource instances by priority.
     *
     * @since 2.10.0
     */
    class Comparator implements java.util.Comparator<PropertySource>, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(final PropertySource o1, final PropertySource o2) {
            return Integer.compare(Objects.requireNonNull(o1).getPriority(), Objects.requireNonNull(o2).getPriority());
        }
    }

    /**
     * Utility methods useful for PropertySource implementations.
     *
     * @since 2.10.0
     */
    final class Util {
        private static final String PREFIXES = "(?i:^log4j2?[-._/]?|^org\\.apache\\.logging\\.log4j\\.)?";
        private static final Pattern PROPERTY_TOKENIZER = Pattern.compile(PREFIXES + "([A-Z]*[a-z0-9]+|[A-Z0-9]+)[-._/]?");
        private static final Map<CharSequence, List<CharSequence>> CACHE = new ConcurrentHashMap<>();

        /**
         * Converts a property name string into a list of tokens. This will strip a prefix of {@code log4j},
         * {@code log4j2}, {@code Log4j}, or {@code org.apache.logging.log4j}, along with separators of
         * dash {@code -}, dot {@code .}, underscore {@code _}, and slash {@code /}. Tokens can also be separated
         * by camel case conventions without needing a separator character in between.
         *
         * @param value property name
         * @return the property broken into lower case tokens
         */
        public static List<CharSequence> tokenize(final CharSequence value) {
            if (CACHE.containsKey(value)) {
                return CACHE.get(value);
            }
            final List<CharSequence> tokens = new ArrayList<>();
            final Matcher matcher = PROPERTY_TOKENIZER.matcher(value);
            while (matcher.find()) {
                tokens.add(matcher.group(1).toLowerCase());
            }
            CACHE.put(value, tokens);
            return tokens;
        }

        /**
         * Joins a list of strings using camelCaseConventions.
         *
         * @param tokens tokens to convert
         * @return tokensAsCamelCase
         */
        public static CharSequence joinAsCamelCase(final Iterable<? extends CharSequence> tokens) {
            final StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (final CharSequence token : tokens) {
                if (first) {
                    sb.append(token);
                } else {
                    sb.append(Character.toUpperCase(token.charAt(0)));
                    if (token.length() > 1) {
                        sb.append(token.subSequence(1, token.length()));
                    }
                }
                first = false;
            }
            return sb.toString();
        }

        private Util() {
        }
    }
}
