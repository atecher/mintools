$(function () {
    bindCodeMirror();
    bindBase64EncodeAction();
    bindBase64DecodeAction();
    bindBase64ExchangeAction();
});
var editor;
var result;

function bindCodeMirror() {
    editor = CodeMirror.fromTextArea(document.getElementById("content"), {lineNumbers: true, matchBrackets: true});
    result = CodeMirror.fromTextArea(document.getElementById("result"), {lineNumbers: true, matchBrackets: true});
}

var bindBase64ExchangeAction = function () {
    $(document).on("click", "#btnExchange", function () {
        var contentValue = editor.getValue();
        editor.setValue(result.getValue());
        result.setValue(contentValue);
    });
}

function bindBase64EncodeAction() {
    $(document).on("click", "#btnEncode", function () {
        var contentValue = editor.getValue();
        $.ajax({
            type: "POST", url: "encode/", data: {content: contentValue}, success: function (data) {
                if (data.code == 'success') {
                    result.setValue(data.result);
                } else {
                    result.setValue("");
                }
            }
        });
    });
}

function bindBase64DecodeAction() {
    $(document).on("click", "#btnDecode", function () {
        var contentValue = editor.getValue();
        $.ajax({
            type: "POST",
            url: "decode/",
            dataType: "json",
            data: {content: contentValue},
            success: function (data) {
                if (data.code == 'success') {
                    result.setValue(data.result);
                } else {
                    result.setValue("");
                }
            }
        });
    });
}