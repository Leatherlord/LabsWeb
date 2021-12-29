// import './Main.css';
// import duck from "./resources/blue_rotate.jpg";
// import zone from "./resources/new-area.png";
// import zoneNeg from "./resources/new-area-negative.png";
// import green_circle from "./resources/green_circle.svg";
// import red_circle from "./resources/red_circle.png";
// import {Button} from "react-toolbox/lib/button";
// import Input from 'react-toolbox/lib/input';
// import {Table, TableRow, TableCell} from "react-toolbox/lib/table";
//
// import {useSelector, useDispatch} from "react-redux";
// import setX from "./actions/setX";
// import setY from "./actions/setY";
// import setR from "./actions/setR";
// import addData from "./actions/addData"
// import sendRequest from "./actions/sendRequest";
// import logOut from "./actions/logOut";
//
//
//
//
// function Main() {
//     const dispatch = useDispatch();
//     const X = useSelector(state => state.X)
//     const Y = useSelector(state => state.Y)
//     const R = useSelector(state => state.R)
//     const data = useSelector(state => state.data)
//     return (
//         <div id="container">
//             <div id="header">
//                 <h1>Бойко Владислав Алексеевич, Р3212, ВАРИАНТ: 12001 <Button id="logOut" label="Выход" onClick={() => {
//                     localStorage.removeItem("user");
//                     localStorage.removeItem("password");
//                     dispatch(logOut())
//                 }}/> </h1>
//             </div>
//             <div id="page">
//                 <div id="content">
//                     <div id="input-field">
//                         <h3>Заполните форму ниже, пожалуйста:</h3>
//                         <form>
//                             <div>
//                                 Координата Х: {X}<br/>
//                                 <Button label="-4" onClick={() => dispatch(setX(-4))}/>
//                                 <Button label="-3" onClick={() => dispatch(setX(-3))}/>
//                                 <Button label="-2" onClick={() => dispatch(setX(-2))}/>
//                                 <Button label="-1" onClick={() => dispatch(setX(-1))}/>
//                                 <Button label="0" onClick={() => dispatch(setX(0))}/>
//                                 <Button label="1" onClick={() => dispatch(setX(1))}/>
//                                 <Button label="2" onClick={() => dispatch(setX(2))}/>
//                                 <Button label="3" onClick={() => dispatch(setX(3))}/>
//                                 <Button label="4" onClick={() => dispatch(setX(4))}/>
//                             </div>
//                             <div>
//                                 Введите Y от -5 до 5: {Y}<br/>
//                                 <Input id="Y" type="number" min="-5" max="5" onChange={() => dispatch(setY(document.querySelector("#Y").value))}/>
//                             </div>
//                             <div>
//                                 Параметр R: {R}<br/>
//                                 <Button label="-4" onClick={() => dispatch(setR(-4))}/>
//                                 <Button label="-3" onClick={() => dispatch(setR(-3))}/>
//                                 <Button label="-2" onClick={() => dispatch(setR(-2))}/>
//                                 <Button label="-1" onClick={() => dispatch(setR(-1))}/>
//                                 {/*<Button label="0" onClick={() => dispatch(setR(0))}/>*/}
//                                 <Button label="1" onClick={() => dispatch(setR(1))}/>
//                                 <Button label="2" onClick={() => dispatch(setR(2))}/>
//                                 <Button label="3" onClick={() => dispatch(setR(3))}/>
//                                 <Button label="4" onClick={() => dispatch(setR(4))}/>
//                             </div>
//                             <div><Button label="Отправить" onClick={() => {
//                                 let data = new FormData();
//                                 data.set("X", X);
//                                 data.set("Y", Y);
//                                 data.set("R", R);
//                                 data.set("operation", "data");
//                                 sendRequest("POST", "http://localhost:8080/RESTFUL-1.0-SNAPSHOT/api/resource", data).then(data => {
//                                     dispatch(addData(data[0]));
//                                 });
//                             }} /></div>
//                         </form>
//                     </div>
//                     <div id="ajax">
//                         <Table>
//                             {data.map((item, idx) => {
//                                 return(
//                                     <TableRow key={idx}>
//                                         <TableCell>{item.X}</TableCell>
//                                         <TableCell>{item.Y}</TableCell>
//                                         <TableCell>{item.R}</TableCell>
//                                         <TableCell>{item.result}</TableCell>
//                                         <TableCell>{item.date}</TableCell>
//                                     </TableRow>
//                                 )
//                             })}
//                         </Table>
//                     </div>
//                 </div>
//
//                 <div id="sidebar">
//                     <img id="duck" src={duck} alt={"duck was here"}/>
//                     <div id="field" onClick={
//                         e => {
//                             e.preventDefault();
//                             let xParent = 0;
//                             let yParent = 0;
//                             let parent = document.querySelector("#field");
//                             while (parent) {
//                                 xParent += (parent.offsetLeft - parent.scrollLeft + parent.clientLeft);
//                                 yParent += (parent.offsetTop - parent.scrollTop + parent.clientTop);
//                                 parent = parent.offsetParent;
//                             }
//                             let xPos = Math.round((e.clientX - xParent - 144 + window.scrollX) * R) / 100
//                             let yPos = Math.round(-(e.clientY - yParent - 143 + window.scrollY) * R) / 100
//                             let data = new FormData();
//                             data.set("X", xPos);
//                             data.set("Y", yPos);
//                             data.set("R", R);
//                             data.set("operation", "data");
//                             sendRequest("POST", "http://localhost:8080/RESTFUL-1.0-SNAPSHOT/api/resource", data).then(data => {
//                                 dispatch(addData(data[0]));
//                             });
//                         }
//                     }>
//                         <img id="zone" src={R > 0 ? zone : zoneNeg} alt={"zone was here"}/>
//                         {data.map((item, idx) => {
//                             let r = +item.R;
//                             if (r === R) {
//                                 let xParent = 0;
//                                 let yParent = 0;
//                                 let parent = document.querySelector("#field");
//                                 while (parent) {
//                                     xParent += (parent.offsetLeft - parent.scrollLeft + parent.clientLeft);
//                                     yParent += (parent.offsetTop - parent.scrollTop + parent.clientTop);
//                                     parent = parent.offsetParent;
//                                 }
//                                 let xPos = +item.X;
//                                 let yPos = +item.Y;
//
//                                 let clientX = (100 * xPos / r) + xParent + 144;
//                                 let clientY = yParent + 143 - (100 * yPos / r);
//                                 let style = {
//                                     width: 10,
//                                     height: 10,
//                                     left: (clientX - 5) + "px",
//                                     top: ((clientY - 64) - 5) + "px",
//                                     position: "absolute"
//                                 }
//                                 return (
//                                     <img id={"img" + idx}
//                                          src={item.result === "true" ? green_circle : red_circle}
//                                          alt={":("} style={style}/>
//                                 )
//                             }
//                             return (<div/>);
//                         })}
//                     </div>
//                 </div>
//
//             </div>
//         </div>
//     );
// }
//
// export default Main;