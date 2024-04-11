import { Component, Input } from '@angular/core';
import { ITweet } from 'src/app/models/tweet.model';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent {
  @Input() tweet!: ITweet;

  constructor() {

  }
}
