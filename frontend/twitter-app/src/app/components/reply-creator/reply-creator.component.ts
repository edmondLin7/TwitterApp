import { Component, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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

    // Call the data service to post the reply
    this.dataService.postReply(this.reply, localStorage.getItem('loginId')!, this.tweet.tweetID!).subscribe((response) => {
      console.log(response);
      console.log(localStorage.getItem('loginId'));
      
      // After successfully posting the reply, navigate to the replyinfo component
      this.router.navigate(['/home']);
    });
    console.log(localStorage.getItem('loginId'));
    // Reset the form and toggle creation form visibility
    this.replyData.reset();
    this.creatingReply = false;
  }
}