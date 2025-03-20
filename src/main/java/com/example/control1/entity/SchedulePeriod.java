package com.example.control1.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Entity class representing a schedule period.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule_periods")
public class SchedulePeriod {

    /**
     * ID of the schedule period.
     */
    @Id
    @Column(length = 32)
    private String id;

    /**
     * ID of the schedule period.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "slot_id", nullable = false)
    private ScheduleSlot slot;

    /**
     * Schedule associated with the schedule period.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    /**
     * Type of the slot.
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "slot_type", nullable = false, length = 20)
    private SlotType slotType;

    /**
     * Administrator of the schedule period.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", nullable = false)
    private Employee administrator;

    /**
     * Executor of the schedule period.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executor_id")
    private Employee executor;

    /**
     * Enumeration of possible slot types.
     * Mapped to custom string values.
     */
    public enum SlotType {
        @Schema(description = "LOCAL")
        LOCAL("LOCAL"),
        @Schema(description = "FROM HOME")
        FROM_HOME("FROM HOME"),
        @Schema(description = "UNDEFINED")
        UNDEFINED("UNDEFINED");

        private final String value;

        SlotType(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the SlotType.
         * Used for serialization.
         *
         * @return value of the SlotType
         */
        @JsonValue
        public String getValue() {
            return value;
        }

        /**
         * Gets the SlotType enum from the given value.
         * Used for deserialization.
         *
         * @param value value
         * @return field enum
         * @throws IllegalArgumentException if the value is invalid
         */
        @JsonCreator
        public static SlotType getItem(String value) {
            for (SlotType item : values()) {
                if (Objects.equals(item.getValue(), value)) {
                    return item;
                }
            }

            if (value != null) throw new IllegalArgumentException(String.format("Invalid enum value: %s", value));
            return null;
        }
    }
}
