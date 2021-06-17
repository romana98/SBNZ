import {Component, OnInit} from '@angular/core';
import {LogInService} from "../../../services/log-in.service";
import {UserRole} from "../../../model/user-role";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  role: String = "";
  constructor(private logInService: LogInService) { }

  ngOnInit(): void {
    const currentRole = this.logInService.getRole();
    this.role = currentRole === UserRole.ROLE_USER ? 'user' : 'admin';
  }

}
