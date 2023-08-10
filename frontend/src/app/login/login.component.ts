import { Component, OnInit } from '@angular/core';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {TokenService} from "../service/token.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginErrors: string[] = [];
  generalError: string = '';
  username!: string;
  password!: string;
  user: any;

  constructor(private authService: AuthService,
              private router: Router,
              private tokenService: TokenService) { }

  ngOnInit(): void {
  }

  login() {
    this.user = {
      username: this.username,
      password: this.password,
    };
    this.authService.login(this.user).subscribe(
      response => {
        const token = response.token;
        this.authService.isLoggedInSubject.next(true);
        this.tokenService.setCurrentUser({ id: response.id, email: response.email,
          username: response.username, role: response.role});
        this.tokenService.saveToken(token);
        if (response.role === "STAFF") {
          this.authService.isStaffSubject.next(true);
        }
        this.router.navigate(['/orders']);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 500 && error.error && error.error.message) {
          this.loginErrors = [error.error.message];
        } else {
          this.generalError = 'An error occurred while logging.';
          console.error('An error occurred while logging.', error);
        }
      }
    );
  }

}
