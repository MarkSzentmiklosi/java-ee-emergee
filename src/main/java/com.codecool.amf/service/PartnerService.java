package com.codecool.amf.service;

import com.codecool.amf.model.Partner;
import com.codecool.amf.model.ServiceType;
import com.codecool.amf.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    public Partner selectPartnerByServiceType(ServiceType serviceType) {
        return partnerRepository.findByService(serviceType);
    }

    public Partner selectPartnerByEmail(String email) {
        return partnerRepository.findByEmail(email);
    }

    public void savePartner(Partner partner) {
        partnerRepository.save(partner);
    }

    public String handleIndexGet(HttpSession session) {
        if (session.getAttribute("partnerId") == null) {
            return "redirect:/partner-login";
        }
        return "partner_index";
    }
}
