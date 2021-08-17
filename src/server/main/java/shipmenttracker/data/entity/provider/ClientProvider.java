package shipmenttracker.data.entity.provider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provider")
public class ClientProvider {
  @Id
  @Column(name = "provider_name", nullable = false)
  private String providerName;

  @Column(name = "token")
  private String token  = null;

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
