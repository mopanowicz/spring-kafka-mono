package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.avro.reflect.Nullable;

@Data
@NoArgsConstructor
@JsonSchemaInject(strings = {
        @JsonSchemaString(path="javaType", value="com.example.model.Event")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    String id;
    Long sent;
    @Nullable
    Long saved;
    @Nullable
    Long received;
    @Nullable
    String cargo;
}
