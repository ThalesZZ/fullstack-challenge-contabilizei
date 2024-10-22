"use client";

import Auth from "@/lib/auth";
import { LogoutOutlined } from "@ant-design/icons";
import { Button, type ButtonProps } from "antd";
import { useRouter } from "next/navigation";

export default function LogoutButton() {
	const router = useRouter();

	const onLogout: ButtonProps["onClick"] = () => {
		Auth.logout();
		router.push("/login");
	};

	return (
		<Button icon={<LogoutOutlined />} onClick={onLogout}>
			{"Logout"}
		</Button>
	);
}
