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
package org.apache.logging.log4j.spi;

/**
 * Extension service provider interface to implement additional custom MDC behavior for
 * {@link org.apache.logging.log4j.ThreadContext}.
 *
 * @see ThreadContextMap
 * @since 2.8
 */
public interface CleanableThreadContextMap extends ThreadContextMap2 {

    /**
     * Removes all given context map keys from the current thread's context map.
     *
     * <p>If the current thread does not have a context map it is
     * created as a side effect.</p>

     * @param keys The keys.
     * @since 2.8
     */
    void removeAll(final Iterable<String> keys);

}
