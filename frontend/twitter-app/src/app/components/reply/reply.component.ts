import { Component, Input } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
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
    var token: string = localStorage.getItem("loginToken")!
    var currentUser = jwtDecode(token).sub!;
    if (currentUser == null) return;
    this.dataService.likeReply(this.reply.replyID!, currentUser).subscribe(
      (response) => {
        this.reply.likeCount = response.likeCount;
      }
    );
  }
}
