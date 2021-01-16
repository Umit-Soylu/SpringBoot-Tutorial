function display(id) {

    alert(document.getElementById("input").value + id);
}

function add(){
    let x = document.getElementById("x").value;
    let y = document.getElementById("y").value;
    let z = Number(x) + Number(y);
    window.display(z)
}

function fruitAdder() {
    let elements = Array.from(document.getElementById("fruits").innerHTML);

    elements.push(document.getElementById("newFruit").value);

    document.getElementById("fruits").innerHTML = elements;
}