import {User} from "./user";

export interface Order {
  id: number;
  product: string;
  status: OrderStatus;
  userId: number;
  user: User;
}

export enum OrderStatus {
  RECEIVED ='Received',
  ACCEPT = 'Accept',
  REJECT = 'Reject'
}

export const StatusMapping = {
  [OrderStatus.RECEIVED]: "Received",
  [OrderStatus.ACCEPT]: "Accept",
  [OrderStatus.REJECT]: "Reject"
}
