function post(url, data, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.onload = function(e) {
        if (this.status == 200) {
            callback(this.responseText);
        }
    };

    xhr.setRequestHeader("Content-Type", "application/json");
    addAuthTokenHeader(xhr);
    xhr.send(JSON.stringify(data));

}
function get(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = (function() { callback(xhr) });
    xhr.open('GET', url);
    addAuthTokenHeader(xhr);
    xhr.send();
}

function addAuthTokenHeader(xhr) {
    xhr.setRequestHeader("Authorization", "bearer " + keycloak.token);
}
