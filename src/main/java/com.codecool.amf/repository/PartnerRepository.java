package com.codecool.amf.repository;

import com.codecool.amf.model.Partner;
import com.codecool.amf.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Partner findByService(ServiceType serviceType);
}
