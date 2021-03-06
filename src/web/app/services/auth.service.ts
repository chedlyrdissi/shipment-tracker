import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Provider } from '@models/provider.model';
import { environment } from 'src/web/environments/environment';

@Injectable()
export class AuthService implements CanActivate {

	constructor(private http: HttpClient) {}

	public logIn(providerName: string, password: string): any | Observable<Provider> {
		const obs = this.http.post<Provider>(`${environment.api}/api/auth`,
			{},
			{
				headers: new HttpHeaders({
	        'Content-Type':  'application/json',
	        'Authorization': `Basic ${btoa(providerName + ':' + password)}`
	    	})
	    }
	  );
	  obs.subscribe(this.saveToSession);
		return obs;
	}

	public isLoggedIn(): boolean {
		return this.getFromSession() !== null;
	}

	public logOut(): void {
		sessionStorage.removeItem('provider');
	}

	public getProviderName(): string {
		return this.getFromSession().providerName;		
	}

	public getToken(): string {
		return this.getFromSession().token;		
	}

	private saveToSession(provider: Provider): void {
		sessionStorage.setItem('provider', JSON.stringify(provider));
	}

	private getFromSession(): Provider {
		return JSON.parse(sessionStorage.getItem('provider'));
	}

	canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    return this.isLoggedIn();
  }
}