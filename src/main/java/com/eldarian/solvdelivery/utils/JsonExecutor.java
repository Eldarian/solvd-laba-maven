package com.eldarian.solvdelivery.utils;

import com.eldarian.solvdelivery.model.order.Order;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonExecutor {

    private static final Logger LOGGER = Logger.getLogger(JsonExecutor.class);

    public void convertPojoToJsonFile(Order obj, String pathToFile) {
        File file = new File(pathToFile);
        try {
            ObjectMapper mapper = new ObjectMapper();
            //mapper.writer().writeValue(file, obj);
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.writeValue(file, obj);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Order readOrderPojoFromJsonFile(String pathToFile) {
        Order obj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            obj = mapper.reader().readValue(Paths.get(pathToFile).toFile(), Order.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(obj == null) {
            LOGGER.error("No data in Json");
        }
        return obj;
    }
}
