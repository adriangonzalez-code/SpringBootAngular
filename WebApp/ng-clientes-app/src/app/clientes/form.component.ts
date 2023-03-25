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
        swal('Nuevo cliente', `Cliente ${this.cliente.nombre} creado con éxito!`, 'success');
      }
    );
  }

  update() : void {
    this.clienteService.update(this.cliente).subscribe(cliente => {
      this.router.navigate(['/clientes']);
      swal('Cliente Actualizado', `Cliente ${this.cliente.nombre} actualizado con éxito!`, 'success');
    });
  }
}
