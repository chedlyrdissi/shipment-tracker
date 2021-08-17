import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Package, PackageUpdate } from '@models/package.model';
import { environment } from 'src/web/environments/environment';


@Injectable()
export class PackageService {
  private packageUpdates: PackageUpdate[] = [];
  private shipmentPackage: Package = null;

  constructor(private http: HttpClient) {}

  loadPackage(packageId: number): void {
    this.http.get(`${environment.api}/api/packages/${packageId}`)
      .subscribe((pkg: Package) => {
        this.shipmentPackage = pkg;
      });
    this.http.get(`${environment.api}/api/package/update`, { params: { packageId: packageId } })
      .subscribe((pkgUpdates: PackageUpdate[]) => {
        this.packageUpdates = pkgUpdates;
      });
  }

  setPackage(shipmentPackage: Package): void {
    this.shipmentPackage = shipmentPackage;
  }

  setPackageUpdates(packageUpdates: PackageUpdate[]): void {
    this.packageUpdates = packageUpdates;
  }

  getPackage(): Package {
    return this.shipmentPackage;
  }

  getPackageUpdates(): PackageUpdate[] {
    return this.packageUpdates;
  }
}
