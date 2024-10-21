import { ConfigProvider } from "antd";
import LoginPage from "./login/page";

export default function Home() {
	return (
		<ConfigProvider theme={{ hashed: false }}>
			<LoginPage />
		</ConfigProvider>
	);
}
