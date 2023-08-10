import { Component, OnInit } from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registrationErrors: string[] = [];
  generalError: string = '';
  email!: string;
  username!: string;
  password!: string;
  repeatPassword!: string;
  newUser: any;

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  register() {
    this.newUser = {
      email: this.email,
      username: this.username,
      password: this.password,
      repeatPassword: this.repeatPassword,
    };
    console.log(this.newUser);
    this.authService.registerWithSubscription(this.newUser).subscribe(
      user => {
        this.router.navigate(['/login']);
      },
      (error: HttpErrorResponse) => {
        if (error.status === 400 && error.error && error.error.errors) {
          this.registrationErrors = error.error.errors;
        } else {
          this.generalError = 'An error occurred while registering.';
          console.error('An error occurred while registering.', error);
        }
      }
    );
  }

}
