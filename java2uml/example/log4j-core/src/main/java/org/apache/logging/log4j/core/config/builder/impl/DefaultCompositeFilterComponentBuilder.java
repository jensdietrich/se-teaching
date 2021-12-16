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
package org.apache.logging.log4j.core.config.builder.impl;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.CompositeFilterComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.FilterComponentBuilder;
import org.apache.logging.log4j.core.filter.AbstractFilter.AbstractFilterBuilder;

/**
 * @since 2.4
 */
class DefaultCompositeFilterComponentBuilder
        extends DefaultComponentAndConfigurationBuilder<CompositeFilterComponentBuilder>
        implements CompositeFilterComponentBuilder {

    public DefaultCompositeFilterComponentBuilder(final DefaultConfigurationBuilder<? extends Configuration> builder,
            final String onMatch, final String onMismatch) {
        super(builder, "Filters");
        addAttribute(AbstractFilterBuilder.ATTR_ON_MATCH, onMatch);
        addAttribute(AbstractFilterBuilder.ATTR_ON_MISMATCH, onMismatch);
    }

    @Override
    public CompositeFilterComponentBuilder add(final FilterComponentBuilder builder) {
        return addComponent(builder);
    }

}
