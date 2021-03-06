/*
 * This file is part of ClassGraph.
 *
 * Author: Michael J. Simons
 *
 * Hosted at: https://github.com/classgraph/classgraph
 *
 * --
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Luke Hutchison
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.classgraph.features;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.classgraph.ClassLoaderHandler;
import io.github.classgraph.ScanSpec;
import io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
import io.github.classgraph.utils.ClasspathOrder;
import io.github.classgraph.utils.LogNode;

import org.assertj.core.api.iterable.Extractor;
import org.junit.Test;

public class ClassLoaderHandlerSPI {
    @Test
    public void shouldLoadAdditionalClassLoaderThroughSPI() {
        assertThat(ClassLoaderHandlerRegistry.DEFAULT_CLASS_LOADER_HANDLERS).extracting(
            new Extractor<ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry, Object>() {

                @Override
                public Object extract(ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry input) {
                    return input.classLoaderHandlerClass;
                }
            }
        ).contains(AFancyClassLoaderHandler.class);
    }

    public static class AFancyClassLoaderHandler implements ClassLoaderHandler {

        public AFancyClassLoaderHandler() {
        }

        @Override
        public String[] handledClassLoaders() {
            return new String[0];
        }

        @Override
        public ClassLoader getEmbeddedClassLoader(ClassLoader outerClassLoaderInstance) {
            return null;
        }

        @Override public DelegationOrder getDelegationOrder(ClassLoader classLoaderInstance) {
            return null;
        }

        @Override
        public void handle(ScanSpec scanSpec, ClassLoader classLoader, ClasspathOrder classpathOrderOut, LogNode log) {
        }
    }
}
