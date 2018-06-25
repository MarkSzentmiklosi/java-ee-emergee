package com.codecool.amf.service;

import com.codecool.amf.model.Address;
import com.codecool.amf.model.Partner;
import com.codecool.amf.model.ServiceType;
import com.codecool.amf.model.User;
import org.springframework.stereotype.Component;

@Component
public class InitBean {

    public InitBean(AdressService adressService, PartnerService partnerService, UserService userService) {

        Address address1 = new Address("Hungary", "Budapest", "1125", "Fogaskereku utca", "5");
        Address address2 = new Address("USA", "Los Angeles", "CA 90021", "Sacramento St", "5");

        adressService.saveAddress(address1);
        adressService.saveAddress(address2);

        partnerService.savePartner(new Partner("Orszagos Mentoszolgalat", "bollaferenc@gmail.com", ServiceType.AMBULANCE));
        partnerService.savePartner(new Partner("Budapesti Rendor-fokapitanysag", "bollaferenc@gmail.com", ServiceType.POLICE));
        partnerService.savePartner(new Partner("XIII. keruleti Tuzoltosagi Parancsnoksag", "bollaferenc@gmail.com", ServiceType.FIRE));
        partnerService.savePartner(new Partner("Magyarorszagi Automento Szolgalat", "bollaferenc@gmail.com", ServiceType.CAR_REPAIR));

        String password = "$2a$12$Stq5xqnL22Ngql7rXeqdLuWE9DjVG9L4VplKObiAKU9h/SI3BJ4Zi";

        userService.saveUser(new User("Zamboki Panna", "pannaz@amf.com", "06301985667", "CE9352D", address1, password));
        userService.saveUser(new User("Tester Odon", "todon@amf.com", "06301985998", "FA9392C", address2, password));
    }
}
