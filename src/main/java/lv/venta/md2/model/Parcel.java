package lv.venta.md2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Parcel_Table")
@Entity
public class Parcel {

    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idpa")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idpa;

    @Column(name = "Order_Created")
    private LocalDate orderCreated;

    @NotNull
    @Column(name = "Planned_Delivery")
    private LocalDate plannedDelivery;

    @NotNull
    @Column(name = "Size")
    private ParcelSize size;

    @Column(name = "Price")
    private float price;

    @NotNull
    @Column(name = "Is_Fragile")
    private boolean isFragile;

    @ManyToOne
    @JoinColumn(name = "Idp")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "Idc")
    private AbstractCustomer customer;

    public void setPrice() {
        int multiplier = switch(size) {
            case X -> 1;
            case S -> 2;
            case M -> 3;
            case L -> 4;
            case XL -> 5;
        };
        this.price = isFragile ? multiplier * 1.99f + 2.99f : multiplier * 1.99f;
    }

    public void setOrderCreated() {
        this.orderCreated = LocalDate.now();
    }

    public void setPlannedDelivery(LocalDate plannedDelivery) {
        setOrderCreated();
        if (plannedDelivery != null) {
            this.plannedDelivery = plannedDelivery.isBefore(orderCreated) ? orderCreated : plannedDelivery;
        } else {
            this.plannedDelivery = LocalDate.now(); // same day delivery or smth
        }
    }


    public Parcel(LocalDate plannedDelivery, ParcelSize size, boolean isFragile, Driver driver, AbstractCustomer customer) {
        setOrderCreated();
        setPlannedDelivery(plannedDelivery);
        setSize(size);
        setFragile(isFragile);
        setPrice();
        setDriver(driver);
        setCustomer(customer);
    }
}
