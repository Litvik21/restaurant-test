import {User} from "./user";

export interface Order {
  id: number;
  product: string;
  function: OrderFunction;
  userId: number;
  user: User;
}

export enum OrderFunction {
  RECEIVED ='Received',
  ACCEPT = 'Accept',
  REJECT = 'Reject'
}

export const FunctionMapping = {
  [OrderFunction.RECEIVED]: "Received",
  [OrderFunction.ACCEPT]: "Accept",
  [OrderFunction.REJECT]: "Reject"
}
