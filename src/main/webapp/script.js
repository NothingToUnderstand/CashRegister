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
        case"ru":
            newhref = href.replace('ru', 'en')
            document.location.href = newhref
            break;
    }
}

function ru() {
    let href = document.location.href;
    let params = (new URL(document.location)).searchParams;
    let newhref;
    if (document.location.search === "") {
        newhref = href + "?lang=ru"
        document.location.href = newhref;
        return;
    }
    switch (params.get("lang")) {
        case null:
            newhref = href + "&lang=ru"
            document.location.href = newhref;
            break;
        case "ru":
            break;
        case"en":
            newhref = href.replace('en', 'ru')
            document.location.href = newhref
            break;
    }
}