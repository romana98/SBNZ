import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {validateMatchPassword} from "../../../validator/custom-validator-match-password";
import {validateLength} from "../../../validator/custom-validator-zero-min-eight-length";
import {UserRole} from "../../../model/user-role";
import {LogInService} from "../../../services/log-in.service";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {

  form: FormGroup;
  role: String = "";
  email: String = "";

  constructor(private fb: FormBuilder,
              public snackBar: MatSnackBar,
              private logInService: LogInService,
              private userService: UserService) {

    this.form = this.fb.group({
        email: ['', [Validators.required]],
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
        password: [''],
        passwordConfirm: ['']
      },
      {
        validator:  [validateMatchPassword('password', 'passwordConfirm'), validateLength('password')]
      });

    const currentRole = this.logInService.getRole();
    this.role = currentRole === UserRole.ROLE_USER ? 'user' : 'admin';
  }

  ngOnInit(): void {
    this.userService.getProfile(JSON.parse(<string>localStorage.getItem('user')).id).toPromise().then( res => {
      this.form.patchValue({
        firstName: res.firstName,
        lastName: res.lastName,
        email: res.email
      });
      this.email = res.email;
    }, err => {
      this.snackBar.open("Server error: " + err, "Close");
    })
  }

  submit(): void {

  }
}
