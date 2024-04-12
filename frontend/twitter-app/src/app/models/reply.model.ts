import { User } from "./user.model";

export interface IReply {
    replyID: number;
    replyContent: string;
    tag?: string; // Optional field
    timestamp?: Date; 
    tweetID?: number;
    user?: User; 
    like_count?: number;
  }
  