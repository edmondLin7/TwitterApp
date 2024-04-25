import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private router: Router) {}

  public logout() {
    console.log("logging out")
    localStorage.clear();
    location.reload();
  }

  public search(tag: string) {
    console.log('searching for tag: ' + tag)
    tag = tag.replace("#","")
    this.router.navigate(["searchResult", tag])
      .then(() => window.location.reload())
  }

  isLoggedIn(): boolean {
    if (localStorage.getItem("loginToken") !== null) {
      // console.log("logged in")
      return true;
    }
    return false;
  }
}
