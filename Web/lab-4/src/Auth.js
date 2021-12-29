import {useDispatch, useSelector} from "react-redux";
import {Button} from "react-toolbox/lib/button"
import wantReg from "./actions/callbacks/wantReg";
import setUserInfo from "./actions/callbacks/setUserInfo";
import sendRequest, {preparations} from "./actions/sendRequest";
import logIn from "./actions/callbacks/logIn";
import setData from "./actions/callbacks/setData";

function Auth() {
    const dispatch = useDispatch();
    const X = useSelector(state => state.X);
    const Y = useSelector(state => state.Y);
    const R = useSelector(state => state.R);
    const data = useSelector(state => state.data);
    const userInfo = useSelector(state => state.userInfo);

    return (
        <div>
            <div id="header">
                <h1>Бойко Владислав Алексеевич, Р3212, ВАРИАНТ: 12001</h1>
            </div>
            <div id="page">
                Enter your Login and Password:
                <br/>
                <form>
                    <label htmlFor="login">Login<br/></label>
                    <input id="login" onChange={(val) => {
                        dispatch(setUserInfo(val.target.value, userInfo.password))
                    }}/>
                    <br/>
                    <label htmlFor="password">Password<br/></label>
                    <input id="password" type="password" onChange={(val) => {
                        dispatch(setUserInfo(userInfo.login, val.target.value))
                    }}/>
                    <br/>
                </form>
                <Button label="Вход" onClick={() => {
                    if (userInfo.password === "") {
                        alert("Пароль не должен быть пустой строкой!")
                    } else if (userInfo.login === "") {
                        alert("Логин не должен быть пустой строкой!")
                    } else {
                        let data = new FormData();
                        data.set("login", userInfo.login);
                        data.set("password", userInfo.password);
                        sendRequest("/authorize", data).then(response => {
                            if (response.ok) {
                                preparations(data).then(response => {
                                    if (response.ok) {
                                        return response.json().then(taken => {
                                            dispatch(setData(taken));
                                        });
                                    } else {
                                        return response.text().then(text => alert(text));
                                    }
                                });
                                dispatch(logIn());
                            } else {
                                return response.text().then(text => alert(text))
                            }
                        }).catch(err => console.log(err));
                    }
                }}/>
                <Button label="Регистрация" onClick={() => dispatch(wantReg(true))}/>
            </div>
        </div>
    );
}


export default Auth;