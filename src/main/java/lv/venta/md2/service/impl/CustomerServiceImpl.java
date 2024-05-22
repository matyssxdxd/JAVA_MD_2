package lv.venta.md2.service.impl;

import lv.venta.md2.model.AbstractCustomer;
import lv.venta.md2.model.Address;
import lv.venta.md2.model.CustomerAsCompany;
import lv.venta.md2.model.CustomerAsPerson;
import lv.venta.md2.repo.ICustomerRepo;
import lv.venta.md2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepo customerRepo;

    @Override
    public void insertNewCustomerAsPerson(CustomerAsPerson person) throws Exception {
        if (person != null) throw new Exception("There is no person provided");
        if (customerRepo.existsByCustomerCode(person.getCustomerCode())) throw new Exception("Customer as person already exists");

        customerRepo.save(person);
    }

    @Override
    public void insertNewCustomerAsCompany(CustomerAsCompany company) throws Exception {
        if (company != null) throw new Exception("There is no company provided");
        if (customerRepo.existsByCustomerCode(company.getCustomerCode())) throw new Exception("Customer as company already exists");

        customerRepo.save(company);
    }

    @Override
    public void addAddressToCustomerById(int customerId, Address address) throws Exception {
        if (customerId < 0) throw new Exception("customerId must be greater than 0");
        if (address == null) throw new Exception("There is no address provided");
        if (!customerRepo.existsById(customerId)) throw new Exception("Customer with ID " + customerId + " does not exist");

        AbstractCustomer customer = customerRepo.findByIdc(customerId);
        customer.setAddress(address);
        customerRepo.save(customer);
    }
}
