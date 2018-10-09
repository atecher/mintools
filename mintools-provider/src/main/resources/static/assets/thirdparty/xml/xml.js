$(function () {
    bindCodeMirror();
    bindXmlFormatAction();
    bindXmlPackAction();
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
function bindXmlFormatAction() {
    $(document).on("click", "#format", function () {
        var contentValue = editor.getValue();
        $.ajax({
            type: "POST",
            url: "format/",
            data: {
                content: contentValue
            },
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

function bindXmlPackAction() {
    $(document).on("click", "#pack", function () {
        var contentValue = editor.getValue();
        $.ajax({
            type: "POST",
            url: "pack/",
            data: {
                content: contentValue
            },
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