import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from 'src/app/models/user.model';

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent {
  @Input() user!: IUser;

  constructor(private router: Router) {

  }

  routeToProfile(userID: number) {
    console.log("routing to: " + `/profile/${userID}`)
    this.router.navigate(['profile', userID])

  }

}
