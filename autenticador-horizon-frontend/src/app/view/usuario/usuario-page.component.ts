import {Component} from '@angular/core';
import {UserService} from "../../service/UserService";


@Component({
    selector: 'app-user',
    templateUrl: 'usuario-page.html',
    providers: [UserService]
})
export class UsuarioPageComponent {
    RESOURCE_NAME = 'Ponto de Transmissão';
    constructor() { }
}
