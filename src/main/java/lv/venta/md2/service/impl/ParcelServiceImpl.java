package lv.venta.md2.service.impl;

import lv.venta.md2.model.*;
import lv.venta.md2.repo.ICustomerRepo;
import lv.venta.md2.repo.IDriverRepo;
import lv.venta.md2.repo.IParcelRepo;
import lv.venta.md2.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ParcelServiceImpl implements IParcelService {

    @Autowired
    private IParcelRepo parcelRepo;

    @Autowired
    private ICustomerRepo customerRepo;

    @Autowired
    private IDriverRepo driverRepo;

    @Override
    public ArrayList<Parcel> selectAllParcelsByCustomerId(int customerId) throws Exception {
        if (!customerRepo.existsById(customerId)) throw new Exception("There is no customer with ID: " + customerId);

        return parcelRepo.findByCustomerIdc(customerId);
    }

    @Override
    public ArrayList<Parcel> selectAllParcelsDeliveredByDriverId(int driverId) throws Exception {
        if (!driverRepo.existsById(driverId)) throw new Exception("There is no driver with ID: " + driverId);

        return parcelRepo.findByDriverIdp(driverId);
    }

    @Override
    public ArrayList<Parcel> selectAllParcelsPriceLessThan(float maxPrice) {
        return parcelRepo.findByPriceLessThan(maxPrice);
    }

    @Override
    public ArrayList<Parcel> selectAllParcelsDeliveredToCity(City city) {
        return parcelRepo.findByCustomerAddressCity(city);
    }

    @Override
    public void insertNewParcelByCustomerCodeAndDriverId(String customerCode, int driverId, LocalDateTime plannedDelivery, ParcelSize size, boolean isFragile) throws Exception {
        if (customerCode == null || driverId < 0 || plannedDelivery == null || size == null) throw new Exception("Problem with input parameters");
        if (!driverRepo.existsById(driverId)) throw new Exception("There is no driver with ID: " + driverId);
        if (!customerRepo.existsByCustomerCode(customerCode)) throw new Exception("There is no customer with customerCode: " + customerCode);

        Driver driver = driverRepo.findByIdp(driverId);
        AbstractCustomer customer = customerRepo.findByCustomerCode(customerCode);
        Parcel parcel = new Parcel(plannedDelivery, size, isFragile, driver, customer);
        parcelRepo.save(parcel);
    }

    @Override
    public void changeParcelDriverByParcelIdAndDriverId(int parcelId, int driverId) throws Exception {
        if (parcelId < 0 || driverId < 0) throw new Exception("parcelID and driverID must be more than 0");
        if (!parcelRepo.existsById(parcelId)) throw new Exception("There is no parcel with ID: " + parcelId);
        if (!driverRepo.existsById(driverId)) throw new Exception("There is no driver with ID: " + driverId);

        Parcel parcel = parcelRepo.findByIdpa(parcelId);
        parcel.setDriver(driverRepo.findByIdp(driverId));
        parcelRepo.save(parcel);

    }

    @Override
    public float calculateIncomeOfParcelsByCustomerId(int customerId) throws Exception {
        if (customerId < 0) throw new Exception("CustomerID must be more than 0");

        ArrayList<Parcel> parcels = parcelRepo.findByCustomerIdc(customerId);
        float total = 0;
        for (Parcel parcel : parcels) {
            total += parcel.getPrice();
        }

        return total;
    }

    @Override
    public int calculateHowManyParcelsNeedToDeliverToday() {
        ArrayList<Parcel> parcels = (ArrayList<Parcel>) parcelRepo.findAll();
        int count = 0;
        for (Parcel parcel : parcels) {
            if (parcel.getPlannedDelivery().getDayOfYear() == LocalDateTime.now().getDayOfYear()) count++;
        }

        return count;
    }
}
