"use client";

import Auth from "@/lib/auth";
import { App, Button } from "antd";
import Link from "next/link";
import { useRouter } from "next/navigation";
import UserForm, { type UserFormProps } from "../(components)/user-form";

export default function LoginPage() {
	const app = App.useApp();
	const router = useRouter();

	const onLogin: UserFormProps["onSubmit"] = async (data) => {
		Auth.login({
			username: data.username as string,
			password: data.password as string,
		})
			.then(() => {
				app.notification.success({ message: "Successfuly logged in." });
				router.push("/");
			})
			.catch((err) => {
				app.notification.error({ message: err.message });
			});
	};

	return (
		<UserForm
			submitText={"Login"}
			fields={["username", "password"]}
			onSubmit={onLogin}
			secondaryButton={
				<Link href="/register">
					<Button type="link">Create account</Button>
				</Link>
			}
		/>
	);
}
