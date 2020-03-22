/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.riking.auto.commmon.inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.riking.auto.commmon.annotation.SourceFile;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Internal class for managing injection metadata.
 * Not intended for direct use in applications.
 */
@Slf4j
@Getter
public class InjectionMetadata {


    private final Class<?> targetClass;


    private final List<InjectedElement> injectedElements;

    private final SourceFile sourceFile;


    public InjectionMetadata(Class targetClass, List<InjectedElement> elements) {
        this.targetClass = targetClass;
        this.injectedElements = elements;
        this.sourceFile = (SourceFile) targetClass.getAnnotation(SourceFile.class);

    }

    public static boolean needsRefresh(@Nullable InjectionMetadata metadata, Class<?> clazz) {
        return (metadata == null || metadata.targetClass != clazz);
    }


}
