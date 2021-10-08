export class AccessLevel {
  id?:number
  description?: string;

  static from(values:any): AccessLevel {
    var accessLevel=new AccessLevel();
    Object.assign(accessLevel,values);
    return accessLevel;
  }
}
