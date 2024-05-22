package lv.venta.md2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Customer_As_Company")
@Entity
public class CustomerAsCompany extends AbstractCustomer {

    @NotNull
    @Size(min = 3, max = 50)
    @Pattern(regexp = "[A-Za-z0-9%^+@=.,! ]{3,50}")
    @Column(name = "Title")
    private String title;

    @NotNull
    @Size(min = 13, max = 13)
    @Pattern(regexp = "[A-Z]{2}\\d{11}")
    @Column(name = "Company_Reg_No")
    private String companyRegNo;

    public void setCustomerCode() {
        this.customerCode = getIdc() + "_company_" + companyRegNo;
    }

    public CustomerAsCompany(Address address, String phoneNo, String title, String companyRegNo) {
        super(address, phoneNo);
        setTitle(title);
        setCompanyRegNo(companyRegNo);
        setCustomerCode();
    }

}
