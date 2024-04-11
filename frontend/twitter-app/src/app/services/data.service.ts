import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITweet } from '../models/tweet.model';

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
    return this.http.get<ITweet[]>(`${this.BASE_URL}/${loginId}`);
  }

  postTweet(tweet: ITweet, loginId: string): Observable<ITweet> {
    return this.http.post<ITweet>(`${this.BASE_URL}/${loginId}/add`, tweet);
  }

  
}
