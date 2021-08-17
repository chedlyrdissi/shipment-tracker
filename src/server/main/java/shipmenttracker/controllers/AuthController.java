package shipmenttracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shipmenttracker.data.entity.credentials.AuthCredentials;
import shipmenttracker.data.entity.provider.ClientProvider;
import shipmenttracker.data.services.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

  private AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping
  public ClientProvider authenticate(@RequestHeader("Authorization") String credentials) throws Exception {
    return this.authService.authenticate(credentials);
  }

  @GetMapping
  public AuthCredentials test(@RequestHeader("Authorization") String credentials) throws Exception {
    return this.authService.translate(credentials);
  }
}
