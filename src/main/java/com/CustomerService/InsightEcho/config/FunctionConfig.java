package com.CustomerService.InsightEcho.config;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.CustomerService.InsightEcho.Model.AnalysisResponse;
import com.CustomerService.InsightEcho.Model.TranscriptRecord;

import com.CustomerService.InsightEcho.Service.AiService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class FunctionConfig{
    private final AiService aiService;
    private final ObjectMapper mapper = new ObjectMapper();

    public FunctionConfig(AiService aiService){
        this.aiService = aiService;
    }

    @Bean
    @Description("Analyze transcript and return AI feedback")
    public Function<TranscriptRecord, TranscriptRecord> insightEchoFunction(){
        return transcript -> {
            try{
                //AI analysis
                AnalysisResponse analysis = aiService.analyzeTranscript(transcript.getAnalysis());

                //Convert DTO -> JSON String
                String json = mapper.writeValueAsString(analysis);

                //Save JSON back into transcript record
                transcript.setAnalysis(json);

                return transcript;
            } catch(Exception e){
                 e.printStackTrace();
                 transcript.setAnalysis("{\"error\":\"AI analysis failed: " + e.getMessage() + "\"}");
                 return transcript;
            }
        };
    }
}
