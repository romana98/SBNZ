import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfigurationsService } from 'src/app/services/configurations.service';

@Component({
  selector: 'app-configurations',
  templateUrl: './configurations.component.html',
  styleUrls: ['./configurations.component.css']
})
export class ConfigurationsComponent implements OnInit {

  page = 0;
  pageSize = 2;
  configurations = {content: [], numberOfElements: 0, totalElements: 0, totalPages: 0, number: 0};

  constructor(private configurationsService: ConfigurationsService, private router: Router) { }

  ngOnInit(): void {
    this.configurationsService.getConfigurations({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.configurations = result;
        console.log(this.configurations.content)
      }
    );
  }

  onPagination(page: number){
    this.page = page;
    this.configurationsService.getConfigurations({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.configurations = result;
        console.log(this.configurations.content)
      }
    );
  }

  onClick(id: number) {
    //this.router.navigate(['/newsletter/update-newsletter', id]);
  }

}
