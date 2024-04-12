import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ITweet } from 'src/app/models/tweet.model';
import { IUser } from 'src/app/models/user.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  tweets?: ITweet[];
  user?: IUser;
  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute) {
    var id: number = parseInt(this.activatedRoute.snapshot.paramMap.get("id")!);
    this.dataService.getUserById(id).subscribe((response: IUser) => {
      this.user = response;
      this.dataService.getAllTweetsByUser(this.user!.loginId!).subscribe((response: ITweet[]) => {
        this.tweets = response;
     });
    })
    

    
    
  }
}
