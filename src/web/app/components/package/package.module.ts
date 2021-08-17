import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PackageComponent } from './package.component';

import { PackageService } from '@services/package.service';

@NgModule({
  declarations: [ PackageComponent ],
  imports: [ CommonModule ],
  exports: [ PackageComponent ],
  providers: [ PackageService ],
  bootstrap: []
})
export class PackageModule { }
