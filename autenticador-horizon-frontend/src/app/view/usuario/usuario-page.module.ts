import {NgModule} from '@angular/core';
import {UsuarioListComponent} from "./usuario-list/usuario-list.component";
import {UsuarioPageRoutingModule} from "./usuario-page-routing.module";
import {CommonModule} from '@angular/common';
import {UsuarioPageComponent} from "./usuario-page.component";
import {UsuarioEditComponent} from "./usuario-edit/usuario-edit.component";
import {DxDataGridModule} from "devextreme-angular";


@NgModule({
    declarations: [
        UsuarioPageComponent,
        UsuarioListComponent,
        UsuarioEditComponent
    ],
  imports: [
    UsuarioPageRoutingModule,
    CommonModule,
    DxDataGridModule,
  ]
})
export class UsuarioPageModule {
}
