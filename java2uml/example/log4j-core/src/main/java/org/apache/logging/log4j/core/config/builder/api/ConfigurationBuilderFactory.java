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

package org.apache.logging.log4j.core.config.builder.api;

import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.builder.impl.DefaultConfigurationBuilder;

/**
 * Provides methods to create ConfigurationBuilders.
 * @since 2.4
 */
public abstract class ConfigurationBuilderFactory {

    /**
     * Returns a new default ConfigurationBuilder to construct Log4j configurations.
     * @return A new ConfigurationBuilder.
     */
    public static ConfigurationBuilder<BuiltConfiguration> newConfigurationBuilder() {
        return new DefaultConfigurationBuilder<>();
    }

    public static <T extends BuiltConfiguration> ConfigurationBuilder<T> newConfigurationBuilder(final Class<T> clazz) {
        return new DefaultConfigurationBuilder<>(clazz);
    }
}
