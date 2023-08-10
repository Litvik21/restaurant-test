import { Component, OnInit } from '@angular/core';
import {OrderService} from "../service/order.service";
import {Router} from "@angular/router";
import {Order} from "../model/order";

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.scss']
})
export class NewOrderComponent implements OnInit {
  isError: boolean = false;

  constructor(private orderService: OrderService,
              private router: Router) { }

  ngOnInit(): void {
  }

  submit() {
    console.log("Submit button")
    const form = document.forms.namedItem('uploadForm');
    if (form) {
      const requestData = {
        product: form['product'].value
      };

      this.orderService.newOrder(requestData).subscribe({
        next: (order: Order) => {
          console.log(order);
          console.log("Order created.");
          this.router.navigate(['orders']);
        },
        error: (error) => {
          console.error("Error while creating new order.");
          console.error(error);
        },
      });
    }
  }

}
