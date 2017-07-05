$(function() {
    bindCodeMirror();
    bindHtmlFormatAction();
    bindHtmlClearAction();
    bindHtmlCompressAction();
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
function bindHtmlFormatAction() {
    $(document).on("click", "#btnHtmlFormat", function () {
        var value = editor.getValue();
        $.ajax({
            type: "POST",
            url: root + "/html/format/",
            data: {
                content: value
            },
            success: function (data) {
                result.setValue(data);
            }
        });
    });
}
function bindHtmlClearAction() {
    $(document).on("click", "#btnHtmlClear", function () {
        var value = editor.getValue();
        $.ajax({
            type: "POST",
            url: root + "/html/clear/",
            data: {
                content: value
            },
            success: function (data) {
                result.setValue(data);
            }
        });
    });
}
function bindHtmlCompressAction() {
    $(document).on("click", "#btnHtmlYS", function () {
        var value = editor.getValue();
        $.ajax({
            type: "POST",
            url: root + "/html/compress/",
            data: {
                content: value
            },
            success: function (data) {
                result.setValue(data);
            }
        });
    });
}


