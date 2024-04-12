import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TweetComponent } from './components/tweet/tweet.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LogoComponent } from './components/logo/logo.component';
import { HttpClientModule } from '@angular/common/http';
import { ProfileComponent } from './pages/profile/profile.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { ErrorPageComponent } from './pages/error-page/error-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './pages/home/home.component';
import { TweetCreatorComponent } from './components/tweet-creator/tweet-creator.component';

import { UserCardComponent } from './components/user-card/user-card.component';
import { UserListComponent } from './pages/user-list/user-list.component';
import { PasswordResetModalComponent } from './components/password-reset-modal/password-reset-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    TweetComponent,
    LoginComponent,
    RegisterComponent,
    MainContentComponent,
    LoginFormComponent,
    RegisterFormComponent,
    NavbarComponent,
    LogoComponent,
    ProfileComponent,
    LogoutComponent,
    ErrorPageComponent,
    HomeComponent,
    TweetCreatorComponent,
    UserCardComponent,
    UserListComponent,
    PasswordResetModalComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
