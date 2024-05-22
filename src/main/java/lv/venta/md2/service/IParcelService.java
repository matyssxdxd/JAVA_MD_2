package lv.venta.md2.service;

import lv.venta.md2.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IParcelService {

    ArrayList<Parcel> selectAllParcelsByCustomerId(int customerId) throws Exception;

    ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int driverId) throws Exception;

    ArrayList<Parcel> selectAllParcelsPriceLessThan(float minPrice);

    ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city);

    void insertNewParcelByCustomerCodeAndDriverId(String customerCode, int driverId, LocalDateTime plannedDelivery, ParcelSize size, boolean isFragile) throws Exception;

    void changeParcelDriverByParcelIdAndDriverId(int parcelId, int driverId) throws Exception;

    float calculateIncomeOfParcelsByCustomerId(int customerId) throws Exception;

    int calculateHowManyParcelsNeedToDeliverToday();
}
