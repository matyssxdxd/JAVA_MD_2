package lv.venta.md2.repo;

import lv.venta.md2.model.City;
import lv.venta.md2.model.Parcel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IParcelRepo extends CrudRepository<Parcel, Integer> {
    ArrayList<Parcel> findByCustomerIdc(int customerId);

    ArrayList<Parcel> findByDriverIdp(int driverId);

    ArrayList<Parcel> findByPriceLessThan(float maxPrice);

    ArrayList<Parcel> findByCustomerAddressCity(City city);

    Parcel findByIdpa(int parcelId);
}
