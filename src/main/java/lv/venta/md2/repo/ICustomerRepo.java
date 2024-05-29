package lv.venta.md2.repo;

import lv.venta.md2.model.AbstractCustomer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepo extends CrudRepository<AbstractCustomer, Integer> {
    boolean existsByCustomerCode(String customerCode);

    AbstractCustomer findByCustomerCode(String customerCode);

    AbstractCustomer findByIdc(int customerId);

    boolean existsByCustomerCodeContains(String personCode);
}
