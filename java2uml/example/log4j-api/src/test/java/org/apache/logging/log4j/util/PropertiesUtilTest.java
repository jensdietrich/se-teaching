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

package org.apache.logging.log4j.util;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *
 */
public class PropertiesUtilTest {

    private final Properties properties = new Properties();

    @Before
    public void setUp() throws Exception {
        properties.load(ClassLoader.getSystemResourceAsStream("PropertiesUtilTest.properties"));
    }

    @Test
    public void testExtractSubset() throws Exception {
        assertHasAllProperties(PropertiesUtil.extractSubset(properties, "a"));
        assertHasAllProperties(PropertiesUtil.extractSubset(properties, "b."));
        assertHasAllProperties(PropertiesUtil.extractSubset(properties, "c.1"));
        assertHasAllProperties(PropertiesUtil.extractSubset(properties, "dd"));
        assertEquals(0, properties.size());
    }

    @Test
    public void testPartitionOnCommonPrefix() throws Exception {
        final Map<String, Properties> parts = PropertiesUtil.partitionOnCommonPrefixes(properties);
        assertEquals(4, parts.size());
        assertHasAllProperties(parts.get("a"));
        assertHasAllProperties(parts.get("b"));
        assertHasAllProperties(PropertiesUtil.partitionOnCommonPrefixes(parts.get("c")).get("1"));
        assertHasAllProperties(parts.get("dd"));
    }

    private static void assertHasAllProperties(final Properties properties) {
        assertNotNull(properties);
        assertEquals("1", properties.getProperty("1"));
        assertEquals("2", properties.getProperty("2"));
        assertEquals("3", properties.getProperty("3"));
    }


    @Test
    public void testGetCharsetProperty() throws Exception {
        final Properties p = new Properties();
        p.setProperty("e.1", StandardCharsets.US_ASCII.name());
        p.setProperty("e.2", "wrong-charset-name");
        final PropertiesUtil pu = new PropertiesUtil(p);

        assertEquals(Charset.defaultCharset(), pu.getCharsetProperty("e.0"));
        assertEquals(StandardCharsets.US_ASCII, pu.getCharsetProperty("e.1"));
        assertEquals(Charset.defaultCharset(), pu.getCharsetProperty("e.2"));
    }
    
    @Test
    public void testGetMappedProperty_sun_stdout_encoding() {
        final PropertiesUtil pu = new PropertiesUtil(System.getProperties());
        final Charset expected = System.console() == null ? Charset.defaultCharset() : StandardCharsets.UTF_8;
        assertEquals(expected, pu.getCharsetProperty("sun.stdout.encoding"));
    }

    @Test
    public void testGetMappedProperty_sun_stderr_encoding() {
        final PropertiesUtil pu = new PropertiesUtil(System.getProperties());
        final Charset expected = System.console() == null ? Charset.defaultCharset() : StandardCharsets.UTF_8;
        assertEquals(expected, pu.getCharsetProperty("sun.err.encoding"));
    }

    @Test
    public void testNonStringSystemProperties() {
        final Object key1 = "1";
        final Object key2 = new Object();
        System.getProperties().put(key1, new Object());
        System.getProperties().put(key2, "value-2");
        try {
            final PropertiesUtil util = new PropertiesUtil(new Properties());
            assertNull(util.getStringProperty("1"));
        } finally {
            System.getProperties().remove(key1);
            System.getProperties().remove(key2);
        }
    }
}
