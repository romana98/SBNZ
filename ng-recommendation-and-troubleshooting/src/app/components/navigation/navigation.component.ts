import {Component, OnInit} from '@angular/core';
import {UserRole} from "../../model/user-role";
import {LogInService} from "../../services/log-in.service";
import {StorageService} from "../../services/storage.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {


  role: UserRole;
  admin: UserRole = UserRole.ADMIN;
  user: UserRole = UserRole.USER;
  unauthorized: UserRole = UserRole.UNAUTHORIZED;

  constructor(private storageService: StorageService,
              private loginService: LogInService,) {
    this.storageService.watchStorage().subscribe(() => {
      const user = JSON.parse(<string>localStorage.getItem('user'));
      this.role = user ? UserRole.ADMIN : (this.role = user ? UserRole.USER : UserRole.UNAUTHORIZED);
    });

    const user = JSON.parse(<string>localStorage.getItem('user'));
    this.role = user ? UserRole.ADMIN : (this.role = user ? UserRole.USER : UserRole.UNAUTHORIZED);
  }

  ngOnInit(): void {
  }

  logOut(): void {
    this.loginService.logOut();
  }

}
