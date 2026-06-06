package com.CustomerService.InsightEcho.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysisResponse {

    private int rating;
    private String tone;
    private String emotionalCues;
    private String coachingTips;
    private String escalationFlags;
    private String fullAnalysis;

}
