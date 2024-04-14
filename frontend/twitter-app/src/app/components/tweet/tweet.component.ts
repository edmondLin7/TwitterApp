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

  constructor(private router: Router) {}

  routeToProfile(userID: number): void {
    console.log("Routing to profile: " + `/profile/${userID}`);
    this.router.navigate(['profile', userID]);
  }

  routeToTweetInfo(tweet: ITweet): void {
    console.log("Routing to tweet info");
    const currentUrl = this.router.url; // Get current URL
    const tweetInfoUrl = '/tweetinfo';

    // Check if the current route is already the tweet info page
    if (currentUrl !== tweetInfoUrl) {
      this.router.navigate([tweetInfoUrl], { state: { tweet } });
    } else {
      console.log("Already on tweet info page.");
    }
  }

  isOnTweetInfoPage(): boolean {
    return this.router.url === '/tweetinfo';
  }
}
