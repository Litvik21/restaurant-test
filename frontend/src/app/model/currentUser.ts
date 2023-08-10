import {Role} from "./role";

export interface CurrentUser {
  id: number;
  email: string;
  username: string;
  role: Role[];
}
