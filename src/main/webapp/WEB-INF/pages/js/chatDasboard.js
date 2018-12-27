var ws;

function connect() {
    
    //var username = document.getElementById("username").value;
    ws = new WebSocket("ws://localhost:8081/mavenproject1/chatRoomServer/Nam");
    ws.onmessage = function(event) {
        alert(9);
        console.log(event.data);
        //var message = JSON.parse(event.data);
        //log.innerHTML += message.from + " : " + message.content + "\n";
    };
}

function send() {
    var content = document.getElementById("msg").value;
    var json = JSON.stringify({
        "content":content
    });

    ws.send(json);
}