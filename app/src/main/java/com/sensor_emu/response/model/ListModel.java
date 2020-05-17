package com.sensor_emu.response.model;

import android.content.Context;
import androidx.room.Ignore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sensor_emu.model.AbstractEntity;
import com.sensor_emu.model.configurable.EnergyFrequency;
import com.sensor_emu.model.configurable.HumidityFrequency;
import com.sensor_emu.model.configurable.PressureFrequency;
import com.sensor_emu.model.configurable.TemperatureFrequency;
import com.sensor_emu.model.measurement.Energy;
import com.sensor_emu.model.measurement.Humidity;
import com.sensor_emu.model.measurement.Pressure;
import com.sensor_emu.model.measurement.Temperature;
import com.sensor_emu.response.VariableType;
import com.sensor_emu.service.ConfigurableService;
import com.sensor_emu.service.MeasurementService;
import java.util.Objects;

@JsonPropertyOrder({
        "variable",
        "type",
        "values",
        "ts_first",
        "ts_last"
})
public class ListModel {

    @Ignore
    @JsonProperty("variable")
    private String variable;

    @Ignore
    @JsonProperty("type")
    private VariableType type;

    @Ignore
    @JsonProperty("ts_first")
    private long ts_first;

    @Ignore
    @JsonProperty("ts_last")
    private long ts_last;

    @Ignore
    @JsonProperty("values")
    private int values;

    public ListModel(final Class<? extends AbstractEntity> clazz, final Context context) {
        final MeasurementService measurementService = new MeasurementService(context);
        final ConfigurableService configurableService = new ConfigurableService(context);

        if (clazz == Energy.class) {
            this.variable = "ENERGY";
            this.type = VariableType.RO;
            this.ts_first = measurementService.getEnergyFirstTimestamp();
            this.ts_last = measurementService.getEnergyLastTimestamp();
            this.values = measurementService.getEnergyList().size();

        } else if (clazz == EnergyFrequency.class) {
            this.variable = "ENERGY_F";
            this.type = VariableType.RW;
            this.ts_first = configurableService.getEnergyFrequencyFirstTimestamp();
            this.ts_last = configurableService.getEnergyFrequencyLastTimestamp();
            this.values = configurableService.getEnergyFrequencyList().size();

        } else if (clazz == Humidity.class) {
            this.variable = "HUM";
            this.type = VariableType.RO;
            this.ts_first = measurementService.getHumidityFirstTimestamp();
            this.ts_last = measurementService.getHumidityLastTimestamp();
            this.values = measurementService.getHumidityList().size();

        } else if (clazz == HumidityFrequency.class) {
            this.variable = "HUM_F";
            this.type = VariableType.RW;
            this.ts_first = configurableService.getHumidityFrequencyFirstTimestamp();
            this.ts_last = configurableService.getHumidityFrequencyLastTimestamp();
            this.values = configurableService.getHumidityFrequencyList().size();

        } else if (clazz == Pressure.class) {
            this.variable = "PRESS";
            this.type = VariableType.RO;
            this.ts_first = measurementService.getPressureFirstTimestamp();
            this.ts_last = measurementService.getPressureLastTimestamp();
            this.values = measurementService.getPressureList().size();

        } else if (clazz == PressureFrequency.class) {
            this.variable = "PRESS_F";
            this.type = VariableType.RW;
            this.ts_first = configurableService.getPressureFrequencyFirstTimestamp();
            this.ts_last = configurableService.getPressureFrequencyLastTimestamp();
            this.values = configurableService.getPressureFrequencyList().size();

        } else if (clazz == Temperature.class) {
            this.variable = "TEMP";
            this.type = VariableType.RO;
            this.ts_first = measurementService.getTemperatureFirstTimestamp();
            this.ts_last = measurementService.getTemperatureLastTimestamp();
            this.values = measurementService.getTemperatureList().size();

        } else if (clazz == TemperatureFrequency.class) {
            this.variable = "TEMP_F";
            this.type = VariableType.RW;
            this.ts_first = configurableService.getTemperatureFrequencyFirstTimestamp();
            this.ts_last = configurableService.getTemperatureFrequencyLastTimestamp();
            this.values = configurableService.getTemperatureFrequencyList().size();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ListModel listModel = (ListModel) o;
        return ts_first == listModel.ts_first &&
                ts_last == listModel.ts_last &&
                values == listModel.values &&
                Objects.equals(variable, listModel.variable) &&
                type == listModel.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, type, ts_first, ts_last, values);
    }
}
