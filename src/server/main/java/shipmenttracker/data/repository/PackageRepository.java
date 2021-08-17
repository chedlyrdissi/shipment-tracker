package shipmenttracker.data.repository;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import shipmenttracker.data.entity.shipmentpackage.Package;

import java.util.List;
import java.util.Optional;

public interface PackageRepository extends CrudRepository<Package, Integer> {
  List<Package> findByProviderName(String providerName);
  List<Package> findAll();

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(
    value = "INSERT INTO package (provider_name, source, destination) " +
      "VALUES (:providerName, :source, :destination);",
    nativeQuery = true
  )
  void insert(
    @Param("providerName") String providerName,
    @Param("source") String source,
    @Param("destination") String destination
  );

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(
    value = "UPDATE package SET status = :status WHERE package_id = :packageId ;",
    nativeQuery = true
  )
  void updateStatus (
    @Param("packageId") int packageId,
    @Param("status") String status
  );

  @Query(
    value = "SELECT package_id, provider_name, source, destination, status FROM package " +
      " WHERE CONCAT(package_id, ' ', provider_name, ' ', source, ' ', destination, ' ', status) LIKE :query ;",
    nativeQuery = true
  )
  List<Package> query(@Param("query") String query);

  @Override
  Optional<Package> findById(Integer packageId);
}
