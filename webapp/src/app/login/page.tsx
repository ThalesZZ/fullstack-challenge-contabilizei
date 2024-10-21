"use client";

import API from "@/api/auth";
import type { LoginRequest } from "@/types/auth";
import { App, Button, Flex, Form, type FormProps, Input } from "antd";
import FormItem from "antd/es/form/FormItem";
import Password from "antd/es/input/Password";
import Cookies from "js-cookie";

export default function LoginPage() {
	const app = App.useApp();
	const onSubmit: FormProps<LoginRequest>["onFinish"] = (data) => {
		const tk = Cookies.get("token");
		if (tk) {
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
		<Flex className="relative h-full">
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
		</Flex>
	);
}
