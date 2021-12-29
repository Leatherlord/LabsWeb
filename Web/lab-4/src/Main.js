import React from "react";
import './Main.css';
import zone from "./resources/new-area.png";
import zoneNeg from "./resources/new-area-negative.jpg";
import green_circle from "./resources/green_circle.png";
import red_circle from "./resources/red_circle.png";
import {Button} from "react-toolbox/lib/button";
import Input from 'react-toolbox/lib/input';
import {Table, TableCell, TableHead, TableRow} from "react-toolbox/lib/table";
import duck from "./resources/blue_rotate.jpg"

import {useDispatch, useSelector} from "react-redux";
import setX from "./actions/callbacks/setX";
import setY from "./actions/callbacks/setY";
import setR from "./actions/callbacks/setR";
import logOut from "./actions/callbacks/logOut";
import clearData from "./actions/callbacks/clearData";
import sendRequest from "./actions/sendRequest";
import addData from "./actions/callbacks/addData";


function Main() {
    const dispatch = useDispatch();
    const X = useSelector(state => state.X)
    const Y = useSelector(state => state.Y)
    const R = useSelector(state => state.R)
    const data = useSelector(state => state.data)
    const userInfo = useSelector(state => state.userInfo);

    let xOffset = 150;
    let yOffset = 150;
    let xSetOffset = -6;
    let ySetOffset = -5;
    let pointDiameter = 10;
    let pixelsToValuesMultiplier = 100;
    let parentRef = React.createRef();

    return (
        <div id="container">

            <div id="header">
                <h1>Бойко Владислав Алексеевич, Р3212, ВАРИАНТ: 12001 <Button id="logOut" label="Выход" onClick={() => {
                    localStorage.removeItem("user");
                    localStorage.removeItem("password");
                    dispatch(clearData());
                    dispatch(logOut());
                }}/></h1>
            </div>

            <div id="page">

                <div id="content">

                    <div id="input-field">
                        <h3>Заполните форму ниже, пожалуйста:</h3>
                        <form>
                            <div>
                                Координата Х: {X}<br/>
                                <Button label="-4" onClick={() => dispatch(setX(-4))}/>
                                <Button label="-3" onClick={() => dispatch(setX(-3))}/>
                                <Button label="-2" onClick={() => dispatch(setX(-2))}/>
                                <Button label="-1" onClick={() => dispatch(setX(-1))}/>
                                <Button label="0" onClick={() => dispatch(setX(0))}/>
                                <Button label="1" onClick={() => dispatch(setX(1))}/>
                                <Button label="2" onClick={() => dispatch(setX(2))}/>
                                <Button label="3" onClick={() => dispatch(setX(3))}/>
                                <Button label="4" onClick={() => dispatch(setX(4))}/>
                            </div>
                            <div>
                                Введите Y от -5 до 5: {Y}<br/>
                                <Input id="Y" type="number" value={Y} min="-5" max="5" onChange={(val) => {
                                    dispatch(setY(val));
                                }}/>
                            </div>
                            <div>
                                Параметр R: {R}<br/>
                                <Button label="-4" onClick={() => dispatch(setR(-4))}/>
                                <Button label="-3" onClick={() => dispatch(setR(-3))}/>
                                <Button label="-2" onClick={() => dispatch(setR(-2))}/>
                                <Button label="-1" onClick={() => dispatch(setR(-1))}/>
                                <Button label="1" onClick={() => dispatch(setR(1))}/>
                                <Button label="2" onClick={() => dispatch(setR(2))}/>
                                <Button label="3" onClick={() => dispatch(setR(3))}/>
                                <Button label="4" onClick={() => dispatch(setR(4))}/>
                            </div>
                            <div><Button label="Отправить" onClick={() => {
                                let data = new FormData();
                                data.set("X", X);
                                data.set("Y", Y);
                                data.set("R", R);
                                data.set("login", localStorage.getItem("user"));
                                data.set("password", localStorage.getItem("password"));
                                sendRequest("/points/add", data, dispatch).then(response => {
                                    if (response.ok) {
                                        return response.json().then(point => dispatch(addData(point[0])));
                                    } else {
                                        return response.text().then(text => alert(text));
                                    }
                                }).catch(err => console.log(err));

                            }}/></div>
                        </form>
                    </div>

                    <div id="sidebar">

                        <img id="duck" src={duck}/>

                        <div id="field" ref={parentRef} onClick={
                            e => {
                            e.preventDefault();

                            let xParent = +parentRef.current.getBoundingClientRect().x.toString();
                            let yParent = +parentRef.current.getBoundingClientRect().y.toString();

                            let xPos = Math.round((e.clientX - xParent - xOffset) * R) / pixelsToValuesMultiplier
                            let yPos = Math.round(-(e.clientY - yParent - yOffset) * R) / pixelsToValuesMultiplier
                            console.log(xPos, " ", yPos)
                            let data = new FormData();
                            data.set("X", xPos.toString());
                            data.set("Y", yPos.toString());
                            data.set("R", R);
                            data.set("login", localStorage.getItem("user"));
                            data.set("password", localStorage.getItem("password"));
                            sendRequest("/points/add", data, dispatch).then(response => {
                                if (response.ok) {
                                    return response.json().then(point => dispatch(addData(point[0])));
                                } else {
                                    return response.text().then(text => alert(text));
                                }
                            }).catch(err => console.log(err));
                        }
                        }>

                            <img id="zone" src={R > 0 ? zone : zoneNeg} alt={"zone was here"}/>
                            {data.map((item, idx) => {
                            let r = +item.R;
                            if (r === R) {

                                let xPos = +item.X;
                                let yPos = +item.Y;

                                let clientX = xOffset + xSetOffset + (pixelsToValuesMultiplier * xPos / r);
                                let clientY = yOffset + ySetOffset - (pixelsToValuesMultiplier * yPos / r);
                                let style = {
                                    width: pointDiameter,
                                    height: pointDiameter,
                                    left: (clientX - pointDiameter / 2) + "px",
                                    top: (clientY - pointDiameter / 2) + "px",
                                    position: "absolute"
                                }
                                return (
                                    <img id={"img" + idx}
                                         src={item.result === "true" ? green_circle : red_circle}
                                         alt={":("} style={style}/>
                                )
                            }
                            return (<div/>);
                        })}

                        </div>

                    </div>

                    <div id="ajax">
                        <Table id="the-only-table">
                            {data.map((item, idx) => {
                                if (idx === 0) {
                                    return (
                                        <TableHead key={idx}>
                                            <TableCell>{item.X}</TableCell>
                                            <TableCell>{item.Y}</TableCell>
                                            <TableCell>{item.R}</TableCell>
                                            <TableCell>{item.result}</TableCell>
                                            <TableCell>{item.date}</TableCell>
                                        </TableHead>
                                    )
                                }
                                return (
                                    <TableRow key={idx}>
                                        <TableCell>{item.X}</TableCell>
                                        <TableCell>{item.Y}</TableCell>
                                        <TableCell>{item.R}</TableCell>
                                        <TableCell>{item.result}</TableCell>
                                        <TableCell>{window.innerWidth > 641 ? item.date: item.date.substring(0,11)}</TableCell>
                                    </TableRow>
                                )
                            })}
                        </Table>
                    </div>

                </div>

            </div>

        </div>
    );
}

export default Main;