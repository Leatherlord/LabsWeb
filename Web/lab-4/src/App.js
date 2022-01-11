import {useDispatch, useSelector} from "react-redux";
import Auth from "./Auth";
import Reg from "./Reg"
import Main from "./Main";
import sendRequest, {preparations} from "./actions/sendRequest";
import logIn from "./actions/callbacks/logIn";
import setData from "./actions/callbacks/setData";
import setState from "./actions/callbacks/setState";


function App() {
    const isLogged = useSelector(state => state.isLogged);
    const doesWantReg = useSelector(state => state.wantReg);
    const dispatch = useDispatch();
    if (!isLogged) {
        let login = localStorage.getItem("user");
        let password = localStorage.getItem("password");
        if (!login || !password) {
            if (!doesWantReg) {
                return (
                    <div className="App">
                        {Auth()}
                    </div>
                );
            } else {
                return (
                    <div className="App">
                        {Reg()}
                    </div>
                );
            }
        }
        let data = new FormData();
        data.set("login", login);
        data.set("password", password);
        sendRequest("/authorize", data).then(response => {
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
                dispatch(logIn());
            } else {
                return response.text().then(text => dispatch(setState(text)))
            }
        }).catch(err => console.log(err));

        return (
            <div className="App">
                {Auth()}
            </div>
        );
    }
    return (
        <div className="App">
            {Main()}
        </div>
    );
}

export default App;
