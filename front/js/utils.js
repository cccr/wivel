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

const html = function(literalSections, ...substs) {
    // Use raw literal sections: we don’t want
    // backslashes (\n etc.) to be interpreted
    let raw = literalSections.raw;

    let result = '';

    substs.forEach((subst, i) => {
        // Retrieve the literal section preceding
        // the current substitution
        let lit = raw[i];

        // In the example, map() returns an array:
        // If substitution is an array (and not a string),
        // we turn it into a string
        if (Array.isArray(subst)) {
            subst = subst.join('');
        }

        // If the substitution is preceded by a dollar sign,
        // we escape special characters in it
        if (lit.endsWith('$')) {
            subst = htmlEscape(subst);
            lit = lit.slice(0, -1);
        }
        result += lit;
        result += subst;
    });
    // Take care of last literal section
    // (Never fails, because an empty template string
    // produces one literal section, an empty string)
    result += raw[raw.length-1]; // (A)

    return result;
};
