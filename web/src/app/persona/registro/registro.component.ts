import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService, Message } from 'primeng/api';
import { User } from 'src/app/models/user';
import { QuestionService } from 'src/app/Service/question.service';
import { ServiceService } from 'src/app/Service/service.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss'],
  providers: [MessageService],
})
export class RegistroComponent implements OnInit {
  userData2!: any;
  mostrar: Boolean = false;
  val1: number = 3;

  public form: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    rating: ['', []],
  });

  constructor(
    private formBuilder: FormBuilder,
    public fireAuth: AngularFireAuth,
    private messageService: MessageService,
    private authService: ServiceService,
    private route: Router,
  ) {}

  ngOnInit(): void {}

  ingresar() {
    this.mostrar = !this.mostrar;
    this.authService
      .register(this.form.value.email, this.form.value.password)
      .then((res) => {
        if (res) {
        //  this.getUserLogged();
        this.authService.getUserLogged2();
        setTimeout(() => {
        this.userData2= JSON.parse(localStorage.getItem('user')!);
        const userData: User = {
          uid:  this.userData2.uid,
          email:  this.userData2.email,
          displayName:  '',
          photoURL:  '',
          emailVerified:  this.userData2.emailVerified,
        };
          this.sendEmailVerificacion();
          this.authService.setUserData(userData);
          this.messageService.add({
            severity: 'success',
            summary: '!Exitoso¡',
            detail: 'Usuario Registrado Correctamente',
          });
          
            this.route.navigate(['preguntas']);
          }, 2000);
        } else {
          this.messageService.add({
            severity: 'error',
            summary: 'Usuarios Registrado',
            detail: 'Por favor intente con otro correo',
          });
        }

        this.mostrar = !this.mostrar;
      });
  }

  loginWithGoogle() {
    this.mostrar = !this.mostrar;
    this.authService
      .loginGoogle()
      .then((res) => {
        this.mostrar = !this.mostrar;
      });
  }
  getUserLogged() {
    this.authService.getUserLogged().subscribe((res) => {
    });
  }

  preguntasHome() {
    this.route.navigate(['preguntas']);
  }

  //TODO: Utilidades
  showSuccess() {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Message Content',
    });
  }
  showInfo() {
    this.messageService.add({
      severity: 'info',
      summary: 'Info',
      detail: 'Message Content',
    });
  }

  spinner() {
    this.mostrar = !this.mostrar;
  }

  //Funcion que envia el correo de validacion para poder iniciar sesion
    sendEmailVerificacion() {
      return this.fireAuth.currentUser
        .then((currentUser:any) => currentUser.sendEmailVerification());
    }
}
