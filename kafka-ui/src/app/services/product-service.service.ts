import { Product } from './../models/product.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { ApprovalResponse } from '../models/approval.model';
@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  constructor(private http:HttpClient) { }

  getRequestOptions(){
    let token = `Basic ${window.btoa('user:password')}`
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token,
    });
    return { headers: headers };
  }

  getAllProducts(){
    const requestOptions = this.getRequestOptions();
    return this.http.get<Product[]>("http://localhost:8080/products", requestOptions);
  }

  saveProduct(product: Product){
    const requestOptions = this.getRequestOptions();
    return this.http.post<ApprovalResponse>("http://localhost:8080/products", product, requestOptions);
  }

  saveProductRest(product: Product){
    const requestOptions = this.getRequestOptions();
    return this.http.post<ApprovalResponse>("http://localhost:8080/products/rest", product, requestOptions);
  }
}
