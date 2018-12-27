var ws;

function connect() {

    var username = document.getElementById("username").value;
    //ws = new WebSocket("ws://localhost:8080/project/chat/kk");
    ws = new WebSocket("ws://localhost:8080/project/chatRoomServer");
    ws.onmessage = function(event) {
        var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };
}

function send() {
    var content = document.getElementById("msg").value;
    var json = JSON.stringify({
        "content":content
    });

    ws.send(json);
}