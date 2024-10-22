"use client";

import Auth from "@/lib/auth";
import { App } from "antd";
import { useRouter } from "next/navigation";
import UserForm, { type UserFormProps } from "../(components)/user-form";

export default function RegisterPage() {
	const app = App.useApp();
	const router = useRouter();

	const onRegister: UserFormProps["onSubmit"] = async (data) => {
		Auth.register({
			username: data.username as string,
			password: data.password as string,
            role: 'USER'
		})
			.then(() => {
				app.notification.success({ message: "Successfuly registered." });
				router.push("/");
			})
			.catch((err) => {
				app.notification.error({ message: err.message });
			});
	};

	return (
		<UserForm
			submitText={"Register"}
			fields={["username", "password"]}
			onSubmit={onRegister}
		/>
	);
}
