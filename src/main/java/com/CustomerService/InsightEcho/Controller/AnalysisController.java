package com.CustomerService.InsightEcho.Controller;

import com.CustomerService.InsightEcho.Model.AnalysisResponse;
import com.CustomerService.InsightEcho.Model.TranscriptRecord;
import com.CustomerService.InsightEcho.Repository.TranscriptRepository;
import com.CustomerService.InsightEcho.Service.AiService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/transcripts")
public class AnalysisController {

    private final AiService aiService;
    private final TranscriptRepository transcriptRepository;

    public AnalysisController(AiService aiService, TranscriptRepository transcriptRepository) {
        this.aiService = aiService;
        this.transcriptRepository = transcriptRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<AnalysisResponse> uploadTranscript(@RequestParam("file") MultipartFile file) {
    System.out.println("Received file: " + file.getOriginalFilename());

    if (file.isEmpty()) {
        System.out.println("File is empty.");
        return ResponseEntity.badRequest().build();
    }

    try {
        // Save file
        Path folder = Paths.get("transcripts");
        Files.createDirectories(folder);
        Path filePath = folder.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File saved to: " + filePath);

        // Read content
        String content = Files.readString(filePath);
        System.out.println("File content read.");

        // AI analysis
        AnalysisResponse analysis = aiService.analyzeTranscript(content);
        System.out.println("AI analysis complete.");

        // Save metadata
        TranscriptRecord record = new TranscriptRecord();
        record.setId(file.getOriginalFilename());
        record.setFileName(file.getOriginalFilename());
        record.setFilePath(filePath.toString());
        record.setAnalysis(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(analysis));
        record.setTimeStamp(LocalDateTime.now());
        transcriptRepository.save(record);
        System.out.println("Transcript record saved.");

        return ResponseEntity.ok(analysis);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @GetMapping("/all")
        public ResponseEntity<List<TranscriptRecord>> getAlltranscripts() {
        try{
            List<TranscriptRecord> transcripts = transcriptRepository.findAll();

            for(TranscriptRecord transcript : transcripts){
                Path filePath = Paths.get(transcript.getFilePath());
                String fileContent = Files.readString(filePath);
                transcript.setFileContent(fileContent);
            }
            return ResponseEntity.ok(transcripts);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}


