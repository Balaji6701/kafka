import { Product } from './../../models/product.model';
import { ProductServiceService } from './../../services/product-service.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {

  constructor(private productSerice:ProductServiceService) { }
  products: Product[] = [];
  ngOnInit(): void {
    this.productSerice.getAllProducts().subscribe(products => {
      this.products = products;
      console.log(products);
    })
  }

  getApprovalStyle(status: string){
    if(status == 'Approved'){
      return 'border border-3 border-success rounded'
    }else if(status == 'Submitted for approval'){
      return 'border border-3 border-warning rounded'
    }else if(status == 'Rejected'){
      return 'border border-3 border-danger rounded'
    }else{
      return ''
    }
  }

 
}
