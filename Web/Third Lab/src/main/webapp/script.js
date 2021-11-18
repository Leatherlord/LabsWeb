var inside = false

let input = document.querySelector(".Y")
let span = document.querySelector(".span")
let checkboxes = document.getElementsByClassName("checkbox")
let pointer = document.querySelector("#pointer")
let rRadio = document.getElementsByClassName("radio")
let table = document.querySelector("#the-only-table")
let form = document.getElementById("toSend")

let x = document.querySelector("#X")

document.querySelector("#field").onclick = function (e) {
    e.preventDefault()
    // if (!isChecked(rRadio)) {
    //     notValid(span, "Выберите R")
    // } else {
        if (e.clientX < 293 && e.clientY < 590 && e.clientY > 303) {
            let r = (+ice.ace.instance("toSend:R").getValue())
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

            let img = document.createElement("img")

            form.elements[4].value = xPos.toString()
            form.elements[5].value = yPos.toString()
            inside = true

            document.querySelector(".button").click()

            inside = false

            form.elements[4].value = 0
            form.elements[5].value = 0

            img.src = "solana-circle.png"
            img.style.height = 10 + "px"
            img.style.width = 10 + "px"
            img.style.left = (e.clientX - 5) + "px"
            img.style.top = ((e.clientY - 64) - 5) + "px"
            img.style.position = "absolute"

            e.currentTarget.appendChild(img)

            pointer.style.left = (e.clientX - (pointer.clientWidth / 2)) + "px"
            pointer.style.top = ((e.clientY - 64) - (pointer.clientWidth / 2)) + "px"
            console.log(xPos.toString(), " ", yPos.toString())

            // let formData = new FormData(form)
            // formData.set("X[]", xPos.toString())
            // formData.set("Y", yPos.toString())
            // formData.set("ONLOAD", "false")
            // sendRequest("POST", "controller-servlet", formData)
            //     .then(addRow)
            //     .catch(err => console.log(err));
        // }
    }
}

document.querySelector(".button").onclick = function (e) {
    if (!isNumber(input.value) || Math.abs(+input.value) > 5 || input.value === "") {
        e.preventDefault()
        notValid(span, "Введите число от -5 до 5")
    } else {
        valid(span, "")
        if (!inside) {
            form.elements[4].value = form.elements[3].value
        }
    }
}

function addRow(data) {
    if (data[0].result.toString() !== ("none") && data[0].state.toString() !== ("true")) {
        for (let i = 0; i < data.length; i++) {
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
            cell.appendChild(text);
            valid(span, "")
        }
    } else {
        notValid(span, data[0].message)
    }
}

function sendRequest(method, url, data = new FormData(form)) {
    return fetch(url, {
        method: method,
        body: data
    }).then(response => {
        return response.json()
    })
}

function isNumber(value) {
    return !isNaN(value) && isFinite(+value);
}

function notValid(element, message) {
    element.innerHTML = message
}

function valid(element, message) {
    element.innerHTML = message
}

function isChecked(massive) {
    for (let i = 0; i < massive.length; i++) {
        if (massive.item(i).checked) {
            return true
        }
    }
    return false
}