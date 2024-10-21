"use client";

import API from "@/api";
import type { LoginRequest } from "@/types/auth";
import {
    App,
    Button,
    type ButtonProps,
    Flex,
    Form,
    type FormProps,
    Input,
} from "antd";
import FormItem from "antd/es/form/FormItem";
import Password from "antd/es/input/Password";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
import React from "react";

export default function LoginPage() {
	const app = App.useApp();
    const router = useRouter()

	const isLogged = React.useMemo(() => {
		const token = Cookies.get("token");
		return !!token;
	}, []);

	const onLogin: FormProps<LoginRequest>["onFinish"] = (data) => {
		if (isLogged) {
			app.notification.warning({ message: "You are already logged in." });
			return;
		}

		API.auth
			.login(data)
			.then((response) => {
				const { token } = response;
				Cookies.set("token", token);
				app.notification.success({ message: "Successfuly logged in." });
                router.refresh()
			})
			.catch((err) => {
				app.notification.error({ message: err.message });
			});
	};

	const onLogout: ButtonProps["onClick"] = () => {
		if (!isLogged) {
			app.notification.warning({ message: "You are not logged. " });
			return;
		}

        Cookies.remove('token')
        router.refresh()
	};

	if (isLogged) return <Flex><Button onClick={onLogout}>{"Logout"}</Button></Flex>;

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
