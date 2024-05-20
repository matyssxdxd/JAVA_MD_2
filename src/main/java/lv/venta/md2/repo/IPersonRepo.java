package lv.venta.md2.repo;

import lv.venta.md2.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonRepo extends CrudRepository<Person, Integer> {
}
