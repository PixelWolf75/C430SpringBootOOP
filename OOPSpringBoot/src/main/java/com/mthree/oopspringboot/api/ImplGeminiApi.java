package com.mthree.oopspringboot.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImplGeminiApi implements IGeminiApi{

    private final String apiKey;

    private final Client client;

    public ImplGeminiApi(@Value("${GEMINI_API_KEY}") String apiKey){
        this.apiKey = apiKey;
        client = Client.builder().apiKey(apiKey).build();
    }

    public GenerateContentConfig getConfig(String schemaJsonString){
        return GenerateContentConfig.builder()
                        .responseMimeType("application/json")
                        .candidateCount(1)
                        .responseSchema(Schema.fromJson(schemaJsonString))
                        .build();
    }

    @Override
    public String analyzeLogs(String exception) throws JsonProcessingException {

        // format the output like:
        // Root cause: NullPointerException caused due to missing user profile.
        // Fix: Add null checks in UserService.getUser().
        // Impact: Affects login workflows.

        // transform the immutablemap to json string
        ImmutableMap<String, Object> schema = ImmutableMap.of(
                "type", "object",
                "properties", ImmutableMap.of(
                        "root_cause", ImmutableMap.of("type", "string"),
                        "fix", ImmutableMap.of("type", "string"),
                        "impact", ImmutableMap.of("type", "string")
                )
                ,
                "required", ImmutableList.of("root_cause", "fix","impact")
        );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(schema);

        // Config
        GenerateContentConfig config = getConfig(json);

        // Response
        GenerateContentResponse response =
                client.models.generateContent("gemini-2.5-flash", exception, config);

        return response.text();
    }

    @Override
    public String summarizeIncident(String text) {
        GenerateContentResponse response =
                client.models.generateContent("gemini-2.5-flash", "Can you generate a concise 5–10 line summary based on the logs: "+text, null);
        return response.text();
    }

    @Override
    public String detectAnomaly(String text) throws JsonProcessingException {
        GenerateContentResponse response =
                client.models.generateContent("gemini-2.5-flash", "Here's the exception: "+text+"\nCan you identify unusual patterns like :sudden spike in errors\n" +
                        "●\n" +
                        "memory leaks\n" +
                        "●\n" +
                        "slow DB queries\n" +
                        "●\n" +
                        "repeating warnings?", null);
        return response.text();
    }

    @Override
    public String generateRecommendFix(String text) {
        GenerateContentResponse response =
                client.models.generateContent("gemini-2.5-flash", "Here's the exception: "+text+"\nCan you recommend fixes?", null);
        return response.text();
    }

    @Override
    public String getResponseFromDevopsChat(String text) throws JsonProcessingException {
        ImmutableMap<String, Object> schema = ImmutableMap.of(
                "type", "object",
                "properties", ImmutableMap.of(
                        "Likely cause: ", ImmutableMap.of("type", "string"),
                        "Fix: ", ImmutableMap.of("type", "string")
                ),
                "required", ImmutableList.of("root_cause", "fix","impact")
        );
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(schema);

        // Config
        GenerateContentConfig config = getConfig(json);

        // Response
        GenerateContentResponse response =
                client.models.generateContent("gemini-2.5-flash", text, config);

        return response.text();
    }
}
