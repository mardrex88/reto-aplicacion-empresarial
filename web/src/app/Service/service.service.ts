import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import {
  AngularFirestore,
  AngularFirestoreDocument,
} from '@angular/fire/compat/firestore';
import { Router } from '@angular/router';

import firebase from 'firebase/compat/app';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class ServiceService {
  userData: any;

  constructor(
    public afauth: AngularFireAuth,
    public store: AngularFirestore,
    public router: Router,
  ) {
    this.getUserLogged2();
  }

  async login(email: string, password: string) {
    try {
      return await this.afauth
        .signInWithEmailAndPassword(email, password)

    } catch (error) {
      return null;
    }
  }
  async register(email: string, password: string) {
    try {
      return await this.afauth
        .createUserWithEmailAndPassword(email, password)
    } catch (error) {
      return null;
    }
  }

  resetPassword(email: string) {
    return this.afauth
      .sendPasswordResetEmail(email)
      .then((result) => {
        return "ok";
      })
      .catch(() => {
        return null;
      })
  }

  async loginGoogle() {
    try {
      return await this.afauth
        .signInWithPopup(new firebase.auth.GoogleAuthProvider())
    } catch (error) {
      return null;
    }
  }

  getUserLogged() {
    return this.afauth.authState;
  }

  getUserLogged2() {
    this.afauth.authState.subscribe((user) => {
      if (user) {
        this.userData = user;
        JSON.parse(localStorage.getItem('user')!);
        localStorage.setItem('user', JSON.stringify(this.userData));
      } else {
        JSON.parse(localStorage.getItem('user')!);
        localStorage.setItem('user', 'null');
      }
    });
  }


  setUserData(user: any) {
    const userRef: AngularFirestoreDocument<any> = this.store.doc(
      `users/${user.uid}`
    );
    const userData: User = {
      uid: user.uid,
      email: user.email,
      displayName: user.displayName,
      photoURL: user.photoURL,
      emailVerified: user.emailVerified,
    };
    return userRef.set(userData, {
      merge: true,
    });
  }
  // Sign out
  SignOut() {
    return this.afauth.signOut().then(() => {
      localStorage.removeItem('user');
      this.router.navigate(['login']);
    });
  }

  async getEmailByUid(uid2:string){
    return this.store.collection("users").doc<User>(uid2).ref.get();
  }

}
