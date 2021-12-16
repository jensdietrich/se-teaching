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

import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.junit.Assert;
import org.junit.Test;

public class SslConfigurationTest {

    private static final String TLS_TEST_HOST = "login.yahoo.com";
    private static final int TLS_TEST_PORT = 443;

    public static SslConfiguration createTestSslConfigurationResourcesDeprecated() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE_RESOURCE,
                TestConstants.KEYSTORE_PWD(), TestConstants.KEYSTORE_TYPE, null);
        final TrustStoreConfiguration tsc = new TrustStoreConfiguration(TestConstants.TRUSTSTORE_FILE_RESOURCE,
                TestConstants.TRUSTSTORE_PWD(), null, null);
        return SslConfiguration.createSSLConfiguration(null, ksc, tsc);
    }

    public static SslConfiguration createTestSslConfigurationResources() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE_RESOURCE,
                new MemoryPasswordProvider(TestConstants.KEYSTORE_PWD()), TestConstants.KEYSTORE_TYPE, null);
        final TrustStoreConfiguration tsc = new TrustStoreConfiguration(TestConstants.TRUSTSTORE_FILE_RESOURCE,
                new MemoryPasswordProvider(TestConstants.TRUSTSTORE_PWD()), null, null);
        return SslConfiguration.createSSLConfiguration(null, ksc, tsc);
    }

    public static SslConfiguration createTestSslConfigurationFilesDeprecated() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE,
                TestConstants.KEYSTORE_PWD(), TestConstants.KEYSTORE_TYPE, null);
        final TrustStoreConfiguration tsc = new TrustStoreConfiguration(TestConstants.TRUSTSTORE_FILE,
                TestConstants.TRUSTSTORE_PWD(), null, null);
        return SslConfiguration.createSSLConfiguration(null, ksc, tsc);
    }

    public static SslConfiguration createTestSslConfigurationFiles() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE,
                new MemoryPasswordProvider(TestConstants.KEYSTORE_PWD()), TestConstants.KEYSTORE_TYPE, null);
        final TrustStoreConfiguration tsc = new TrustStoreConfiguration(TestConstants.TRUSTSTORE_FILE,
                new MemoryPasswordProvider(TestConstants.TRUSTSTORE_PWD()), null, null);
        return SslConfiguration.createSSLConfiguration(null, ksc, tsc);
    }

    @Test
    public void testGettersFromScratchFiles() throws StoreConfigurationException {
        Assert.assertNotNull(createTestSslConfigurationFiles().getProtocol());
        Assert.assertNotNull(createTestSslConfigurationFiles().getKeyStoreConfig());
        Assert.assertNotNull(createTestSslConfigurationFiles().getSslContext());
        Assert.assertNotNull(createTestSslConfigurationFiles().getSslSocketFactory());
        Assert.assertNotNull(createTestSslConfigurationFiles().getTrustStoreConfig());
    }

    @Test
    public void testGettersFromScratchResources() throws StoreConfigurationException {
        Assert.assertNotNull(createTestSslConfigurationResources().getProtocol());
        Assert.assertNotNull(createTestSslConfigurationResources().getKeyStoreConfig());
        Assert.assertNotNull(createTestSslConfigurationResources().getSslContext());
        Assert.assertNotNull(createTestSslConfigurationResources().getSslSocketFactory());
        Assert.assertNotNull(createTestSslConfigurationResources().getTrustStoreConfig());
    }

    @Test
    public void equals() {
        Assert.assertEquals(SslConfiguration.createSSLConfiguration(null, null, null), SslConfiguration.createSSLConfiguration(null, null, null));
    }

    @Test
        public void emptyConfigurationDoesntCauseNullSSLSocketFactory() {
        final SslConfiguration sc = SslConfiguration.createSSLConfiguration(null, null, null);
        final SSLSocketFactory factory = sc.getSslSocketFactory();
        Assert.assertNotNull(factory);
    }

    @Test
    public void emptyConfigurationHasDefaultTrustStore() throws IOException {
        final SslConfiguration sc = SslConfiguration.createSSLConfiguration(null, null, null);
        final SSLSocketFactory factory = sc.getSslSocketFactory();
        try {
            try (final SSLSocket clientSocket = (SSLSocket) factory.createSocket(TLS_TEST_HOST, TLS_TEST_PORT)) {
                Assert.assertNotNull(clientSocket);
                clientSocket.close();
            }
        } catch (final UnknownHostException offline) {
            // this exception is thrown on Windows when offline
        }
    }

    @Test
    public void connectionFailsWithoutValidServerCertificate() throws IOException, StoreConfigurationException {
        final TrustStoreConfiguration tsc = new TrustStoreConfiguration(TestConstants.TRUSTSTORE_FILE,
                new MemoryPasswordProvider(TestConstants.NULL_PWD), null, null);
        final SslConfiguration sc = SslConfiguration.createSSLConfiguration(null, null, tsc);
        final SSLSocketFactory factory = sc.getSslSocketFactory();
        try {
            try (final SSLSocket clientSocket = (SSLSocket) factory.createSocket(TLS_TEST_HOST, TLS_TEST_PORT)) {
                try (final OutputStream os = clientSocket.getOutputStream()) {
                    os.write("GET config/login_verify2?".getBytes());
                    Assert.fail("Expected IOException");
                } catch (final IOException e) {
                    // Expected, do nothing.
                }
            }
        } catch (final UnknownHostException offline) {
            // this exception is thrown on Windows when offline
        }
    }

    @Test
    public void loadKeyStoreWithoutPassword() throws StoreConfigurationException {
        final KeyStoreConfiguration ksc = new KeyStoreConfiguration(TestConstants.KEYSTORE_FILE,
                new MemoryPasswordProvider(TestConstants.NULL_PWD), null, null);
        final SslConfiguration sslConf = SslConfiguration.createSSLConfiguration(null, ksc, null);
        final SSLSocketFactory factory = sslConf.getSslSocketFactory();
        Assert.assertNotNull(factory);
    }
}
