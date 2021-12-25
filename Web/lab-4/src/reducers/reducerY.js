const yReducer = (state = 0, action) => {
    switch (action.type) {
        case "SET_Y":
            if (!isNumber(action.value) || action.value === "") {
                return "Введите число";
            } else if (Math.abs(+action.value) > 5) {
                return "Введите число от -5 до 5";
            }
            return +action.value;
        default:
            return state;
    }
}

function isNumber(value) {
    return !isNaN(value) && isFinite(+value);
}

export default yReducer;