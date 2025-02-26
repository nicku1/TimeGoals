import { useState } from "react";
import { useAuth } from "../hooks/useAuth";
import { Input } from "../components/Input";

export const LoginForm = () => {
  const [loginState, setLoginState] = useState({ username: "", password: "" });

  const handleLoginForm = (e) => {
    const { name, value } = e.target;
    setLoginState((prevState) => {
      return {
        ...prevState,
        [name]: value,
      };
    });
  };

  return (
    <div class="h-screen w-screen flex flex-col items-center justify-center">
      <form class="flex flex-col">
        <Input
          label={"Login"}
          onChange={handleLoginForm}
          name={"username"}
          value={loginState.username}
        />
        <Input
          label={"Password"}
          onChange={handleLoginForm}
          name={"password"}
          value={loginState.password}
        />
        <button
          class="mt-1 border rounded-lg duration-300 hover:bg-blue-600"
          onClick={(e) => {
            e.preventDefault(), useAuth((node = "auth/login"), loginState);
          }}
        >
          Login
        </button>
      </form>

      <p class="mt-3">
        Don't have an account?
        <a href="/" class="font-bold text-blue-500">
          {" "}
          Register
        </a>
      </p>
    </div>
  );
};
