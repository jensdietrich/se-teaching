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

package org.apache.logging.log4j.core.config;

import java.util.Objects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.Supplier;

/**
 * Reliability strategy that sleeps unconditionally for some time before allowing a Configuration to be stopped.
 */
public class AwaitUnconditionallyReliabilityStrategy implements ReliabilityStrategy {

    private static final long DEFAULT_SLEEP_MILLIS = 5000; // 5 seconds
    private static final long SLEEP_MILLIS = sleepMillis();
    private final LoggerConfig loggerConfig;

    public AwaitUnconditionallyReliabilityStrategy(final LoggerConfig loggerConfig) {
        this.loggerConfig = Objects.requireNonNull(loggerConfig, "loggerConfig is null");
    }

    private static long sleepMillis() {
        return PropertiesUtil.getProperties().getLongProperty("log4j.waitMillisBeforeStopOldConfig",
                DEFAULT_SLEEP_MILLIS);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.logging.log4j.core.config.ReliabilityStrategy#log(org.apache.logging.log4j.util.Supplier,
     * java.lang.String, java.lang.String, org.apache.logging.log4j.Marker, org.apache.logging.log4j.Level,
     * org.apache.logging.log4j.message.Message, java.lang.Throwable)
     */
    @Override
    public void log(final Supplier<LoggerConfig> reconfigured, final String loggerName, final String fqcn, final Marker marker, final Level level,
            final Message data, final Throwable t) {
        loggerConfig.log(loggerName, fqcn, marker, level, data, t);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.logging.log4j.core.config.ReliabilityStrategy#log(org.apache.logging.log4j.util.Supplier,
     * org.apache.logging.log4j.core.LogEvent)
     */
    @Override
    public void log(final Supplier<LoggerConfig> reconfigured, final LogEvent event) {
        loggerConfig.log(event);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.logging.log4j.core.config.ReliabilityStrategy#beforeLogEvent(org.apache.logging.log4j.core.config.
     * LoggerConfig, org.apache.logging.log4j.util.Supplier)
     */
    @Override
    public LoggerConfig getActiveLoggerConfig(final Supplier<LoggerConfig> next) {
        return this.loggerConfig;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.logging.log4j.core.config.ReliabilityStrategy#afterLogEvent()
     */
    @Override
    public void afterLogEvent() {
        // no action
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.logging.log4j.core.config.ReliabilityStrategy#beforeStopAppenders()
     */
    @Override
    public void beforeStopAppenders() {
        // no action
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.logging.log4j.core.config.ReliabilityStrategy#beforeStopConfiguration(org.apache.logging.log4j.core
     * .config.Configuration)
     */
    @Override
    public void beforeStopConfiguration(final Configuration configuration) {
        // only sleep once per configuration stop
        if (loggerConfig == configuration.getRootLogger()) {
            try {
                Thread.sleep(SLEEP_MILLIS);
            } catch (final InterruptedException e) {
                StatusLogger.getLogger().warn("Sleep before stop configuration was interrupted.");
            }
        }
    }

}
