package com.codecool.amf.service;

import com.codecool.amf.model.Partner;
import com.codecool.amf.model.ServiceType;
import com.codecool.amf.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    public Partner selectPartnerByServiceType(ServiceType serviceType) {
        return partnerRepository.findByService(serviceType);
    }

    public void savePartner(Partner partner) {
        partnerRepository.save(partner);
    }
}
