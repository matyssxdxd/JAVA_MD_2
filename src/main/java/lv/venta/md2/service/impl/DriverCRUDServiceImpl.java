package lv.venta.md2.service.impl;

import lv.venta.md2.model.Driver;
import lv.venta.md2.repo.IDriverRepo;
import lv.venta.md2.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DriverCRUDServiceImpl implements IDriverCRUDService {

    @Autowired
    private IDriverRepo driverRepo;

    @Override
    public ArrayList<Driver> selectAllDriver() {
        return (ArrayList<Driver>) driverRepo.findAll();
    }

    @Override
    public Driver selectDriverById(int id) throws Exception {
        if (id < 0) throw new Exception("driverID must be more than 0");
        if (!driverRepo.existsById(id)) throw new Exception("There is no driver with ID: " + id);

        return driverRepo.findByIdp(id);
    }

    @Override
    public void deleteDriverById(int id) throws Exception {
        if (id < 0) throw new Exception("driverID must be more than 0");
        if (!driverRepo.existsById(id)) throw new Exception("There is no driver with ID: " + id);

        driverRepo.deleteById(id);
    }

    @Override
    public void insertNewDriver(Driver driver) throws Exception {
        if (driver == null) throw new Exception("There is no driver provided");
        if (driverRepo.existsByPersonCode(driver.getPersonCode())) throw new Exception("Driver already exists");

        driverRepo.save(driver);
    }

    @Override
    public void updateDriverById(int id, Driver driver) throws Exception {
        if (driver == null) throw new Exception("There is no driver provided");
        if (!driverRepo.existsById(id)) throw new Exception("There is no driver with ID: " + driver.getIdp());

        Driver updateDriver = driverRepo.findByIdp(id);
        updateDriver.setName(driver.getName());
        updateDriver.setSurname(driver.getSurname());
        updateDriver.setPersonCode(driver.getPersonCode());
        updateDriver.setExperienceInYears(driver.getExperienceInYears());
        updateDriver.setLicenseNo(driver.getLicenseNo());
        driverRepo.save(updateDriver);
    }
}
