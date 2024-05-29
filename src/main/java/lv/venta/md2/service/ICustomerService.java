package lv.venta.md2.service;

import lv.venta.md2.model.AbstractCustomer;
import lv.venta.md2.model.Address;
import lv.venta.md2.model.CustomerAsCompany;
import lv.venta.md2.model.CustomerAsPerson;

import java.util.ArrayList;
import java.util.Map;

public interface ICustomerService {

    void insertNewCustomerAsPerson(CustomerAsPerson customer) throws Exception;
    void insertNewCustomerAsCompany(CustomerAsCompany customer) throws Exception;
    void addAddressToCustomerById(int customerId, Address address) throws Exception;

    ArrayList<AbstractCustomer> getAllCustomers();
}
