import {environment} from 'src/environments/environment';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {LoadOptions} from "devextreme/data/load_options";
import {HttpParamsAdapter} from "../@core/types/http-params-adapter";
import {User} from "../domain/user";
import {Page} from "../@core/types";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  API_RESOURCE_PATH: string = `${environment.urlbase}/api/users`

  constructor(private http: HttpClient) {
  }

  public insert(resource: User): Observable<User> {
    return this.http.post<User>(this.API_RESOURCE_PATH, resource);
  }
  public update(id: number, resource: User): Observable<User> {
    return this.http.put<User>(`${this.API_RESOURCE_PATH}/${id}`, resource);
  }
  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_RESOURCE_PATH}/${id}`);
  }
  public patch(id: number, resourceValues: any) {
    return this.http.patch(`${this.API_RESOURCE_PATH}/${id}`, User.from(resourceValues));
  }

  public findById(id: number, resource: User): Observable<User> {
    return this.http.post<User>(`${this.API_RESOURCE_PATH}/${id}`, resource);
  }

  public findAll(loadOptions: LoadOptions): Observable<{ data: User[]; totalCount: number | any }> {
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
  }
}
