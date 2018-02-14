import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { NgbDropdownConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styles: [ `
    .nav-link { cursor: pointer !important; }
  `],
  providers: [ NgbDropdownConfig ]
})
export class NavbarComponent implements OnInit {

  public isCollapsed = true;

  constructor(
    private config: NgbDropdownConfig,
    private router: Router) { 
      config.placement = 'bottom-right';
  }

  ngOnInit() { }

  toggleMenu() {
    this.isCollapsed = !this.isCollapsed;
  }
  
  goHome() {
    this.router.navigate(['']); 
  }
}
