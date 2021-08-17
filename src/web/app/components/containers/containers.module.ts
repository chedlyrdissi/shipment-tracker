import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { PackagesModule } from '@components/packages/packages.module';

import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    PackagesModule
  ],
  providers: [],
  bootstrap: [HomeComponent]
})
export class ContainersModule { }
