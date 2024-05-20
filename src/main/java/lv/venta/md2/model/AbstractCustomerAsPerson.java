package lv.venta.md2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class AbstractCustomerAsPerson extends AbstractCustomer {

    @OneToOne
    @JoinColumn(name = "Idp")
    protected Person person;

    public AbstractCustomerAsPerson(Address address, String phoneNo, Person person) {
        super(address, phoneNo);
        setPerson(person);
    }
}
