package com.sensor_emu.response.get;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sensor_emu.model.AbstractEntity;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
        "command",
        "values"
})
public class GetResponse {

    @JsonProperty("command")
    private final String command = "get response";

    @JsonProperty("values")
    private final List<AbstractEntity> values;

    public GetResponse(final List<AbstractEntity> values) {
        this.values = values;
    }

    public List<AbstractEntity> getValues() {
        return values;
    }
}
