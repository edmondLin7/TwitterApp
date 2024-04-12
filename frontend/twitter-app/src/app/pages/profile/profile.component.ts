import { Component } from '@angular/core';
import { ITweet } from 'src/app/models/tweet.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  tweets?: ITweet[];

  constructor(private dataService: DataService) {
    /*
    this.dataService.getAllTweetsByUser().subscribe((response: ITweet[]) => {
       this.tweets = response;
    });
    */
    
  }
}
