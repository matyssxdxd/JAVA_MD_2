package lv.venta.md2.repo;

import lv.venta.md2.model.Parcel;
import org.springframework.data.repository.CrudRepository;

public interface IParcelRepo extends CrudRepository<Parcel, Integer> {
}
