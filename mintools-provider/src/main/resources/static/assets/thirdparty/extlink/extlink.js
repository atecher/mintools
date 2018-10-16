/**
 * Created by atecher on 2016/7/7.
 */
var index = 0;
var $result;
$(function () {
    bindExtLinkSearch();
    $result = $("#extLinkResult tbody");
});
var page = 1;
var domain;
var wait = 0;
var intervalId;

function bindExtLinkSearch() {
    var $result = $("#extLinkResult tbody");
    $result.empty();
    $(document).on("click", "#searchBtn", function (event) {
        var contentValue = $("#domain").val();
        page = 0;
        wait = 0;
        domain = contentValue;
        if (intervalId) {
            clearInterval(intervalId);
            intervalId = null;
        }
        intervalId = setInterval(getLinks, 1000);
    });
}

function getLinks() {
    if (wait == 0) {
        page = page + 1;
        $.ajax({
            type: "POST",
            url: "/extlink/?tmp=" + new Date(),
            data: {
                domain: domain,
                page: page
            },
            success: function (data) {
                if (data.code == "success") {
                    var res = data.result.rows;
                    $("#total").text(data.result.total);
                    if (res.length == 0) {
                        clearInterval(getLinks);
                    }
                    var index = (data.result.page - 1) * data.result.limit;
                    $result.empty();
                    for (var i in res) {
                        addResult($result, index++, res[i]);
                    }
                }
                if (!$("#loadTips").is(':visible')) {
                    $("#loadTips").show();
                }
            }
        });
        wait = 20;
    } else {
        wait--;
        $("#timeDown").text(wait);
    }
}

function addResult($result, index, link) {
    $result.append($("<tr><td class='text-center'>" + (index + 1) + "</td><td style='word-break:break-all;'><a href='" + link + "' target='_blank' style='color: #009a61;'>" + link + "</a></td></tr>"));
    $("#count").text(index + 1);
    $.getJSON(link, function (data, status, xhr) {
    });
}