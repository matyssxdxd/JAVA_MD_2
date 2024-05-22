package lv.venta.md2.service;

import lv.venta.md2.model.Address;
import lv.venta.md2.model.CustomerAsCompany;
import lv.venta.md2.model.CustomerAsPerson;

public interface ICustomerService {

    void insertNewCustomerAsPerson(CustomerAsPerson person) throws Exception;
    void insertNewCustomerAsCompany(CustomerAsCompany company) throws Exception;
    void addAddressToCustomerById(int customerId, Address address) throws Exception;
}
