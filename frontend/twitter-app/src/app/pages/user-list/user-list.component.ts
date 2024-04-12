import { Component } from '@angular/core';
import { IUser } from 'src/app/models/user.model';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  users?: IUser[];
  constructor(private dataService: DataService) {
    dataService.getAllUsers().subscribe((response: IUser[]) => {
      this.users = response;
    })
  }
}
