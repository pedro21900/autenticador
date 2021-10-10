import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import * as AppUtils from '../@shared/comum/app.utils';
import { HttpParams, HttpClient } from '@angular/common/http';
import {User} from "../domain/user";



@Injectable({
  providedIn: 'root'
})
export class ApiService {
  public baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = `${AppUtils.BASE_URL}` + 'api/users';
  }

  login(username:string ,password:string): Observable <any> {

    const params = new HttpParams()
      .set('username', username)
      .set('password', password)
      .set('grant_type', 'password');

    const options = {
      headers: AppUtils.HEADERS_COMMUN,
      params
    };
    return this.httpClient.post(AppUtils.URL_TOKEN, null, options);
  }

  getMainUser(token: any): Observable <any> {
    return this.httpClient.get<any>(`${this.baseUrl}` + '/main', AppUtils.OPTIONS_OBJECTO);
  }


}
