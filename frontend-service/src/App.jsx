import {useState} from "react";
import log from "eslint-plugin-react/lib/util/log.js";

export function App() {
    const [registerState, setRegisterState] = useState({username: '', password: '', email: ''});
    const [loginState, setLoginState] = useState({username: '', password: ''});

    const handleRegisterForm = (e) => {
        const {name, value} = e.target;
        setRegisterState(prevState => {
            return {
                ...prevState,
                [name]: value
            }})
    }

    const handleLoginForm = (e) => {
        const {name, value} = e.target;
        setLoginState(prevState => {
            return {
                ...prevState,
                [name]: value
            }
        })
    }

    const handleRegisterSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8084/account', {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Access-Control-Allow-Origin': '*',
            },
            body: JSON.stringify(registerState)
        })
            .then((res) => console.log(res))
    }

    const handleLoginSubmit = (e) => {
        e.preventDefault();
        console.log(loginState)
        fetch('http://localhost:8084/auth/login', {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Access-Control-Allow-Origin': '*',
            },
            body: JSON.stringify(loginState)
        })
            .then((res) => console.log(res))
    }

  return (
      <>

          <form>
              <label>Login</label>
              <input onChange={handleRegisterForm} name={'username'} value={registerState.username}/>
              <br/>
              <label>Email</label>
              <input onChange={handleRegisterForm} name={'email'} value={registerState.email}/>
              <br/>
              <label>Password</label>
              <input onChange={handleRegisterForm} name={'password'} value={registerState.password}/>
              <br/>
              <button onClick={handleRegisterSubmit}>Register</button>
          </form>
          <form>
              <label>Login</label>
              <input onChange={handleLoginForm} name={'username'} value={loginState.username}/>
              <br />
              <label>Password</label>
              <input onChange={handleLoginForm} name={'password'} value={loginState.password}/>
              <br />
              <button onClick={handleLoginSubmit}>Login</button>
          </form>
      </>
  )
}