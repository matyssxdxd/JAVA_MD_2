package lv.venta.md2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class AbstractCustomer {

    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idc")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idc;

    @ManyToOne
    @JoinColumn(name = "Ida")
    private Address address;

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "\\+371\\d{8}")
    @Column(name = "Phone_No")
    private String phoneNo;


    @OneToMany(mappedBy = "customer")
    private List<Parcel> parcels;

    @NotNull
    @Column(name = "Customer_Code")
    protected String customerCode;

    public AbstractCustomer(Address address, String phoneNo) {
        setAddress(address);
        setPhoneNo(phoneNo);
    }

}
