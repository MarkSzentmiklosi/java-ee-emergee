package com.codecool.amf.repository;

import com.codecool.amf.model.HelpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpRequestRepository extends JpaRepository<HelpRequest, Long> {

    public List<HelpRequest> findByPartnerId(Long id);
}
