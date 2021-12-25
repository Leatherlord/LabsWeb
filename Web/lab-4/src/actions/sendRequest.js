function sendRequest(method, url, data) {
    return fetch(url, {
        method: method,
        // mode: "no-cors",
        body: data
    }).then(response => {
        return response.json()
    })
}

export default sendRequest;