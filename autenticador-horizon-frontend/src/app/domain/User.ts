export class User {
  username?: string;
  password?: string;
  name?: string;
  company?: string
  accessLevel?: string;

  static from(values:any): User {
    var user=new User();
    Object.assign(user,values);
    return user;
  }
}
