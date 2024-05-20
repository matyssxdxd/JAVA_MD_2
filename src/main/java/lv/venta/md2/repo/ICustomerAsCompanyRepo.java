package lv.venta.md2.repo;

import lv.venta.md2.model.CustomerAsCompany;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerAsCompanyRepo extends CrudRepository<CustomerAsCompany, Integer> {
}
