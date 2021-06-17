import {Component, Input, OnChanges, Output, SimpleChanges, EventEmitter} from '@angular/core';
import { LogInService } from 'src/app/services/log-in.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnChanges {

  @Input() dataSource = [];
  @Input() columnsToDisplay = [];
  @Input() columnsToIterate = [];
  @Output() Delete = new EventEmitter<number>();
  @Output() Click = new EventEmitter<number>();
  @Output() DoubleClick = new EventEmitter<number>();
  role = 0;
  constructor(private loginService: LogInService) {
    this.role = this.loginService.getRole();
  }

  ngOnChanges(changes: SimpleChanges): void {
    for (const propName in changes) {
      if (changes.hasOwnProperty(propName)){
        let vary = this.get(propName);
        vary = changes[propName].currentValue;
      }
    }
  }
  deleted(id: number){
    this.Delete.emit(id);
  }
  clicked(id: number){
    this.Click.emit(id);
  }
  doubleClicked(id: number){
    this.DoubleClick.emit(id);
  }
  doubleClickedWithData(data: any){
    this.DoubleClick.emit(data);
  }
  get(element: string): string[]{
    switch (element) {
      case 'dataSource':
        return this.dataSource;
      case 'columnsToDisplay':
        return this.columnsToDisplay;
      default:
        return this.columnsToIterate;
    }
  }
}
