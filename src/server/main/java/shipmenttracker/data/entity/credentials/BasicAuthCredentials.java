package shipmenttracker.data.entity.credentials;

public class BasicAuthCredentials extends AuthCredentials {
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isBasicCredentials() {
    return true;
  }
  public boolean isBearerCredentials() {
    return false;
  }
}
