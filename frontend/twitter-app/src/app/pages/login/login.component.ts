import { Component, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PasswordResetModalComponent } from 'src/app/components/password-reset-modal/password-reset-modal.component';
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

  @ViewChild(PasswordResetModalComponent)
  settingsModal!: PasswordResetModalComponent;

  get emailOrUsername() {
    return this.loginForm.get('emailOrUsername')
  }

  get password() {
    return this.loginForm.get('password')
  }

  onLoginHandler() {
    this.submitted = true;
    var loginDetails = {usernameOrEmail: this.loginForm.value.emailOrUsername, password: this.loginForm.value.password}
    
    // console.log(loginDetails)
    this.authService.login(loginDetails).subscribe((response: any) => {
      console.log(response)
      this.message = response.message
      alert(response.message)
      if (!response.error) {
        localStorage.setItem('loginToken', response.token);
        this.alertType = "alert alert-success"
        this.router.navigateByUrl('')
      }
    }, (error: any) => {
      console.log(error)
      this.message = "Login failed please try again"
      this.alertType = "alert alert-danger"
    })
  }

  public openPasswordReset() {
    console.log("in openSettings")
    this.settingsModal.openPopup();
  }
}
