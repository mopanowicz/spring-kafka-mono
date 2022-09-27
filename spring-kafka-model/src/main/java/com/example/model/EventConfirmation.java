package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSchemaInject(strings = {
        @JsonSchemaString(path="javaType", value="com.example.model.EventConfirmation")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventConfirmation {
    @Indexed
    String eventUuid;
    Long eventReceived;
}
