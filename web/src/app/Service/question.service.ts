import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { QuestionI } from '../models/question-i';
import { AnswerI } from '../models/answer-i';
import { EmailBody } from '../models/emailBody-i';
@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  push(arg0: string) {
    throw new Error('Method not implemented.');
  }


  private url: string = 'https://app-reto-preguntas.herokuapp.com/';


  constructor(private http: HttpClient) {}

  getAllQuetions(page: number): Observable<QuestionI[]> {
    let direction = this.url + 'getAll/'  ;
    return this.http.get<QuestionI[]>(direction);
  }

  getAnswer(id: any): Observable<QuestionI> {
    let direction = this.url + 'get/' + id;
    return this.http.get<QuestionI>(direction);
  }

  getQuestion(id: string): Observable<QuestionI> {
    let direction = this.url + 'get/' + id;
    return this.http.get<QuestionI>(direction);
  }

  getTotalPages(): Observable<number> {
    let direction = this.url + 'totalPages';
    return this.http.get<number>(direction);
  }

  getCountQuestions(): Observable<number> {
    let direction = this.url + 'getCountQuestions';
    return this.http.get<number>(direction);
  }

  saveQuestion(question: QuestionI): Observable<any> {
    let direction = this.url + 'create';
    return this.http.post<any>(direction, question, {
      responseType: 'text' as 'json',
    });
  }

  saveAnswer(answer: AnswerI): Observable<any> {
    let direction = this.url + 'add';
    return this.http.post<any>(direction, answer);
  }

  editQuestion(question: QuestionI): Observable<any> {
    let direction = this.url + 'update';
    return this.http.post<any>(direction, question);
  }

  sendEmail(toEmail: string, subject: string, body: string): Observable<any> {
    let direction = this.url + 'sendEmail';
    return this.http.post<any>(direction, { toEmail, subject, body });
  }

  sendEmailAnwer(email1: any, question: QuestionI): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    let direction = this.url + 'sendEmail/';
    const email:EmailBody ={
        toEmail : ''+email1,
        subject: "Alguien Respondio tu pregunta",
        bodyQuestion: question.question,
        idQuestion: question.id
    }
    return this.http.post<string>(direction, email, httpOptions); 
  }
}
