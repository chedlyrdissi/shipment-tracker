import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthModule } from '@components/auth/auth.module';
import { AuthComponent } from '@components/auth/auth.component';

import { ContainersModule } from '@components/containers/containers.module';
import { HomeComponent } from '@components/containers/home/home.component';

import { PackageModule } from '@components/package/package.module';
import { PackageComponent } from '@components/package/package.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'package', component: PackageComponent },
  { path: 'login', component: AuthComponent },
  { path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    AuthModule,
    ContainersModule,
    PackageModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
