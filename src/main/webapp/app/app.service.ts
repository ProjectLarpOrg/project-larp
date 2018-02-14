import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

export class AppConfig {
    geoserverWorkspace: string
    mapUploadUrl: string
}

@Injectable()
export class AppService {

    constructor(
        private http: HttpClient) { }

	getConfig(): Promise<AppConfig> {
        const url = 'api/app/config';
        return this.http.get<AppConfig>(url)
            .toPromise();
    }

}
