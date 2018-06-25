package com.codecool.amf.service;

import com.codecool.amf.model.HelpRequest;
import com.codecool.amf.repository.HelpRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class HelpRequestService{

    @Autowired
    HelpRequestRepository helpRequestRepository;

    public void saveHelpRequest(HelpRequest helpRequest){
        helpRequestRepository.save(helpRequest);
    }

}
