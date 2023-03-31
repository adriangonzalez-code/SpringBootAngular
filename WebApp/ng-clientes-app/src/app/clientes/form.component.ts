import {Component, OnInit} from '@angular/core';
import { Cliente } from "./cliente";
import { ClienteService } from "./cliente.service";
import { Router, ActivatedRoute } from "@angular/router";
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit{
  ngOnInit(): void {
    this.cargarCliente();
  }

  protected cliente: Cliente = new Cliente();
  protected titulo: string = "Crear cliente";
  protected errores : string[] = [];

  constructor(private clienteService : ClienteService, private router : Router, private activatedRoute : ActivatedRoute) {
  }

  cargarCliente(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      console.log(id);

      if (id) {
        this.clienteService.getCliente(id).subscribe(cliente => this.cliente = cliente);
      }
    });
  }

  public create(): void {
    console.log("Clicked!");
    console.log(this.cliente);
    this.clienteService.create(this.cliente).subscribe(
      response => {
        this.router.navigate(['/clientes']);
        swal('Nuevo cliente', `El cliente: ${response.nombre} ha sido creado con éxito!`, 'success');
      },

      err => {
        this.errores = err.error.errors as string[];
        console.log('Código del error desde el backend: ' + err.status);
        console.log(err.error.errors);
      }
    );
  }

  update() : void {
    this.clienteService.update(this.cliente).subscribe(response => {
      this.router.navigate(['/clientes']);
      swal('Cliente Actualizado', `${response.mensaje} : ${response.cliente.nombre}`, 'success');
    },
        err => {
        this.errores = err.error.errors as string[];
        console.log('Código del error desde el backend: ' + err.status);
        console.log(err.error.errors);
      });
  }
}
