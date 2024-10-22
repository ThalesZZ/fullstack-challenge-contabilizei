import type { CreateUserRequest, LoginRequest, LoginResponse } from "@/types/auth";
import type { AxiosResponse } from "axios";
import jsCookie from "js-cookie";
import { api } from "../api/axios";

function storeSessionCookie(token: string): void {
	jsCookie.set("session", token);
}

export async function register(data: CreateUserRequest): Promise<void> {
	const response: AxiosResponse<LoginResponse> = await api
		.post("/api/public/auth/register", data)
		.catch((err) => err);

	if (response.status === 200) {
		const { token } = response.data;
		storeSessionCookie(token)
		return;
	}

	throw new Error("Unexpected error occured.");
}

export async function login(data: LoginRequest): Promise<void> {
	const response: AxiosResponse<LoginResponse> = await api
		.post("/api/public/auth/login", data)
		.catch((err) => err);

	if (response.status === 200) {
		const { token } = response.data;
		storeSessionCookie(token)
		return;
	}

	if (response.status === 403) throw new Error("Invalid credentials.");
	throw new Error("Unexpected error occured.");
}

// TODO improve
export async function logout(): Promise<void> {
	return new Promise((resolve, reject) => {
		jsCookie.remove("session");
		resolve();
	});
}
