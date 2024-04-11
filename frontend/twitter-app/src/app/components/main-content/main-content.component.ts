import { Component } from '@angular/core';
import { ITweet } from 'src/app/models/tweet.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.css']
})
export class MainContentComponent {
  tweets?: ITweet[];

  constructor(private dataService: DataService) {
    this.dataService.getAllTweets().subscribe((response: ITweet[]) => {
      this.tweets = response;
    });

  }
}
