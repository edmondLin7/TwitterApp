import { Component, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { Reply } from 'src/app/models/reply.model';
import { ITweet } from 'src/app/models/tweet.model';
import { DataService } from 'src/app/services/data.service';
@Component({
  selector: 'app-reply-creator',
  templateUrl: './reply-creator.component.html',
  styleUrls: ['./reply-creator.component.css']
})
export class ReplyCreatorComponent {
  @Input() tweet!: ITweet;
  
  constructor(private fb: FormBuilder, private dataService: DataService, private router: Router) {}

  creatingReply: boolean = false;
  // Form group for reply content and tag
  replyData = this.fb.group({
    replyContent: [null, [Validators.maxLength(144)]],
    tag: [null, []]
  });
  
  reply: Reply = new Reply();
  // Method to toggle reply creation form visibility
  public toggleCreation() {
    this.creatingReply = !this.creatingReply;
  }
  // Method to handle reply creation
  public onReplyCreate() {
    console.log("I came here");
    var token: string = localStorage.getItem("loginToken")!
    var username = jwtDecode(token).sub!;
    // Call the data service to post the reply
    this.dataService.postReply(this.reply, username, this.tweet.tweetId!).subscribe((response) => {
      console.log(response);
      console.log(username);
      
      // After successfully posting the reply, navigate to the replyinfo component
      // this.router.navigate(['/tweetinfo']);
      location.reload();
    });

    // Reset the form and toggle creation form visibility
    this.replyData.reset();
    this.creatingReply = false;
  }

}