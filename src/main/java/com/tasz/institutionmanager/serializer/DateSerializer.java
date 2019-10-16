package com.tasz.institutionmanager.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateSerializer extends JsonSerializer<Date> {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd.");

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        final String formattedDate = dateFormat.format(date);
        jsonGenerator.writeString(formattedDate);
    }
}
