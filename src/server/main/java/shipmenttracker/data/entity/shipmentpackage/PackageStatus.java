package shipmenttracker.data.entity.shipmentpackage;

public enum PackageStatus {
  PENDING("PENDING"),
  DELIVERED("DELIVERED"),
  CANCELED("CANCELED");

  private String code;

  private PackageStatus(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
