import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { Product } from './../../models/product.model';
import { ApprovalServiceService } from './../../services/approval-service.service';
import { Component, OnInit } from '@angular/core';
import { ApprovalResponse } from 'src/app/models/approval.model';

@Component({
  selector: 'app-approval-component',
  templateUrl: './approval-component.component.html',
  styleUrls: ['./approval-component.component.css']
})
export class ApprovalComponentComponent implements OnInit {
  method:string='Kafka'
  products: Product[] = [];
  constructor(private approvalService: ApprovalServiceService, private toastrService:ToastrService) { }

  ngOnInit(): void {
    this.approvalService.getProductsForApproval().subscribe((products) => {
      this.products = products;
      console.log(products);
    });
  }

  handleToast(obs: Observable<ApprovalResponse>){
    obs.subscribe({
      next:(data:ApprovalResponse) => {
        this.toastrService.success(data.approvalStatus+ " "+ data.message)
        window.location.reload();
      },error: (err:ApprovalResponse) => {
        console.log(err)
        this.toastrService.error(err.message)
      }
    })
  }

  approveProduct(id: string){
    if(this.method == 'Kafka'){
      this.handleToast(this.approvalService.approveProduct(id))
    }else if(this.method == 'Rest'){
      this.handleToast(this.approvalService.approveProductRest(id))
    }
  }

  rejectProduct(id: string){
    if(this.method == 'Kafka'){
      this.handleToast(this.approvalService.rejectProduct(id))
    }else if(this.method == 'Rest'){
      this.handleToast(this.approvalService.rejectProductRest(id))
    }
  }

}
