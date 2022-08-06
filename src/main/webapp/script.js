window.onload = ch();

function ch() {
    var i = document.getElementById('lu').value;
    if (i === " " || i > 0) {
        document.getElementById('login').style.display = 'none';
        document.getElementById('signup').style.display = 'none';
    } else {
        document.getElementById('logout').style.display = 'none';
        document.getElementById('account').style.display = 'none';
    }

}

function hide() {
    document.getElementById('errormessage').classList.toggle("hide");
    document.getElementById('message').classList.toggle("hide");
}


function eng() {
    let href = document.location.href;
    let params = (new URL(document.location)).searchParams;
    let newhref;
    if (document.location.search === "") {
        newhref = href + "?lang=en"
        document.location.href = newhref;
        return;
    }
    switch (params.get("lang")) {
        case null:
            newhref = href + "&lang=en"
            document.location.href = newhref;
            break;
        case "en":
            break;
        case"ua":
            newhref = href.replace('ua', 'en')
            document.location.href = newhref
            break;
    }
}

function ukr() {
    let href = document.location.href;
    let params = (new URL(document.location)).searchParams;
    let newhref;
    if (document.location.search === "") {
        newhref = href + "?lang=ua"
        document.location.href = newhref;
        return;
    }
    switch (params.get("lang")) {
        case null:
            newhref = href + "&lang=ua"
            document.location.href = newhref;
            break;
        case "ua":
            break;
        case"en":
            newhref = href.replace('en', 'ua')
            document.location.href = newhref
            break;
    }
}


function lock() {
    var x = document.getElementById("password-input");
    var y = document.getElementById("img");
    if (x.type === "password") {
        y.src="https://cdn-icons-png.flaticon.com/512/100/100849.png"
        x.type = "text";
    } else {
        x.type = "password";
        y.src="https://cdn-icons-png.flaticon.com/512/61/61457.png"
    }
}