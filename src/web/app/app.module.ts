import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AuthModule } from '@components/auth/auth.module';
import { HeaderModule } from '@components/header/header.module';
import { PackagesModule } from '@components/packages/packages.module';
import { AddPackageModule } from '@components/addpackage/addpackage.module';
import { ContainersModule } from '@components/containers/containers.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AuthService } from '@services/auth.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    HeaderModule,
    AddPackageModule,
    ContainersModule
  ],
  providers: [ AuthService ],
  bootstrap: [ AppComponent ]
})
export class AppModule {}
