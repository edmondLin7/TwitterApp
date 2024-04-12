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
  
  openPopup() {
    console.log("in open popup")
    this.displayStyle = "block"; 
    
  } 
  closePopup() { 
    this.displayStyle = "none"; 
  } 

  passwordResetForm = this.fb.group({
    password: ['', [Validators.required, Validators.minLength(6)]],
    passwordConfirm: ['', [Validators.required, Validators.minLength(6)]],
  }, {validators: this.validatePasswordConfirm})
  

  public onSubmitHandler() {
    let password: string = this.password!.value!;
    console.log("resetting password...")
    this.authService.resetPassword(password).subscribe((response) => {
      console.log(response);
    });
  }

  get password() {
    return this.passwordResetForm.get('password');
  }

  get passwordConfirm() {
    return this.passwordResetForm.get('passwordConfirm');

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


