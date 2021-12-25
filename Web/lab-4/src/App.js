import {useSelector, useDispatch} from "react-redux";
import Start from "./Start";
import Main from "./Main";
import sendRequest from "./actions/sendRequest";
import logIn from "./actions/logIn"
import addData from "./actions/addData"


function App() {
  const isLogged = useSelector(state => state.isLogged);
  const dispatch = useDispatch();
  const sha256 = require('js-sha256');
  if (!isLogged) {
      let login = localStorage.getItem("user");
      let password = localStorage.getItem("password");
      if (!login || !password) {
        return (
            <div className="App">
              {Start()}
            </div>
        );
      }
      let data = new FormData();
      data.set("login", login);
      data.set("password", password);
      data.set("operation", "login");
      sendRequest("POST", "http://localhost:8080/RESTFUL-1.0-SNAPSHOT/api/resource", data)
          .then(
              (data) => {
                if (data[0].result.toString() === "true") {
                  localStorage.setItem("user", login);
                  localStorage.setItem("password", password);
                  dispatch(logIn());
                  for (let i = 1; i < data.length; i++) {
                    dispatch(addData(data[i]));
                  }
                  return (
                      <div className="App">
                        {Main()}
                      </div>
                  );
                }
                return (
                    <div className="App">
                      {Start()}
                    </div>
                );
              }
          )
  }
  return (
      <div className="App">
        {Main()}
      </div>
  );
}

export default App;
