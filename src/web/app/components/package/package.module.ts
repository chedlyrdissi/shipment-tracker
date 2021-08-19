import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PackageComponent } from './package.component';

import { AuthService } from '@services/auth.service';
import { PackageService } from '@services/package.service';

@NgModule({
  declarations: [ PackageComponent ],
  imports: [ CommonModule, FormsModule ],
  exports: [ PackageComponent ],
  providers: [ AuthService, PackageService ],
  bootstrap: []
})
export class PackageModule { }
