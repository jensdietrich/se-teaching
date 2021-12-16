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
package org.apache.logging.log4j.core.appender.rolling;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.appender.ConfigurationFactoryData;
import org.apache.logging.log4j.core.appender.ManagerFactory;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.logging.log4j.core.util.NullOutputStream;

/**
 * Extends RollingFileManager but instead of using a buffered output stream, this class uses a {@code ByteBuffer} and a
 * {@code RandomAccessFile} to do the I/O.
 */
public class RollingRandomAccessFileManager extends RollingFileManager {
    /**
     * The default buffer size.
     */
    public static final int DEFAULT_BUFFER_SIZE = 256 * 1024;

    private static final RollingRandomAccessFileManagerFactory FACTORY = new RollingRandomAccessFileManagerFactory();

    private RandomAccessFile randomAccessFile;
    private final ThreadLocal<Boolean> isEndOfBatch = new ThreadLocal<>();

    @Deprecated
    public RollingRandomAccessFileManager(final LoggerContext loggerContext, final RandomAccessFile raf,
            final String fileName, final String pattern, final OutputStream os, final boolean append,
            final boolean immediateFlush, final int bufferSize, final long size, final long time,
            final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI,
            final Layout<? extends Serializable> layout, final boolean writeHeader) {
        this(loggerContext, raf, fileName, pattern, os, append, immediateFlush, bufferSize, size, time, policy, strategy, advertiseURI,
               layout, null, null, null, writeHeader);
    }

    /**
     * @since 2.8.3
     */
    public RollingRandomAccessFileManager(final LoggerContext loggerContext, final RandomAccessFile raf,
            final String fileName, final String pattern, final OutputStream os, final boolean append,
            final boolean immediateFlush, final int bufferSize, final long size, final long initialTime,
            final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI,
            final Layout<? extends Serializable> layout,
            final String filePermissions, final String fileOwner, final String fileGroup,
            final boolean writeHeader) {
        super(loggerContext, fileName, pattern, os, append, false, size, initialTime, policy, strategy, advertiseURI,
                layout, filePermissions, fileOwner, fileGroup, writeHeader, ByteBuffer.wrap(new byte[bufferSize]));
        this.randomAccessFile = raf;
        isEndOfBatch.set(Boolean.FALSE);
        writeHeader();
    }

    /**
     * Writes the layout's header to the file if it exists.
     */
    private void writeHeader() {
        if (layout == null) {
            return;
        }
        final byte[] header = layout.getHeader();
        if (header == null) {
            return;
        }
        try {
            if (randomAccessFile != null && randomAccessFile.length() == 0) {
                // write to the file, not to the buffer: the buffer may not be empty
                randomAccessFile.write(header, 0, header.length);
            }
        } catch (final IOException e) {
            logError("Unable to write header", e);
        }
    }

    public static RollingRandomAccessFileManager getRollingRandomAccessFileManager(final String fileName,
            final String filePattern, final boolean isAppend, final boolean immediateFlush, final int bufferSize,
            final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI,
            final Layout<? extends Serializable> layout, final String filePermissions, final String fileOwner, final String fileGroup,
            final Configuration configuration) {
        if (strategy instanceof DirectWriteRolloverStrategy && fileName != null) {
            LOGGER.error("The fileName attribute must not be specified with the DirectWriteRolloverStrategy");
            return null;
        }
        final String name = fileName == null ? filePattern : fileName;
        return narrow(RollingRandomAccessFileManager.class, getManager(name, new FactoryData(fileName, filePattern, isAppend,
                immediateFlush, bufferSize, policy, strategy, advertiseURI, layout,
                filePermissions, fileOwner, fileGroup, configuration), FACTORY));
    }

    public Boolean isEndOfBatch() {
        return isEndOfBatch.get();
    }

    public void setEndOfBatch(final boolean endOfBatch) {
        this.isEndOfBatch.set(Boolean.valueOf(endOfBatch));
    }

    // override to make visible for unit tests
    @Override
    protected synchronized void write(final byte[] bytes, final int offset, final int length,
            final boolean immediateFlush) {
        super.write(bytes, offset, length, immediateFlush);
    }

    @Override
    protected synchronized void writeToDestination(final byte[] bytes, final int offset, final int length) {
        try {
            if (randomAccessFile == null) {
                final String fileName = getFileName();
                final File file = new File(fileName);
                FileUtils.makeParentDirs(file);
                createFileAfterRollover(fileName);
            }
            randomAccessFile.write(bytes, offset, length);
            size += length;
        } catch (final IOException ex) {
            final String msg = "Error writing to RandomAccessFile " + getName();
            throw new AppenderLoggingException(msg, ex);
        }
    }

    @Override
    protected void createFileAfterRollover() throws IOException {
        createFileAfterRollover(getFileName());
    }

    private void createFileAfterRollover(final String fileName) throws IOException {
        this.randomAccessFile = new RandomAccessFile(fileName, "rw");
        if (isAppend()) {
            randomAccessFile.seek(randomAccessFile.length());
        }
        writeHeader();
    }

    @Override
    public synchronized void flush() {
        flushBuffer(byteBuffer);
    }

    @Override
	public synchronized boolean closeOutputStream() {
		flush();
		if (randomAccessFile != null) {
			try {
				randomAccessFile.close();
				return true;
			} catch (final IOException e) {
				logError("Unable to close RandomAccessFile", e);
				return false;
			}
		}
		return true;
	}

    /**
     * Returns the buffer capacity.
     *
     * @return the buffer size
     */
    @Override
    public int getBufferSize() {
        return byteBuffer.capacity();
    }

    /**
     * Factory to create a RollingRandomAccessFileManager.
     */
    private static class RollingRandomAccessFileManagerFactory implements
            ManagerFactory<RollingRandomAccessFileManager, FactoryData> {

        /**
         * Create the RollingRandomAccessFileManager.
         *
         * @param name The name of the entity to manage.
         * @param data The data required to create the entity.
         * @return a RollingFileManager.
         */
        @Override
        public RollingRandomAccessFileManager createManager(final String name, final FactoryData data) {
            File file = null;
            long size = 0;
            long time = System.currentTimeMillis();
            RandomAccessFile raf = null;
            if (data.fileName != null) {
                file = new File(name);

                if (!data.append) {
                    file.delete();
                }
                size = data.append ? file.length() : 0;
                if (file.exists()) {
                    time = file.lastModified();
                }
                try {
                    FileUtils.makeParentDirs(file);
                    raf = new RandomAccessFile(name, "rw");
                    if (data.append) {
                        final long length = raf.length();
                        LOGGER.trace("RandomAccessFile {} seek to {}", name, length);
                        raf.seek(length);
                    } else {
                        LOGGER.trace("RandomAccessFile {} set length to 0", name);
                        raf.setLength(0);
                    }
                } catch (final IOException ex) {
                    LOGGER.error("Cannot access RandomAccessFile " + ex, ex);
                    if (raf != null) {
                        try {
                            raf.close();
                        } catch (final IOException e) {
                            LOGGER.error("Cannot close RandomAccessFile {}", name, e);
                        }
                    }
                    return null;
                }
            }
            final boolean writeHeader = !data.append || file == null || !file.exists();

            final RollingRandomAccessFileManager rrm = new RollingRandomAccessFileManager(data.getLoggerContext(), raf, name, data.pattern,
                    NullOutputStream.getInstance(), data.append, data.immediateFlush, data.bufferSize, size, time, data.policy,
                    data.strategy, data.advertiseURI, data.layout, data.filePermissions, data.fileOwner, data.fileGroup, writeHeader);
            if (rrm.isAttributeViewEnabled()) {
                rrm.defineAttributeView(file.toPath());
            }
            return rrm;
        }
    }

    /**
     * Factory data.
     */
    private static class FactoryData extends ConfigurationFactoryData {
        private final String fileName;
        private final String pattern;
        private final boolean append;
        private final boolean immediateFlush;
        private final int bufferSize;
        private final TriggeringPolicy policy;
        private final RolloverStrategy strategy;
        private final String advertiseURI;
        private final Layout<? extends Serializable> layout;
        private final String filePermissions;
        private final String fileOwner;
        private final String fileGroup;

        /**
         * Create the data for the factory.
         *
         * @param fileName The file name.
         * @param pattern The pattern.
         * @param append The append flag.
         * @param immediateFlush
         * @param bufferSize
         * @param policy
         * @param strategy
         * @param advertiseURI
         * @param layout
         * @param filePermissions File permissions
         * @param fileOwner File owner
         * @param fileGroup File group
         * @param configuration
         */
        public FactoryData(final String fileName, final String pattern, final boolean append, final boolean immediateFlush,
                final int bufferSize, final TriggeringPolicy policy, final RolloverStrategy strategy,
                final String advertiseURI, final Layout<? extends Serializable> layout,
                final String filePermissions, final String fileOwner, final String fileGroup,
                final Configuration configuration) {
            super(configuration);
            this.fileName = fileName;
            this.pattern = pattern;
            this.append = append;
            this.immediateFlush = immediateFlush;
            this.bufferSize = bufferSize;
            this.policy = policy;
            this.strategy = strategy;
            this.advertiseURI = advertiseURI;
            this.layout = layout;
            this.filePermissions = filePermissions;
            this.fileOwner = fileOwner;
            this.fileGroup = fileGroup;
        }

        public String getPattern() {
            return pattern;
        }

        public TriggeringPolicy getTriggeringPolicy() {
            return this.policy;
        }

        public RolloverStrategy getRolloverStrategy() {
            return this.strategy;
        }

    }

    @Override
    public void updateData(final Object data) {
        final FactoryData factoryData = (FactoryData) data;
        setRolloverStrategy(factoryData.getRolloverStrategy());
        setTriggeringPolicy(factoryData.getTriggeringPolicy());
        setPatternProcessor(new PatternProcessor(factoryData.getPattern(), getPatternProcessor()));
    }
}
