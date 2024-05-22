package lv.venta.md2.service;

import lv.venta.md2.model.Driver;

import java.util.ArrayList;

public interface IDriverCRUDService {

    ArrayList<Driver> selectAllDriver();
    Driver selectDriverById(int id) throws Exception;
    void deleteDriverById(int id) throws Exception;
    void insertNewDriver(Driver driver) throws Exception;
    void updateDriverById(int id, Driver driver) throws Exception;
}
