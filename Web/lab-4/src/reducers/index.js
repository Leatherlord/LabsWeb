import loggedReducer from "./isLogged"
import xReducer from "./reducerX"
import yReducer from "./reducerY"
import rReducer from "./reducerR"
import dataReducer from "./dataReducer"
import {combineReducers} from "redux"

const combinedReducer = combineReducers({
    isLogged: loggedReducer,
    X: xReducer,
    Y: yReducer,
    R: rReducer,
    data: dataReducer
})

export default combinedReducer;