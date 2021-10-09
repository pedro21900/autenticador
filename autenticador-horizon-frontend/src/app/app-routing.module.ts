import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginFormComponent, ResetPasswordFormComponent, CreateAccountFormComponent, ChangePasswordFormComponent } from './@shared/components';
import { AuthGuardService } from './service';
import { HomeComponent } from './view/home/home.component';
import { HistoricoComponent } from './view/historico/historico.component';
import { DxDataGridModule, DxFormModule } from 'devextreme-angular';
import {PerfilComponent} from "./view/perfil/perfil.component";


const routes: Routes = [

  { path : 'usuario', loadChildren : () => import('./view/usuario/usuario-page.module').then(m => m.UsuarioPageModule) ,
    canActivate: [ AuthGuardService ]},
  {
    path: 'historico',
    component: HistoricoComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'perfil',
    component: PerfilComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'login-form',
    component: LoginFormComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'reset-password',
    component: ResetPasswordFormComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'create-account',
    component: CreateAccountFormComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'change-password/:recoveryCode',
    component: ChangePasswordFormComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: '**',
    redirectTo: 'home'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true }), DxDataGridModule, DxFormModule],
  providers: [AuthGuardService],
  exports: [RouterModule],
  declarations: [HomeComponent, PerfilComponent, HistoricoComponent]
})
export class AppRoutingModule { }
