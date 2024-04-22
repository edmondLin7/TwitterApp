import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { IReply } from 'src/app/models/reply.model';
import { ITweet } from 'src/app/models/tweet.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-one-tweet-info',
  templateUrl: './one-tweet-info.component.html',
  styleUrls: ['./one-tweet-info.component.css']
})
export class OneTweetInfoComponent implements OnInit {
  tweet?: ITweet;
  defaultTweet: ITweet = {
    tweetContent: '',
    likeCount: 0
  }; // Provide default values as needed
  replies: IReply[] = [];

  constructor(private router: Router, private dataService: DataService) {}

  ngOnInit(): void {
    const state = window.history.state;

    if (state && state.tweet) {
      this.tweet = state.tweet;
    } else {
      console.error('Tweet information is not available.');
      return;
    }
    
    var token: string = localStorage.getItem("loginToken")!
    const username = jwtDecode(token).sub || '';

    if (!username) {
      console.error('Login ID is not available in local storage.');
      // Consider redirecting to a login page or displaying a message to the user
      return;
    }

    
    // Ensure tweet is defined before fetching replies
    if (!this.tweet || this.tweet.tweetID === undefined) {
      console.error('Tweet information is not available.');
      return;
    }

    this.dataService.getAllReplies(username, this.tweet.tweetID).subscribe(
      (response: IReply[]) => {
        // Log the response data for debugging
        console.log(response);

        // Filter replies to include only those associated with the fetched tweet
        this.replies = response.filter(reply => reply.tweet?.tweetID === this.tweet?.tweetID);
        console.log(this.replies);
      },
      error => {
        console.error('Error fetching replies:', error);
        // Handle error appropriately, such as displaying an error message to the user
      }
    );
  }
}
