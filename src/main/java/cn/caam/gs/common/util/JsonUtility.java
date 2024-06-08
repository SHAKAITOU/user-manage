/**
 * @(#)JsonUtility.java
 * 
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package cn.caam.gs.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;
import cn.caam.gs.common.exception.ShaException;

/**
 * provides functionality for reading and writing JSON.
 * 
 * @author Fast Retailing
 * @version $Revision$
 */
@Slf4j
public class JsonUtility {

    /**
     * The ObjectMapper instance.
     */
    private static ObjectMapper objectMapper;
    
    private static final String JSON_TREE_DATA = "jsonTreeData";

    static {
    	objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule())
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, 
                        DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .build();
    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    /**
     * Serialize any Java value as a JSON String.
     * 
     * @param value any java value
     * @return The JSON content string
     */
    public static String toJson(Object value) {
        String result = "";
        try {
            result = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            // shouldn't really happen,but is declared as possibility so
            log.error("Failed to serialize Java value as a JSON String", e);
            throw new ShaException(e);
        }
        return result;
    }

    /**
     * Deserialize JSON content from given JSON content String.
     */
    public static <T> T toObject(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            log.error("Can not deserialize JSON content from given JSON content String");
            throw new ShaException(e);
        }
    }
    
    public static void writeValue(File resultFile, Object value) {
    	try {
			objectMapper.writeValue(resultFile, value);
		} catch (JsonGenerationException e) {
			throw new ShaException(e);
		} catch (JsonMappingException e) {
			throw new ShaException(e);
		} catch (IOException e) {
			throw new ShaException(e);
		}
    }
    
    /**
     * Set temporary data.
     * @param key The key of data.
     * @param data Temporary data to keep.
     * @param commonForm CommonBaseForm.
     */
    public static void setJsonData(String key, Object data, HttpServletRequest request) {
        try {
            JsonNode jsonData = objectMapper.convertValue(data, JsonNode.class);
            String temporaryData = "";
            if(Objects.nonNull(request.getSession().getAttribute(JSON_TREE_DATA))) {
            	temporaryData = request.getSession().getAttribute(JSON_TREE_DATA).toString();
            }
            ObjectNode objectNode;
            if (!StringUtils.isEmpty(temporaryData)) {
                JsonNode rootNode = objectMapper.readTree(temporaryData);
                objectNode = (ObjectNode) rootNode;
            } else {
                objectNode = objectMapper.createObjectNode();
                
            }
            objectNode.set(key, jsonData);
            temporaryData = objectMapper.writeValueAsString(objectNode);
            request.getSession().setAttribute(JSON_TREE_DATA, temporaryData);
        } catch (IOException e) {
            throw new ShaException("error in set temporary data to form", e);
        }
    }
    
    /**
     * Remove the data from temporary data by key.
     * @param key The key of data.
     * @param commonForm CommonBaseForm.
     */
    public static void removeJsonData(String key, HttpServletRequest request) {
    	
    	if(Objects.isNull(request.getSession().getAttribute(JSON_TREE_DATA))) {
    		return;
    	}
    	
        try {
            String temporaryData = request.getSession().getAttribute(JSON_TREE_DATA).toString();
            if (!StringUtils.isEmpty(temporaryData)) {
                JsonNode rootNode = objectMapper.readTree(temporaryData);
                ObjectNode objectNode = (ObjectNode) rootNode;
                if (objectNode.has(key)) {
                    objectNode.remove(key);
                }
                temporaryData = objectMapper.writeValueAsString(objectNode);
                request.getSession().setAttribute(JSON_TREE_DATA, temporaryData);
            }
        } catch (IOException e) {
            throw new ShaException("error in remove temporary data from form", e);
        }
    }
    
    /**
     * Convert the key data to a target instance.
     * @param key The key of data.
     * @param valueType Converted target class type.
     * @param commonForm CommonBaseForm.
     * @return T Converted target instance.
     */
    public static <T> T getJsonData(String key,  Class<T> valueType, 
    		HttpServletRequest request) {
    	
    	if(Objects.isNull(request.getSession().getAttribute(JSON_TREE_DATA))) {
    		return null;
    	}
    	
        try {
            String temporaryData = request.getSession().getAttribute(JSON_TREE_DATA).toString();
            if (!StringUtils.isEmpty(temporaryData)) {
                JsonNode rootNode = objectMapper.readTree(temporaryData);
                ObjectNode objectNode = (ObjectNode) rootNode;
                if ( objectNode.has(key)) {
                    String objectData = objectMapper.writeValueAsString(objectNode.get(key));
                    return objectMapper.readValue(objectData, valueType);
                }
            }
            return null;
        } catch (IOException e) {
            throw new ShaException("error in get temporary data from form", e);
        }
    }
    
    /**
     * Convert the key data to a target instance.
     * @param key The key of data.
     * @param valueType Converted target class type.
     * @param commonForm CommonBaseForm.
     * @return T Converted target instance.
     */
    public static <T> T getJsonData(String key,  TypeReference<T> valueTypeRef, 
    		HttpServletRequest request) {
    	
    	if(Objects.isNull(request.getSession().getAttribute(JSON_TREE_DATA))) {
    		return null;
    	}
    	
        try {
            String temporaryData = request.getSession().getAttribute(JSON_TREE_DATA).toString();
            if (!StringUtils.isEmpty(temporaryData)) {
                JsonNode rootNode = objectMapper.readTree(temporaryData);
                ObjectNode objectNode = (ObjectNode) rootNode;
                if ( objectNode.has(key)) {
                    String objectData = objectMapper.writeValueAsString(objectNode.get(key));
                    return objectMapper.readValue(objectData, valueTypeRef);
                }
            }
            return null;
        } catch (IOException e) {
            throw new ShaException("error in get temporary data from form", e);
        }
    }
}
