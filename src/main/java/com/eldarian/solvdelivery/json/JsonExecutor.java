package com.eldarian.solvdelivery.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonExecutor {

    private Logger logger = Logger.getLogger(JsonExecutor.class);

    public void convertPojoToJsonFile(Object obj, String pathToFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writer().writeValue(Paths.get(pathToFile).toFile(), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readPojoFromJsonFile(String pathToFile) {
        Object obj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            obj = mapper.reader().readValue(Paths.get(pathToFile).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(obj == null) {
            logger.error("No data in Json");
        }
        return obj;
    }
}
