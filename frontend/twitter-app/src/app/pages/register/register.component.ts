import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user.model';
import { AbstractControl, FormBuilder, ValidationErrors, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  userModel = new User(
  )

  submitted: boolean=false;
  alertType: string = "";
  message: string = "";

  // Form declaration
  registerForm = this.fb.group({
    firstName:        ['', [Validators.required]],
    lastName:         ['', [Validators.required]],
    loginId:          ['', [Validators.required]],
    email:            ['', [Validators.required, Validators.email]],
    contactNumber:    ['', [Validators.required, Validators.pattern(/^\d{10}/)]],
    password:         ['', [Validators.required, Validators.minLength(6)]],
    passwordConfirm:  ['', [Validators.required, Validators.minLength(6)]],
  }, {validators: this.validatePasswordConfirm})

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) {
  
  }

  private collectRegistrationData(): User {
    this.userModel.firstName = this.firstName!.value!;
    this.userModel.lastName = this.lastName!.value!;
    this.userModel.loginId = this.loginId!.value!;
    this.userModel.email = this.email!.value!;
    this.userModel.contactNumber = this.contactNumber!.value!;
    this.userModel.password = this.password!.value!;
    return this.userModel;
  }

  // Submit Registration
  onSubmitHandler() {
    console.log('Submitting register')
    this.submitted=true;
    this.userModel = this.collectRegistrationData();
    this.authService.register(this.userModel).subscribe((response: any) => {
      console.log(response)
      this.alertType = "alert alert-success"
      this.message = response.message
      alert(this.message)
      if (!response.error) {
        this.router.navigateByUrl('login')
      }
    })
    // }, (error: any) => {
    //   console.log(error)
    //   this.alertType = "alert alert-danger"
    //   this.message = error.error.message
      
    // })
  }

  // Validator for ensure password and passwordConfirm match
  public validatePasswordConfirm (controls: AbstractControl): ValidationErrors | null {
    let pass: string = controls.get('password')?.value;
    let confirmPass = controls.get('passwordConfirm')?.value
    if (!pass || !confirmPass) {return null}
  
    if (pass === confirmPass) {
      controls.setErrors(null)
    } else {
      controls.setErrors({"notSame": true})
    }
    return pass === confirmPass ? null : { notSame: true }
  }

  // Getters
  get firstName() {
    return this.registerForm.get("firstName");
  }

  get lastName() {
    return this.registerForm.get("lastName");
  }
  get loginId() {
    return this.registerForm.get("loginId");
  }
  get email() {
    return this.registerForm.get("email");
  }
  get contactNumber() {
    return this.registerForm.get("contactNumber");
  }

  get password() {
    return this.registerForm.get('password');
  }

  get passwordConfirm() {
    return this.registerForm.get('passwordConfirm');

  }

}
