import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { PackagesComponent } from './packages.component';
import { SearchPackageComponent } from './package/package.component';

import { SearchService } from '@services/search.service';

@NgModule({
  declarations: [ PackagesComponent, SearchPackageComponent ],
  imports: [ HttpClientModule, CommonModule ],
  exports: [ PackagesComponent ],
  providers: [ SearchService, HttpClient ],
  bootstrap: [ PackagesComponent, SearchPackageComponent ]
})
export class PackagesModule {}
