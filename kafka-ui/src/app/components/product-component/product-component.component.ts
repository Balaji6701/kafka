import { ToastrService } from 'ngx-toastr';
import { ApprovalResponse } from './../../models/approval.model';
import { ProductServiceService } from './../../services/product-service.service';
import { Product } from './../../models/product.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbToast } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product-component',
  templateUrl: './product-component.component.html',
  styleUrls: ['./product-component.component.css']
})
export class ProductComponentComponent implements OnInit {
  method:string = 'Kafka'
  product:Product = {
    productId: '',
    id: '',
    productName: '',
    price: 0,
    active: false,
    brand: '',
    createdAt: new Date(),
    createdBy: '',
    lastModifiedAt: new Date(),
    lastModifiedBy: '',
    approvalStatus: '',
    productCategory: ''
  };

  constructor(private productService: ProductServiceService, private router: Router, private toastrService:ToastrService) { }

  handleToast(obs: Observable<ApprovalResponse>){
    obs.subscribe({
      next: (data:ApprovalResponse) => {
        this.toastrService.success(data.approvalStatus+ " "+data.message)
        this.router.navigate(['/home'])
      },error: (err:Error) => {
        this.toastrService.error(err.message)
      }
    })
  }

  createProduct(product:Product){
    if(this.method == 'Kafka'){
      this.handleToast(this.productService.saveProduct(product))
    }else if(this.method == 'Rest'){
      this.handleToast(this.productService.saveProductRest(product))
    }
  }

  ngOnInit(): void {

  }

}
