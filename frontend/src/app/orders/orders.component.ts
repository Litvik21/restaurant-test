import {Component, NgZone, OnInit} from '@angular/core';
import {FunctionMapping, Order, OrderFunction} from "../model/order";
import {Router} from "@angular/router";
import {OrderService} from "../service/order.service";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders: Order[] = []

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders(): void {
    this.orderService.getOrdersOfCurrentUser()
      .subscribe(orders => {
        this.orders = orders;
      });
  }

}
