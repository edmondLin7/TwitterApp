import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import * as bootstrap from 'bootstrap';
import { DataService } from 'src/app/services/data.service';


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
    let loginId: string = localStorage.getItem("loginId")!;
    if (!loginId) {
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
    loginId: ['', [Validators.required]], 
    password: ['', [Validators.required, Validators.minLength(6)]],
    passwordConfirm: ['', [Validators.required, Validators.minLength(6)]],
  }, {validators: this.validatePasswordConfirm})
  

  public onSubmitHandler() {
    let password: string = this.password!.value!;
    console.log("resetting password...")
    this.authService.resetPassword(this.loginId, password).subscribe((response) => {
      console.log("response")
      console.log(response);
      if (!(typeof(response.error) === 'boolean')) {
        
        alert(response.error.responseMessage)
      } else {
        alert(response.responseMessage)

      }
    });
  }

  get password() {
    return this.passwordResetForm.get('password');
  }

  get passwordConfirm() {
    return this.passwordResetForm.get('passwordConfirm');
  }

  get loginId() {
    if (!this.loggedIn) {
      return this.passwordResetForm.get('loginId')!.value!;
    } else {
      return localStorage.getItem("loginId")!;
    }
    
  }

  public validatePasswordConfirm (controls: AbstractControl): ValidationErrors | null {
    console.log(`validating ${controls.get('password')?.value} and ${controls.get('passwordConfirm')?.value}`)
    let pass: string = controls.get('password')?.value;
    let confirmPass = controls.get('passwordConfirm')?.value
    if (!pass || !confirmPass) {return null}
  
    if (pass === confirmPass) {
      console.log("validated " + pass + " of type " + typeof(pass));
      controls.setErrors(null)
    } else {
      console.log("Not validated")
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


