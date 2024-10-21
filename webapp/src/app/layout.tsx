import { AntdRegistry } from "@ant-design/nextjs-registry";
import { App, ConfigProvider } from "antd";
import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
	title: "Challenge: Contabilizei",
};

export default function RootLayout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<html lang="en">
			<body>
				<AntdRegistry>
					<ConfigProvider theme={{ hashed: false }}>
						<App>{children}</App>
					</ConfigProvider>
				</AntdRegistry>
			</body>
		</html>
	);
}
