package com.ooha.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonUtils {
    private final static Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T parse(final String data, final Class<?> target) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructType(Class.forName(target.getName())));
        } catch (final JsonParseException e) {
            // logger.error("parse", e);
        } catch (final JsonMappingException e) {
            // logger.error("parse", e);
        } catch (final IOException e) {
            // logger.error("parse", e);
        } catch (final ClassNotFoundException e) {
            // logger.error("parse", e);
        }
		return null;
    }

    public static <T> T parseFAILONUNKNOWNPROPERTIES(final String data, final Class<?> target)
         {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(data,
                objectMapper.getTypeFactory().constructType(Class.forName(target.getName())));
        } catch (final JsonParseException e) {
            // logger.error("parse", e);
        } catch (final JsonMappingException e) {
            // logger.error("parse", e);
        } catch (final IOException e) {
            // logger.error("parse", e);
        } catch (final ClassNotFoundException e) {
            // logger.error("parse", e);
        }
        return null;
    }

    /**
     * Parses the list.
     *
     * @param <T>
     *            the generic type
     * @param data
     *            the data
     * @param target
     *            the target
     * @return the list
     * @throws RequestParserException
     *             the request parser exception
     */
    public static <T> List<T> parseList(final String data, final Class<?> target)  {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(data,
                TypeFactory.defaultInstance().constructCollectionType(List.class, Class.forName(target.getName())));
        } catch (final JsonParseException e) {
        } catch (final JsonMappingException e) {
        } catch (final IOException e) {
        } catch (final ClassNotFoundException e) {
        }
        return null;
    }

    public static <T> Map<String, String> parseStringMap(final String data)  {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final Map<String, String> hmMessage = objectMapper.readValue(data,
                new TypeReference<Map<String, String>>() {
            });
            return hmMessage;
        } catch (final JsonParseException e) {
        } catch (final JsonMappingException e) {
        } catch (final IOException e) {
        }
        return null;
    }

    public static String getBody(final HttpServletRequest request)  {

        String body = null;
        final StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            final InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                final char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (final IOException ex) {
            // logger.error("getBody", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (final IOException ex) {
                    // logger.error("getBody", ex);
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    public static String toJson(final Object model) throws  JsonProcessingException {
        final ObjectMapper mapperObj = new ObjectMapper();
        try {
            return mapperObj.writeValueAsString(model);
        } catch (final JsonProcessingException e) {
            LOG.debug("JsonProcessingException " + e.getMessage());
            throw e;
        }
    }

}
