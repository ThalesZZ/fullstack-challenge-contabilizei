"use client";

import API from "@/api";
import type { LoginRequest } from "@/types/auth";
import { App, Button, Form, Input, type FormProps } from "antd";
import FormItem from "antd/es/form/FormItem";
import Password from "antd/es/input/Password";
import { useRouter } from "next/navigation";

export default function LoginPage() {
	const app = App.useApp();
    const router = useRouter()

	const onLogin: FormProps<LoginRequest>["onFinish"] = (data) => {
		API.auth
			.login(data)
			.then(() => {
				app.notification.success({ message: "Successfuly logged in." });
                router.push('/')
			})
			.catch((err) => {
				app.notification.error({ message: err.message });
			});
	};

	return (
		<Form<LoginRequest>
			size="large"
			onFinish={onLogin}
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
