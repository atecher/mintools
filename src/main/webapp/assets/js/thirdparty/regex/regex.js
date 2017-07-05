function setVisible(a, c) {
    var b = document.getElementById(a);
    b.style.visibility = c ? "visible" : "hidden"
}
function isValidFields() {
    var a = document.getElementById("textSour");
    if (null == a.value || a.value.length < 1) {
        a.focus();
        alert("\u8bf7\u8f93\u5165\u6e90\u6587\u672c");
        return false
    }
    var b = document.getElementById("textPattern");
    if (null == b.value || b.value.length < 1) {
        b.focus();
        alert("\u8bf7\u8f93\u5165\u6b63\u5219\u8868\u8fbe\u5f0f");
        return false
    }
    return true
}
function buildRegex() {
    var a = "";
    if (document.getElementById("optionGlobal").checked) {
        a = "g"
    }
    if (document.getElementById("optionIgnoreCase").checked) {
        a = a + "i"
    }
    return new RegExp(document.getElementById("textPattern").value, a)
}
function onMatch() {
    if (!isValidFields()) {
        return false
    }
    document.getElementById("textMatchResult").value = "";
    var d = buildRegex();
    var b = document.getElementById("textSour").value.match(d);
    if (null == b || 0 == b.length) {
        document.getElementById("textMatchResult").value = "\uff08\u6ca1\u6709\u5339\u914d\uff09";
        return false
    }
    if (document.getElementById("optionGlobal").checked) {
        var a = "\u5171\u627e\u5230 " + b.length
            + " \u5904\u5339\u914d\uff1a\r\n";
        for ( var c = 0; c < b.length; ++c) {
            a = a + b[c] + "\r\n"
        }
        document.getElementById("textMatchResult").value = a
    } else {
        document.getElementById("textMatchResult").value = "\u5339\u914d\u4f4d\u7f6e\uff1a"
        + d.lastIndex + "\r\n\u5339\u914d\u7ed3\u679c\uff1a" + b[0]
    }
    return true
}
function onReplace() {
    var b = document.getElementById("textSour").value;
    var a = buildRegex();
    document.getElementById("textReplaceResult").value = b.replace(a, document
        .getElementById("textReplace").value)
};
