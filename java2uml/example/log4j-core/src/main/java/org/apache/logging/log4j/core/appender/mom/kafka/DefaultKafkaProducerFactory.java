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

package org.apache.logging.log4j.core.appender.mom.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

/**
 * Creates <a href="https://kafka.apache.org/">Apache Kafka</a> {@link Producer} instances.
 */
public class DefaultKafkaProducerFactory implements KafkaProducerFactory {

    /**
     * Creates a new Kafka Producer from the given configuration properties.
     *
     * @param config
     *            <a href="https://kafka.apache.org/documentation.html#producerconfigs">Kafka Producer configuration
     *            properties.</a>
     * @return a new Kafka {@link Producer}.
     */
    @Override
    public Producer<byte[], byte[]> newKafkaProducer(final Properties config) {
        return new KafkaProducer<>(config);
    }

}
