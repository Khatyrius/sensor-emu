package com.sensor_emu.response.delete;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sensor_emu.response.GenericResponse;
import com.sensor_emu.response.ResponseStatus;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
        "command",
        "status"
})
public class DeleteResponse extends GenericResponse {

    @JsonProperty("command")
    private final String command = "delete response";

    public DeleteResponse(final ResponseStatus status) {
        super(status);
    }
}
