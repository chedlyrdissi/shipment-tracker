import { Component, Output, EventEmitter } from '@angular/core';
import { SearchService } from '@services/search.service';
import { Package } from '@models/package.model';

@Component({
  selector: 'packages',
  templateUrl: './packages.component.html',
  styleUrls: ['./packages.component.css']
})
export class PackagesComponent {

  @Output() submit: EventEmitter<Package> = new EventEmitter<Package>();
  @Output() query: EventEmitter<string> = new EventEmitter<string>();

  public constructor(private searchService: SearchService) {}

  public runSearch(query: string, event: any = null): void {
    event && event.preventDefault();
    event && event.stopPropagation();
    this.query.emit(query);
  }

  public getPackages(): Package[] {
    return this.searchService.getPackages();
  }

  public emit(shipmentPackage: Package): void {
    this.submit.emit(shipmentPackage);
  }
}
