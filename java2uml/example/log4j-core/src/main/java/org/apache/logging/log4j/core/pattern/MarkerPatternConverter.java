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
package org.apache.logging.log4j.core.pattern;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StringBuilders;

/**
 * Returns events' full marker string in a StringBuilder.
 */
@Plugin(name = "MarkerPatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({ "marker" })
@PerformanceSensitive("allocation")
public final class MarkerPatternConverter extends LogEventPatternConverter {

    /**
     * Private constructor.
     * @param options options, may be null.
     */
    private MarkerPatternConverter(final String[] options) {
        super("Marker", "marker");
    }

    /**
     * Obtains an instance of pattern converter.
     *
     * @param options options, may be null.
     * @return instance of pattern converter.
     */
    public static MarkerPatternConverter newInstance(final String[] options) {
        return new MarkerPatternConverter(options);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        final Marker marker = event.getMarker();
        if (marker != null) {
            StringBuilders.appendValue(toAppendTo, marker);
        }
    }
}
