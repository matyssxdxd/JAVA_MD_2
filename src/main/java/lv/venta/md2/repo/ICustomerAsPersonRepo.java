package lv.venta.md2.repo;

import lv.venta.md2.model.CustomerAsPerson;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson, Integer> {
}
