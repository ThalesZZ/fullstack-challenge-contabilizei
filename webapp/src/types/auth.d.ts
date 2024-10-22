export type UserRole = "USER" | "ADMIN";

export interface User {
	id: string;
	username: string;
	password: string;
	role: UserRole;
}

export type CreateUserRequest = Omit<User, "id">;

export type LoginRequest = Pick<User, "username" | "password">;

export type LoginResponse = { token: string };
