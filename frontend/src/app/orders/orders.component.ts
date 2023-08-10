import {Component, NgZone, OnInit} from '@angular/core';
import {StatusMapping, Order, OrderStatus} from "../model/order";
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
