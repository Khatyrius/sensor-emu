package com.sensor_emu;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor_emu.model.AbstractEntity;
import com.sensor_emu.response.ResponseStatus;
import com.sensor_emu.response.delete.DeleteResponse;
import com.sensor_emu.response.get.GetResponse;
import com.sensor_emu.response.set.SetResponse;
import com.sensor_emu.utils.TestUtil;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ResponseInstrumentedTest {

    private ObjectMapper objectMapper;

    @Before
    public void before() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldCreateSetResponseJsonWithStatusOK() throws JsonProcessingException {
        // given
        final SetResponse setResponse = new SetResponse(ResponseStatus.OK);

        // when
        final String actual = objectMapper.writeValueAsString(setResponse);

        // then
        final String expected = "{\"command\":\"set response\",\"status\":\"OK\"}";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateSetResponseJsonWithStatusERROR() throws JsonProcessingException {
        // given
        final SetResponse setResponse = new SetResponse(ResponseStatus.ERROR);

        // when
        final String actual = objectMapper.writeValueAsString(setResponse);

        // then
        final String expected = "{\"command\":\"set response\",\"status\":\"ERROR\"}";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateDeleteResponseJsonWithStatusOK() throws JsonProcessingException {
        // given
        final DeleteResponse deleteResponse = new DeleteResponse(ResponseStatus.OK);

        // when
        final String actual = objectMapper.writeValueAsString(deleteResponse);

        // then
        final String expected = "{\"command\":\"delete response\",\"status\":\"OK\"}";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateDeleteResponseJsonWithStatusERROR() throws JsonProcessingException {
        // given
        final DeleteResponse deleteResponse = new DeleteResponse(ResponseStatus.ERROR);

        // when
        final String actual = objectMapper.writeValueAsString(deleteResponse);

        // then
        final String expected = "{\"command\":\"delete response\",\"status\":\"ERROR\"}";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateGetResponseConfigurableJson() throws JsonProcessingException {
        // given
        final AbstractEntity humidityFrequency1 = TestUtil
                .createHumidityFrequency(1L, 123, 456);
        final AbstractEntity humidityFrequency2 = TestUtil
                .createHumidityFrequency(2L, 789, 1000);

        final List<AbstractEntity> values = Arrays
                .asList(humidityFrequency1, humidityFrequency2);

        final GetResponse getResponseConfigurable = new GetResponse(values);

        // when
        final String actual = objectMapper.writeValueAsString(getResponseConfigurable);

        // then
        final String expected = "{\"command\":\"get response\",\"values\":[{\"ts\":456,\"value\":123},{\"ts\":1000,\"value\":789}]}";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateGetResponseMeasurementJson() throws JsonProcessingException {
        // given
        final AbstractEntity humidity1 = TestUtil.createHumidity(1L, 123, 456);
        final AbstractEntity humidity2 = TestUtil.createHumidity(2L, 789, 1000);

        final List<AbstractEntity> values = Arrays
                .asList(humidity1, humidity2);

        final GetResponse getResponseMeasurement = new GetResponse(values);

        // when
        final String actual = objectMapper.writeValueAsString(getResponseMeasurement);

        // then
        final String expected = "{\"command\":\"get response\",\"values\":[{\"ts\":456,\"value\":123.0},{\"ts\":1000,\"value\":789.0}]}";
        assertEquals(expected, actual);
    }
}
