function titleChanger(){
    document.getElementById("scr").innerHTML = "Change employee";
}

function trigger(){
    document.getElementById("hover").addEventListener("mouseover", popup);

    function popup(){
        alert("Welcome to my WebPage!!!");
    }
}

let globalCounter = 0;

function counter(){
    ++globalCounter;
    document.getElementById("counter").innerHTML = globalCounter;
}

function getToday(){
    document.getElementById("date").innerHTML = new Date();
}