let inside = false
let input = document.querySelector(".Y")
let span = document.querySelector(".span")
let table = document.querySelector("#the-only-table")
let form = document.getElementById("toSend")
let R = Math.round(ice.ace.instance("toSend:R").getValue() * 100) / 100

window.onload = function (e) {
    document.querySelector("a[role=\"slider\"]").click()
}

document.querySelector("a[role=\"slider\"]").onclick = function (e) {
    R = Math.round(ice.ace.instance("toSend:R").getValue() * 100) / 100
    document.querySelector("#field").innerHTML = "<img id=\"zone\" src=\"red_field.png\" alt=\"red_field.png\"/>\n"
    let tbody = document.querySelector("tbody")
    let trs = tbody.children
    for (let tr of trs) {
        let tds = tr.children
        if (Math.round(parseFloat(tds[2].innerText) * 100) / 100 === R) {
            drawTheImg(tr)
        }
    }
}


document.querySelector("#field").onclick = function (e) {
    e.preventDefault()
    if (e.clientX < 293 && e.clientY < 590 && e.clientY > 303) {
        let xParent = 0;
        let yParent = 0;
        let parent = e.currentTarget
        while (parent) {
            xParent += (parent.offsetLeft - parent.scrollLeft + parent.clientLeft);
            yParent += (parent.offsetTop - parent.scrollTop + parent.clientTop);
            parent = parent.offsetParent;
        }

        let xPos = Math.round((e.clientX - xParent - 143) * R / 96 * 100) / 100
        let yPos = Math.round(-(e.clientY - yParent - 143) * R / 96 * 100) / 100

        form.elements[4].value = xPos.toString()
        form.elements[5].value = yPos.toString()

        inside = true

        document.querySelector(".button").click()

        inside = false

        form.elements[4].value = 0
        form.elements[5].value = 0
    }
}


let target = document.querySelector("#the-only-table")

let config = {
    childList: true
}

function callback() {
    let tr = document.querySelector("tbody").lastElementChild
    drawTheImg(tr)
}

let observer = new MutationObserver(callback)
observer.observe(target, config)


function drawTheImg(tr) {
    let img = document.createElement("img")
    let tds = tr.children
    let position = false
    if (tds[3].innerText === "true") {
        position = true
    }
    if (position) {
        img.src = "green_circle.svg"
    } else {
        img.src = "red_circle.png"
    }
    let xPos = (+tds[0].innerText)
    let yPos = (+tds[1].innerText)
    let r = (+tds[2].innerText)

    let xParent = 0;
    let yParent = 0;
    let parent = document.querySelector("#field")
    while (parent) {
        xParent += (parent.offsetLeft - parent.scrollLeft + parent.clientLeft);
        yParent += (parent.offsetTop - parent.scrollTop + parent.clientTop);
        parent = parent.offsetParent;
    }
    let clientX = (96 * xPos / r) + xParent + 143
    let clientY = yParent + 143 - (96 * yPos / r)

    img.style.height = 10 + "px"
    img.style.width = 10 + "px"
    img.style.left = (clientX - 5) + "px"
    img.style.top = ((clientY - 64) - 5) + "px"
    img.style.position = "absolute"

    document.querySelector("#field").appendChild(img)
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
        form.elements[6].value = R
        form.elements[7].value = R
    }
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
