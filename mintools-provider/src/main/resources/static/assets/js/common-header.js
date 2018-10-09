!function () {
    function buildMenu() {
        function a() {
            $(".menu .menu-dropdown").css({
                width: c.width() + c.position().left - d.position().left + "px"
            })
        }

        var c = $("#J_common_header_search_wrap");
        var d = $("#J_common_header_menu");
        var e = $(window).width();


        function b() {
            var a, b, c = $(".menu-dropdown-sidebar").find("a"),
                d = $(".item-sub[sidebar-type]");
            return {
                eventBind: function () {
                    this.initStatus();
                    var d = null;
                    c.mouseenter(function () {
                        var c = $(this);
                        c.data("enterTime", (new Date).getTime()), c != b && (d = setTimeout(function () {
                            b.removeClass("active"), c.addClass("active"), b = c;
                            var d = c.attr("sidebar-type"),
                                e = $('.item-sub[sidebar-type="' + d + '"]');
                            a.hide(), e.show(), a = e, c.parents(".menu-dropdown").css({
                                height: e.height() + 20 + "px"
                            })
                        }, 200))
                    }).mouseleave(function () {
                        var a = $(this),
                            b = parseInt(a.data("enterTime") || 0, 10),
                            c = (new Date).getTime();
                        200 >= c - b && d && (clearTimeout(d), d = null)
                    }), $("#J_common_header_menu .menu-dropdown-content").mouseenter(function (a) {
                        d && (clearTimeout(d), d = null)
                    })
                },
                initStatus: function () {
                    c.removeClass("active"), d.hide(), a = $(".item-sub[sidebar-type]").eq(0).show(), b = g.eq(0).addClass("active")
                }
            }
        }

        a();
        $(window).resize(function () {
            a();
            e = $(window).width();
        });
        var f = null, g = $(".menu-dropdown-sidebar").find("a");
        $(".item-sub[sidebar-type]").eq(0);
        $("#J_common_header_menu .top-menu-item").mouseenter(function () {
            var a, c = $(this);
            (a = c.data("mouseoutTimer")) && (clearTimeout(a), a = null);
            c.data("enterTime", (new Date).getTime());

            c.data("mouseoverTimter", setTimeout(function () {
                f && (clearTimeout(f), f = null);
                var a = "true" === c.attr("has-dropdown");
                var b = c.attr("menu-type");
                if (a) {
                    var d = c.parent().position(),
                        e = c.position(),
                        g = c.find(".menu-dropdown");
                    g.css({
                        left: -(e.left - d.left) + "px"
                    });
                    var h = g.find(".menu-dropdown-inner").height();
                    "product" != b && (h += 47), g.addClass("animate").css({
                        height: h + "px",
                        zIndex: 1
                    })
                }
            }, 200));
        }).mouseleave(function () {
            var $this = $(this),
                enterTime = parseInt($this.data("enterTime") || 0, 10),
                now = (new Date).getTime(),
                timer = $this.data("mouseoverTimter");
            200 >= now - enterTime && timer && (clearTimeout(timer), timer = null);
            var hasDropdown = "true" === $this.attr("has-dropdown");
            if (hasDropdown) {
                var Timeout = setTimeout(function () {
                    var dropdown = $this.find(".menu-dropdown");
                    dropdown.removeClass("animate").css({
                        height: 0,
                        zIndex: 0
                    }), $this.data("showing", !1), b.initStatus(), timer && (clearTimeout(timer), timer = null)
                }, 100);
                $this.data("mouseoutTimer", Timeout);
            }
        }), b = b(), b.eventBind();
    }

    function buildSearch() {
        var a = "custom-placeholder";
        var $search = $("#J_common_header_search[" + a + "]");
        $search.focus(function () {
            var $this = $(this);
            $this.val() == $this.attr(a) && ($this.val(""), $this.removeClass("placeholder"));
        }).blur(function () {
            var $this = $(this);
            ("" == $this.val() || $this.val() == $this.attr(a)) && ($this.addClass("placeholder"), $this.val($search.attr(a)))
        }).blur();
        $search.parents("form").submit(function () {
            $(this).find("[" + a + "]").each(function () {
                var $this = $(this);
                $this.val() || $this.val($this.attr(a));
            })
        });
        $("#J_common_header_search_btn").click(function () {
            var val = $search.val();
            val || $search.val(val), $(this)[0].parentNode.submit()
        });
    }

    function init() {
        jQuery(function ($) {
            if (0 != $("#J_common_header_menu").length && 0 != $("#J_common_header_search_wrap").length) {
                buildMenu();
                buildSearch();
            }
        })
    }

    init();

}();