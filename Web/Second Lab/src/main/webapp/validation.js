let input = document.querySelector("#Y")
let span = document.querySelector(".span")
let checkboxes = document.getElementsByClassName("checkbox")
let pointer = document.querySelector("#pointer")
let rRadio = document.getElementsByClassName("radio")
let table = document.querySelector("#the-only-table")

let form = document.getElementById("toSend")

window.onload = function (e) {
    let formData = new FormData(form)
    formData.set("ONLOAD", "true")
    sendRequest("POST", "controller-servlet", formData)
        .then(addRow)
        .catch(err => console.log(err))

}

document.querySelector("#field").onclick = function (e) {
    e.preventDefault()
    // console.log(e.clientX + " " + e.clientY)

    if (!isChecked(rRadio)) {
        notValid(span, "Выберите R")
    } else {
        if (e.clientX < 293 && e.clientY < 590 && e.clientY > 303) {
            let r = 0
            for (let i = 0; i < rRadio.length; i++) {
                if (rRadio.item(i).checked) {
                    r = (+rRadio.item(i).value)
                }
            }

            let xParent = 0;
            let yParent = 0;
            let parent = e.currentTarget
            while (parent) {
                xParent += (parent.offsetLeft - parent.scrollLeft + parent.clientLeft);
                yParent += (parent.offsetTop - parent.scrollTop + parent.clientTop);
                parent = parent.offsetParent;
            }

            let xPos = Math.round((e.clientX - xParent - 143) * r / 96 * 100) / 100
            let yPos = Math.round(-(e.clientY - yParent - 143) * r / 96 * 100) / 100
            // console.log(e.clientX + " " + e.clientY)

            let img = document.createElement("img")
            img.src = "solana-circle.png"
            img.style.height = 10 + "px"
            img.style.width = 10 + "px"
            img.style.left = (e.clientX - 5) + "px"
            img.style.top = ((e.clientY - 64) - 5) + "px"
            img.style.position = "absolute"

            e.currentTarget.appendChild(img)

            pointer.style.left = (e.clientX - (pointer.clientWidth / 2)) + "px"
            pointer.style.top = ((e.clientY - 64) - (pointer.clientWidth / 2)) + "px"
            // table.innerHTML = "<tr><td>X</td><td>Y</td><td>R</td><td>Result</td><td>Date</td></tr>"

            let formData = new FormData(form)
            formData.set("X[]", xPos.toString())
            formData.set("Y", yPos.toString())
            formData.set("ONLOAD", "false")
            sendRequest("POST", "controller-servlet", formData)
                .then(addRow)
                .catch(err => console.log(err));
        }
    }
}


document.querySelector(".button").onclick = function (e) {
    e.preventDefault()
     if (!isChecked(checkboxes)) {
        notValid(span, "Выберите хотя бы один X")
    } else if (!isNumber(input.value) || Math.abs(+input.value) > 5 || input.value === "") {
        notValid(span, "Введите число от -5 до 5")
    } else {
        valid(span, "")
         // table.innerHTML = "<tr><td>X</td><td>Y</td><td>R</td><td>Result</td><td>Date</td></tr>"
         let formData = new FormData(form)
         formData.set("ONLOAD", "false")
        sendRequest("POST", "controller-servlet", formData)
            .then(addRow)
            .catch(err => console.log(err));
     }
}

function addRow(data) {
    if (data[0].result.toString() !== ("none")) {
        for (let i = 0; i < data.length; i++) {
            // console.log(data[i])
            let row = table.insertRow();
            let cell = row.insertCell(0)
            let text = document.createTextNode(data[i].x)
            cell.appendChild(text);
            cell = row.insertCell(1)
            text = document.createTextNode(data[i].y)
            cell.appendChild(text);
            cell = row.insertCell(2)
            text = document.createTextNode(data[i].r)
            cell.appendChild(text);
            cell = row.insertCell(3)
            text = document.createTextNode(data[i].result)
            cell.appendChild(text);
            cell = row.insertCell(4)
            text = document.createTextNode(data[i].date)
            // let date = new Date()
            // text = document.createTextNode(date.getDate() + "." + date.getMonth() + "." + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds())
            cell.appendChild(text);
        }
    }
}
function sendRequest(method, url, data = new FormData(form)) {
    return fetch(url, {
        method: method,
        body: data
    }).then(response => {
        let resp = response.clone()
        // console.log(resp.text())
        let json = response.json().catch(e => {
            console.log(resp.text())
            let obj = new Object()
            obj.result = "none"
            return [obj]
        })
        return json
    })
}

function isNumber(value) {
    return !isNaN(value) && isFinite(+value);
}

function notValid(element, message) {
    // input.classList.add("is-invalid")
    element.innerHTML = message
}

function valid(element, message) {
    // input.classList.remove("is-invalid")
    // input.classList.add("is-valid")
    element.innerHTML = message
}

function isChecked(massive) {
    // console.log(massive)
    for (let i = 0; i < massive.length; i++) {
        // console.log(massive.item(i))
        if (massive.item(i).checked) {
            return true
        }
    }
    return false
}
