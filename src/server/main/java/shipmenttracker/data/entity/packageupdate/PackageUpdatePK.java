package shipmenttracker.data.entity.packageupdate;

import java.io.Serializable;
import java.sql.Timestamp;

public class PackageUpdatePK implements Serializable {
  private int packageId;
  private Timestamp updateDate;
}
