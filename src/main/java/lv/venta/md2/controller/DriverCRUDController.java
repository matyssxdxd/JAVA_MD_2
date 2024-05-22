package lv.venta.md2.controller;

import jakarta.validation.Valid;
import lv.venta.md2.model.Driver;
import lv.venta.md2.service.IDriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/driver")
public class DriverCRUDController {

    @Autowired
    private IDriverCRUDService driverCRUD;

    @GetMapping("/show/all")
    public ResponseEntity<Object> getDrivers() {
        return ResponseEntity.ok(driverCRUD.selectAllDriver());
    }

    @GetMapping("/show/all/{id}")
    public ResponseEntity<Object> getDriverById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(driverCRUD.selectDriverById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/remove/{id}")
    public ResponseEntity<Object> removeDriver(@PathVariable int id) {
        try {
            driverCRUD.deleteDriverById(id);
            return ResponseEntity.ok("Driver removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addDriver(@Valid Driver driver, BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.status(400).body(result.getAllErrors());

        try {
            driverCRUD.insertNewDriver(driver);
            return ResponseEntity.ok("Driver added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateDriver(@Valid Driver driver, BindingResult result, @PathVariable int id) {
        if (result.hasErrors()) return ResponseEntity.status(400).body(result.getAllErrors());

        try {
            driverCRUD.updateDriverById(id, driver);
            return ResponseEntity.ok("Driver updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

