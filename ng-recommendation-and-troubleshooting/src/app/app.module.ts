import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from "./app.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {SharedModule} from "./shared/shared.module";
import {NavigationModule} from "./components/navigation/navigation.module";
import {MaterialModule} from "./shared/material.module";
import {AuthModule} from "./components/auth/auth.module";
import {UsersModule} from "./components/users/users.module";
import {TroubleshootingModule} from "./components/troubleshooting/troubleshooting.module";
import {RecommendationModule} from "./components/recommendation/recommendation.module";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import {HttpAuthInterceptor} from "./interceptors/http-auth.interceptor";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NavigationModule,
    SharedModule,
    MaterialModule,
    AuthModule,
    UsersModule,
    TroubleshootingModule,
    RecommendationModule,
    AppRoutingModule

  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
