import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { HeaderComponent } from './header.component';


@NgModule({
  declarations: [ HeaderComponent ],
  imports: [ CommonModule, RouterModule ],
  exports: [ HeaderComponent ],
  providers: [],
  bootstrap: [ HeaderComponent ]
})
export class HeaderModule { }
