import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ITweet } from 'src/app/models/tweet.model';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent {
  @Input() tweet!: ITweet;

  constructor(private router: Router) {

  }

  routeToProfile(userID: number) {
    this.router.navigate(['/profile', userID])

  }
}
