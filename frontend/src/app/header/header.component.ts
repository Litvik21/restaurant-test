import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isNavbarOpen = false;
  isStaff = false;
  isLoggedIn = false;

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
    if (this.isLoggedIn === false) {
      this.router.navigate(['/register']);
    }
    this.authService.isLoggedIn$.subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
    });
    this.authService.isStaff$.subscribe(isStaff => {
      this.isStaff = isStaff;
    })
  }

  logout(): void {
    this.authService.logout();
  }

  isCurrentTab(tabPath: string): boolean {
    return this.router.isActive(tabPath, true);
  }

}
