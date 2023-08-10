import { Role } from './role';

export interface User {
  id: number;
  email: string;
  username: string;
  role: Role[];
}
