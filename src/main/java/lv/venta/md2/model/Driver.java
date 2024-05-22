package lv.venta.md2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Driver_Table")
@Entity
public class Driver extends Person {

    @NotNull
    @Size(min = 8, max = 8)
    @Pattern(regexp = "[A-Z]{2}\\d{6}")
    @Column(name = "License_No")
    private String licenseNo;

    @NotNull
    @Min(0)
    @Column(name = "Experience_In_Years")
    private float experienceInYears;

    @OneToMany(mappedBy = "driver")
    private List<Parcel> parcels;

    public Driver(String name, String surname, String personCode, String licenseNo, float experienceInYears) {
        super(name, surname, personCode);
        setLicenseNo(licenseNo);
        setExperienceInYears(experienceInYears);
    }
 }
