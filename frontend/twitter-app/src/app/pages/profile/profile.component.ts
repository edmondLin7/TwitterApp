import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PasswordResetModalComponent } from 'src/app/components/password-reset-modal/password-reset-modal.component';
import { ITweet } from 'src/app/models/tweet.model';
import { IUser } from 'src/app/models/user.model';
import { DataService } from 'src/app/services/data.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  @ViewChild(PasswordResetModalComponent)
  settingsModal!: PasswordResetModalComponent;

  tweets?: ITweet[];
  user?: IUser;


  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute) {
    var id: number = parseInt(this.activatedRoute.snapshot.paramMap.get("id")!);
    // Get user
    this.dataService.getUserById(id).subscribe((response: IUser) => {
      this.user = response;
      // After user is recieved, get all user's tweets
      this.dataService.getAllTweetsByUser(this.user!.loginId!).subscribe((response: ITweet[]) => {
        this.tweets = response;
     });
    })
  }

  public openSettings() {
    console.log("in openSettings")
    this.settingsModal.openPopup();
  }


}
