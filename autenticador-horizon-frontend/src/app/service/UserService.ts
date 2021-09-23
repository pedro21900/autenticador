import {Page} from './../@core/types/page';
import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from 'src/environments/environment';
import {Observable, of, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {LoadOptions} from "devextreme/data/load_options";
import {HttpParamsAdapter} from "../@core/types/http-params-adapter";
import {User} from "../domain/User";

@Injectable(({
  providedIn: 'root',
}))
export class UserService {
  API_RESOURCE_PATH: string = `${environment.urlbase}/users`

  constructor(private http: HttpClient) {
  }

  public insert(resource: User): Observable<User> {
    return this.http.post<User>(this.API_RESOURCE_PATH, resource);
  }
  public update(id: number, resource: User): Observable<User> {
    return this.http.put<User>(`${this.API_RESOURCE_PATH}/${id}`, resource);
  }
  public delete(id: number, resource: User): Observable<User> {
    return this.http.delete<User>(`${this.API_RESOURCE_PATH}/${id}`);
  }

  public findById(id: number, resource: User): Observable<User> {
    return this.http.post<User>(`${this.API_RESOURCE_PATH}/${id}`, resource);
  }

/*  public FindAll(loadOptions: LoadOptions): Observable<{ data: User[]; totalCount: number  }> {
    const params = new HttpParamsAdapter(loadOptions).httpParams();
    return this.http.get<Page<User>>(this.API_RESOURCE_PATH, {params})
      .pipe(
        map((page: Page<User>) => ({
          data: page.content,
          totalCount: page.totalElements
        })),
        catchError(error => {
          return throwError(new Error(error.message))
        })
      )
  }*/
}
