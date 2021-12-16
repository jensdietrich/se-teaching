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
package org.apache.logging.log4j.core.async.perftest;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.LifeCycle;

public class RunLogback implements IPerfTestRunner {
    final Logger LOGGER = (Logger) LoggerFactory.getLogger(getClass());

    @Override
    public void runThroughputTest(final int lines, final Histogram histogram) {
        final long s1 = System.nanoTime();
        final Logger logger = LOGGER;
        for (int j = 0; j < lines; j++) {
            logger.info(THROUGHPUT_MSG);
        }
        final long s2 = System.nanoTime();
        final long opsPerSec = (1000L * 1000L * 1000L * lines) / (s2 - s1);
        histogram.addObservation(opsPerSec);
    }

    @Override
    public void runLatencyTest(final int samples, final Histogram histogram,
            final long nanoTimeCost, final int threadCount) {
        final Logger logger = LOGGER;
        for (int i = 0; i < samples; i++) {
            final long s1 = System.nanoTime();
            logger.info(LATENCY_MSG);
            final long s2 = System.nanoTime();
            final long value = s2 - s1 - nanoTimeCost;
            if (value > 0) {
                histogram.addObservation(value);
            }
            // wait 1 microsec
            final long PAUSE_NANOS = 10000 * threadCount;
            final long pauseStart = System.nanoTime();
            while (PAUSE_NANOS > (System.nanoTime() - pauseStart)) {
                // busy spin
            }
        }
    }

    @Override
    public void shutdown() {
        ((LifeCycle) LoggerFactory.getILoggerFactory()).stop();
    }

    @Override
    public void log(final String msg) {
        LOGGER.info(msg);
    }
}
