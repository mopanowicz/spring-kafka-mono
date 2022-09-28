package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSchemaInject(strings = {
        @JsonSchemaString(path="javaType", value="com.example.model.EventConfirmation")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventConfirmation {
    String eventId;
    Long eventReceived;

    public EventConfirmation(Event event) {
        this.eventId = event.getId();
        this.eventReceived = event.getReceived();
    }
}
