package lv.venta.md2.controller;

import jakarta.validation.Valid;
import lv.venta.md2.model.Address;
import lv.venta.md2.model.CustomerAsCompany;
import lv.venta.md2.model.CustomerAsPerson;
import lv.venta.md2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/show/all")
    public ResponseEntity<Object> getCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping("/create/person")
    public ResponseEntity<Object> createCustomerAsPerson(@RequestBody @Valid CustomerAsPerson customer, BindingResult result) {
        if (result.hasErrors()) return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            customerService.insertNewCustomerAsPerson(customer);
            return new ResponseEntity<>("CustomerAsPerson created", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create/company")
    public ResponseEntity<Object> createCustomerAsCompany(@RequestBody @Valid CustomerAsCompany customer, BindingResult result) {
        if (result.hasErrors()) return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);

        try {
            customerService.insertNewCustomerAsCompany(customer);
            return new ResponseEntity<>("CustomerAsCompany created", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/add/address/{customerId}")
    public ResponseEntity<Object> updateCustomerAddress(@RequestBody @Valid Address address, @PathVariable int customerId) {
        try {
            customerService.addAddressToCustomerById(customerId, address);
            return new ResponseEntity<>("Customer address updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
