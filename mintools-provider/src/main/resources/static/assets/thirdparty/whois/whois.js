/**
 * Created by atecher on 2016/7/7.
 */

$(function () {
    bindWhoisSearch();
});

function bindWhoisSearch() {
    $(document).on("click", "#searchBtn", function (event) {
        var contentValue = $("#whois_search").val();
        $.ajax({
            type: "POST",
            url: "search/",
            data: {
                domainName: contentValue
            },
            success: function (data) {
                createResultTable(data);
            }
        });
    });
}

function createResultTable(result) {
    var htmlArray = new Array();
    // htmlArray.push(JSON.stringify(result));
    htmlArray.push("<h2>以下信息获取时间:" + result.cacheUpdatedDate + "</h2>");
    htmlArray.push("<table class='table table-striped'>");
    htmlArray.push("<tr><td  style='position:relative; top:0px;text-align: right;padding-right: 15px;'>所有者/Registrant Name:</td><td>" + result.registrantName + "</td></tr>");
    htmlArray.push("<tr><td  style='position:relative; top:0px;text-align: right;padding-right: 15px;'>所有者联系邮箱/Registrant E-mail:</td><td>" + result.registrantEmail + "</td></tr>");
    htmlArray.push("<tr><td  style='position:relative; top:0px;text-align: right;padding-right: 15px;'>注册商/Sponsoring Registrar:</td><td>" + result.registrar + "</td></tr>");
    htmlArray.push("<tr><td  style='position:relative; top:0px;text-align: right;padding-right: 15px;'>注册日期/Registration Date(EDT):</td><td>" + result.formatCreationDate + "</td></tr>");
    htmlArray.push("<tr><td  style='position:relative; top:0px;text-align: right;padding-right: 15px;'>到期日期/Expiration Date(EDT):</td><td>" + result.formatExpirationDate + "</td></tr>");
    htmlArray.push("<tr><td  style='position:relative; top:0px;text-align: right;padding-right: 15px;'>域名状态/Domain Status:</td><td>");
    var statusList = result.domainStatusList;
    for (var o in statusList) {
        htmlArray.push(statusList[o].desc + " " + statusList[o].status + "<br/>");
    }
    htmlArray.push("</td></tr>");
    htmlArray.push("<tr><td  style='position:relative; top:0px;text-align: right;'>DNS服务器/Name Server:</td><td>" + result.nameServerList + "</td></tr>");
    htmlArray.push("</table>");
    htmlArray.push("<h2>详细英文注册信息如下</h2>");
    htmlArray.push("<div class='content'>" + result.originalInfo.replace(/\n/g, "<br/>").split("For complete domain details")[0] + "</div>");
    $("#result").html(htmlArray.join("\n"));

}