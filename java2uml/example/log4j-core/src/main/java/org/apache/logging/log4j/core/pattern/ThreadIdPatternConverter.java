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

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.util.PerformanceSensitive;

/**
 * Formats the event thread ID.
 *
 * @since 2.6
 */
@Plugin(name = "ThreadIdPatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({ "T", "tid", "threadId" })
@PerformanceSensitive("allocation")
public final class ThreadIdPatternConverter extends LogEventPatternConverter {
    /**
     * Singleton.
     */
    private static final ThreadIdPatternConverter INSTANCE =
        new ThreadIdPatternConverter();

    /**
     * Private constructor.
     */
    private ThreadIdPatternConverter() {
        super("ThreadId", "threadId");
    }

    /**
     * Obtains an instance of ThreadPatternConverter.
     *
     * @param options options, currently ignored, may be null.
     * @return instance of ThreadPatternConverter.
     */
    public static ThreadIdPatternConverter newInstance(
        final String[] options) {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        toAppendTo.append(event.getThreadId());
    }
}
