package shipmenttracker.data.entity.credentials;

public class BearerAuthCredentials extends AuthCredentials {
  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isBasicCredentials() {
    return false;
  }
  public boolean isBearerCredentials() {
    return true;
  }
}
