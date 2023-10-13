//package com.example.hongpark.objectmapper;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ConversionTest {
//
//    @Test
//    public void 자바_객체를_JSON으로_변환() throws JsonProcessingException {
//        // 준비
//        ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper 스프링에서 제공
//        List<String> ingredients = Arrays.asList("통새우 패티", "토마토", "스파이시 어니언 소스");
//        Conversion conversion = new Conversion("슈비버거", 5000, ingredients);
//
//
//        // 수행
//        //ObjectMapper가 json 만든다
//        String json = objectMapper.writeValueAsString(conversion);
//
//        // 예상
//        String expected = "{\"name\":\"슈비버거\",\"price\":5000,\"ingredients\":[\"통새우 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";
//
//        //검증
//        assertEquals(expected, json);
//        JsonNode jsonNode = objectMapper.readTree(json);
//        System.out.println(jsonNode.toPrettyString());
//    }
//
//    @Test
//    public void JSON을_자바객체로_변환() throws JsonProcessingException {
//        // 준비
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "{\"name\":\"슈비버거\",\"price\":5000,\"ingredients\":[\"통새우 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";
//
//        // 수행
//        Conversion conversion = objectMapper.readValue(json, Conversion.class);
//
//        // 예상
//        List<String> ingredients = Arrays.asList("통새우 패티", "토마토", "스파이시 어니언 소스");
//        Conversion expected = new Conversion("슈비버거", 5000, ingredients);
//
//        // 검증
//        assertEquals(expected.toString(), json.toString());
//        JsonNode jsonNode = objectMapper.readTree(json);
//        System.out.println(json);
//        System.out.println(conversion.toString());
//    }
//
//    @Test
//    public void json_직접_만들기() throws JsonProcessingException {
//
//        // 준비
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        /*
//        {
//            "name" : "슈비버거",
//            "price" : 5500,
//            "ingredients" : [ "통새우 패티", "토마토", "스파이시 어니언 소스" ]
//        }
//        */
//        ObjectNode objectNode = objectMapper.createObjectNode();
//        objectNode.put("name", "슈비버거");
//        objectNode.put("price", 5500);
//
//        ArrayNode arrayNode = objectMapper.createArrayNode();
//        arrayNode.add("통새우 패티");
//        arrayNode.add("토마토");
//        arrayNode.add("스파이시 어니언 소스");
//        objectNode.set("ingredients", arrayNode);
//        String json = objectNode.toString();
//
//        // 수행
//        Conversion conversion = objectMapper.readValue(json, Conversion.class);
//
//        // 예상
//        List<String> ingredients = Arrays.asList("통새우 패티", "토마토", "스파이시 어니언 소스");
//        Conversion expected = new Conversion("슈비버거", 5000, ingredients);
//
//        // 검증
//        assertEquals(expected.toString(), json.toString());
//        JsonNode jsonNode = objectMapper.readTree(json);
//        System.out.println(json);
//        System.out.println(conversion.toString());
//
//    }
//
//}