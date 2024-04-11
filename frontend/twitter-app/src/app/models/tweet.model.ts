import { IUser } from "./user.model";

export interface ITweet {
    tweetId: number;
    user: IUser;
    tweetContent: string;
    tag: string;
    timestamp: Date;
    likeCount: number;
}