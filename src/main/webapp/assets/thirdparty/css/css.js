$(function() {
    bindCodeMirror();
    bindCSSFormatAction();
    bindCSSClearAction();
    bindCSSCompressAction();
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
function bindCSSFormatAction(){
    $(document).on("click", "#btnCSSFormat",function() {
        CSS('format');
    });
}
function bindCSSCompressAction(){
    $(document).on("click", "#btnCSSYS",function() {
        CSS('pack');
    });
}
function bindCSSClearAction() {
    $(document).on("click", "#btnCSSClear",function () {
        CSS('format');
        CSS('clear');
    });
}

var lCSSCoder = {
    format: function(s) { //格式化代码
        s = s.replace(/\s*([\{\}\:\;\,])\s*/g, "$1");
        s = s.replace(/;\s*;/g, ";"); //清除连续分号
        s = s.replace(/\,[\s\.\#\d]*{/g, "{");
        s = s.replace(/([^\s])\{([^\s])/g, "$1 {\n\t$2");
        s = s.replace(/([^\s])\}([^\n]*)/g, "$1\n}\n$2");
        s = s.replace(/([^\s]);([^\s\}])/g, "$1;\n\t$2");
        return s;
    },
    pack: function(s) { //压缩代码
        s = s.replace(/\/\*(.|\n)*?\*\//g, ""); //删除注释
        s = s.replace(/\s*([\{\}\:\;\,])\s*/g, "$1");
        s = s.replace(/\,[\s\.\#\d]*\{/g, "{"); //容错处理
        s = s.replace(/;\s*;/g, ";"); //清除连续分号

        s = s.match(/^\s*(\S+(\s+\S+)*)\s*$/); //去掉首尾空白
        return (s == null) ? "": s[1];
    },
    clear: function(s) { //压缩代码
        s = s.replace(/\/\*(.|\n)*?\*\//g, ""); //删除注释
        s = s.replace(/\s*([\{\:\;\,])\s*/g, "$1");
        s = s.replace(/\s*([\n])\s*/g, "$1");
        s = s.replace(/\,[\s\.\#\d]*\{/g, "{"); //容错处理
        s = s.replace(/;\s*;/g, ";"); //清除连续分号
        s = s.match(/^\s*(\S+(\s+\S+)*)\s*$/); //去掉首尾空白
        return (s == null) ? "": s[1];
    }
};
function CSS(s) {
    var r = editor.getValue();
    r = lCSSCoder[s](r);
    result.setValue(r);
}

