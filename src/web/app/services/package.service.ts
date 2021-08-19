import { Injectable } from '@angular/core';
import { Observable } from 'Rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Package, PackageUpdate } from '@models/package.model';
import { environment } from 'src/web/environments/environment';
import { AuthService } from '@services/auth.service';


@Injectable()
export class PackageService {
  private packageUpdates: PackageUpdate[] | null = null;
  private shipmentPackage: Package = null;

  constructor(private http: HttpClient, private authService: AuthService) {}

  loadPackage(packageId: number): { packageObs: Observable<any>, packageUpdatesObs: Observable<any> } {
    const packageObs = this.http.get(`${environment.api}/api/packages/${packageId}`);
    packageObs.subscribe((pkg: Package) => {
      this.shipmentPackage = pkg;
    });
    const packageUpdatesObs = this.http.get(`${environment.api}/api/package/update`, { params: { packageId: packageId } });
    packageUpdatesObs.subscribe((pkgUpdates: PackageUpdate[]) => {
      this.packageUpdates = pkgUpdates;
    });
    return { packageObs, packageUpdatesObs }
  }

  reloadPackage(): { packageObs: Observable<any>, packageUpdatesObs: Observable<any> } {
    return this.loadPackage(this.shipmentPackage.packageId);
  }

  createPackage(source, destination): Observable<Object> {
    return this.http.post(`${environment.api}/api/packages`, {
      source, destination
    }, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `bearer ${this.authService.getToken()}`
      })
    });
  }

  addNote(notes: string): Observable<Object> {
    return this.http.post(`${environment.api}/api/package/update/${this.shipmentPackage.packageId}`, {
      notes
    }, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `bearer ${this.authService.getToken()}`
      })
    });
  }

  saveChanges(source, destination, status): Observable<Object> {
    console.log(source, destination, status)
    return this.http.put(`${environment.api}/api/packages/${this.shipmentPackage.packageId}`, {
      source, destination, status
    }, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `bearer ${this.authService.getToken()}`
      })
    });
  }

  setPackage(shipmentPackage: Package): void {
    this.shipmentPackage = shipmentPackage;
  }

  setPackageUpdates(packageUpdates: PackageUpdate[] | null): void {
    this.packageUpdates = packageUpdates;
  }

  getPackage(): Package {
    return this.shipmentPackage;
  }

  getPackageUpdates(): PackageUpdate[] | null {
    return this.packageUpdates;
  }
}
