import {AccessLevel} from "./acess-level";
import {Company} from "./company";


export class User {
  id?:number;
  username?: string;
  password?: string;
  name?: string;
  company?: Company
  accessLevel?: AccessLevel;

  static from(values:any): User {
    var user=new User();
    Object.assign(user,values);
    return user;
  }
}
