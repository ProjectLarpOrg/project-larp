import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'
import { AuthenticationService } from './authentication.service';
import { User } from '../models';



@Injectable()
export class UserService {

    constructor(
        private http: HttpClient,
        private authenticationService: AuthenticationService) {
    }

    getCurrentUser(): Observable<User> {
        // add authorization header with jwt token
        let headers = new HttpHeaders({ 'Authorization': 'Bearer ' + this.authenticationService.token });
        let options = { headers: headers };

        // get users from api
        return this.http.get<User>('/api/user/whoami', options);
    }
    
    getUsers(): Observable<User[]> {
        // add authorization header with jwt token
        let headers = new HttpHeaders({ 'Authorization': 'Bearer ' + this.authenticationService.token });
        let options = { headers: headers };
        
        // get users from api
        return this.http.get<User[]>('/api/user/all', options);
    }
}