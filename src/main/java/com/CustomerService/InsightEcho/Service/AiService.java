package com.CustomerService.InsightEcho.Service;

import javax.swing.text.html.ObjectView;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import com.CustomerService.InsightEcho.Model.AnalysisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AiService{
    private final OpenAiChatModel openAiChatModel;

    public AiService(OpenAiChatModel openAiChatModel){
        this.openAiChatModel = openAiChatModel;
    }

    public AnalysisResponse analyzeTranscript(String content){
        String promptText = """
            You are InsightEcho, an emotionally intelligent AI coach.
            Analyze the following customer service transcript in strict JSON format:
            
            {
              "rating": <number between 1-5>,
              "tone": "<tone of the interaction>",
              "emotionalCues": "<Provide actionable tips for improving the executive's response>",
              "escalationFlags": "<any escalation flags, or 'None'>",
              "fullAnalysis": "<complete summary and reasoning>"
            }

            Transcript:
            """ + content;

        try{
            Prompt prompt = new Prompt(promptText);
            var response = openAiChatModel.call(prompt);
             // Raw text from AI
        String raw = response.getResult().getOutput().getText();

        // ✅ Clean response (remove code fences like ```json ... ```)
        String json = raw.trim();
        if (json.startsWith("```")) {
            int start = json.indexOf("{");
            int end = json.lastIndexOf("}");
            if (start != -1 && end != -1) {
                json = json.substring(start, end + 1);
            }
        }

            //Convert JSON to DTO/Data To Object
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, AnalysisResponse.class);
        } catch (Exception e){
            e.printStackTrace();
            AnalysisResponse error = new AnalysisResponse();
            error.setFullAnalysis("AI Analysis failed: " + e.getMessage());
            return error;
        }
    }
}

