import { Component, Input, OnInit } from '@angular/core';
import { QuestionI } from 'src/app/models/question-i';
import { QuestionService } from 'src/app/Service/question.service';
import { ServiceService } from 'src/app/Service/service.service';

@Component({
  selector: 'app-preguntas',
  templateUrl: './preguntas.component.html',
  styleUrls: ['./preguntas.component.css'],
})
export class PreguntasComponent implements OnInit {
  userLogged = this.authService.getUserLogged();
  uid: any;

  totalQuestions: number = 0;

  questions: QuestionI[] | any;
  user: any = '';
  pages: Array<number> | undefined;
  disabled: boolean = false;

  page: number = 1;
  count: number = 0;
  tableSize: number = 10;

  constructor(
    private service: QuestionService,
    public authService: ServiceService

  ) {}

  ngOnInit(): void {
    this.getQuestions();
    this.traerdatos();
  }

  onTableDataChange(event: any) {
    this.page = event;
    this.getQuestions();
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.page = 1;
    this.getQuestions();
  }

  getQuestions(): void {
    this.userLogged.subscribe(value =>{
        this.uid=value?.uid
    });
    this.service.getAllQuetions(this.page).subscribe((data) => {
        this.questions = data;
    }); 
    this.service
      .getTotalPages()
      .subscribe((data) => (this.pages = new Array(data)));
    this.service
      .getCountQuestions()
      .subscribe((data) => (this.totalQuestions = data));
  }

  isLast(): boolean {
    let totalPeges: any = this.pages?.length;
    return this.page == totalPeges - 1;
  }

  isFirst(): boolean {
    return this.page == 0;
  }

  previousPage(): void {
    !this.isFirst() ? (this.page--, this.getQuestions()) : false;
  }

  nextPage(): void {
    !this.isLast() ? (this.page++, this.getQuestions()) : false;
  }

  getPage(page: number): void {
    this.page = page;
    this.getQuestions();
  }

  traerdatos() {
    this.userLogged.subscribe((value) => {     
      if (value?.email == undefined) {
        this.disabled = true;       
      } else {
        this.disabled = false;     
      }
   
    });
  }
}
