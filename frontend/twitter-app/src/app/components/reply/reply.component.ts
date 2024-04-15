import { Component, Input } from '@angular/core';
import { IReply } from 'src/app/models/reply.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-reply',
  templateUrl: './reply.component.html',
  styleUrls: ['./reply.component.css']
})
export class ReplyComponent {
  @Input() reply!: IReply;

  constructor(private dataService: DataService) {

  }

  likeReply() {
    var currentUser = localStorage.getItem("loginId")!;
    if (currentUser == null) return;
    this.dataService.likeReply(this.reply.replyID!, currentUser).subscribe(
      (response) => {
        this.reply.likeCount = response.likeCount;
      }
    );
  }
}
