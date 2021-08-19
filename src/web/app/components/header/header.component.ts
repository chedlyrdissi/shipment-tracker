import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '@services/auth.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(
    private authService: AuthService,
    private actRoute: ActivatedRoute,
    private router: Router
  ) {}

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  getProviderName(): string {
    return this.authService.getProviderName();
  }

  navigateLogIn(): void {
    this.navigate("/login")
  }

  navigateSignUp(): void {
    this.navigate("/signup")
  }

  navigate(target: string): void {
    const splitUrl = this.router.routerState.snapshot.url.split('?');
    if (splitUrl[0] === target) {
      return
    }
    if(target === '/signup' && splitUrl[0] === '/login'
      || target === '/login' && splitUrl[0] === '/signup') {
      this.router.navigateByUrl(`${target}${splitUrl.length > 1? '?' + splitUrl[1]: ''}`);
      return
    }
    if (target === '/signup' || target === '/login') {
      this.router.navigate([target], {
        queryParams: {
          redirect: this.router.routerState.snapshot.url
        }
      });
      return
    }
  }

  logOut(): void {
    this.authService.logOut();
  }
}

// signup signup => nothing
// login login => nothing
// signup login => go login + copy only params
// login signup => go signup + copy only params
// none signup => navigate + copy url
// none login => navigate + copy url

