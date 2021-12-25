import {useSelector, useDispatch} from "react-redux";
import logIn from "./actions/logIn"
import addData from "./actions/addData"
import {Button} from "react-toolbox/lib/button"
import Input from 'react-toolbox/lib/input';
import sendRequest from "./actions/sendRequest";


function Start() {
    const dispatch = useDispatch();
    const X = useSelector(state => state.X);
    const Y = useSelector(state => state.Y);
    const R = useSelector(state => state.R);
    const data = useSelector(state => state.data)

    var sha256 = require('js-sha256');
    return (
        <div>
            <div id="header">
                <h1>Бойко Владислав Алексеевич, Р3212, ВАРИАНТ: 12001</h1>
            </div>
            <div id="page">
                Enter your Login and Password:
                <br/>
                <form>
                    <Input id="login" label="L"/>
                    <Input id="password" label="P" type="password"/>
                </form>
                <Button label="Вход" onClick={() => {
                    let data = new FormData();
                    data.set("login", document.querySelector("#login").value);
                    data.set("password", sha256(document.querySelector("#password").value));
                    data.set("operation", "login");
                    sendRequest("POST", "http://localhost:8080/RESTFUL-1.0-SNAPSHOT/api/resource", data)
                        .then(
                            (data) => {
                                if (data[0].result.toString() === "true") {
                                    localStorage.setItem("user", document.querySelector("#login").value);
                                    localStorage.setItem("password", sha256(document.querySelector("#password").value));
                                    dispatch(logIn());
                                    for (let i = 1; i < data.length; i++) {
                                        dispatch(addData(data[i]))
                                    }
                                }
                            }
                    ).catch(err => console.log(err))
                }}/>
            </div>
        </div>
    );
}


export default Start;