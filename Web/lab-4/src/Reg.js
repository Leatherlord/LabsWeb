import {useSelector, useDispatch} from "react-redux";
import {Button} from "react-toolbox/lib/button"
import wantReg from "./actions/callbacks/wantReg";
import setUserInfo from "./actions/callbacks/setUserInfo";
import sendRequest, {preparations} from "./actions/sendRequest";
import setData from "./actions/callbacks/setData";
import setState from "./actions/callbacks/setState";


function Reg() {
    const dispatch = useDispatch();
    const X = useSelector(state => state.X);
    const Y = useSelector(state => state.Y);
    const R = useSelector(state => state.R);
    const data = useSelector(state => state.data)
    const userInfo = useSelector(state => state.userInfo);
    const message = useSelector(state => state.state);

    return (
        <div>
            <div id="header">
                <h1>Бойко Владислав Алексеевич, Р3212, ВАРИАНТ: 12001</h1>
            </div>
            <div id="page">
                Enter new Login and Password:
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
                    {message}
                </form>
                <Button label="Регистрация" onClick={() => {
                    if (userInfo.password === "") {
                        dispatch(setState("Пароль не должен быть пустой строкой!"))
                        // alert("Пароль не должен быть пустой строкой!")
                    } else if (userInfo.login === "") {
                        dispatch(setState("Логин не должен быть пустой строкой!"))
                        // alert("Логин не должен быть пустой строкой!")
                    } else {
                        let data = new FormData();
                        data.set("login", userInfo.login);
                        data.set("password", userInfo.password);
                        sendRequest("/registration", data).then(response => {
                            if (response.ok) {
                                preparations(data).then(response => {
                                    if (response.ok) {
                                        return response.json().then(taken => {
                                            dispatch(setData(taken));
                                        });
                                    } else {
                                        return response.text().then(text => dispatch(setState(text)));
                                    }
                                });
                                dispatch(setState());
                                dispatch(wantReg(false));
                            } else {
                                return response.text().then(text => dispatch(setState(text)))
                            }
                        }).catch(err => console.log(err));
                    }
                }}/>
                <Button label="Вход" onClick={() => dispatch(wantReg(false))}/>
            </div>
        </div>
    );
}


export default Reg;