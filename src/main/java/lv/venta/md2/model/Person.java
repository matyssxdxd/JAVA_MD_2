package lv.venta.md2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Person_Table")
@Entity
public class Person {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Idp")
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
        this.name = name;
        this.surname = surname;
        this.personCode = personCode;
    }
}
