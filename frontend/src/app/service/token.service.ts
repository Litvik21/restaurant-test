import { Injectable } from '@angular/core';
import { CurrentUser } from '../model/currentUser';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private readonly TOKEN_KEY = 'token';
  private readonly USER_KEY = 'user';

  constructor(private router: Router) {
    this.checkAndRemoveTokenOnUnload();
  }

  private checkAndRemoveTokenOnUnload(): void {
    window.addEventListener('beforeunload', () => {
      this.removeToken();
    });
  }

  getCurrentUser(): CurrentUser {
    const userData = localStorage.getItem(this.USER_KEY);
    return JSON.parse(userData!);
  }

  setCurrentUser(user: CurrentUser): void {
    localStorage.setItem(this.USER_KEY, JSON.stringify(user));
  }

  saveToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }


  removeToken(): void {
    this.router.navigate(['orders']);
    console.log("Token removed!")
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY);
  }
}
