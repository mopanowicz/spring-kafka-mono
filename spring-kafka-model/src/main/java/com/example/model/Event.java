package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.avro.reflect.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSchemaInject(strings = {
        @JsonSchemaString(path="javaType", value="com.example.model.Event")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    @Indexed
    String uuid;
    Long sent;
    @Nullable
    Cargo[] cargo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Cargo {
        @Nullable
        Double d;
        @Nullable
        Integer i;
        @Nullable
        String s;
    }
}
