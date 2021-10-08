import {Component, ViewChild} from '@angular/core';
import DataSource from 'devextreme/data/data_source';
import {HttpParamsAdapter} from "../../../@core/types/http-params-adapter";
import {LoadOptions} from "devextreme/data/load_options";
import CustomStore from "devextreme/data/custom_store";
import {Title} from "@angular/platform-browser";
import {DxDataGridComponent} from "devextreme-angular";
import {UserService} from "../../../service/UserService";
import {User} from "../../../domain/user";

@Component({
    selector: 'app-transmissao',
    templateUrl: 'usuario-list.component.html',
    styles: [],
    providers: [UserService]
})
export class UsuarioListComponent {

   // @ViewChild(DxDataGridComponent, { static: false }) dataGrid: DxDataGridComponent;



    RESOURCE_NAME = 'Ponto de Transmissão';

    // Datasource do componente DataGrid
    dataSource: DataSource;

    constructor(private service: UserService, private title: Title) {
        this.title.setTitle(this.RESOURCE_NAME);
        this.dataSource = new DataSource({
            store: new CustomStore({
                key: 'id',
                load: (options: LoadOptions) => {
                    return this.service.findAll(options).toPromise();
                },
                insert: (values) => {
                    return service.insert(values).toPromise();
                },
                update: (key, values) => {
                    // var pontoTransmissao = new PontoTransmissao()
                    // Object.assign(pontoTransmissao, values)
                    // console.log(pontoTransmissao);
                    // // return service.patch(key, values).toPromise();
                    // return Promise.resolve()
                    return service.patch(key, User.from(values)).toPromise()
                },
                remove: (key) => {
                   return  service.delete(key).toPromise();
                }
            }),
            sort: [{selector: 'nome', desc: false}]
        })
    }
  customizeText():any {
    return "************";
  }

}
