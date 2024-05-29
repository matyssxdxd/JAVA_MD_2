package lv.venta.md2.controller;

import jakarta.validation.Valid;
import lv.venta.md2.model.Driver;
import lv.venta.md2.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/driver")
public class DriverCRUDController {

    @Autowired
    private IDriverCRUDService driverCRUD;

    @GetMapping("/show/all")
    public ResponseEntity<Object> getDrivers() {
        return new ResponseEntity<>(driverCRUD.selectAllDriver(), HttpStatus.OK);
    }

    @GetMapping("/show/all/{id}")
    public ResponseEntity<Object> getDriverById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(driverCRUD.selectDriverById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeDriver(@PathVariable int id) {
        try {
            driverCRUD.deleteDriverById(id);
            return new ResponseEntity<>("Driver removed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addDriver(@RequestBody @Valid Driver driver, BindingResult result) {
        if (result.hasErrors()) return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            driverCRUD.insertNewDriver(driver);
            return new ResponseEntity<>("Driver added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateDriver(@RequestBody @Valid Driver driver, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            driverCRUD.updateDriverById(id, driver);
            return new ResponseEntity<>("Driver updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

