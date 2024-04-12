
// The fields are nullable so we can initialize an empty object
// All fields must be populated (other than userID) prior to sending data
export interface IUser {
    userId?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    loginId?: string;
    password?: string;
    contactNumber?: string
}

export class User implements IUser {
    constructor(
        public userId?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public loginId?: string,
        public password?: string,
        public contactNumber?: string) {}
}