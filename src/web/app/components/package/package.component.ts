import { Component } from '@angular/core';
import { Package, PackageUpdate } from '@models/package.model';
import { PackageService } from '@services/package.service';
import { AuthService } from '@services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent {

  private packageError: string = null;
  private packageUpdatesError: string = null;
  private errorMsg: string = null;

  sourceModel: string;
  destModel: string;
  selectModel: string;

  public constructor(
    private authService: AuthService,
    private packageService: PackageService,
    private actRoute: ActivatedRoute,
    private router: Router
  ) {
    actRoute.queryParams.subscribe(({ packageId }) => {
      const id = Number(packageId);
      if (isNaN(id)) {
        router.navigate(['/']);
      }
      const { packageObs, packageUpdatesObs } = packageService.loadPackage(id);
      packageObs.subscribe((pkg: Package) => {
        this.sourceModel = pkg.source;
        this.destModel = pkg.destination;
        this.selectModel = pkg.status;
      }, this.setPackageError.bind(this));
      packageUpdatesObs.subscribe(null, this.setPackageUpdatesError.bind(this));
    })
    this.reload = this.reload.bind(this);
  }

  public saveChanges(): void {
    this.packageService.saveChanges(
      this.sourceModel,
      this.destModel,
      this.selectModel
    )
      .subscribe(
        this.reload,
        this.setError
      );
  }

  private setPackageError(): void {
    this.packageError = "Failed to get package.";
    setTimeout(() => {
      this.packageError = null;
    }, 10000)
  }

  private setPackageUpdatesError(): void {
    this.packageUpdatesError = "Failed to fetch package updates.";      
    setTimeout(() => {
      this.packageUpdatesError = null;      
    }, 10000)
  }

  getPackageError(): string {
    return this.packageError;
  }

  getPackageUpdatesError(): string {
    return this.packageUpdatesError;
  }

  public getPackage(): Package {
    return this.packageService.getPackage();
  }

  public getPackageUpdates(): PackageUpdate[] {
    return this.packageService.getPackageUpdates();
  }

  public formatDate(date: string): string {
    return (new Date(date)).toLocaleString()
  }

  public isEdit(): boolean {
    return this.authService.isLoggedIn();
  }

  public updateSelectModel({target: { value }}): void {
    this.selectModel = value;
  }

  private reload(): void {
    this.packageService.reloadPackage();
  }

  private setError({ message }): void {
    this.errorMsg = message;
    setTimeout(() => {
      this.errorMsg = null;
    }, 10000);
  }

  public addNote(note): void {
    this.packageService.addNote(note).subscribe(
      this.reload,
      this.setError
    );
  }
}
