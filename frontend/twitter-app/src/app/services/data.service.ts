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

  private BASE_URL = 'http://localhost:9090';
  private TWEET_PREFIX = "/api/v1.0/tweets";
  private AUTH_PREFIX = "/api/v1.0/auth";
  private REPLY_PREFIX = "/api/v1.0/replies";
  constructor(private http: HttpClient) {}

  // Get all tweets
  getAllTweets(): Observable<ITweet[]> {
    return this.http.get<ITweet[]>(`${this.BASE_URL}${this.TWEET_PREFIX}/all`);
  }
  // Get all tweets by user
  getAllTweetsByUser(username: string): Observable<ITweet[]> {
    return this.http.get<ITweet[]>(`${this.BASE_URL}${this.TWEET_PREFIX}/user/${username}`);
  }
  // Post a tweet
  postTweet(tweet: ITweet, username: string): Observable<ITweet> {
    return this.http.post<ITweet>(`${this.BASE_URL}${this.TWEET_PREFIX}/${username}/add`, tweet);
  }

  // Update a tweet
  updateTweet(tweet: ITweet, username: string): Observable<ITweet> {
    return this.http.put<ITweet>(`${this.BASE_URL}${this.TWEET_PREFIX}/${username}/update`, tweet);
  }
  // Delete a tweet
  deleteTweet(tweetId: number, username: string): Observable<ITweet> {
    return this.http.delete<ITweet>(`${this.BASE_URL}${this.TWEET_PREFIX}/${username}/delete/${tweetId}`);
  }
  // like a tweet
  updateLikeTweet(tweetId: number, username: string): Observable<ITweet> {
    return this.http.put<ITweet>(`${this.BASE_URL}${this.TWEET_PREFIX}/${username}/like/${tweetId}`, null);
  }

  getAllReplies(username: string, tweetId: number): Observable<IReply[]> {
    return this.http.get<IReply[]>(`${this.BASE_URL}/${username}/replies/${tweetId}`);
  }

  postReply(reply: IReply, username: string, tweetId: number): Observable<IReply> {
    return this.http.post<IReply>(`${this.BASE_URL}/${username}/replies/${tweetId}`, reply);
  }

  likeReply(replyId: number, username: string): Observable<IReply> {
    return this.http.put<IReply>(`${this.BASE_URL}/${username}/replies/${replyId}/like`, null)
  }
  // Get user by their id
  getUserById(userId: number): Observable<IUser> {
    return this.http.get<IUser>(`${this.BASE_URL}${this.AUTH_PREFIX}/users/id/${userId}`)
  }
  // Get user by their username
  getUserByName(username: string): Observable<IUser> {
    return this.http.get<IUser>(`${this.BASE_URL}${this.AUTH_PREFIX}/users/username/${username}`)
  }
  // Get all users
  getAllUsers(): Observable<IUser[]> {
    return this.http.get<IUser[]>(`${this.BASE_URL}${this.AUTH_PREFIX}/users`)
  }
  
}
