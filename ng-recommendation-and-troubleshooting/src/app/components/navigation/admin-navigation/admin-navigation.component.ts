import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-navigation',
  templateUrl: './admin-navigation.component.html',
  styleUrls: ['./admin-navigation.component.css']
})
export class AdminNavigationComponent implements OnInit {

  @Output() logOut = new EventEmitter<void>();

  constructor(public router: Router) { }

  ngOnInit(): void {
  }

  logOutUser(): void {
    this.logOut.emit();
  }

}
