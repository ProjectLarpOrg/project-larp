import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent }  from './app.component';
import { AppRoutingModule } from './app-routing.routing';
import { AppService } from './app.service';
import { Globals } from './globals'
import { NavbarComponent, MainLayoutComponent, DefaultLayoutComponent } from './layout';
import { AuthGuard } from './security/guards';
import { AuthenticationService, UserService } from './security/services';
import { LoginComponent } from './security/login';
import { HomeComponent }  from './home';
import { AdminModule } from './admin';

@NgModule({
    imports: [
        BrowserModule, 
        FormsModule, 
        ReactiveFormsModule, 
        HttpClientModule,
        NgbModule.forRoot(),
        AppRoutingModule,
        AdminModule
    ],
    declarations: [
        AppComponent, 
        NavbarComponent, 
        MainLayoutComponent, 
        DefaultLayoutComponent,
        LoginComponent, 
        HomeComponent
    ],
    providers: [
        Globals,
        AuthGuard, 
        AuthenticationService, 
        UserService,
        AppService
    ],
    bootstrap: [ AppComponent ],
})
export class AppModule { }