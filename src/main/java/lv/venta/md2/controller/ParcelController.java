package lv.venta.md2.controller;

import jakarta.validation.Valid;
import lv.venta.md2.model.City;
import lv.venta.md2.model.Parcel;
import lv.venta.md2.service.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/parcel")
public class ParcelController {

    @Autowired
    private IParcelService parcelService;

    @GetMapping("/show/all")
    public ResponseEntity<ArrayList<Parcel>> getAllParcels() {
        return ResponseEntity.ok(parcelService.selectAllParcels());
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> getParcelById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(parcelService.selectParcelById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/show/customer/{id}")
    public ResponseEntity<Object> getParcelsByCustomerId(@PathVariable int id) {
        try {
            return new ResponseEntity<>(parcelService.selectAllParcelsByCustomerId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/show/driver/{id}")
    public ResponseEntity<Object> getParcelsByDriverId(@PathVariable int id) {
        try {
            return ResponseEntity.ok(parcelService.selectAllParcelsDeliveredByDriverId(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/show/price/{threshold}")
    public ResponseEntity<Object> getParcelsByPrice(@PathVariable int threshold) {
        try {
            return ResponseEntity.ok(parcelService.selectAllParcelsPriceLessThan(threshold));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/show/city/{cityParam}")
    public ResponseEntity<Object> getParcelsByCity(@PathVariable @Valid City cityParam) {
        try {
            return ResponseEntity.ok(parcelService.selectAllParcelsDeliveredToCity(cityParam));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/add/{customerCode}/{driverId}")
    public ResponseEntity<Object> addParcel(@RequestBody @Valid Parcel parcel, @PathVariable String customerCode, @PathVariable int driverId, BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.status(400).body(result.getAllErrors());

        try {
            parcelService.insertNewParcelByCustomerCodeAndDriverId(parcel, customerCode, driverId);
            return ResponseEntity.ok("Parcel added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @GetMapping("/change/{parcelId}/{driverId}")
    public ResponseEntity<Object> updateParcelDriverByParcelIdAndDriverId(@PathVariable int parcelId, @PathVariable int driverId) {
        try {
            parcelService.changeParcelDriverByParcelIdAndDriverId(parcelId, driverId);
            return ResponseEntity.ok("Parcel updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/calculate/income/{customerId}")
    public ResponseEntity<Object> getIncomeByCustomerId(@PathVariable int customerId) {
        try {
            return ResponseEntity.ok(parcelService.calculateIncomeOfParcelsByCustomerId(customerId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/calculate/count/today")
    public ResponseEntity<Object> getParcelsToDeliverToday() {
        try {
            return ResponseEntity.ok(parcelService.calculateHowManyParcelsNeedToDeliverToday());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
