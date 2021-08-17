package shipmenttracker.data.entity.shipmentpackage;

import javax.persistence.*;

@Entity
@Table(name = "package")
public class Package {

  @Id
  @Column(name = "package_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer packageId;

  @Column(name = "provider_name", nullable = false)
  private String providerName;

  @Column(name = "source", nullable = false)
  private String source;

  @Column(name = "destination", nullable = false)
  private String destination;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private PackageStatus status = PackageStatus.PENDING;

  public Integer getPackageId() {
    return packageId;
  }

  public void setPackageId(Integer packageId) {
    this.packageId = packageId;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public PackageStatus getStatus() {
    return status;
  }

  public void setStatus(PackageStatus status) {
    this.status = status;
  }
}
