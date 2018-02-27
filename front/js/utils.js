function ready(fn) {
    if (document.readyState != 'loading') {
        fn();
    } else {
        document.addEventListener('DOMContentLoaded', fn);
    }
}

const LOGGER = function (message) {
    return function () {
        console.log(message);
    }
};

const gebi = function (id) {
    return document.getElementById(id);
};

const gevbi = function (id) {
    return gebi(id).value;
};

const createElement = function (tag, inner, attrs, classList, parent) {
    var element = document.createElement(tag);
    if (parent) {
        parent.appendChild(element);
    }
    if (inner) {
        if (typeof inner === 'string' || inner instanceof String) {
            inner = document.createTextNode(inner);
        }
        element.appendChild(inner);
    }
    Object.keys(attrs).forEach(function (attr) {
        element.setAttribute(attr, attrs[attr]);
    });
    DOMTokenList.prototype.add.apply(element.classList, classList);
    return element;
};

const Hide = {};

Hide.toggle = function(el) {
    Hide.toggleClass(el, 'hide');
};

Hide.toggleClass = function(el, className) {
    if (el.classList) {
        el.classList.toggle(className);
    } else {
        var classes = el.className.split(' ');
        var existingIndex = classes.indexOf(className);
        if (existingIndex >= 0) {
            classes.splice(existingIndex, 1);
        } else {
            classes.push(className);
        }
        el.className = classes.join(' ');
    }
};
