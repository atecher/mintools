$(function () {
    bindCodeMirror();
    bindEncryptAction();
    bindDecodeAction();
    bindFormatAction();
    bindPurifyAction();
});
var editor;
var result;
function bindCodeMirror() {
    editor = CodeMirror.fromTextArea(document.getElementById("content"), {
        lineNumbers: true,
        matchBrackets: true
    });
    result = CodeMirror.fromTextArea(document.getElementById("result"), {
        lineNumbers: true,
        matchBrackets: true
    });
}

function bindEncryptAction() {
    $(document).on("click", "#btnEncrypt", function () {
        var packer = new Packer;
        var c = editor.getValue();
        if (c.length <= 0 || c == "请输入内容") {
            $("#content").val("请输入内容");

        } else {
            var v = packer.pack(c, true, true);
            result.setValue(v);
        }
    });
}

function bindDecodeAction() {
    $(document).on("click", "#btnDecode", function () {
        var c = editor.getValue();
        if (c.length <= 0 || c == "请输入内容") {
            $("#content").val("请输入内容");
        } else {
            try {
                var r = eval(c.slice(4));
                r = r.replace(/^\s+/, '');
                if (r && r.charAt(0) === '<') {
                    r = style_html(r, 4, ' ', 80);
                } else {
                    r = js_beautify(r, 4, ' ');
                }
                result.setValue(r);
            } catch (err) {
                if (err.name == 'SyntaxError') {
                    alert("无法进行解密(可能并未加密)!");
                }
            }
        }
    });
}

function bindFormatAction() {
    $(document).on("click", "#btnJSFormat", function () {
        var r = editor.getValue();
        r = r.replace(/^\s+/, '');
        if (r && r.charAt(0) === '<') {
            r = style_html(r, 4, ' ', 80);
        } else {
            r = js_beautify(r, 4, ' ');
        }
        result.setValue(r);

    });
}

function bindPurifyAction() {
    $(document).on("click", "#btnPurify", function () {
        var c = editor.getValue();
        if (c.length <= 0 || c == "请输入内容") {
            $("#content").val("请输入内容");
        } else {
            var v = jsmin("", c, 2);
            result.setValue(trim(v));
        }
    });
}

String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
};


