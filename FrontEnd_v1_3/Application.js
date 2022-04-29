let vinCount = document.getElementById("vinCount");
let delay = document.getElementById("delay");
let different = document.getElementById("different");
let empty = document.getElementById("empty");
let emptyDelay = document.getElementById("emptyDelay");
let emptyDiff = document.getElementById("emptyDiff");
let countErr = document.getElementById("countErr");
let delayErr = document.getElementById("delayErr");
let diffErr = document.getElementById("diffErr");
let acknowledge = document.getElementById("display");
let ackErr = document.getElementById("disErr");
let emptyMsg = "It should not be empty*";
let countErrMsg = "It should be an integer & greater than or equal to 1";
let delayErrMsg = "It should be an integer & greater than or equal to 0";
let diffErrMsg = "It should be 'Y' or 'N'";
let acknowMsg = "Success";
let ackErrMsg = "Try Again";


function checking() {
    empty.textContent = "";
    countErr.textContent = "";
    delayErr.textContent = "";
    diffErr.textContent = "";
    emptyDelay.textContent = "";
    emptyDiff.textContent = "";
    acknowledge.textContent="";
    ackErr.textContent ="";

    if (vinCount.value == "") {
        empty.textContent = emptyMsg;
    }
    if (parseInt(vinCount.value) < 1 || isNaN(vinCount.value)) {
        countErr.textContent = countErrMsg;
    }
    if (delay.value == "") {
        emptyDelay.textContent = emptyMsg;
    }
    if (parseInt(delay.value) < 0 || isNaN(delay.value)) {
        delayErr.textContent = delayErrMsg;
    }
    if (different.value == "") {
        emptyDiff.textContent = emptyMsg;
    } else if ((different.value != "Y") && (different.value != "N")) {
        diffErr.textContent = diffErrMsg;
    } else if ((vinCount.value != "") && (delay.value != "") && (different.value != "") && parseInt(vinCount.value) >= 1 && !isNaN(vinCount.value) && parseInt(delay.value) >= 0 && !isNaN(delay.value) && ((different.value == "Y") || (different.value == "N"))) {
        let data = {
            vinCount: parseInt(vinCount.value),
            delay: parseInt(delay.value),
            different: different.value
        };
        console.log(data);
        let options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
                Authorization: "Bearer eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbiIsImF1dCI6IkFQUExJQ0FUSU9OIiwiYXVkIjoiWnJjazluWFpVUktIZ29icG00b3hiV245R2hBYSIsIm5iZiI6MTY0MDMyMTYzNywiYXpwIjoiWnJjazluWFpVUktIZ29icG00b3hiV245R2hBYSIsInNjb3BlIjoiZGVmYXVsdCIsImlzcyI6Imh0dHBzOlwvXC9sb2NhbGhvc3Q6OTQ0M1wvb2F1dGgyXC90b2tlbiIsImV4cCI6MTY0MDQwODAzNywiaWF0IjoxNjQwMzIxNjM3LCJqdGkiOiIwODhjZTc2NS01NmE2LTQ4OTQtOGU0Ni1lN2UxYWM2NTNjNmIifQ.MqGm7BKmPHZ6MCQgjaWOpWQYbPyt1Rc_Oj7FLvErrcwqdc0v5atrwlT2AqK5qGp0QhSOAXuGN1-NQzQ-SYilayI6mV2-hZgWJz7E0ApACPqANXDyibYWq6pIwjWrQWqE0FKJs7zOwn7lDHySYZDKT0LBgaNpUrFTJBwhkHc7JAdkiSLKqvWGpnoQMx9Wy7YadttPZ4VgHu2biZ3N_s7VZEYxzB_oTJEma41PCysDCnMVo5KAqv2TxKULhPYXEgjBkUrSD74LsffdyZT6hYB_VcMa38DlOVmuDjachWJ2n3L9qg5nd-DtDjdY4JFfdh9f-JJPaLrqdvGjwIDnTxRMog"

            },
            body: JSON.stringify(data)
        };
        fetch("http://localhost:8084/data", options)
            .then(function(response) {
                return response.status;
            })
            .then(function(status) {
                console.log(status);
                if (status == 200) {
                    acknowledge.textContent = acknowMsg;
                } else {
                    ackErr.textContent = ackErrMsg;
                }
            })
        vinCount.value = "";
        delay.value = "";
        different.value = "";
    }
}