import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {SideNavOuterToolbarModule, SideNavInnerToolbarModule, SingleCardModule} from './layouts';
import {
  FooterModule,
  ResetPasswordFormModule,
  CreateAccountFormModule,
  ChangePasswordFormModule,
  LoginFormModule
} from './@shared/components';
import {AuthService, ScreenService, AppInfoService} from './service';
import {UnauthenticatedContentModule} from './unauthenticated-content';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {UsuarioPageModule} from "./view/usuario/usuario-page.module";


@NgModule({
  declarations: [
    AppComponent
  ],
  providers: [ScreenService,
    AppInfoService],
  imports: [
    UsuarioPageModule,
    BrowserModule,
    SideNavOuterToolbarModule,
    SideNavInnerToolbarModule,
    SingleCardModule,
    FooterModule,
    ResetPasswordFormModule,
    CreateAccountFormModule,
    ChangePasswordFormModule,
    LoginFormModule,
    UnauthenticatedContentModule,
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}
