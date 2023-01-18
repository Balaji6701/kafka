import { Product } from './../models/product.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApprovalResponse } from '../models/approval.model';

@Injectable({
  providedIn: 'root'
})
export class ApprovalServiceService {
  
  constructor(private http:HttpClient ) { }

  getRequestOptions(){
    let token = `Basic ${window.btoa('admin:password')}`
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': token,
    });
    return { headers: headers };
  }

  approveProduct(id:string){
    return this.http.get<ApprovalResponse>(`http://localhost:9000/approvals/approve/${id}`, this.getRequestOptions());
  }

  approveProductRest(id:string){
    return this.http.get<ApprovalResponse>(`http://localhost:9000/approvals/rest/approve/${id}`, this.getRequestOptions());
  }

  getProductsForApproval(){
    return this.http.get<Product[]>('http://localhost:9000/approvals', this.getRequestOptions());
  }

  rejectProduct(id:string){
    return this.http.get<ApprovalResponse>(`http://localhost:9000/approvals/reject/${id}`, this.getRequestOptions());
  }

  rejectProductRest(id:string){
    return this.http.get<ApprovalResponse>(`http://localhost:9000/approvals/rest/reject/${id}`, this.getRequestOptions());
  }
}
