import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class authGuardGuard implements CanActivate {


  constructor(private authService: AuthService, private router: Router){}

  canActivate(): boolean {
    if (this.authService.getToken()) {
      return true;
    } else {
      this.router.navigateByUrl('login')
      return false;
    }
  }
};
