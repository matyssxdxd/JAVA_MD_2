package lv.venta.md2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Address_Table")
@Entity
public class Address {

    @Setter(value = AccessLevel.NONE)
    @Column(name = "Ida")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ida;

    @NotNull
    @Column(name = "City")
    private City city;

    @NotNull
    @Min(1)
    @Max(500)
    @Column(name = "House_No")
    private int houseNo;

    @NotNull
    @Size(min = 1, max = 50)
    @Pattern(regexp = "[A-Z][A-Za-z0-9 -]+")
    @Column(name = "Street_Or_House_Title")
    private String streetOrHouseTitle;

    @OneToOne(mappedBy = "address")
    private AbstractCustomer abstractCustomer;

    public Address(City city, int houseNo, String streetOrHouseTitle) {
        setCity(city);
        setHouseNo(houseNo);
        setStreetOrHouseTitle(streetOrHouseTitle);
    }
}
