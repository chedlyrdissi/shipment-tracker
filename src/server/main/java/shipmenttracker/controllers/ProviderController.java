package shipmenttracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shipmenttracker.data.entity.provider.ClientProvider;
import shipmenttracker.data.entity.provider.Provider;
import shipmenttracker.data.repository.AuthRepository;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/provider")
public class ProviderController {

  private AuthRepository authRepository;

  @Autowired
  public ProviderController(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  @PostMapping
  public ClientProvider createProvider(
    @RequestBody(required = true) Map<String, String> payload
  ) throws Exception {
    if(!payload.containsKey("providerName") || !payload.containsKey("password")) {
      throw new Exception(String.format("Invalid body, body: {0}, {1}",
        (payload.containsKey("providerName")) ? " contains provider name": "missing provider name",
        (payload.containsKey("password")) ? " contains password": "missing password"
      ));
    }
    String providerName = payload.get("providerName");
    String password = payload.get("password");
    Provider provider = new Provider();
    provider.setProviderName(providerName);
    provider.setPassword(password);
    return this.authRepository.createProvider(provider);
  }
}
