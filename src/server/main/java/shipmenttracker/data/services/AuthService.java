package shipmenttracker.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shipmenttracker.data.entity.credentials.AuthCredentials;
import shipmenttracker.data.entity.provider.ClientProvider;
import shipmenttracker.data.repository.AuthRepository;

@Service
public class AuthService {

  private AuthRepository authRepository;

  @Autowired
  public AuthService(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  public AuthCredentials translate(String credentials) throws Exception {
    return AuthCredentials.translateCredentials(credentials);
  }

  public ClientProvider authenticate(String credentials) throws Exception {
    return this.authRepository.authenticate(credentials);
  }
}
