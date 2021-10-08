import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsuarioListComponent} from "./usuario-list/usuario-list.component";
import {UsuarioEditComponent} from "./usuario-edit/usuario-edit.component";
import {UsuarioPageComponent} from "./usuario-page.component";


export const routes: Routes = [
    {
        path: '', component: UsuarioPageComponent, children: [
            {path: '', component: UsuarioListComponent},
            {
                path: 'edit', children: [
                    {path: '', component: UsuarioEditComponent}

                ]
            }
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UsuarioPageRoutingModule {
}
