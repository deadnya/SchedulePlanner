package com.example.control1.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule_periods")
public class SchedulePeriod {

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

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static SlotType getItem(String value) {
            for (SlotType item : values()) {
                if (Objects.equals(item.getValue(), value)) {
                    return item;
                }
            }

            throw new IllegalArgumentException(String.format("Invalid enum value: %s", value));
        }
    }

    @Id
    @Column(length = 32)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "slot_id", nullable = false)
    private ScheduleSlot scheduleSlot;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id", nullable = false)
    @JsonManagedReference
    private Schedule schedule;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "slot_type", nullable = false, length = 20)
    private SlotType slotType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", nullable = false)
    private Employee administrator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executor_id")
    private Employee executor;
}
