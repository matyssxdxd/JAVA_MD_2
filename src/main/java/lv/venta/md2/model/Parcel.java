package lv.venta.md2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @NotNull
    @Column(name = "Order_Created")
    private LocalDateTime orderCreated;

    @NotNull
    @Column(name = "Planned_Delivery")
    private LocalDateTime plannedDelivery;

    @NotNull
    @Column(name = "Size")
    private ParcelSize size;

    @NotNull
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

        this.price = (float) (isFragile ? multiplier * 1.99 + 2.99 : multiplier * 1.99);
    }

    public void setOrderCreated() {
        this.orderCreated = LocalDateTime.now();
    }

    public void setPlannedDelivery(LocalDateTime plannedDelivery) {
        this.plannedDelivery = ( plannedDelivery.isAfter(orderCreated) ) ? plannedDelivery : orderCreated.plusDays(1);
    }

    public Parcel(LocalDateTime plannedDelivery, ParcelSize size, boolean isFragile, Driver driver, AbstractCustomer customer) {
        setOrderCreated();
        setPlannedDelivery(plannedDelivery);
        setSize(size);
        setFragile(isFragile);
        setPrice();
        setDriver(driver);
        setCustomer(customer);
    }

}
