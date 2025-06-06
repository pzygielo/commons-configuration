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
package org.apache.commons.configuration2.event;

/**
 * <p>
 * An event class for reporting updates on a configuration object.
 * </p>
 * <p>
 * Event objects of this type are used for &quot;raw&quot; events, i.e. unfiltered modifications of any kind. A level
 * with semantically higher events (for example for property changes) may be built on top of this fundamental event mechanism.
 * </p>
 * <p>
 * Each event can contain the following data:
 * </p>
 * <ul>
 * <li>A source object, which is usually the configuration object that was modified.</li>
 * <li>The event's type. This is an object that corresponds to constant declarations in specific event classes. It
 * describes what exactly has happened.</li>
 * <li>If available, the name of the property whose modification caused the event.</li>
 * <li>If available, the value of the property that caused this event.</li>
 * <li>A flag whether this event was generated before or after the update of the source configuration. A modification of
 * a configuration typically causes two events: one event before and one event after the modification is performed. This
 * allows event listeners to react at the correct point of time.</li>
 * </ul>
 * <p>
 * The following standard events are generated by typical configuration implementations (the constants for the event
 * types are defined in this class:
 * </p>
 * <dl>
 * <dt>ADD_PROPERTY</dt>
 * <dd>This event is triggered for each call of the {@code addProperty()} method of a configuration object. It contains
 * the name of the property, to which new data is added, and the value object that is added to this property (this may
 * be an array or a list if multiple values are added).</dd>
 * <dt>SET_PROPERTY</dt>
 * <dd>Calling the {@code setProperty()} method triggers this event. The event object stores the name of the affected
 * property and its new value.</dd>
 * <dt>CLEAR_PROPERTY</dt>
 * <dd>If a property is removed from a configuration (by calling the {@code clearProperty()} method), an event of this
 * type is fired. In this case the event object only stores the name of the removed property, the value is
 * <strong>null</strong>.</dd>
 * <dt>CLEAR</dt>
 * <dd>This event is fired when the whole configuration is cleared. The corresponding event object contains no
 * additional data.</dd>
 * </dl>
 *
 * @since 1.3
 */
public class ConfigurationEvent extends Event {
    /**
     * Constant for the common super type of all configuration update events.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> ANY = new EventType<>(Event.ANY, "CONFIGURATION_UPDATE");

    /**
     * Constant for the event type for an add property operation.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> ADD_PROPERTY = new EventType<>(ANY, "ADD_PROPERTY");

    /**
     * Constant for the event type for a set property operation.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> SET_PROPERTY = new EventType<>(ANY, "SET_PROPERTY");

    /**
     * Constant for the event type for a clear property operation.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> CLEAR_PROPERTY = new EventType<>(ANY, "CLEAR_PROPERTY");

    /**
     * Constant for the event type for a clear operation.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> CLEAR = new EventType<>(ANY, "CLEAR");

    /**
     * Constant for the common base event type for all hierarchical update events. Events derived from this type are
     * generated by some specific methods of hierarchical configurations.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> ANY_HIERARCHICAL = new EventType<>(ANY, "HIERARCHICAL");

    /**
     * Constant for the event type for an add nodes operation.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> ADD_NODES = new EventType<>(ANY_HIERARCHICAL, "ADD_NODES");

    /**
     * Constant for the event type for a clear tree operation.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> CLEAR_TREE = new EventType<>(ANY_HIERARCHICAL, "CLEAR_TREE");

    /**
     * Constant for the event type indicating a change on a sub configuration.
     *
     * @since 2.0
     */
    public static final EventType<ConfigurationEvent> SUBNODE_CHANGED = new EventType<>(ANY_HIERARCHICAL, "SUBNODE_CHANGED");

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 20140703L;

    /** Stores the property name. */
    private final String propertyName;

    /** Stores the property value. */
    private final Object propertyValue;

    /** Stores the before update flag. */
    private final boolean beforeUpdate;

    /**
     * Creates a new instance of {@code ConfigurationEvent} and initializes it.
     *
     * @param source the event source
     * @param type the event's type
     * @param propertyName the name of the affected property
     * @param propertyValue the value of the affected property
     * @param beforeUpdate the before update flag
     */
    public ConfigurationEvent(final Object source, final EventType<? extends ConfigurationEvent> type, final String propertyName, final Object propertyValue,
        final boolean beforeUpdate) {
        super(source, type);
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.beforeUpdate = beforeUpdate;
    }

    /**
     * Gets the name of the affected property. This can be <strong>null</strong> if no property change has lead to this event.
     *
     * @return the name of the property
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Gets the value of the affected property if available.
     *
     * @return the value of the property; can be <strong>null</strong>
     */
    public Object getPropertyValue() {
        return propertyValue;
    }

    /**
     * Returns a flag if this event was generated before or after an update.
     *
     * @return <strong>true</strong> if this event was generated before an update; <strong>false</strong> otherwise
     */
    public boolean isBeforeUpdate() {
        return beforeUpdate;
    }
}
