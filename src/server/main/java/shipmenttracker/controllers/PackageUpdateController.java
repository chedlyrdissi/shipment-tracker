package shipmenttracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shipmenttracker.data.entity.packageupdate.PackageUpdate;
import shipmenttracker.data.entity.provider.ClientProvider;
import shipmenttracker.data.entity.shipmentpackage.Package;
import shipmenttracker.data.repository.PackageRepository;
import shipmenttracker.data.repository.PackageUpdateRepository;
import shipmenttracker.data.services.AuthService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/package/update")
public class PackageUpdateController {

  private PackageUpdateRepository packageUpdateRepository;
  private PackageRepository packageRepository;
  private AuthService authService;

  @Autowired
  public PackageUpdateController(
    PackageUpdateRepository packageUpdateRepository,
    PackageRepository packageRepository,
    AuthService authService
  ) {
    this.packageUpdateRepository = packageUpdateRepository;
    this.packageRepository = packageRepository;
    this.authService = authService;
  }

  @GetMapping
  public List<PackageUpdate> getPackageUpdateByPackageId(@RequestParam(required = true, name = "packageId") int packageId) throws Exception {
    if(this.packageRepository.existsById(packageId)) {
      return this.packageUpdateRepository.findByPackageId(packageId);
    }
    throw new Exception("Invalid package id.");
  }

  @PostMapping("/{packageId}")
  public Map<String, Object> createPackageUpdate(
    @RequestHeader("Authorization") String credentials,
    @PathVariable int packageId,
    @RequestBody(required = true) Map<String, Object> payload
  ) throws Exception {
    ClientProvider clientProvider = this.authService.authenticate(credentials);
    Optional<Package> optPackage = this.packageRepository.findById(packageId);
    if(optPackage.isEmpty() || !optPackage.get().getProviderName().equals(clientProvider.getProviderName())) {
      throw new Exception("package doesn't belong to user.");
    }
    String notes = (String) payload.get("notes");
    Map<String, Object> resp = new HashMap<>();
    try {
      this.packageUpdateRepository.save(new PackageUpdate(packageId, notes));
      resp.put("status", "Success");
    } catch (Exception e) {
      resp.put("status", "Failure");
      resp.put("Comment", e);
    }
    return resp;
  }

  @PutMapping("/{packageId}")
  public Map<String, Object> packageUpdateUpdateStatus(
    @RequestHeader("Authorization") String credentials,
    @PathVariable int packageId,
    @RequestBody(required = true) Map<String, Object> payload
  ) throws Exception {
    ClientProvider clientProvider = this.authService.authenticate(credentials);
    Optional<Package> optPackage = this.packageRepository.findById(packageId);
    if(optPackage.isEmpty() || !optPackage.get().getProviderName().equals(clientProvider.getProviderName())) {
      throw new Exception("package doesn't belong to user.");
    }
    String status = (String) payload.get("status");
    Map<String, Object> resp = new HashMap<>();
    try {
      this.packageRepository.updateStatus(packageId, status);
      resp.put("status", "Success");
    } catch (Exception e) {
      resp.put("status", "Failure");
      resp.put("Comment", e);
    }
    return resp;
  }
}
