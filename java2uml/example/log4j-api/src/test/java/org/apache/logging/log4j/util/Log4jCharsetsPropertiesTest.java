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

import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Test;

public class Log4jCharsetsPropertiesTest {

    /**
     * Tests that we can load all mappings.
     */
    @Test
    public void testLoadAll() {
        final ResourceBundle resourceBundle = PropertiesUtil.getCharsetsResourceBundle();
        final Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()) {
            final String key = keys.nextElement();
            Assert.assertFalse(String.format("The Charset %s is available and should not be mapped", key),
                    Charset.isSupported(key));
            final String value = resourceBundle.getString(key);
            Assert.assertTrue(String.format("The Charset %s is is not available and is mapped from %s", value, key),
                    Charset.isSupported(value));
        }
    }
}
