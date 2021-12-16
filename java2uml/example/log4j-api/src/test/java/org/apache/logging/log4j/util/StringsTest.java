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

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

    /**
     * A sanity test to make sure a typo does not mess up {@link Strings#EMPTY}.
     */
    @Test
    public void testEMPTY() {
        Assert.assertEquals("", Strings.EMPTY);
        Assert.assertEquals(0, Strings.EMPTY.length());
    }

    @Test
    public void testJoin() {
        Assert.assertEquals(null, Strings.join((Iterable<?>) null, '.'));
        Assert.assertEquals(null, Strings.join((Iterator<?>) null, '.'));
        Assert.assertEquals("", Strings.join((Arrays.asList()), '.'));

        Assert.assertEquals("a", Strings.join(Arrays.asList("a"), '.'));
        Assert.assertEquals("a.b", Strings.join(Arrays.asList("a", "b"), '.'));
        Assert.assertEquals("a.b.c", Strings.join(Arrays.asList("a", "b", "c"), '.'));

        Assert.assertEquals("", Strings.join(Arrays.asList((String) null), ':'));
        Assert.assertEquals(":", Strings.join(Arrays.asList(null, null), ':'));
        Assert.assertEquals("a:", Strings.join(Arrays.asList("a", null), ':'));
        Assert.assertEquals(":b", Strings.join(Arrays.asList(null, "b"), ':'));
    }

    @Test
    public void testQuote() {
        Assert.assertEquals("'Q'", Strings.quote("Q"));
    }

}
