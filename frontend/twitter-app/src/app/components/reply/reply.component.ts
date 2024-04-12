import { Component, Input } from '@angular/core';
import { IReply } from 'src/app/models/reply.model';

@Component({
  selector: 'app-reply',
  templateUrl: './reply.component.html',
  styleUrls: ['./reply.component.css']
})
export class ReplyComponent {
  @Input() reply!: IReply;

  constructor() {

  }
}
