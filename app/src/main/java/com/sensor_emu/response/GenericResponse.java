package com.sensor_emu.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

public class GenericResponse {

    @JsonProperty("status")
    private final ResponseStatus status;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public GenericResponse(final ResponseStatus status) {
        this.status = status;
    }

    @JsonProperty("status")
    public ResponseStatus getStatus() {
        return status;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
