import type { LoginRequest, LoginResponse } from "@/types/auth";
import type { AxiosResponse } from "axios";
import { api } from "../axios";

export async function login(data: LoginRequest): Promise<LoginResponse> {
	const response: AxiosResponse<LoginResponse> = await api
		.post("/api/public/auth/login", data)
		.catch((err) => err);

	if (response.status === 200) return response.data;
	if (response.status === 403) throw new Error("Invalid credentials.");
	throw new Error("Unexpected error occured.");
}
