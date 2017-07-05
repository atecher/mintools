<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="zh-cn" class="ks-webkit537 ks-webkit ks-chrome42 ks-chrome">
<head>
    <jsp:include page="../plugins/header.jsp" flush="true"/>
    <title>正则表达式测试工具-MinTools</title>
</head>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <div class="col-xs-8">
            <div class="mt-head"><h1>正则表达式测试工具</h1></div>
            <p>源文本：</p>
            <p><textarea id="textSour" name="textSour" class="form-control"></textarea></p>
            <p class="form-inline">
                <label class="control-label">正则表达式：</label>
                <input type="text" size="32" value="" id="textPattern" name="textPattern" class="form-control"/>
                <label class="checkbox">
                    <input type="checkbox" value="global" id="optionGlobal" name="optionGlobl"> 全局搜索&nbsp;&nbsp;&nbsp;&nbsp;
                </label>
                <label class="checkbox">
                    <input type="checkbox" value="ignoreCase" id="optionIgnoreCase" name="optionIgnoreCase"> 忽略大小写&nbsp;&nbsp;&nbsp;&nbsp;
                </label>
                <button onclick="return onMatch();" id="btnTest" name="btnTest" class="btn btn-danger pull-right">测试匹配</button>
            </p>
            <p>匹配结果：</p>
            <p><textarea readonly="readonly" name="textMatchResult" id="textMatchResult" class="form-control"></textarea></p>
            <p>替换为：</p>
            <p><textarea wrap="off" id="textReplace" name="textReplace" class="form-control"></textarea></p>
            <p class="text-right"><button onclick="return onReplace()"  id="btnReplace" name="btnReplace" class="btn btn-danger"> 替 换 </button></p>
            <p class="form-group">替换结果：</p>
            <p><textarea wrap="off" readonly="readonly" id="textReplaceResult" name="textReplaceResult" class="form-control"></textarea></p>
            <p class="text-right"><button type="reset" id="btnReset" name="btnReset" class="btn btn-danger">清空所有字段</button></p>
            <div class="mt-head"><h2>关于正则表达式</h2></div>
            <p style="background: #FFFFFF;font-size: 14px;padding: 20px;text-indent: 2em;">
                在计算机科学中，是指一个用来描述或者匹配一系列符合某个句法规则的字符串的单个字符串。在很多文本编辑器或其他工具里，正则表达式通常被用来检索和/或替换那些符合某个模式的文本内容。许多程序设计语言都支持利用正则表达式进行字符串操作。例如，在Perl中就内建了一个功能强大的正则表达式引擎。正则表达式这个概念最初是由Unix中的工具软件（例如sed和grep）普及开的。正则表达式通常缩写成“regex”，单数有regexp、regex，复数有regexps、regexes、regexen。
            </p>
            <p>
                <table width="100%" border="0" cellpadding="8" cellspacing="1" class="table-bordered table-hover table">
                    <tbody>
                    <tr>
                        <td width="90">元字符</td>
                        <td>描述</td>
                    </tr>
                    <tr>
                        <td>\</td>
                        <td>
                            将下一个字符标记为一个特殊字符、或一个原义字符、或一个向后引用、或一个八进制转义符。例如，“\n”匹配字符“n”。“\\n”匹配一个换行符。序列“\\”匹配“\”而“\(”则匹配“(”。
                        </td>
                    </tr>
                    <tr>
                        <td>^</td>
                        <td>匹配输入字符串的开始位置。如果设置了RegExp对象的Multiline属性，^也匹配“\n”或“\r”之后的位置。</td>
                    </tr>
                    <tr>
                        <td>$</td>
                        <td>匹配输入字符串的结束位置。如果设置了RegExp对象的Multiline属性，$也匹配“\n”或“\r”之前的位置。</td>
                    </tr>
                    <tr>
                        <td>*</td>
                        <td>匹配前面的子表达式零次或多次。例如，zo*能匹配“z”以及“zoo”。*等价于{0,}。</td>
                    </tr>
                    <tr>
                        <td>+</td>
                        <td>匹配前面的子表达式一次或多次。例如，“zo+”能匹配“zo”以及“zoo”，但不能匹配“z”。+等价于{1,}。</td>
                    </tr>
                    <tr>
                        <td>?</td>
                        <td>匹配前面的子表达式零次或一次。例如，“do(es)?”可以匹配“does”或“does”中的“do”。?等价于{0,1}。</td>
                    </tr>
                    <tr>
                        <td>{n}</td>
                        <td>n是一个非负整数。匹配确定的n次。例如，“o{2}”不能匹配“Bob”中的“o”，但是能匹配“food”中的两个o。</td>
                    </tr>
                    <tr>
                        <td>{n,}</td>
                        <td>
                            n是一个非负整数。至少匹配n次。例如，“o{2,}”不能匹配“Bob”中的“o”，但能匹配“foooood”中的所有o。“o{1,}”等价于“o+”。“o{0,}”则等价于“o*”。
                        </td>
                    </tr>
                    <tr>
                        <td>{n,m}</td>
                        <td>m和n均为非负整数，其中n&lt;=m。最少匹配n次且最多匹配m次。例如，“o{1,3}”将匹配“fooooood”中的前三个o。“o{0,1}”等价于“o?”。请注意在逗号和两个数之间不能有空格。</td>
                    </tr>
                    <tr>
                        <td>?</td>
                        <td>
                            当该字符紧跟在任何一个其他限制符（*,+,?，{n}，{n,}，{n,m}）后面时，匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串。例如，对于字符串“oooo”，“o?”将匹配单个“o”，而“o+”将匹配所有“o”。
                        </td>
                    </tr>
                    <tr>
                        <td>.点</td>
                        <td>匹配除“\n”之外的任何单个字符。要匹配包括“\n”在内的任何字符，请使用像“[\s\S]”的模式。</td>
                    </tr>
                    <tr>
                        <td>(pattern)</td>
                        <td>
                            匹配pattern并获取这一匹配。所获取的匹配可以从产生的Matches集合得到，在VBScript中使用SubMatches集合，在JScript中则使用$0…$9属性。要匹配圆括号字符，请使用“\(”或“\)”。
                        </td>
                    </tr>
                    <tr>
                        <td>(?:pattern)</td>
                        <td>
                            匹配pattern但不获取匹配结果，也就是说这是一个非获取匹配，不进行存储供以后使用。这在使用或字符“(|)”来组合一个模式的各个部分是很有用。例如“industr(?:y|ies)”就是一个比“industry|industries”更简略的表达式。
                        </td>
                    </tr>
                    <tr>
                        <td>(?=pattern)</td>
                        <td>
                            正向肯定预查，在任何匹配pattern的字符串开始处匹配查找字符串。这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用。例如，“Windows(?=95|98|NT|2000)”能匹配“Windows2000”中的“Windows”，但不能匹配“Windows3.1”中的“Windows”。预查不消耗字符，也就是说，在一个匹配发生后，在最后一次匹配之后立即开始下一次匹配的搜索，而不是从包含预查的字符之后开始。
                        </td>
                    </tr>
                    <tr>
                        <td>(?!pattern)</td>
                        <td>
                            正向否定预查，在任何不匹配pattern的字符串开始处匹配查找字符串。这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用。例如“Windows(?!95|98|NT|2000)”能匹配“Windows3.1”中的“Windows”，但不能匹配“Windows2000”中的“Windows”。
                        </td>
                    </tr>
                    <tr>
                        <td>(?&lt;=pattern)</td>
                        <td>反向肯定预查，与正向肯定预查类似，只是方向相反。例如，“(?&lt;=95|98|NT|2000)Windows”能匹配“2000Windows”中的“Windows”，但不能匹配“3.1Windows”中的“Windows”。</td>
                    </tr>
                    <tr>
                        <td>(?&lt;!pattern)</td>
                        <td>反向否定预查，与正向否定预查类似，只是方向相反。例如“(?&lt;!95|98|NT|2000)Windows”能匹配“3.1Windows”中的“Windows”，但不能匹配“2000Windows”中的“Windows”。</td>
                    </tr>
                    <tr>
                        <td>x|y</td>
                        <td>匹配x或y。例如，“z|food”能匹配“z”或“food”。“(z|f)ood”则匹配“zood”或“food”。</td>
                    </tr>
                    <tr>
                        <td>[xyz]</td>
                        <td>字符集合。匹配所包含的任意一个字符。例如，“[abc]”可以匹配“plain”中的“a”。</td>
                    </tr>
                    <tr>
                        <td>[^xyz]</td>
                        <td>负值字符集合。匹配未包含的任意字符。例如，“[^abc]”可以匹配“plain”中的“plin”。</td>
                    </tr>
                    <tr>
                        <td>[a-z]</td>
                        <td>字符范围。匹配指定范围内的任意字符。例如，“[a-z]”可以匹配“a”到“z”范围内的任意小写字母字符。
                            注意:只有连字符在字符组内部时,并且出两个字符之间时,才能表示字符的范围; 如果出字符组的开头,则只能表示连字符本身.
                        </td>
                    </tr>
                    <tr>
                        <td>[^a-z]</td>
                        <td>负值字符范围。匹配任何不在指定范围内的任意字符。例如，“[^a-z]”可以匹配任何不在“a”到“z”范围内的任意字符。</td>
                    </tr>
                    <tr>
                        <td>\b</td>
                        <td>匹配一个单词边界，也就是指单词和空格间的位置。例如，“er\b”可以匹配“never”中的“er”，但不能匹配“verb”中的“er”。</td>
                    </tr>
                    <tr>
                        <td>\B</td>
                        <td>匹配非单词边界。“er\B”能匹配“verb”中的“er”，但不能匹配“never”中的“er”。</td>
                    </tr>
                    <tr>
                        <td>\cx</td>
                        <td>匹配由x指明的控制字符。例如，\cM匹配一个Control-M或回车符。x的值必须为A-Z或a-z之一。否则，将c视为一个原义的“c”字符。</td>
                    </tr>
                    <tr>
                        <td>\d</td>
                        <td>匹配一个数字字符。等价于[0-9]。</td>
                    </tr>
                    <tr>
                        <td>\D</td>
                        <td>匹配一个非数字字符。等价于[^0-9]。</td>
                    </tr>
                    <tr>
                        <td>\f</td>
                        <td>匹配一个换页符。等价于\x0c和\cL。</td>
                    </tr>
                    <tr>
                        <td>\n</td>
                        <td>匹配一个换行符。等价于\x0a和\cJ。</td>
                    </tr>
                    <tr>
                        <td>\r</td>
                        <td>匹配一个回车符。等价于\x0d和\cM。</td>
                    </tr>
                    <tr>
                        <td>\s</td>
                        <td>匹配任何空白字符，包括空格、制表符、换页符等等。等价于[
                            \f\n\r\t\v]。
                        </td>
                    </tr>
                    <tr>
                        <td>\S</td>
                        <td>匹配任何非空白字符。等价于[^ \f\n\r\t\v]。</td>
                    </tr>
                    <tr>
                        <td>\t</td>
                        <td>匹配一个制表符。等价于\x09和\cI。</td>
                    </tr>
                    <tr>
                        <td>\v</td>
                        <td>匹配一个垂直制表符。等价于\x0b和\cK。</td>
                    </tr>
                    <tr>
                        <td>\w</td>
                        <td>匹配包括下划线的任何单词字符。等价于“[A-Za-z0-9_]”。</td>
                    </tr>
                    <tr>
                        <td>\W</td>
                        <td>匹配任何非单词字符。等价于“[^A-Za-z0-9_]”。</td>
                    </tr>
                    <tr>
                        <td>\xn</td>
                        <td>
                            匹配n，其中n为十六进制转义值。十六进制转义值必须为确定的两个数字长。例如，“\x41”匹配“A”。“\x041”则等价于“\x04&amp;1”。正则表达式中可以使用ASCII编码。
                        </td>
                    </tr>
                    <tr>
                        <td>\num</td>
                        <td>匹配num，其中num是一个正整数。对所获取的匹配的引用。例如，“(.)\1”匹配两个连续的相同字符。</td>
                    </tr>
                    <tr>
                        <td>\n</td>
                        <td>标识一个八进制转义值或一个向后引用。如果\n之前至少n个获取的子表达式，则n为向后引用。否则，如果n为八进制数字（0-7），则n为一个八进制转义值。</td>
                    </tr>
                    <tr>
                        <td>\nm</td>
                        <td>
                            标识一个八进制转义值或一个向后引用。如果\nm之前至少有nm个获得子表达式，则nm为向后引用。如果\nm之前至少有n个获取，则n为一个后跟文字m的向后引用。如果前面的条件都不满足，若n和m均为八进制数字（0-7），则\nm将匹配八进制转义值nm。
                        </td>
                    </tr>
                    <tr>
                        <td>\nml</td>
                        <td>如果n为八进制数字（0-7），且m和l均为八进制数字（0-7），则匹配八进制转义值nml。</td>
                    </tr>
                    <tr>
                        <td>\un</td>
                        <td>匹配n，其中n是一个用四个十六进制数字表示的Unicode字符。例如，\u00A9匹配版权符号（&amp;copy;）。</td>
                    </tr>
                    </tbody>
                </table>

            </p>
            <jsp:include page="../plugins/comment.jsp"/>
        </div>
        <div class="col-xs-4">
            <jsp:include page="../plugins/sidebar.jsp" flush="true"/>
        </div>
    </div>
</div>
<jsp:include page="../plugins/footer.jsp" flush="true"/>
<script type="text/javascript" src="<c:url value="/assets/js/thirdparty/regex/regex.js"/>"></script>
</body>
</html>