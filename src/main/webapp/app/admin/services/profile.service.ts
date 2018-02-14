import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/map';
import { Profile } from './profile.model';

@Injectable()
export class ProfileService { 

    url = 'api/profiles';

    constructor(
        private http: HttpClient) { }

    public readAll(): Promise<Profile[]> {
        const url = this.url + '?size=999&sort=name';
        return this.http.get(url)
            .toPromise()
            .then((response) => (<any>response)._embedded['profiles'] as Profile[]);
    }

    public create(profile: Profile): Promise<Profile> {
        return this.http.post<Profile>(this.url, profile)
            .toPromise();
    }

	public read(id: string): Promise<any> {
        return this.http.get(this.url+'/'+id)
            .toPromise();
    }

	public update(profile: Profile): Promise<Profile> {
        var id = profile.username;
        return this.http.put<Profile>(this.url+'/'+id, profile)
            .toPromise();
    }

    public delete(id: string): Promise<Object> {
        return this.http.delete(`${this.url}/${id}`)
            .toPromise();
    }
}