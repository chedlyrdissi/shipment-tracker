import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Package } from '@models/package.model';
import { SearchService } from '@services/search.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  public constructor(
    private searchService: SearchService,
    private actRoute: ActivatedRoute,
    private router: Router
  ) {
    actRoute.queryParams.subscribe(({ q }) => {
      this.searchService.search(q);
    })
  }

  public query(q: string): void {
    this.router.navigate([], { queryParams: { q }});
  }

  public selectPackage(shipmentPackage: Package): void {
    this.router.navigate(['/package'], {
      queryParams: { packageId: shipmentPackage.packageId }
    });
  }
}