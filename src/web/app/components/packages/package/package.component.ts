import { Component, Input } from '@angular/core';

@Component({
  selector: 'search-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class SearchPackageComponent {
  @Input() packageId: number;
  @Input() providerName: string;
  @Input() source: string = '';
  @Input() destination: string = '';
  @Input() status: string;
}
