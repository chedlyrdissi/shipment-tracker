package shipmenttracker.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import shipmenttracker.data.entity.packageupdate.PackageUpdate;
import shipmenttracker.data.entity.packageupdate.PackageUpdatePK;

import java.util.List;

public interface PackageUpdateRepository extends CrudRepository<PackageUpdate, PackageUpdatePK> {

  List<PackageUpdate> findByPackageId(int packageId);
  PackageUpdate save(PackageUpdate entity);
}
