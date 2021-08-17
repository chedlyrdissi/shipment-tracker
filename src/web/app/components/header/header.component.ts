import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@services/auth.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  getProviderName(): string {
    return this.authService.getProviderName();
  }

  navigateLogIn(): void {
    this.router.navigate(['/login'], { queryParams: { redirect: this.router.routerState.snapshot.url }});
  }

  logOut(): void {
    this.authService.logOut();
  }
}
