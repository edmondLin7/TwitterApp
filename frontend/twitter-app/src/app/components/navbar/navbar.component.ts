import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {


  public logout() {
    console.log("logging out")
    localStorage.clear();
    location.reload();
  }
}
