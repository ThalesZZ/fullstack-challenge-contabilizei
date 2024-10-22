"use client";

import API from "@/api";
import { LogoutOutlined } from "@ant-design/icons";
import { Button, type ButtonProps } from "antd";
import { useRouter } from "next/navigation";

export default function LogoutButton() {
	const router = useRouter();

	const onLogout: ButtonProps["onClick"] = () => {
		API.auth.logout();
		router.push("/login");
	};

	return (
		<Button icon={<LogoutOutlined />} onClick={onLogout}>
			{"Logout"}
		</Button>
	);
}
