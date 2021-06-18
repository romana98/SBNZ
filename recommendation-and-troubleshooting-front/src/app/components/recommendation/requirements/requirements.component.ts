import { Component, OnInit } from '@angular/core';
import { ConfigurationsService } from 'src/app/services/configurations.service';

@Component({
  selector: 'app-requirements',
  templateUrl: './requirements.component.html',
  styleUrls: ['./requirements.component.css']
})
export class RequirementsComponent implements OnInit {

  page = 0;
  pageSize = 7;
  requirements = {content: [], numberOfElements: 0, totalElements: 0, totalPages: 0, number: 0};

  constructor(private configurationsService: ConfigurationsService) { }

  ngOnInit(): void {
    this.page = 0;
    this.configurationsService.getRequirements({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.requirements = result;
      }
    );
  }

  onPagination(page: number){
    this.page = page;
    this.configurationsService.getRequirements({ page: this.page, size: this.pageSize }).subscribe(
      result => {
        this.requirements = result;
      }
    );
  }

  onDelete(event: any) {
    console.log(event)
  }

}
