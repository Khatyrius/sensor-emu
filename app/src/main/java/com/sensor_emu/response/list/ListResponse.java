package com.sensor_emu.response.list;

import android.content.Context;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sensor_emu.model.AbstractConfigurableEntity;
import com.sensor_emu.model.AbstractEntity;
import com.sensor_emu.response.model.ListModel;
import com.sensor_emu.service.ConfigurableService;
import com.sensor_emu.service.MeasurementService;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
        "command",
        "var_list"
})
public class ListResponse {

    @JsonProperty("command")
    private final String command = "list response";

    @JsonProperty("var_list")
    private final List<ListModel> values;

    public ListResponse(final Context context) {
        this.values = new ArrayList<>();
        final MeasurementService measurementService = new MeasurementService(context);
        final ConfigurableService configurableService = new ConfigurableService(context);

        for (final Class<? extends AbstractEntity> clazz : measurementService.getClassList()) {
            this.values.add(new ListModel(clazz, context));
        }

        for (final Class<? extends AbstractConfigurableEntity> clazz :
                configurableService.getClassList()) {
            this.values.add(new ListModel(clazz, context));
        }
    }
}
