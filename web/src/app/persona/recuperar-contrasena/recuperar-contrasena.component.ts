import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService, Message } from 'primeng/api';
import { ServiceService } from 'src/app/Service/service.service';

@Component({
  selector: 'app-recuperar-contrasena',
  templateUrl: './recuperar-contrasena.component.html',
  styleUrls: ['./recuperar-contrasena.component.css'],
  providers: [MessageService],
})
export class RecuperarContrasenaComponent implements OnInit {
  mostrar: Boolean = false;

  public form: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]]
  });

  constructor(
    private formBuilder: FormBuilder,
    public fireAuth: AngularFireAuth,
    public authService: ServiceService,
    private messageService: MessageService,
    private route: Router,
  ) { }

  ngOnInit(): void {
  }

  recuperarContrasenia(){
      this.authService.resetPassword(this.form.value.email).then(
        (result) => {
          if(result){
            this.messageService.add({
              severity: 'success',
              summary: '!Exitoso¡',
              detail: 'Mensaje enviado correctamente',
            });
            setTimeout(() => {
              this.route.navigate(['login']);
            }, 2000);
          }else{
            this.messageService.add({
              severity: 'error',
              summary: '!Error',
              detail: 'Por favor verifica tu correo ya que no pudimos enviar tu correo',
            });
          }
        }
      )
  }

}
