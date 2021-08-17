package shipmenttracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shipmenttracker.data.entity.provider.ClientProvider;
import shipmenttracker.data.entity.shipmentpackage.Package;
import shipmenttracker.data.entity.shipmentpackage.PackageStatus;
import shipmenttracker.data.repository.PackageRepository;
import shipmenttracker.data.services.AuthService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/packages")
public class PackageController {
  private final PackageRepository packageRepository;
  private AuthService authService;

  @Autowired
  public PackageController(PackageRepository packageRepository, AuthService authService) {
    this.packageRepository = packageRepository;
    this.authService = authService;
  }

  @GetMapping("/all")
  public List<Package> getAllPackages() {
    return this.packageRepository.findAll();
  }

  @GetMapping
  public List<Package> getPackages(@RequestParam(name = "q", defaultValue = "", required = false) String query) {
    return this.packageRepository.query("%" + query + "%");
  }

  @GetMapping("/{packageId}")
  public Package getPackage(@PathVariable int packageId) throws Exception {
    Optional<Package> pkg = this.packageRepository.findById(packageId);
    if (pkg.isPresent()) {
      return pkg.get();
    }
    throw new Exception("Package not found.");
  }

  @GetMapping("/param")
  public List<Package> getPackagesForProvider(@RequestParam(required = true, name = "providerName") String providerName) {
    return this.packageRepository.findByProviderName(providerName);
  }

  @PostMapping
  public Map<String, String> createPackage(
    @RequestHeader("Authorization") String credentials,
    @RequestBody(required = true) Map<String, String> payload
  ) throws Exception {
    ClientProvider clientProvider = this.authService.authenticate(credentials);
    this.packageRepository.insert(
      clientProvider.getProviderName(),
      payload.get("source"),
      payload.get("destination")
    );
    Map<String, String> resp = new HashMap<String, String>();
    resp.put("Status", "Success");
    return resp;
  }

  @PutMapping("/{packageId}")
  public Package updatePackage(
    @PathVariable int packageId,
    @RequestBody(required = true) Map<String, Object> payload
  ) throws Exception {
    Optional<Package> result = this.packageRepository.findById(packageId);
    if(result.isPresent()) {
      Package shipmentPackage = result.get();
      if(payload.containsKey("source")) {
        shipmentPackage.setSource((String) payload.get("source"));
      }
      if(payload.containsKey("destination")) {
        shipmentPackage.setDestination((String) payload.get("destination"));
      }
      if(payload.containsKey("status")) {
        shipmentPackage.setStatus(PackageStatus.valueOf((String) payload.get("status")));
      }
      return this.packageRepository.save(shipmentPackage);
    }
    throw new Exception("package not found");
  }
}
