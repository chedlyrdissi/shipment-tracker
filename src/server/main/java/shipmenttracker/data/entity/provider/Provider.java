package shipmenttracker.data.entity.provider;

import org.hibernate.annotations.SQLInsert;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Provider extends ClientProvider {

  @Column(name = "password", nullable = false)
  private String password = null;

  @Column(name = "salt", nullable = false)
  private String salt = null;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
