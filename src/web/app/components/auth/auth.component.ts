import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Provider } from '@models/provider.model';

@Component({
  selector: 'auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {

  errorMsg: string = null;
  isLogIn: boolean = true;

  constructor(
    private authService: AuthService,
    private router: Router,
    private actRoute: ActivatedRoute
  ) {
    this.handleSuccess = this.handleSuccess.bind(this);
    this.handleError = this.handleError.bind(this);
    this.clearError = this.clearError.bind(this);
    actRoute.data.subscribe(({ login }) => this.isLogIn = login);
  }

  public errorExists(): boolean {
    return this.errorMsg !== null;
  }

  private clearError(): void {
    this.errorMsg = null;
  }

  private handleSuccess(provider: Provider): void {
    this.router.navigateByUrl(this.actRoute.snapshot.queryParams.redirect || '/');
  }

  private handleError(error: HttpErrorResponse): void {
    this.errorMsg = 'Invalid credentials'; 
    setTimeout(this.clearError, 10000);
  }

  public submit(providerName: string, password: string): boolean {
    this.authService.logIn(providerName, password).subscribe(
      this.handleSuccess,
      this.handleError
    );
    return false;
  }
}
