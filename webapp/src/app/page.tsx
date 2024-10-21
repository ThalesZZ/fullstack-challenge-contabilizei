import { App, ConfigProvider } from "antd";
import LoginPage from "./login/page";

export default function Home() {
	return (
		<ConfigProvider theme={{ hashed: false }}>
			<App>
				<LoginPage />
			</App>
		</ConfigProvider>
	);
}
