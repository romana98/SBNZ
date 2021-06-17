import { UsersModule } from './components/users/users.module';
import { RecommendationModule } from './components/recommendation/recommendation.module';
import { TroubleshootingModule } from './components/troubleshooting/troubleshooting.module';
import { AuthModule } from './components/auth/auth.module';
import { NavigationModule } from './navigation/navigation.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { HttpAuthInterceptor } from "./interceptors/http-auth.interceptor";
import { AppRoutingModule } from "./app-routing/app-routing.module";
import { SharedModule } from "./shared/shared.module";
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    SharedModule,
    NavigationModule,
    AuthModule,
    UsersModule,
    TroubleshootingModule,
    RecommendationModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
