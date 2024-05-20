package lv.venta.md2;

import lv.venta.md2.model.Address;
import lv.venta.md2.model.City;
import lv.venta.md2.model.Person;
import lv.venta.md2.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Md2Application {

    public static void main(String[] args) {
        SpringApplication.run(Md2Application.class, args);
    }

    @Bean
    public CommandLineRunner testDatabase(IAddressRepo addressRepo, ICustomerAsCompanyRepo customerAsCompanyRepo,
                                          ICustomerAsPersonRepo customerAsPersonRepo, IDriverRepo driverRepo,
                                          IParcelRepo parcelRepo, IPersonRepo personRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Person p1 = new Person("Janis", "Berzins", "130202-20821");
                Person p2 = new Person("Juris", "Berzins", "210222-20321");
                personRepo.save(p1);
                personRepo.save(p2);

                Address a1 = new Address(City.LIEPAJA, 10, "Liela iela");
                Address a2 = new Address(City.TALSI, 21, "Maza iela");
                addressRepo.save(a1);
                addressRepo.save(a2);

            }
        };
    }
}
