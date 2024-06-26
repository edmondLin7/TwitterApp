import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ITweet, Tweet } from 'src/app/models/tweet.model';
import { DataService } from 'src/app/services/data.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-tweet-creator',
  templateUrl: './tweet-creator.component.html',
  styleUrls: ['./tweet-creator.component.css']
})
export class TweetCreatorComponent {
  
  constructor(private fb: FormBuilder, private dataService: DataService, private router: Router) {

  }

  creatingTweet: boolean = false;
  tweetData = this.fb.group({
    tweetContent: [null, [Validators.maxLength(144)]],
    tag: [null, []]
  })

  tweet: Tweet = new Tweet("");

  public toggleCreation() {
    this.creatingTweet = !this.creatingTweet;
  }

  public onTweetCreate() {
    // this.tweet.tweetContent = this.tweetData.get("tweetContent")!;
    console.log(this.tweet)
    var token: string = localStorage.getItem("loginToken")!
    var username = jwtDecode(token).sub!;
    this.dataService.postTweet(this.tweet, username).subscribe((response) => {
      console.log(response);
      console.log(username)
      location.reload();
    })
    this.creatingTweet = false;
  }
}
