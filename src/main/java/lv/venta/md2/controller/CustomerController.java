package lv.venta.md2.controller;

import jakarta.validation.Valid;
import lv.venta.md2.model.Address;
import lv.venta.md2.model.CustomerAsCompany;
import lv.venta.md2.model.CustomerAsPerson;
import lv.venta.md2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/show/all")
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/create/person")
    public ResponseEntity<Object> createCustomerAsPerson(@RequestBody @Valid CustomerAsPerson customer, BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.status(400).body(result.getAllErrors());

        try {
            customerService.insertNewCustomerAsPerson(customer);
            return ResponseEntity.ok("CustomerAsPerson created");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create/company")
    public ResponseEntity<Object> createCustomerAsCompany(@RequestBody @Valid CustomerAsCompany customer, BindingResult result) {
        if (result.hasErrors()) return ResponseEntity.status(400).body(result.getAllErrors());

        try {
            customerService.insertNewCustomerAsCompany(customer);
            return ResponseEntity.ok("CustomerAsCompany created");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PatchMapping("/add/address/{customerId}")
    public ResponseEntity<Object> updateCustomerAddress(@RequestBody @Valid Address address, @PathVariable int customerId) {
        try {
            customerService.addAddressToCustomerById(customerId, address);
            return ResponseEntity.ok("Customer address updated");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
