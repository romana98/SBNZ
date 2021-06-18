import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LogInService} from "../../../services/log-in.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent implements OnInit {

  id: number = -1;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              public snackBar: MatSnackBar,
              private loginService: LogInService,
  ) {
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.id = params.id;
      this.loginService.activateAccount(this.id).toPromise().then(res => {
        this.router.navigate(['/']);
      }, err => {
        this.snackBar.open("Server error: " + err, "Close", {duration: 2000});
      })
    });
  }

}
