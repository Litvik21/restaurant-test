import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OrdersComponent} from "./orders/orders.component";
import {NewOrderComponent} from "./new-order/new-order.component";
import {OrdersStaffComponent} from "./orders-staff/orders-staff.component";
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  { path:  "", pathMatch:  "full", redirectTo:  "orders" },
  { path: 'newOrder', component: NewOrderComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'staff', component: OrdersStaffComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
