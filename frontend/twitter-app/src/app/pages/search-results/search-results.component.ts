import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITweet } from 'src/app/models/tweet.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent {
  tweets?: ITweet[];
  searchTag!: string;


  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute) {
    this.searchTag = this.activatedRoute.snapshot.paramMap.get("tag")!;
    
    this.dataService.getAllTweetsByTag(this.searchTag).subscribe((response: ITweet[]) => {
      console.log(response);
      this.tweets = response;
    });
  }
}
