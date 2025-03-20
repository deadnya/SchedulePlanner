package com.example.control1.dto.schedule.period;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * Data transfer object for sorting schedule periods.
 *
 * @param field field by which to sort
 * @param direction direction of the sort (ascending or descending)
 */
public record SchedulePeriodSort(
    Field field,
    Direction direction
) {

    /**
     * Enum representing the fields by which schedule periods can be sorted.
     * Mapped to lowerCamelCase in JSON.
     * Provides methods for getting the database path of the field.
     */
    public enum Field {

        ID("id"),
        SLOT_ID("slotId"),
        TEMPLATE_ID("templateId"),
        TEMPLATE_CREATION_DATE("templateCreationDate"),
        TEMPLATE_TYPE("templateType"),
        BEGIN_TIME("beginTime"),
        END_TIME("endTime"),
        SCHEDULE_ID("scheduleId"),
        SCHEDULE_NAME("scheduleName"),
        SLOT_TYPE("slotType"),
        ADMINISTRATOR_ID("administratorId"),
        ADMINISTRATOR_EMPLOYEE_NAME("administratorEmployeeName"),
        ADMINISTRATOR_EMPLOYEE_STATUS("administratorStatus"),
        ADMINISTRATOR_EMPLOYEE_POSITION("administratorPosition"),
        EXECUTOR_ID("executorId"),
        EXECUTOR_EMPLOYEE_NAME("executorEmployeeName"),
        EXECUTOR_EMPLOYEE_STATUS("executorStatus"),
        EXECUTOR_EMPLOYEE_POSITION("executorPosition");

        private final String value;

        Field(String value) {
            this.value = value;
        }

        /**
         * Gets the database path for the given field.
         *
         * @param field field
         * @return database path
         */
        public static String getDatabasePath(Field field) {
            return switch (field) {
                case SLOT_ID -> "slot.id";
                case TEMPLATE_ID -> "slot.scheduleTemplate.id";
                case TEMPLATE_CREATION_DATE -> "slot.scheduleTemplate.creationDate";
                case TEMPLATE_TYPE -> "slot.scheduleTemplate.";
                case BEGIN_TIME -> "slot.beginTime";
                case END_TIME -> "slot.endTime";
                case SCHEDULE_ID -> "schedule.id";
                case SCHEDULE_NAME -> "schedule.scheduleName";
                case SLOT_TYPE -> "slotType";
                case ADMINISTRATOR_ID -> "administrator.id";
                case ADMINISTRATOR_EMPLOYEE_NAME -> "administrator.employeeName";
                case ADMINISTRATOR_EMPLOYEE_STATUS -> "administrator.status";
                case ADMINISTRATOR_EMPLOYEE_POSITION -> "administrator.position";
                case EXECUTOR_ID -> "executor";
                case EXECUTOR_EMPLOYEE_NAME -> "executor.employeeName";
                case EXECUTOR_EMPLOYEE_STATUS -> "executor.status";
                case EXECUTOR_EMPLOYEE_POSITION -> "executor.position";
                default -> "id";
            };
        }

        /**
         * Gets the value of the field.
         * Used for serialization.
         *
         * @return value of the field
         */
        @JsonValue
        public String getValue() {
            return value;
        }

        /**
         * Gets the field enum from the given value.
         * Used for deserialization.
         *
         * @param value the value
         * @return field enum
         * @throws IllegalArgumentException if the value is invalid
         */
        @JsonCreator
        public static SchedulePeriodSort.Field getItem(String value) {
            for (SchedulePeriodSort.Field item : values()) {
                if (Objects.equals(item.getValue(), value)) {
                    return item;
                }
            }

            if (value != null) throw new IllegalArgumentException(String.format("Invalid enum value: %s", value));
            return null;
        }
    }

    /**
     * Enum representing the direction of the sort.
     */
    public enum Direction {
        ASC,
        DESC
    }
}
