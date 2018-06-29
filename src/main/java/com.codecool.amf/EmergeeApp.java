package com.codecool.amf;

import com.codecool.amf.model.Address;
import com.codecool.amf.model.Partner;
import com.codecool.amf.model.ServiceType;
import com.codecool.amf.model.User;
import com.codecool.amf.service.AddressService;
import com.codecool.amf.service.PartnerService;
import com.codecool.amf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmergeeApp {

    @Autowired
    private AddressService addressService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(EmergeeApp.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return (String... args) -> {
            Address address1 = new Address("Hungary", "Budapest", "1125", "Fogaskereku utca", "5");
            Address address2 = new Address("USA", "Los Angeles", "CA 90021", "Sacramento St", "123");

            addressService.saveAddress(address1);
            addressService.saveAddress(address2);

            String password = "$2a$12$Stq5xqnL22Ngql7rXeqdLuWE9DjVG9L4VplKObiAKU9h/SI3BJ4Zi"; // password is: hello

            partnerService.savePartner(new Partner("Orszagos Mentoszolgalat", "ambulance@amf.com", ServiceType.AMBULANCE, password));
            partnerService.savePartner(new Partner("Budapesti Rendor-fokapitanysag", "police@amf.com", ServiceType.POLICE, password));
            partnerService.savePartner(new Partner("XIII. keruleti Tuzoltosagi Parancsnoksag", "fire@amf.com", ServiceType.FIRE, password));
            partnerService.savePartner(new Partner("Magyarorszagi Automento Szolgalat", "car@amf.com", ServiceType.CAR_REPAIR, password));

            userService.saveUser(new User("Robot Otto", "robot@amf.com", "06301985667", "CE9352D", address1, password));
            userService.saveUser(new User("Tester Odon", "todon@amf.com", "06301985998", "FA9392C", address2, password));
        };
    }
}
