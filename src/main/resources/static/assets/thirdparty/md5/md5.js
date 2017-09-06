$(function() {
    bindCodeMirror();
    bindMD5Encode16Action();
    bindMD5Encode32Action();
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
function bindMD5Encode16Action(){
    $(document).on("click", "#btnEncode16",function() {
        var contentValue=editor.getValue();
        $.ajax({
            type:"POST",
            url:"/md5/encode/16/",
            data:{
                content:contentValue
            },
            success: function(data){
                if(data.code=='success'){
                    result.setValue(data.result);
                }else{
                    result.setValue("");
                }

            }
        });
    });
}

function bindMD5Encode32Action(){
    $(document).on("click", "#btnEncode32",function() {
        var contentValue=editor.getValue();
        $.ajax({
            type:"POST",
            url:"/md5/encode/32/",
            data:{
                content:contentValue
            },
            success: function(data){
                if(data.code=='success'){
                    result.setValue(data.result);
                }else{
                    result.setValue("");
                }

            }
        });
    });
}


