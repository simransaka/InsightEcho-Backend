package com.CustomerService.InsightEcho.Repository;

import com.CustomerService.InsightEcho.Model.TranscriptRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends JpaRepository<TranscriptRecord, String> {
    // You can add custom query methods here if needed
}
