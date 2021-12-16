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
package org.apache.logging.log4j.core.net.ssl;

import java.security.KeyStore;

import org.junit.Assert;
import org.junit.Test;

public class KeyStoreConfigurationTest {
    @Test(expected = StoreConfigurationException.class)
    public void loadEmptyConfigurationDeprecated() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(null, TestConstants.NULL_PWD, null, null);
        final KeyStore ks = ksc.getKeyStore();
        Assert.assertTrue(ks == null);
    }
    @Test(expected = StoreConfigurationException.class)
    public void loadEmptyConfiguration() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(null, new MemoryPasswordProvider(TestConstants.NULL_PWD), null, null);
        final KeyStore ks = ksc.getKeyStore();
        Assert.assertTrue(ks == null);
    }

    @Test
    public void loadNotEmptyConfigurationDeprecated() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE, TestConstants.KEYSTORE_PWD(),
                TestConstants.KEYSTORE_TYPE, null);
        final KeyStore ks = ksc.getKeyStore();
        Assert.assertTrue(ks != null);
    }

    @Test
    public void loadNotEmptyConfiguration() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE, new MemoryPasswordProvider(TestConstants.KEYSTORE_PWD()),
                TestConstants.KEYSTORE_TYPE, null);
        final KeyStore ks = ksc.getKeyStore();
        Assert.assertTrue(ks != null);
    }

    @Test
    public void returnTheSameKeyStoreAfterMultipleLoadsDeprecated() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE, TestConstants.KEYSTORE_PWD(),
                TestConstants.KEYSTORE_TYPE, null);
        final KeyStore ks = ksc.getKeyStore();
        final KeyStore ks2 = ksc.getKeyStore();
        Assert.assertTrue(ks == ks2);
    }

    @Test
    public void returnTheSameKeyStoreAfterMultipleLoads() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE, new MemoryPasswordProvider(TestConstants.KEYSTORE_PWD()),
                TestConstants.KEYSTORE_TYPE, null);
        final KeyStore ks = ksc.getKeyStore();
        final KeyStore ks2 = ksc.getKeyStore();
        Assert.assertTrue(ks == ks2);
    }

    @Test(expected = StoreConfigurationException.class)
    public void wrongPasswordDeprecated() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE, "wrongPassword!", null, null);
        ksc.getKeyStore();
    }

    @Test(expected = StoreConfigurationException.class)
    public void wrongPassword() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE, new MemoryPasswordProvider("wrongPassword!".toCharArray()), null, null);
        ksc.getKeyStore();
    }
}
