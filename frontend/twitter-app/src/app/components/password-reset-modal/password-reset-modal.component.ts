import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import * as bootstrap from 'bootstrap';
import { DataService } from 'src/app/services/data.service';
import { jwtDecode } from 'jwt-decode';


@Component({
  selector: 'app-password-reset-modal',
  templateUrl: './password-reset-modal.component.html',
  styleUrls: ['./password-reset-modal.component.css']
})
export class PasswordResetModalComponent implements OnInit {

  constructor(private fb: FormBuilder, private authService: AuthService) {
  }

  ngOnInit() {
    this.passwordResetForm.addValidators(checkPasswordsEqual(
      this.password!,
      this.passwordConfirm!
    ))
  } 
  
  displayStyle = "none"; 
  loggedIn: boolean = false;

  openPopup() {
    var token: string = localStorage.getItem("loginToken")!
    console.log(token)
    if (token === null) {
      this.loggedIn = false;
    } else {
      this.loggedIn = true;
    }
    
    console.log("in open popup")
    this.displayStyle = "block";   
  } 

  closePopup() { 
    this.displayStyle = "none"; 
  } 

  passwordResetForm = this.fb.group({
    username: ['', []], 
    password: ['', [Validators.required, Validators.minLength(6)]],
    passwordConfirm: ['', [Validators.required, Validators.minLength(6)]],
  }, {validators: this.validatePasswordConfirm})
  
  // Needs to handle Bad request caused by username existing
  public onSubmitHandler() {
    let password: string = this.password!.value!;
    console.log("resetting password...")
    this.authService.resetPassword(this.username, password).subscribe((response) => {
      console.log(response);

      alert(response.responseMessage)
      if (response.error == false) {
        this.closePopup();
      }

    });
  }

  get password() {
    return this.passwordResetForm.get('password');
  }

  get passwordConfirm() {
    return this.passwordResetForm.get('passwordConfirm');
  }

  get username() {
    if (!this.loggedIn) {
      return this.passwordResetForm.get('username')!.value!;
    } else {
      var token: string = localStorage.getItem("loginToken")!
      var username = jwtDecode(token).sub!;
      console.log(username);
      return username;
    }
    
  }

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
}


function checkPasswordsEqual (controlOne: AbstractControl, controlTwo: AbstractControl): ValidatorFn {
  return () => {
  if (controlOne.value !== controlTwo.value) {
    return { match_error: 'Value does not match' };
  }
  
  return null;
  };
}


