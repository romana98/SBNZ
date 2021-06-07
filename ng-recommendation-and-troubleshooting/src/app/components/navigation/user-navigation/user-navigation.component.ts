import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-navigation',
  templateUrl: './user-navigation.component.html',
  styleUrls: ['./user-navigation.component.css']
})
export class UserNavigationComponent implements OnInit {

  @Output() logOut = new EventEmitter<void>();

  constructor(public router: Router) { }

  ngOnInit(): void {
  }

  logOutUser(): void {
    this.logOut.emit();
  }

}
