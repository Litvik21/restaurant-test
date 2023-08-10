import {Component, NgZone, OnInit} from '@angular/core';
import {StatusMapping, Order, OrderStatus} from "../model/order";
import {OrderService} from "../service/order.service";
import {Router} from "@angular/router";
import {UserService} from "../service/user.service";
import {forkJoin} from "rxjs";
import {User} from "../model/user";

@Component({
  selector: 'app-orders-staff',
  templateUrl: './orders-staff.component.html',
  styleUrls: ['./orders-staff.component.scss']
})
export class OrdersStaffComponent implements OnInit {
  orders: Order[] = []
  statuses = Object.values(OrderStatus);
  statusMapping = StatusMapping;
  dropdownStates: Map<number, boolean> = new Map<number, boolean>();

  constructor(private orderService: OrderService,
              private router: Router,
              private ngZone: NgZone,
              private userService: UserService) { }

  ngOnInit(): void {
    this.getOrders();
    this.checkForNewOrders();
  }

  toggleDropdown(order: Order) {
    this.dropdownStates.set(order.id, !this.isDropdownVisibleFor(order));
  }

  isDropdownVisibleFor(order: Order): boolean {
    return this.dropdownStates.get(order.id) || false;
  }

  getOrders(): void {
    this.orderService.getOrders().subscribe(orders => {
      if (orders.length <= 0) {
        console.log("No orders yet!");
        return;
      }

      const getUserObservables = [];

      for (const order of orders) {
        getUserObservables.push(this.userService.getUser(order.userId));
      }

      forkJoin(getUserObservables).subscribe(
        users => {
          for (let i = 0; i < orders.length; i++) {
            orders[i].user = users[i];
          }
          this.orders = orders;
        },
        error => {
          console.error("An error occurred:", error);
        }
      );
    });
  }

  onStatusChange(order: Order, event: any) {
    order.status = event.target.value as OrderStatus;
  }


  submit(order: any) {
    console.log(order)
    this.toggleDropdown(order);
    const newOrder: any = {product: order.product, status: order.status, userId: order.userId};
    this.orderService.updateOrder(order.id, newOrder).subscribe(updatedOrder => {
      this.userService.getUser(updatedOrder.userId).subscribe(user => {
        updatedOrder.user = user;
        const orderIndex = this.orders.findIndex(order => order.id === updatedOrder.id);
        if (orderIndex !== -1) {
          console.log(updatedOrder)
          this.orders[orderIndex] = updatedOrder;
          console.log(this.orders)
        }
      })
    });
  }

  checkForNewOrders() {
    this.orderService.listenToEmitter().subscribe({
      next: (data: any) => {
        this.ngZone.run(() => {
          console.log(data);
          this.userService.getUser(data.userId).subscribe(user => {
            const newOrder: Order = { id: data.id, product: data.product, status: data.status,
              user: user, userId: data.userId};
            console.log(newOrder)
            const orderIndex = this.orders.findIndex(order => order.id === newOrder.id);

            if (orderIndex === -1) {
              this.orders.push(newOrder);
            }
          })
        });
      },
      error: (error: any) => {
        console.error('An error occurred:', error);
      }
    });
  }

}
