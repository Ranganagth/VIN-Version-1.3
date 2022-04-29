let vinNo = document.getElementById("vinNo1");
let speed = document.getElementById("speed1");
let errorVin = document.getElementById("errorVin");
let errLen = document.getElementById("errLen");
let errorSpd = document.getElementById("errorSpd");
let acknowledge = document.getElementById("display");
let ackErr = document.getElementById("disErr");
let errorMsg = "It should not be empty*";
let speedInput = "Speed must be an integer & between 1 to 300*";
let errorLength = "Length must be 17";
let acknowMsg =  "SUCCESS";
let ackErrMsg = "Try Again";

function checking() {
    errorVin.textContent = "";
    errLen.textContent = "";
    errorSpd.textContent = "";
    acknowledge.textContent = "";
    let vinNoCheck = vinNo.value;
    if (vinNoCheck.length != 17) {
        errLen.textContent = errorLength;
        errorVin.textContent = "";
    }
    if (vinNo.value == "") {
        errorVin.textContent = errorMsg;
        errLen.textContent = "";
    }
    if (speed.value == "") {
        errorSpd.textContent = errorMsg;
    }
    if (parseInt(speed.value) <= 0 || (isNaN(speed.value)) || parseInt(speed.value)>300) {
        errorSpd.textContent = speedInput;
    } else if (vinNo != "" && speed.value != "" && vinNoCheck.length == 17) {
        errorVin.textContent = "";
        errLen.textContent = "";
        errorSpd.textContent = "";
        var today=new Date();
        
        let timeStamp= [today.getFullYear(), today.getMonth() + 1, today.getDate()].join('-') + ' ' + [today.getHours(),today.getMinutes(),today.getSeconds(),today.getMilliseconds()].join(':');
        let data = {
            vin: vinNo.value,
            speed: parseInt(speed.value),
            time: timeStamp
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
        fetch("http://localhost:8085/vin", options)
            .then(function(response) {
                return response.status;
            })
            .then(function(status) {
                console.log(status);
                if (status == 200) {
                    acknowledge.textContent = acknowMsg;
                }
                else {
                    ackErr.textContent = ackErrMsg;
                }
            })
        vinNo.value = "";
        speed.value = "";

        
    }

}
