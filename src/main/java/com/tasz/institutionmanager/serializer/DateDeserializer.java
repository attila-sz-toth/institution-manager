package com.tasz.institutionmanager.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import static com.tasz.institutionmanager.serializer.DateSerializer.dateFormat;

@Slf4j
@Component
public class DateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        final String dateString = jsonParser.getText();

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            log.info("Error while parsing date: {}", Arrays.toString(e.getStackTrace()));
            return new Date();
        }
    }
}
