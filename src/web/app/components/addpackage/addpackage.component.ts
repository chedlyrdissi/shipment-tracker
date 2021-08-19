import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PackageService } from '@services/package.service';

@Component({
  selector: 'add-package',
  templateUrl: './addpackage.component.html',
  styleUrls: ['./addpackage.component.css']
})
export class AddPackageComponent {

  error: string = null;
  success: boolean = false;

  constructor(
    private packageService: PackageService,
    private router: Router
  ) {
    this.packageService = packageService;
  }

  createPackage(e, source, dest): boolean {
    e.preventDefault();
    this.packageService.createPackage(source, dest)
    .subscribe(
      () => {
        this.success = true;
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 1000);
      },
      () => {
        // show error message
        this.error = 'Failed to create package.';
        setTimeout(() => {
          this.error = null;
        }, 10000);
      }
    );
    return false;
  }
}
