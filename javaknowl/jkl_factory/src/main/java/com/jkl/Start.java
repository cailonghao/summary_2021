package com.jkl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Start {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> map = new HashMap<String, String>();
        map.put("aa","bbb");
        String json = mapper.writeValueAsString(map);
        JsonNode node = mapper.readTree(json);
        if(node.has("bb")){
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
    }
}
