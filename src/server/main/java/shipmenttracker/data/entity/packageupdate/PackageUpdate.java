package shipmenttracker.data.entity.packageupdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.TemporalField;

@Entity
@Table(name = "package_update")
@IdClass(PackageUpdatePK.class)
public class PackageUpdate {

  @Id
  @Column(name="package_id", nullable = false)
  private int packageId;

  @Id
  @Column(name = "update_date")
  private Timestamp updateDate = new Timestamp(Instant.now().toEpochMilli());

  @Column(name="notes")
  private String notes;

  public PackageUpdate() {}

  public PackageUpdate(int packageId) {
    this.packageId = packageId;
  }

  public PackageUpdate(int packageId, Timestamp updateDate, String notes) {
    this.packageId = packageId;
    this.updateDate = updateDate;
    this.notes = notes;
  }

  public PackageUpdate(int packageId, String notes) {
    this.packageId = packageId;
    this.notes = notes;
  }

  public int getPackageId() {
    return packageId;
  }

  public void setPackageId(int packageId) {
    this.packageId = packageId;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
