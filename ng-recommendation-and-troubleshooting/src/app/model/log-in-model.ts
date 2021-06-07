import {UserRole} from "./user-role";

export class LogInModel {
  constructor(
    private email: string,
    private accessToken: string,
    private id: number,
    private role: string
  ) {
  }

  getRole(): UserRole {
    return this.role === 'ADMIN' ? UserRole.ADMIN : (this.role === 'USER' ? UserRole.USER : UserRole.UNAUTHORIZED);
  }
}
