
import { ApprovalComponentComponent } from './components/approval-component/approval-component.component';
import { ProductComponentComponent } from './components/product-component/product-component.component';
import { HomeComponentComponent } from './components/home-component/home-component.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{path: "home", component: HomeComponentComponent},
                        {path: "products", component: ProductComponentComponent},
                        {path: "approvals", component: ApprovalComponentComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
