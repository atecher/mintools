$(function () {
    $("input[type=radio]").iCheck({
        checkboxClass: 'icheckbox_square-red',
        radioClass: 'iradio_square-red',
        increaseArea: '20%'
    });
    bindCodeMirror();
    bindSqlFormatAction();
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
function bindSqlFormatAction() {
    $(document).on("click", "#format", function () {

        var contentValue = editor.getValue();
        $.ajax({
            type: "POST",
            url: "/sql/format/",
            data: {
                sql: contentValue,
                type: $("input[name='type']:checked").val()
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
