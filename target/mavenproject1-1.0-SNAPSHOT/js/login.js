// jQuery & Velocity.js

function slideUpIn() {
    $("#login").velocity("transition.slideUpIn", 1250)
}
;

function slideLeftIn() {
    $(".row").delay(500).velocity("transition.slideLeftIn", {stagger: 500})
}

function shake() {
    $(".password-row").velocity("callout.shake");
}

slideUpIn();
slideLeftIn();
$("button").on("click", function () {
    shake();
});

$("#login-button").on("click", function () {
    var url = "LoginServlet?username=" + $("#username_input").val() + "&password=" + $("#password_input").val();
    console.log(url);
   $.get("LoginServlet?username='namvh3'&password='namvh3'");
//    $.ajax({
//        url: url,
//        cache: false,
//        type: "get",
//        success: function (html) {
//            alert(html);
//            //$("#results").append(html);
//        }
//    });
});

