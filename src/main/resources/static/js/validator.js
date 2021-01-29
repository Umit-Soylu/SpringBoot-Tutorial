function checkForm() {
    const min = 3;
    const max = 15;

    return checkString(min, max, 'firstname') && checkString(min, max, 'lastname');
}

function checkElement(min, max, id){
    let element = document.getElementById(id).value;

    if (element.length >= min && element.length <= max)
        return true;
    else {
        alert(document.getElementById(id).name + ' should be at least ' + min + ' and at most ' + max + ' characters.')
        document.getElementById(id).value = '';
        return false;
    }
}

function checkString(min, max, id){
    let regExp = new RegExp('^[a-zA-Z]{' + min + ',' + max + '}$');
    //let regExp = /^[a-zA-Z]{3,15}$/

    let element = document.getElementById(id).value;

    if (regExp.test(element)) {
        document.getElementById(id).style.backgroundColor = 'rgb(255, 255, 255)';
        return true;
    } else{
        alert(document.getElementById(id).name + ' should be at least ' + min + ' and at most ' + max + ' characters.')
        document.getElementById(id).style.backgroundColor = 'red';
        return false;
    }

}