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
        return new ResponseEntity<>(parcelService.selectAllParcels(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> getParcelById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(parcelService.selectParcelById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>(parcelService.selectAllParcelsDeliveredByDriverId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/show/price/{threshold}")
    public ResponseEntity<Object> getParcelsByPrice(@PathVariable int threshold) {
        try {
            return new ResponseEntity<>(parcelService.selectAllParcelsPriceLessThan(threshold), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/show/city/{cityParam}")
    public ResponseEntity<Object> getParcelsByCity(@PathVariable @Valid City cityParam) {
        try {
            return new ResponseEntity<>(parcelService.selectAllParcelsDeliveredToCity(cityParam), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add/{customerCode}/{driverId}")
    public ResponseEntity<Object> addParcel(@RequestBody @Valid Parcel parcel, @PathVariable String customerCode, @PathVariable int driverId, BindingResult result) {
        if (result.hasErrors()) return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            parcelService.insertNewParcelByCustomerCodeAndDriverId(parcel, customerCode, driverId);
            return new ResponseEntity<>("Parcel added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/change/{parcelId}/{driverId}")
    public ResponseEntity<Object> updateParcelDriverByParcelIdAndDriverId(@PathVariable int parcelId, @PathVariable int driverId) {
        try {
            parcelService.changeParcelDriverByParcelIdAndDriverId(parcelId, driverId);
            return new ResponseEntity<>("Parcel updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/calculate/income/{customerId}")
    public ResponseEntity<Object> getIncomeByCustomerId(@PathVariable int customerId) {
        try {
            return new ResponseEntity<>(parcelService.calculateIncomeOfParcelsByCustomerId(customerId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/calculate/count/today")
    public ResponseEntity<Object> getParcelsToDeliverToday() {
        try {
            return new ResponseEntity<>(parcelService.calculateHowManyParcelsNeedToDeliverToday(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
