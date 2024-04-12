export interface IReply {
    replyid: number;
    reply_content: string;
    tag?: string; // Optional field
    timestamp?: Date; 
    tweetid?: number;
    userid?: number; 
    like_count?: number;
  }
  