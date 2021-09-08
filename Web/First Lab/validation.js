let input = document.querySelector("#X")
let span = document.querySelector(".span")
let checkboxes = document.getElementsByClassName("checkbox")
let y1 = document.querySelector("#radioButton1")
y1.onclick = function (e) {
    chooseY(e, y1)
}
let y2 = document.querySelector("#radioButton2")
y2.onclick = function (e) {
    chooseY(e, y2)
}
let y3 = document.querySelector("#radioButton3")
y3.onclick = function (e) {
    chooseY(e, y3)
}
let y4 = document.querySelector("#radioButton4")
y4.onclick = function (e) {
    chooseY(e, y4)
}
let y5 = document.querySelector("#radioButton5")
y5.onclick = function (e) {
    chooseY(e, y5)
}
let y6 = document.querySelector("#radioButton6")
y6.onclick = function (e) {
    chooseY(e, y6)
}
let y7 = document.querySelector("#radioButton7")
y7.onclick = function (e) {
    chooseY(e, y7)
}
let y8 = document.querySelector("#radioButton8")
y8.onclick = function (e) {
    chooseY(e, y8)
}
let y9 = document.querySelector("#radioButton9")
y9.onclick = function (e) {
    chooseY(e, y9)
}

let yChosen = y1;
let valueY = document.querySelector("#valueY")

function chooseY(e, y) {
    e.preventDefault()
    yChosen.classList.remove("chosen")
    yChosen.classList.add("not-chosen")
    yChosen = y
    yChosen.classList.remove("not-chosen")
    yChosen.classList.add("chosen")
    // valueY.innerHTML = "stupid"
    valueY.outerHTML = `<input id="valueY" name="Y" value="${yChosen.value}" type="hidden">`
    valueY = document.querySelector("#valueY")
}

// console.log(checkboxes)


document.querySelector(".button").onclick = function (e) {
    e.preventDefault()
    if (!isNumber(input.value) || Math.abs(+input.value) > 5 || input.value === "") {
        notValid(span, "Введите число от -5 до 5")
    } else if (!isChecked(checkboxes)) {
        notValid(span, "Выберите хотя бы один R")
    } else {
        valid(span, "")
        document.forms.item(0).requestSubmit()
    }
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
