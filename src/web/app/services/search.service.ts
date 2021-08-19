import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Package } from '@models/package.model';
import { environment } from 'src/web/environments/environment';


@Injectable()
export class SearchService {

  private packages: Package[] | null = null;
  
  constructor(private http: HttpClient) {}

  public search(query): any {
    this.http.get(`${environment.api}/api/packages`, { params: { q: query } })
      .subscribe((pkgs: Package[]) => {
        this.packages = pkgs;
      });
  }

  public getPackages(): Package[] | null {
    return this.packages;
  }
}
