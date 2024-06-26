package lv.venta.md2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class AbstractCustomer {

    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idc")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idc;

    @ManyToOne
    @JoinColumn(name = "Ida", nullable = true)
    private Address address;

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "\\+371\\d{8}")
    @Column(name = "Phone_No")
    private String phoneNo;


    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Parcel> parcels;

    @Column(name = "Customer_Code")
    protected String customerCode;

    protected AbstractCustomer(Address address, String phoneNo) {
        setAddress(address);
        setPhoneNo(phoneNo);
    }

}
