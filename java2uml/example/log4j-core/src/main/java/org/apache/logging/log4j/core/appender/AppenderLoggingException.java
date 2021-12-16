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
package org.apache.logging.log4j.core.appender;

import org.apache.logging.log4j.LoggingException;

/**
 * Thrown from an appender when a log event could not be written. Appenders should not thrown an exception if an error
 * occurs that does <em>not</em> stop the event from being successfully written. Those types of errors should be logged
 * using the {@link org.apache.logging.log4j.status.StatusLogger}. Appenders should only throw exceptions when an error
 * prevents an event from being written. Appenders <em>must</em> throw an exception in this case so that error-handling
 * features like the {@link FailoverAppender} work properly.
 *
 * Also note that appenders <em>must</em> provide a way to suppress exceptions when the user desires and abide by
 * that instruction. See {@link org.apache.logging.log4j.core.Appender#ignoreExceptions()}, which is the standard
 * way to do this.
 */
public class AppenderLoggingException extends LoggingException {

    private static final long serialVersionUID = 6545990597472958303L;

    /**
     * Construct an exception with a message.
     *
     * @param message The reason for the exception
     */
    public AppenderLoggingException(final String message) {
        super(message);
    }

    /**
     * Construct an exception with a message and underlying cause.
     *
     * @param message The reason for the exception
     * @param cause The underlying cause of the exception
     */
    public AppenderLoggingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Construct an exception with an underlying cause.
     *
     * @param cause The underlying cause of the exception
     */
    public AppenderLoggingException(final Throwable cause) {
        super(cause);
    }
}
