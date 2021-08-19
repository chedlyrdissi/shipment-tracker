import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { AddPackageComponent } from './addpackage.component';
import { PackageService } from '@services/package.service';

@NgModule({
  declarations: [AddPackageComponent],
  imports: [CommonModule, RouterModule],
  exports: [ AddPackageComponent ],
  providers: [ PackageService ],
  bootstrap: [ AddPackageComponent ]
})
export class AddPackageModule { }
