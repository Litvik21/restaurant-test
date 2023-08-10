import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, delay, Observable, of, retryWhen, takeUntil, timeout, timer} from 'rxjs';
import { environment } from '../../environments/environment';
import {Order} from "../model/order";
import {TokenService} from "./token.service";

@Injectable({providedIn: 'root'})
export class OrderService {
  constructor(
    private tokenService: TokenService,
    private http: HttpClient) {
  }
  getHeaders(): HttpHeaders {
    const token = this.tokenService.getToken();

    if (token) {
      return new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
    }

    return new HttpHeaders();
  }

  private orderUrl = environment.urlPath + '/orders';

  getOrders(): Observable<Order[]> {
    const headers = this.getHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.get<Order[]>(this.orderUrl, {headers}).pipe(
        catchError(this.handleError<Order[]>('getOrders', []))
      );
  }

  listenToEmitter(): Observable<any> {
    const url = `${this.orderUrl}/newOrder`;
    return new Observable<any>((observer) => {
      const eventSource = new EventSource(url);
      eventSource.onmessage = (event) => {
        const data = JSON.parse(event.data);
        observer.next(data);
      };
      eventSource.onerror = (error) => {
        console.log("some log");
        observer.error(error);
        eventSource.close();
      };
    });
  }

  updateOrder(orderId: any, newOrder: any): Observable<Order> {
    const headers = this.getHeaders();
    headers.append('Content-Type', 'application/json');
    const url = `${this.orderUrl}/update/${orderId}`;
    console.log(newOrder)
    return this.http.put<Order>(url, newOrder, {headers}).pipe(
      catchError(this.handleError<Order>(`updateOrder`))
    );
  }

  getOrdersOfCurrentUser(): Observable<Order[]> {
    const headers = this.getHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.get<Order[]>(`${this.orderUrl}/my`, {headers}).pipe(
      catchError(this.handleError<Order[]>('getOrdersOfCurrentUser', []))
    );
  }

  public newOrder(requestData: any): Observable<Order> {
    const headers = this.getHeaders();
    headers.append('Content-Type', 'application/json');
    const url = `${this.orderUrl}/new`;
    return this.http.post<Order>(url, requestData, {headers}).pipe(
      catchError(this.handleError<Order>(`newOrder`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      if (error.name === 'TimeoutError') {
        console.error('Connection terminated due to timeout.', error);
      }

      console.error(error);

      return of(result as T);
    };
  }
}
