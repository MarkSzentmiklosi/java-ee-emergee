package com.codecool.amf.service;

import com.codecool.amf.model.HelpRequest;
import com.codecool.amf.model.Partner;
import com.codecool.amf.model.ServiceType;
import com.codecool.amf.repository.HelpRequestRepository;
import com.codecool.amf.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    HelpRequestRepository helpRequestRepository;

    public Partner selectPartnerByServiceType(ServiceType serviceType) {
        return partnerRepository.findByService(serviceType);
    }

    public Partner selectPartnerByEmail(String email) {
        return partnerRepository.findByEmail(email);
    }

    public void savePartner(Partner partner) {
        partnerRepository.save(partner);
    }

    public String handleIndexGet(HttpSession session, Model model) {
        if (session.getAttribute("partnerId") == null) {
            return "redirect:/partner-login";
        }
        long partnerId = (Long) session.getAttribute("partnerId");
        Partner partner = partnerRepository.findById(partnerId);
        List<HelpRequest> requests = helpRequestRepository.findByPartnerId(partnerId);
        session.removeAttribute("user");
        model.addAttribute("partner", partner);
        model.addAttribute("requests", requests);
        return "partner_index";
    }
}
