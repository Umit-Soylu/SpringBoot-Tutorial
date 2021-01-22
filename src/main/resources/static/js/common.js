let count = 0;

function welcomeMessage(){
    let message  = 'Welcome to SpringBoot Tutorials';
    if (count < 1)
        alert(message + ': ' + ++count);
}

function addTwoValues() {
    let numberOne = document.getElementById('x').value;
    let numberTwo = document.getElementById('y').value;

    let sum = Number(numberOne) + Number(numberTwo);
    //alert('Sum is ' + sum);

    document.getElementById('sum').value = sum;

    console.log(numberOne);
    console.log(numberTwo);
    console.log(sum);
}

function changeStyle() {
    let elements = document.getElementsByTagName('p');

    for (let element of elements) {
        element.style.fontStyle = 'italic';
    }

    console.log(elements.length);
}

function demoCollection() {
    let x = 3;

    let y = '3'
    let X = [1, 2, '3'];

    let people = {
        name: "Umit",
        surname: "Soylu",
        age: 35
    };

    for (const peopleKey in people) {
        console.log(peopleKey + people.valueOf(peopleKey));
    }
}
