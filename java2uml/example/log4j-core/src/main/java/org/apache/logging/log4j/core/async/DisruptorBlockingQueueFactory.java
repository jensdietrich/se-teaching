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
package org.apache.logging.log4j.core.async;

import java.util.concurrent.BlockingQueue;

import com.conversantmedia.util.concurrent.DisruptorBlockingQueue;
import com.conversantmedia.util.concurrent.SpinPolicy;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

/**
 * Factory for creating instances of {@link DisruptorBlockingQueue}.
 *
 * @since 2.7
 */
@Plugin(name = "DisruptorBlockingQueue", category = Node.CATEGORY, elementType = BlockingQueueFactory.ELEMENT_TYPE)
public class DisruptorBlockingQueueFactory<E> implements BlockingQueueFactory<E> {

    private final SpinPolicy spinPolicy;

    private DisruptorBlockingQueueFactory(final SpinPolicy spinPolicy) {
        this.spinPolicy = spinPolicy;
    }

    @Override
    public BlockingQueue<E> create(final int capacity) {
        return new DisruptorBlockingQueue<>(capacity, spinPolicy);
    }

    @PluginFactory
    public static <E> DisruptorBlockingQueueFactory<E> createFactory(
        @PluginAttribute(value = "SpinPolicy", defaultString = "WAITING") final SpinPolicy spinPolicy
    ) {
        return new DisruptorBlockingQueueFactory<>(spinPolicy);
    }
}
