import { Tweet } from "./tweet.model";
import { User } from "./user.model";

export interface IReply {
    replyID: number;
    replyContent: string;
    tag?: string; // Optional field
    timestamp?: Date; 
    tweet?: Tweet;
    user?: User; 
    likeCount?: number;
}
  export class Reply implements IReply {
    constructor(
        public replyContent: string= "",
        public tweet?: Tweet,
        public user?: User,
        public tag: string = "",
        public timestamp?: Date,
        public replyID: number = 0,
        public likeCount: number = 0
    ) {}

}