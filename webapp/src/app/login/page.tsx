"use client";

import type { LoginRequest } from "@/types/auth";
import { Button, Flex, Form, type FormProps, Input } from "antd";
import FormItem from "antd/es/form/FormItem";
import Password from "antd/es/input/Password";

export default function LoginPage() {
	const onSubmit: FormProps<LoginRequest>["onFinish"] = (data) => {
		
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
