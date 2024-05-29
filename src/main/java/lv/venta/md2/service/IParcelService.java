package lv.venta.md2.service;

import lv.venta.md2.model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IParcelService {

    ArrayList<Parcel> selectAllParcels();

    Parcel selectParcelById(int id) throws Exception;

    ArrayList<Parcel> selectAllParcelsByCustomerId(int customerId) throws Exception;

    ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int driverId) throws Exception;

    ArrayList<Parcel> selectAllParcelsPriceLessThan(float minPrice);

    ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city);

    void insertNewParcelByCustomerCodeAndDriverId(Parcel parcel, String customerCode, int driverId) throws Exception;

    void changeParcelDriverByParcelIdAndDriverId(int parcelId, int driverId) throws Exception;

    float calculateIncomeOfParcelsByCustomerId(int customerId) throws Exception;

    int calculateHowManyParcelsNeedToDeliverToday();
}
