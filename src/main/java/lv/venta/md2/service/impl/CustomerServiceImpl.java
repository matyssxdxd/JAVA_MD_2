package lv.venta.md2.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lv.venta.md2.model.*;
import lv.venta.md2.repo.IAddressRepo;
import lv.venta.md2.repo.ICustomerRepo;
import lv.venta.md2.repo.IPersonRepo;
import lv.venta.md2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepo customerRepo;

    @Autowired
    private IPersonRepo personRepo;

    @Autowired
    private IAddressRepo addressRepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void insertNewCustomerAsPerson(CustomerAsPerson customer) throws Exception {
        if (customer == null) throw new Exception("There is no person provided");
        if (customerRepo.existsByCustomerCodeContains(customer.getPerson().getPersonCode())) throw new Exception("Customer already exists");

        personRepo.save(customer.getPerson());
        CustomerAsPerson newCustomer = new CustomerAsPerson(null , customer.getPhoneNo(), customer.getPerson());
        entityManager.persist(newCustomer);
        newCustomer.setCustomerCode();
        customerRepo.save(newCustomer);
    }


    @Override
    @Transactional
    public void insertNewCustomerAsCompany(CustomerAsCompany customer) throws Exception {
        if (customer == null) throw new Exception("There is no company provided");
        if (customerRepo.existsByCustomerCodeContains(customer.getCompanyRegNo())) throw new Exception("Customer already exists");

        CustomerAsCompany newCustomer = new CustomerAsCompany(null, customer.getPhoneNo(), customer.getTitle(), customer.getCompanyRegNo());
        entityManager.persist(newCustomer);
        newCustomer.setCustomerCode();
        customerRepo.save(newCustomer);
    }

    @Override
    public void addAddressToCustomerById(int customerId, Address address) throws Exception {
        if (customerId < 0) throw new Exception("customerId must be greater than 0");
        if (address == null) throw new Exception("There is no address provided");
        if (!customerRepo.existsById(customerId)) throw new Exception("Customer with ID " + customerId + " does not exist");

        AbstractCustomer customer = customerRepo.findByIdc(customerId);
        addressRepo.save(address);
        customer.setAddress(address);
        customerRepo.save(customer);
    }

    @Override
    public ArrayList<AbstractCustomer> getAllCustomers() {
        return (ArrayList<AbstractCustomer>) customerRepo.findAll();
    }
}
