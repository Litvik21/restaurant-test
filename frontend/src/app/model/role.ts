export interface Role {
  id: number;
  roleName: RoleName;
}

export enum RoleName {
  STAFF = 'STAFF',
  USER = 'USER'
}

export const StatusMapping = {
  [RoleName.STAFF]: "STAFF",
  [RoleName.USER]: "USER",
}
