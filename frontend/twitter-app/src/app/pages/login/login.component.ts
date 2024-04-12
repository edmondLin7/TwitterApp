import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {

  }

  submitted: boolean = false;
  message: string = "";
  alertType: string = "";
  loginForm = this.fb.group({
    emailOrUsername: [null, []],
    password: [null, [Validators.required, Validators.minLength(6)]]
  })

  get emailOrUsername() {
    return this.loginForm.get('emailOrUsername')
  }

  get password() {
    return this.loginForm.get('password')
  }

  onLoginHandler() {
    this.submitted = true;
    var loginDetails = {usernameOrEmail: this.loginForm.value.emailOrUsername, password: this.loginForm.value.password}
    localStorage.setItem('loginId', loginDetails.usernameOrEmail!);
    // console.log(loginDetails)
    this.authService.login(loginDetails).subscribe((response: any) => {
      console.log(response)
      this.message = response.message
      localStorage.setItem('loginToken', response.token);
      this.alertType = "alert alert-success"
      this.router.navigateByUrl('')
    }, (error: any) => {
      console.log(error)
      this.message = error.error.message
      this.alertType = "alert alert-danger"
    })
  }
}
