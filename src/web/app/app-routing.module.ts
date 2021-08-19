import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthModule } from '@components/auth/auth.module';
import { AuthComponent } from '@components/auth/auth.component';

import { ContainersModule } from '@components/containers/containers.module';
import { HomeComponent } from '@components/containers/home/home.component';

import { PackageModule } from '@components/package/package.module';
import { PackageComponent } from '@components/package/package.component';

import { AddPackageModule } from '@components/addpackage/addpackage.module';
import { AddPackageComponent } from '@components/addpackage/addpackage.component';

import { AuthService } from '@services/auth.service';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'package', component: PackageComponent },
  { path: 'signup', component: AuthComponent, data: { login: false } },
  { path: 'login', component: AuthComponent, data: { login: true } },
  { path: 'add', component: AddPackageComponent, canActivate: [ AuthService ] },
  { path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    AuthModule,
    ContainersModule,
    PackageModule,
    AddPackageModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
