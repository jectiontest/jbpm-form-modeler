/**
 * Copyright (C) 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jbpm.formModeler.api.processing;


import org.jbpm.formModeler.api.model.BindingSource;
import org.jbpm.formModeler.api.model.FieldType;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface BindingManager {
    PropertyDefinition getPropertyDefinition(FieldType type) throws Exception;
    PropertyDefinition getPropertyDefinition(String propertyName, String className) throws Exception;
    PropertyDefinition getPropertyDefinition(String propertyName, Class clazz) throws Exception;
    boolean hasProperty(Object obj, String propName);
    Object getPropertyValue(Object obj, String propName);
    void setPropertyValue(Object destination, String propName, Object value) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    public Map getBindingFields(BindingSource source);
}
