import { ITweet, Tweet } from "./tweet.model";
import { IUser } from "./user.model";

export interface IReply {
    replyId: number;
    replyContent: string;
    tag?: string; // Optional field
    timestamp?: Date; 
    tweet?: ITweet;
    user?: IUser; 
    likeCount?: number;
}
  export class Reply implements IReply {
    constructor(
        public replyContent: string= "",
        public tweet?: ITweet,
        public user?: IUser,
        public tag: string = "",
        public timestamp?: Date,
        public replyId: number = 0,
        public likeCount: number = 0
    ) {}

}