import { Component } from '@angular/core';
import { Package, PackageUpdate } from '@models/package.model';
import { PackageService } from '@services/package.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent {
  public constructor(
    private packageService: PackageService,
    private actRoute: ActivatedRoute,
    private router: Router
  ) {
    actRoute.queryParams.subscribe(({ packageId }) => {
      const id = Number(packageId);
      if (isNaN(id)) {
        router.navigate(['/']);
      }
      packageService.loadPackage(id);
    })
  }

  public getPackage(): Package {
    return this.packageService.getPackage();
  }

  public getPackageUpdates(): PackageUpdate[] {
    return this.packageService.getPackageUpdates();
  }
}
