/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.configuration2.io;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@code DefaultFileSystem}. Note: This class tests only basic functionality. Other parts are tested by
 * actual access to configuration files in other test classes.
 */
public class TestDefaultFileSystem {
    /** The file system to be tested. */
    private DefaultFileSystem fileSystem;

    @BeforeEach
    public void setUp() throws Exception {
        fileSystem = new DefaultFileSystem();
    }

    /**
     * Tests the default logger.
     */
    @Test
    void testDefaultLogger() {
        assertNotNull(fileSystem.getLogger());
    }

    /**
     * Tests that an invalid output path causes an exception to be thrown when creating an ouput stream.
     */
    @Test
    void testGetOutputStreamInvalidPath() {
        final File file = new File("");
        assertThrows(ConfigurationException.class, () -> fileSystem.getOutputStream(file));
    }

    /**
     * Tests whether the logger can be changed.
     */
    @Test
    void testSetLogger() {
        final ConfigurationLogger log = new ConfigurationLogger(getClass());
        fileSystem.setLogger(log);
        assertSame(log, fileSystem.getLogger());
    }
}
