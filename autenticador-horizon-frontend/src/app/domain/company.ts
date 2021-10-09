export class Company {
  id?:number;
  description?: string;
  cnpj?: string;
  static from(values:any): Company {
    var company=new Company();
    Object.assign(company,values);
    return company;
  }
}
