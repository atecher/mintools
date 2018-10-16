$(function () {
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
            url: "/html/format/",
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
            url: "/html/clear/",
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
            url: "/html/compress/",
            data: {
                content: value
            },
            success: function (data) {
                result.setValue(data);
            }
        });
    });
}


