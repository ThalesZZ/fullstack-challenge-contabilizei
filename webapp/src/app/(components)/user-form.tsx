"use client";

import type { LoginRequest, User } from "@/types/auth";
import { Button, Flex, Form, Input, type FormProps } from "antd";
import FormItem from "antd/es/form/FormItem";
import Password from "antd/es/input/Password";
import React from "react";

type PartialUser = Partial<User>;

export interface UserFormProps {
	fields: (keyof Omit<User, "id">)[];
	submitText: React.ReactNode;
	onSubmit: (data: PartialUser) => Promise<void>;
	secondaryButton?: React.ReactNode;
}

export default function UserForm({
	submitText,
	onSubmit,
	secondaryButton,
}: UserFormProps) {
	const onFinish: FormProps<PartialUser>["onFinish"] = (data) => {
		onSubmit(data);
	};
	return (
		<Form<PartialUser>
			size="large"
			onFinish={onFinish}
			className="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 w-1/4 max-w-80"
		>
			<FormItem<PartialUser> name="username" rules={[{ required: true }]}>
				<Input placeholder={"Username"} />
			</FormItem>
			<FormItem<LoginRequest>
				name="password"
				rules={[{ required: true }]}
			>
				<Password placeholder={"Password"} />
			</FormItem>
			<Flex justify="space-between">
				<FormItem>
					<Button type="primary" htmlType="submit">
						{submitText}
					</Button>
				</FormItem>

				{secondaryButton}
			</Flex>
		</Form>
	);
}
