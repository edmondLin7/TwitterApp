import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user.model';

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

  constructor(private authService: AuthService, private router: Router) {}

  onSubmitHandler() {
    console.log('Submitting register')
    this.submitted=true;
    this.authService.register(this.userModel).subscribe((response: any) => {
      console.log(response)
      this.alertType = "alert alert-success"
      this.message = response.message
      this.router.navigateByUrl('login')
    }, (error: any) => {
      console.log(error)
      this.alertType = "alert alert-danger"
      this.message = error.error.message
      
    })
  }

}
