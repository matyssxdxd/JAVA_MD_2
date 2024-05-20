package lv.venta.md2.repo;

import lv.venta.md2.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepo extends CrudRepository<Address, Integer> {
}
