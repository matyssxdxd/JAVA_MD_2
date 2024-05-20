package lv.venta.md2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Person_Table")
@Entity
public class Person {

    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idp")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idp;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "[A-Z][a-z]+")
    @Column(name = "Name")
    private String name;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "[A-Z][a-z]+")
    @Column(name = "Surname")
    private String surname;

    @NotNull
    @Size(min = 12, max = 12)
    @Pattern(regexp = "\\d{6}-\\d{5}")
    @Column(name = "Person_Code")
    private String personCode;

    public Person(String name, String surname, String personCode) {
        setName(name);
        setSurname(surname);
        setPersonCode(personCode);
    }
}
