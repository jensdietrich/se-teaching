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
package org.apache.logging.log4j.core.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.time.Instant;

/**
 * Jackson mix-in for {@link Instant}.
 * <p>
 * <em>Consider this class private.</em>
 * </p>
 * @see Marker
 */
@JsonIgnoreProperties({ "epochMillisecond", "nanoOfMillisecond" })
abstract class InstantMixIn {

    @JsonCreator
    InstantMixIn(
            // @formatter:off
            @JsonProperty("epochSecond") final long epochSecond,
            @JsonProperty("nanoOfSecond") final int nanoOfSecond)
            // @formatter:on
    {
        // empty
    }

    @JsonProperty("epochSecond")
    @JacksonXmlProperty(localName = "epochSecond", isAttribute = true)
    abstract long getEpochSecond();

    @JsonProperty("nanoOfSecond")
    @JacksonXmlProperty(localName = "nanoOfSecond", isAttribute = true)
    abstract int getNanoOfSecond();
}
