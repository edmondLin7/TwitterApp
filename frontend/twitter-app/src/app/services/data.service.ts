import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITweet } from '../models/tweet.model';
import { IReply } from '../models/reply.model';
import { IUser } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private BASE_URL = 'http://localhost:9001/api/v1.0/tweets';

  constructor(private http: HttpClient) {}

  getAllTweets(): Observable<ITweet[]> {
    return this.http.get<ITweet[]>(`${this.BASE_URL}/all`);
  }

  getAllTweetsByUser(loginId: string): Observable<ITweet[]> {
    return this.http.get<ITweet[]>(`${this.BASE_URL}/user/${loginId}`);
  }

  postTweet(tweet: ITweet, loginId: string): Observable<ITweet> {
    return this.http.post<ITweet>(`${this.BASE_URL}/${loginId}/add`, tweet);
  }

  // newly added data
  updateTweet(tweet: ITweet, loginId: string): Observable<ITweet> {
    return this.http.put<ITweet>(`${this.BASE_URL}/${loginId}/update`, tweet);
  }

  deleteTweet(tweetId: number, loginId: string): Observable<ITweet> {
    return this.http.delete<ITweet>(`${this.BASE_URL}/${loginId}/delete/${tweetId}`);
  }

  updateLikeTweet(tweetId: number, loginId: string, requestBody: ITweet): Observable<ITweet> {
    return this.http.put<ITweet>(`${this.BASE_URL}/${loginId}/like/${tweetId}`, requestBody);
  }

  getAllReplies(loginId: string, tweetId: number): Observable<IReply[]> {
    return this.http.get<IReply[]>(`${this.BASE_URL}/${loginId}/replies/${tweetId}`);
  }

  postReply(reply: IReply, loginId: string, tweetId: number): Observable<IReply> {
    return this.http.post<IReply>(`${this.BASE_URL}/${loginId}/replies/${tweetId}/add`, reply);
  }

  getUserById(userId: number): Observable<IUser> {
    return this.http.get<IUser>(`${this.BASE_URL}/users/id/${userId}`)
  }

  getUserByName(loginId: string): Observable<IUser> {
    return this.http.get<IUser>(`${this.BASE_URL}/users/loginId/${loginId}`)
  }

  getAllUsers(): Observable<IUser[]> {
    return this.http.get<IUser[]>(`${this.BASE_URL}/users`)
  }
  
}
