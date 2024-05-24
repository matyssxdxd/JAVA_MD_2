package lv.venta.md2;

import lv.venta.md2.model.*;
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
    public CommandLineRunner testDatabase(IAddressRepo addressRepo, IDriverRepo driverRepo,
                                          IParcelRepo parcelRepo, IPersonRepo personRepo, ICustomerRepo customerRepo) {
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

                CustomerAsCompany c1 = new CustomerAsCompany(a1, "+37120627905", "Kalmars un ko", "LV20394839214");
                customerRepo.save(c1);

                CustomerAsCompany c3 = new CustomerAsCompany(a1, "+37122627905", "Kalmars un ksso", "LV23294839214");
                customerRepo.save(c3);


                CustomerAsPerson c2 = new CustomerAsPerson(a2, "+37120627123", p1);
                customerRepo.save(c2);

                Driver d1 = new Driver("Rudolfs", "Kalmars", "130301-20821", "AT789221", 8.3f);
                Driver d2 = new Driver("Daniels", "Kalnas", "210210-21221", "AT832221", 1.2f);

                driverRepo.save(d1);
                driverRepo.save(d2);
            }
        };
    }
}
