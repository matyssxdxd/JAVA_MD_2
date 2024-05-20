package lv.venta.md2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Customer_As_Person")
@Entity
public class CustomerAsPerson extends AbstractCustomerAsPerson {

    public void setCustomerCode() {
        this.customerCode = "customer_" + this.person.getPersonCode();
    }

    public CustomerAsPerson(Address address, String phoneNo, Person person) {
        super(address, phoneNo, person);
        setCustomerCode();
    }
}
