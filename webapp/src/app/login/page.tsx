"use client";

import API from "@/api/auth";
import type { LoginRequest } from "@/types/auth";
import { App, Button, Form, type FormProps, Input } from "antd";
import FormItem from "antd/es/form/FormItem";
import Password from "antd/es/input/Password";
import Cookies from "js-cookie";
import React from "react";

export default function LoginPage() {
	const app = App.useApp();

	const isLogged = React.useMemo(() => {
		const token = Cookies.get("token");
		return !!token;
	}, []);

	const onSubmit: FormProps<LoginRequest>["onFinish"] = (data) => {
		if (isLogged) {
			app.notification.warning({
				message: "You are already logged in. ",
			});
			return;
		}

		API.auth
			.login(data)
			.then((response) => {
				const { token } = response;
				Cookies.set("token", token);
				app.notification.success({ message: "Successfuly logged in." });
			})
			.catch((err) => {
				app.notification.error({ message: err.message });
			});
	};

	return (
		<Form<LoginRequest>
			size="large"
			onFinish={onSubmit}
			className="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2"
		>
			<FormItem<LoginRequest>
				name={"username"}
				rules={[{ required: true }]}
			>
				<Input placeholder={"Username"} />
			</FormItem>
			<FormItem<LoginRequest>
				name={"password"}
				rules={[{ required: true }]}
			>
				<Password placeholder={"Password"} />
			</FormItem>

			<FormItem>
				<Button type="primary" htmlType="submit">
					{"Login"}
				</Button>
			</FormItem>
		</Form>
	);
}
