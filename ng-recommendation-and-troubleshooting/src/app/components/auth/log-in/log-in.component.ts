import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {LogInService} from "../../../services/log-in.service";
import {Router} from "@angular/router";
import {StorageService} from "../../../services/storage.service";
import {JwtHelperService} from "@auth0/angular-jwt";
import {LogInModel} from "../../../model/log-in-model";
import {LogIn} from "../../../model/user-role";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
    ngOnInit(): void {
        throw new Error('Method not implemented.');
    }
/*
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    public snackBar: MatSnackBar,
    private logInService: LogInService,
    public router: Router,
    private storageService: StorageService
  ) {
    this.form = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, Validators.required]
    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    const logIn = new LogIn(this.form.value.email, this.form.value.password);

    this.logInService.logIn(logIn).subscribe(
      result => {
        const jwt: JwtHelperService = new JwtHelperService();
        const info = jwt.decodeToken(result.accessToken);
        const role = info.role;
        const user = new LogInModel(info.email, result.accessToken, info.id, info.role);
        localStorage.setItem('user', JSON.stringify(user));
        this.storageService.setStorageItem('user', JSON.stringify(user))

        this.snackBar.open('Successfully logged in!', 'Ok', {duration: 2000});

        if (role === 'ROLE_SUPER_ADMIN') {
          this.router.navigate(['/manage-certificates/manage-requests']);
        }
      },
      error => {
        this.snackBar.open('Bad credentials!', 'Ok', {duration: 2000});
      }
    );
  }*/

}
