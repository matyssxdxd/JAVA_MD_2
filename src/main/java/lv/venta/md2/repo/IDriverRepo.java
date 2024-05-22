package lv.venta.md2.repo;

import lv.venta.md2.model.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IDriverRepo extends CrudRepository<Driver, Integer> {
    Driver findByIdp(int driverId);

    void deleteByIdp(int id);

    boolean existsByPersonCode(String personCode);
}
