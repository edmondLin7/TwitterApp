import { IUser } from "./user.model";

export interface ITweet {
    tweetID?: number;
    user?: IUser;
    tweetContent: string;
    tag?: string;
    timestamp?: Date;
    likeCount: number;
}

export class Tweet implements ITweet{
    likeCount: number;
    constructor(
        public tweetContent: string,
        public tweetID?: number,
        public user?: IUser,
        public tag?: string,
        public timestamp?: Date,
    ) {
        this.likeCount = 0;
    }

}