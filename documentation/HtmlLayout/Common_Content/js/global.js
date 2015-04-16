var pageLayout;

$(document).ready(function() {
// create page layout
pageLayout = $('body').layout({
    scrollToBookmarkOnLoad: false // handled by custom code so can 'unhide' section first
    , defaults: {
    }
    , north: {
        size: "auto"
        , spacing_open: 0
        , closable: false
        , resizable: false
    }
    , west: {
        size: 250
        , spacing_closed: 22
        , togglerLength_closed: 140
        , togglerAlign_closed: "top"
        , togglerContent_closed: "C<BR>o<BR>n<BR>t<BR>e<BR>n<BR>t<BR>s"
        , togglerTip_closed: "Open & Pin Contents"
        , sliderTip: "Slide Open Contents"
        , slideTrigger_open: "mouseover"
    }
});

var tocLinks = $('div.contents_panel a');
tocLinks.click(function() {
	tocLinks.removeClass('selected');
	$(this).addClass('selected');
});

$('div.disclosure').click(function() {
	$(this).toggleClass("open");
	$(this).toggleClass("closed");
	$(this).parent().next("dd").toggleClass("hidden");
});


//expand TOC on the current page
var pageName = location.pathname.substring(location.pathname.lastIndexOf("/") + 1);
var hash = window.location.hash.substring(1);
var path = hash ? pageName + '#' + hash : pageName;
var acitveLink = $('div.contents_panel').find("a[href='"+ path + "']");
acitveLink.addClass('selected');

acitveLink.parent().parent().next("dd").removeClass("hidden");
acitveLink.parents("dd").removeClass("hidden");

acitveLink.parent().parent().find("div.disclosure").removeClass("closed").addClass("open");
acitveLink.parents("dd").prev("dt").find("div.disclosure").removeClass("closed").addClass("open");
});
