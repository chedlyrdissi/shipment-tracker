import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { AuthComponent } from './auth.component';
import { AuthService } from '@services/auth.service';

@NgModule({
  declarations: [ AuthComponent ],
  imports: [ HttpClientModule, CommonModule ],
  exports: [ AuthComponent ],
  providers: [ AuthService, HttpClient ],
  bootstrap: [ AuthComponent ]
})
export class AuthModule {
	
}