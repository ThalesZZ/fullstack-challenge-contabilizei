import axios from "axios";
import Cookies from "js-cookie";

export const api = axios.create({
	baseURL: "http://localhost:8080",
	headers: { "Content-Type": "application/json" },
});

api.interceptors.request.use(
	(config) => {
		const token = Cookies.get("token");
		if (token) config.headers.Authorization = `Bearer ${token}`;
		return config;
	},
	(error) => {
		return Promise.reject(error);
	}
);
