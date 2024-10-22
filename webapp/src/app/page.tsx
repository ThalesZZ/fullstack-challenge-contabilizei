import { cookies } from "next/headers";
import LogoutButton from "./(components)/logout-btn";

export default function Home() {
	const isAuthenticated = cookies().has("session");

	if (!isAuthenticated) return <div>redirecting to login...</div>;

	return <LogoutButton />;
}
