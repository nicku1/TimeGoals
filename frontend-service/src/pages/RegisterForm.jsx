import { useState } from "react";
import { Input } from "../components/Input";
import { useAuth } from "../hooks/useAuth";

export function RegisterForm() {
  const [registerState, setRegisterState] = useState({
    username: "",
    password: "",
    email: "",
  });

  const handleRegisterForm = (e) => {
    const { name, value } = e.target;
    setRegisterState((prevState) => {
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
          onChange={handleRegisterForm}
          name={"username"}
          value={registerState.username}
        />
        <Input
          label={"Email"}
          onChange={handleRegisterForm}
          name={"email"}
          value={registerState.email}
        />
        <Input
          label={"Password"}
          onChange={handleRegisterForm}
          name={"password"}
          value={registerState.password}
        />
        <button
          class="border rounded-lg duration-300 hover:bg-blue-600"
          onClick={(e) => {
            e.preventDefault(), useAuth("account", registerState);
          }}
        >
          Register
        </button>
      </form>
      <p class="mt-3">
        Already have an account?
        <a href="/login" class="font-bold text-blue-500">
          {" "}
          Login
        </a>
      </p>
    </div>
  );
}
