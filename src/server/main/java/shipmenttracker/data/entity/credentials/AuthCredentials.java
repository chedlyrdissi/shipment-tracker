package shipmenttracker.data.entity.credentials;

import java.util.Base64;

public abstract class AuthCredentials {
  public abstract boolean isBasicCredentials();
  public abstract boolean isBearerCredentials();

  public static AuthCredentials translateCredentials(String credentials) throws Exception {
    String[] creds = credentials.split(" ");
    if (creds.length != 2) {
      throw new Exception(String.format("Invalid Credentials header: length {0}", creds.length));
    }
    if(!creds[0].equalsIgnoreCase("Basic") && !creds[0].equalsIgnoreCase("Bearer")) {
      throw new Exception("Invalid Credentials header: " + creds[0]);
    }
    AuthCredentials credentialsObject = null;
    if(creds[0].equalsIgnoreCase("Basic")) {
      String[] credStrings = (new String(Base64.getDecoder().decode(creds[1]))).split(":");
      credentialsObject = new BasicAuthCredentials();
      ((BasicAuthCredentials) credentialsObject).setUsername(credStrings[0]);
      ((BasicAuthCredentials) credentialsObject).setPassword(credStrings[1]);
    }
    if(creds[0].equalsIgnoreCase("Bearer")) {
      credentialsObject = new BearerAuthCredentials();
      ((BearerAuthCredentials) credentialsObject).setToken(creds[1]);
    }
    return credentialsObject;
  }
}
