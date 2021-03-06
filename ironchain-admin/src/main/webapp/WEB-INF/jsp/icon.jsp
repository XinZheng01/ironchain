<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
 <head>
 <link href="${staticUrl}/plugins/zui-1.6.0/css/zui.min.css" rel="stylesheet">
 	<style>
        .bs-glyphicons-list { padding-left: 0; list-style: none }
        li {
		    cursor: pointer;
		    float: left;
		    width: 145px;
		    line-height: 35px;
		    list-style: none;
		    padding: 2px 10px;
		    white-space: nowrap;
		    transition: all .3s;
		    margin-left: 10px;
		}
		li:hover {color: #333;background-color: #d5f1d7; /* transform: scale(1.2); */}
		a{color: #333;font-size: 14px!important;}
		a:hover{color: #333;text-decoration:none;}
		i{font-size: 24px!important;}
    </style>
 </head>
 <body>
  <div class="bs-glyphicons">
   <ul class="bs-glyphicons-list">
    <li><a href="#search/icon-resize"><i class="icon-resize"></i> resize</a></li>
    <li><a href="#search/icon-expand-full"><i class="icon-expand-full"></i> expand-full</a></li>
    <li><a href="#search/icon-collapse-full"><i class="icon-collapse-full"></i> collapse-full</a></li>
    <li><a href="#search/icon-yinyang"><i class="icon-yinyang"></i> yinyang</a></li>
    <li><a href="#search/icon-window"><i class="icon-window"></i> window</a></li>
    <li><a href="#search/icon-window-alt"><i class="icon-window-alt"></i> window-alt</a></li>
    <li><a href="#search/icon-carousel"><i class="icon-carousel"></i> carousel</a></li>
    <li><a href="#search/icon-spinner-snake"><i class="icon-spinner-snake"></i> spinner-snake</a></li>
    <li><a href="#search/icon-spinner-indicator"><i class="icon-spinner-indicator"></i> spinner-indicator</a></li>
    <li><a href="#search/icon-check-board"><i class="icon-check-board"></i> check-board</a></li>
    <li><a href="#search/icon-bar-chart"><i class="icon-bar-chart"></i> bar-chart</a></li>
    <li><a href="#search/icon-github"><i class="icon-github"></i> github</a></li>
    <li><a href="#search/icon-dot-circle"><i class="icon-dot-circle"></i> dot-circle</a></li>
    <li><a href="#search/icon-cube-alt"><i class="icon-cube-alt"></i> cube-alt</a></li>
    <li><a href="#search/icon-cubes"><i class="icon-cubes"></i> cubes</a></li>
    <li><a href="#search/icon-file-pdf"><i class="icon-file-pdf"></i> file-pdf</a></li>
    <li><a href="#search/icon-file-word"><i class="icon-file-word"></i> file-word</a></li>
    <li><a href="#search/icon-file-excel"><i class="icon-file-excel"></i> file-excel</a></li>
    <li><a href="#search/icon-file-powerpoint"><i class="icon-file-powerpoint"></i> file-powerpoint</a></li>
    <li><a href="#search/icon-file-image"><i class="icon-file-image"></i> file-image</a></li>
    <li><a href="#search/icon-file-archive"><i class="icon-file-archive"></i> file-archive</a></li>
    <li><a href="#search/icon-file-audio"><i class="icon-file-audio"></i> file-audio</a></li>
    <li><a href="#search/icon-file-movie"><i class="icon-file-movie"></i> file-movie</a></li>
    <li><a href="#search/icon-file-code"><i class="icon-file-code"></i> file-code</a></li>
    <li><a href="#search/icon-circle-o-notch"><i class="icon-circle-o-notch"></i> circle-o-notch</a></li>
    <li><a href="#search/icon-qq"><i class="icon-qq"></i> qq</a></li>
    <li><a href="#search/icon-wechat"><i class="icon-wechat"></i> wechat</a></li>
    <li><a href="#search/icon-history"><i class="icon-history"></i> history</a></li>
    <li><a href="#search/icon-circle-thin"><i class="icon-circle-thin"></i> circle-thin</a></li>
    <li><a href="#search/icon-sliders"><i class="icon-sliders"></i> sliders</a></li>
    <li><a href="#search/icon-newspaper-o"><i class="icon-newspaper-o"></i> newspaper-o</a></li>
    <li><a href="#search/icon-calculator"><i class="icon-calculator"></i> calculator</a></li>
    <li><a href="#search/icon-paint-brush"><i class="icon-paint-brush"></i> paint-brush</a></li>
    <li><a href="#search/icon-area-chart"><i class="icon-area-chart"></i> area-chart</a></li>
    <li><a href="#search/icon-pie-chart"><i class="icon-pie-chart"></i> pie-chart</a></li>
    <li><a href="#search/icon-line-chart"><i class="icon-line-chart"></i> line-chart</a></li>
    <li><a href="#search/icon-toggle-off"><i class="icon-toggle-off"></i> toggle-off</a></li>
    <li><a href="#search/icon-toggle-on"><i class="icon-toggle-on"></i> toggle-on</a></li>
    <li><a href="#search/icon-diamond"><i class="icon-diamond"></i> diamond</a></li>
    <li><a href="#search/icon-venus"><i class="icon-venus"></i> venus</a></li>
    <li><a href="#search/icon-mars"><i class="icon-mars"></i> mars</a></li>
    <li><a href="#search/icon-server"><i class="icon-server"></i> server</a></li>
    <li><a href="#search/icon-music"><i class="icon-music"></i> music</a></li>
    <li><a href="#search/icon-search"><i class="icon-search"></i> search</a></li>
    <li><a href="#search/icon-envelope"><i class="icon-envelope"></i> envelope</a></li>
    <li><a href="#search/icon-heart"><i class="icon-heart"></i> heart</a></li>
    <li><a href="#search/icon-star"><i class="icon-star"></i> star</a></li>
    <li><a href="#search/icon-star-empty"><i class="icon-star-empty"></i> star-empty</a></li>
    <li><a href="#search/icon-user"><i class="icon-user"></i> user</a></li>
    <li><a href="#search/icon-film"><i class="icon-film"></i> film</a></li>
    <li><a href="#search/icon-th-large"><i class="icon-th-large"></i> th-large</a></li>
    <li><a href="#search/icon-th"><i class="icon-th"></i> th</a></li>
    <li><a href="#search/icon-th-list"><i class="icon-th-list"></i> th-list</a></li>
    <li><a href="#search/icon-check"><i class="icon-check"></i> check</a></li>
    <li><a href="#search/icon-times"><i class="icon-times"></i> times</a></li>
    <li><a href="#search/icon-zoom-in"><i class="icon-zoom-in"></i> zoom-in</a></li>
    <li><a href="#search/icon-zoom-out"><i class="icon-zoom-out"></i> zoom-out</a></li>
    <li><a href="#search/icon-off"><i class="icon-off"></i> off</a></li>
    <li><a href="#search/icon-cog"><i class="icon-cog"></i> cog</a></li>
    <li><a href="#search/icon-trash"><i class="icon-trash"></i> trash</a></li>
    <li><a href="#search/icon-home"><i class="icon-home"></i> home</a></li>
    <li><a href="#search/icon-file-o"><i class="icon-file-o"></i> file-o</a></li>
    <li><a href="#search/icon-time"><i class="icon-time"></i> time</a></li>
    <li><a href="#search/icon-download-alt"><i class="icon-download-alt"></i> download-alt</a></li>
    <li><a href="#search/icon-download"><i class="icon-download"></i> download</a></li>
    <li><a href="#search/icon-upload"><i class="icon-upload"></i> upload</a></li>
    <li><a href="#search/icon-inbox"><i class="icon-inbox"></i> inbox</a></li>
    <li><a href="#search/icon-play-circle"><i class="icon-play-circle"></i> play-circle</a></li>
    <li><a href="#search/icon-repeat"><i class="icon-repeat"></i> repeat</a></li>
    <li><a href="#search/icon-refresh"><i class="icon-refresh"></i> refresh</a></li>
    <li><a href="#search/icon-list-alt"><i class="icon-list-alt"></i> list-alt</a></li>
    <li><a href="#search/icon-lock"><i class="icon-lock"></i> lock</a></li>
    <li><a href="#search/icon-flag"><i class="icon-flag"></i> flag</a></li>
    <li><a href="#search/icon-headphones"><i class="icon-headphones"></i> headphones</a></li>
    <li><a href="#search/icon-volume-off"><i class="icon-volume-off"></i> volume-off</a></li>
    <li><a href="#search/icon-volume-up"><i class="icon-volume-up"></i> volume-up</a></li>
    <li><a href="#search/icon-qrcode"><i class="icon-qrcode"></i> qrcode</a></li>
    <li><a href="#search/icon-barcode"><i class="icon-barcode"></i> barcode</a></li>
    <li><a href="#search/icon-tag"><i class="icon-tag"></i> tag</a></li>
    <li><a href="#search/icon-tags"><i class="icon-tags"></i> tags</a></li>
    <li><a href="#search/icon-book"><i class="icon-book"></i> book</a></li>
    <li><a href="#search/icon-bookmark"><i class="icon-bookmark"></i> bookmark</a></li>
    <li><a href="#search/icon-print"><i class="icon-print"></i> print</a></li>
    <li><a href="#search/icon-camera"><i class="icon-camera"></i> camera</a></li>
    <li><a href="#search/icon-font"><i class="icon-font"></i> font</a></li>
    <li><a href="#search/icon-bold"><i class="icon-bold"></i> bold</a></li>
    <li><a href="#search/icon-italic"><i class="icon-italic"></i> italic</a></li>
    <li><a href="#search/icon-header"><i class="icon-header"></i> header</a></li>
    <li><a href="#search/icon-underline"><i class="icon-underline"></i> underline</a></li>
    <li><a href="#search/icon-strikethrough"><i class="icon-strikethrough"></i> strikethrough</a></li>
    <li><a href="#search/icon-eraser"><i class="icon-eraser"></i> eraser</a></li>
    <li><a href="#search/icon-align-justify"><i class="icon-align-justify"></i> align-justify</a></li>
    <li><a href="#search/icon-list"><i class="icon-list"></i> list</a></li>
    <li><a href="#search/icon-picture"><i class="icon-picture"></i> picture</a></li>
    <li><a href="#search/icon-pencil"><i class="icon-pencil"></i> pencil</a></li>
    <li><a href="#search/icon-map-marker"><i class="icon-map-marker"></i> map-marker</a></li>
    <li><a href="#search/icon-adjust"><i class="icon-adjust"></i> adjust</a></li>
    <li><a href="#search/icon-tint"><i class="icon-tint"></i> tint</a></li>
    <li><a href="#search/icon-edit"><i class="icon-edit"></i> edit</a></li>
    <li><a href="#search/icon-share"><i class="icon-share"></i> share</a></li>
    <li><a href="#search/icon-checked"><i class="icon-checked"></i> checked</a></li>
    <li><a href="#search/icon-arrows"><i class="icon-arrows"></i> arrows</a></li>
    <li><a href="#search/icon-step-backward"><i class="icon-step-backward"></i> step-backward</a></li>
    <li><a href="#search/icon-fast-backward"><i class="icon-fast-backward"></i> fast-backward</a></li>
    <li><a href="#search/icon-backward"><i class="icon-backward"></i> backward</a></li>
    <li><a href="#search/icon-play"><i class="icon-play"></i> play</a></li>
    <li><a href="#search/icon-pause"><i class="icon-pause"></i> pause</a></li>
    <li><a href="#search/icon-stop"><i class="icon-stop"></i> stop</a></li>
    <li><a href="#search/icon-forward"><i class="icon-forward"></i> forward</a></li>
    <li><a href="#search/icon-fast-forward"><i class="icon-fast-forward"></i> fast-forward</a></li>
    <li><a href="#search/icon-step-forward"><i class="icon-step-forward"></i> step-forward</a></li>
    <li><a href="#search/icon-eject"><i class="icon-eject"></i> eject</a></li>
    <li><a href="#search/icon-chevron-left"><i class="icon-chevron-left"></i> chevron-left</a></li>
    <li><a href="#search/icon-chevron-right"><i class="icon-chevron-right"></i> chevron-right</a></li>
    <li><a href="#search/icon-plus-sign"><i class="icon-plus-sign"></i> plus-sign</a></li>
    <li><a href="#search/icon-minus-sign"><i class="icon-minus-sign"></i> minus-sign</a></li>
    <li><a href="#search/icon-remove-sign"><i class="icon-remove-sign"></i> remove-sign</a></li>
    <li><a href="#search/icon-check-circle"><i class="icon-check-circle"></i> check-circle</a></li>
    <li><a href="#search/icon-question-sign"><i class="icon-question-sign"></i> question-sign</a></li>
    <li><a href="#search/icon-info-sign"><i class="icon-info-sign"></i> info-sign</a></li>
    <li><a href="#search/icon-remove-circle"><i class="icon-remove-circle"></i> remove-circle</a></li>
    <li><a href="#search/icon-check-circle-o"><i class="icon-check-circle-o"></i> check-circle-o</a></li>
    <li><a href="#search/icon-ban-circle"><i class="icon-ban-circle"></i> ban-circle</a></li>
    <li><a href="#search/icon-arrow-left"><i class="icon-arrow-left"></i> arrow-left</a></li>
    <li><a href="#search/icon-arrow-right"><i class="icon-arrow-right"></i> arrow-right</a></li>
    <li><a href="#search/icon-arrow-up"><i class="icon-arrow-up"></i> arrow-up</a></li>
    <li><a href="#search/icon-arrow-down"><i class="icon-arrow-down"></i> arrow-down</a></li>
    <li><a href="#search/icon-share-alt"><i class="icon-share-alt"></i> share-alt</a></li>
    <li><a href="#search/icon-resize-full"><i class="icon-resize-full"></i> resize-full</a></li>
    <li><a href="#search/icon-resize-small"><i class="icon-resize-small"></i> resize-small</a></li>
    <li><a href="#search/icon-plus"><i class="icon-plus"></i> plus</a></li>
    <li><a href="#search/icon-minus"><i class="icon-minus"></i> minus</a></li>
    <li><a href="#search/icon-asterisk"><i class="icon-asterisk"></i> asterisk</a></li>
    <li><a href="#search/icon-exclamation-sign"><i class="icon-exclamation-sign"></i> exclamation-sign</a></li>
    <li><a href="#search/icon-gift"><i class="icon-gift"></i> gift</a></li>
    <li><a href="#search/icon-leaf"><i class="icon-leaf"></i> leaf</a></li>
    <li><a href="#search/icon-eye-open"><i class="icon-eye-open"></i> eye-open</a></li>
    <li><a href="#search/icon-eye-close"><i class="icon-eye-close"></i> eye-close</a></li>
    <li><a href="#search/icon-warning-sign"><i class="icon-warning-sign"></i> warning-sign</a></li>
    <li><a href="#search/icon-plane"><i class="icon-plane"></i> plane</a></li>
    <li><a href="#search/icon-calendar"><i class="icon-calendar"></i> calendar</a></li>
    <li><a href="#search/icon-random"><i class="icon-random"></i> random</a></li>
    <li><a href="#search/icon-comment"><i class="icon-comment"></i> comment</a></li>
    <li><a href="#search/icon-chevron-up"><i class="icon-chevron-up"></i> chevron-up</a></li>
    <li><a href="#search/icon-chevron-down"><i class="icon-chevron-down"></i> chevron-down</a></li>
    <li><a href="#search/icon-shopping-cart"><i class="icon-shopping-cart"></i> shopping-cart</a></li>
    <li><a href="#search/icon-folder-close"><i class="icon-folder-close"></i> folder-close</a></li>
    <li><a href="#search/icon-folder-open"><i class="icon-folder-open"></i> folder-open</a></li>
    <li><a href="#search/icon-resize-v"><i class="icon-resize-v"></i> resize-v</a></li>
    <li><a href="#search/icon-resize-h"><i class="icon-resize-h"></i> resize-h</a></li>
    <li><a href="#search/icon-bar-chart-alt"><i class="icon-bar-chart-alt"></i> bar-chart-alt</a></li>
    <li><a href="#search/icon-camera-retro"><i class="icon-camera-retro"></i> camera-retro</a></li>
    <li><a href="#search/icon-key"><i class="icon-key"></i> key</a></li>
    <li><a href="#search/icon-cogs"><i class="icon-cogs"></i> cogs</a></li>
    <li><a href="#search/icon-comments"><i class="icon-comments"></i> comments</a></li>
    <li><a href="#search/icon-thumbs-o-up"><i class="icon-thumbs-o-up"></i> thumbs-o-up</a></li>
    <li><a href="#search/icon-thumbs-o-down"><i class="icon-thumbs-o-down"></i> thumbs-o-down</a></li>
    <li><a href="#search/icon-star-half"><i class="icon-star-half"></i> star-half</a></li>
    <li><a href="#search/icon-heart-empty"><i class="icon-heart-empty"></i> heart-empty</a></li>
    <li><a href="#search/icon-signout"><i class="icon-signout"></i> signout</a></li>
    <li><a href="#search/icon-pushpin"><i class="icon-pushpin"></i> pushpin</a></li>
    <li><a href="#search/icon-external-link"><i class="icon-external-link"></i> external-link</a></li>
    <li><a href="#search/icon-signin"><i class="icon-signin"></i> signin</a></li>
    <li><a href="#search/icon-trophy"><i class="icon-trophy"></i> trophy</a></li>
    <li><a href="#search/icon-upload-alt"><i class="icon-upload-alt"></i> upload-alt</a></li>
    <li><a href="#search/icon-lemon"><i class="icon-lemon"></i> lemon</a></li>
    <li><a href="#search/icon-phone"><i class="icon-phone"></i> phone</a></li>
    <li><a href="#search/icon-check-empty"><i class="icon-check-empty"></i> check-empty</a></li>
    <li><a href="#search/icon-bookmark-empty"><i class="icon-bookmark-empty"></i> bookmark-empty</a></li>
    <li><a href="#search/icon-phone-sign"><i class="icon-phone-sign"></i> phone-sign</a></li>
    <li><a href="#search/icon-credit"><i class="icon-credit"></i> credit</a></li>
    <li><a href="#search/icon-rss"><i class="icon-rss"></i> rss</a></li>
    <li><a href="#search/icon-hdd"><i class="icon-hdd"></i> hdd</a></li>
    <li><a href="#search/icon-bullhorn"><i class="icon-bullhorn"></i> bullhorn</a></li>
    <li><a href="#search/icon-bell"><i class="icon-bell"></i> bell</a></li>
    <li><a href="#search/icon-certificate"><i class="icon-certificate"></i> certificate</a></li>
    <li><a href="#search/icon-hand-right"><i class="icon-hand-right"></i> hand-right</a></li>
    <li><a href="#search/icon-hand-left"><i class="icon-hand-left"></i> hand-left</a></li>
    <li><a href="#search/icon-hand-up"><i class="icon-hand-up"></i> hand-up</a></li>
    <li><a href="#search/icon-hand-down"><i class="icon-hand-down"></i> hand-down</a></li>
    <li><a href="#search/icon-circle-arrow-left"><i class="icon-circle-arrow-left"></i> circle-arrow-left</a></li>
    <li><a href="#search/icon-circle-arrow-right"><i class="icon-circle-arrow-right"></i> circle-arrow-right</a></li>
    <li><a href="#search/icon-circle-arrow-up"><i class="icon-circle-arrow-up"></i> circle-arrow-up</a></li>
    <li><a href="#search/icon-circle-arrow-down"><i class="icon-circle-arrow-down"></i> circle-arrow-down</a></li>
    <li><a href="#search/icon-globe"><i class="icon-globe"></i> globe</a></li>
    <li><a href="#search/icon-wrench"><i class="icon-wrench"></i> wrench</a></li>
    <li><a href="#search/icon-tasks"><i class="icon-tasks"></i> tasks</a></li>
    <li><a href="#search/icon-filter"><i class="icon-filter"></i> filter</a></li>
    <li><a href="#search/icon-group"><i class="icon-group"></i> group</a></li>
    <li><a href="#search/icon-link"><i class="icon-link"></i> link</a></li>
    <li><a href="#search/icon-cloud"><i class="icon-cloud"></i> cloud</a></li>
    <li><a href="#search/icon-beaker"><i class="icon-beaker"></i> beaker</a></li>
    <li><a href="#search/icon-cut"><i class="icon-cut"></i> cut</a></li>
    <li><a href="#search/icon-copy"><i class="icon-copy"></i> copy</a></li>
    <li><a href="#search/icon-paper-clip"><i class="icon-paper-clip"></i> paper-clip</a></li>
    <li><a href="#search/icon-save"><i class="icon-save"></i> save</a></li>
    <li><a href="#search/icon-sign-blank"><i class="icon-sign-blank"></i> sign-blank</a></li>
    <li><a href="#search/icon-bars"><i class="icon-bars"></i> bars</a></li>
    <li><a href="#search/icon-list-ul"><i class="icon-list-ul"></i> list-ul</a></li>
    <li><a href="#search/icon-list-ol"><i class="icon-list-ol"></i> list-ol</a></li>
    <li><a href="#search/icon-table"><i class="icon-table"></i> table</a></li>
    <li><a href="#search/icon-magic"><i class="icon-magic"></i> magic</a></li>
    <li><a href="#search/icon-caret-down"><i class="icon-caret-down"></i> caret-down</a></li>
    <li><a href="#search/icon-caret-up"><i class="icon-caret-up"></i> caret-up</a></li>
    <li><a href="#search/icon-caret-left"><i class="icon-caret-left"></i> caret-left</a></li>
    <li><a href="#search/icon-caret-right"><i class="icon-caret-right"></i> caret-right</a></li>
    <li><a href="#search/icon-columns"><i class="icon-columns"></i> columns</a></li>
    <li><a href="#search/icon-sort"><i class="icon-sort"></i> sort</a></li>
    <li><a href="#search/icon-sort-down"><i class="icon-sort-down"></i> sort-down</a></li>
    <li><a href="#search/icon-sort-up"><i class="icon-sort-up"></i> sort-up</a></li>
    <li><a href="#search/icon-envelope-alt"><i class="icon-envelope-alt"></i> envelope-alt</a></li>
    <li><a href="#search/icon-undo"><i class="icon-undo"></i> undo</a></li>
    <li><a href="#search/icon-dashboard"><i class="icon-dashboard"></i> dashboard</a></li>
    <li><a href="#search/icon-comment-alt"><i class="icon-comment-alt"></i> comment-alt</a></li>
    <li><a href="#search/icon-comments-alt"><i class="icon-comments-alt"></i> comments-alt</a></li>
    <li><a href="#search/icon-bolt"><i class="icon-bolt"></i> bolt</a></li>
    <li><a href="#search/icon-sitemap"><i class="icon-sitemap"></i> sitemap</a></li>
    <li><a href="#search/icon-umbrella"><i class="icon-umbrella"></i> umbrella</a></li>
    <li><a href="#search/icon-paste"><i class="icon-paste"></i> paste</a></li>
    <li><a href="#search/icon-lightbulb"><i class="icon-lightbulb"></i> lightbulb</a></li>
    <li><a href="#search/icon-exchange"><i class="icon-exchange"></i> exchange</a></li>
    <li><a href="#search/icon-cloud-download"><i class="icon-cloud-download"></i> cloud-download</a></li>
    <li><a href="#search/icon-cloud-upload"><i class="icon-cloud-upload"></i> cloud-upload</a></li>
    <li><a href="#search/icon-bell-alt"><i class="icon-bell-alt"></i> bell-alt</a></li>
    <li><a href="#search/icon-coffee"><i class="icon-coffee"></i> coffee</a></li>
    <li><a href="#search/icon-file-text-o"><i class="icon-file-text-o"></i> file-text-o</a></li>
    <li><a href="#search/icon-building"><i class="icon-building"></i> building</a></li>
    <li><a href="#search/icon-double-angle-left"><i class="icon-double-angle-left"></i> double-angle-left</a></li>
    <li><a href="#search/icon-double-angle-right"><i class="icon-double-angle-right"></i> double-angle-right</a></li>
    <li><a href="#search/icon-double-angle-up"><i class="icon-double-angle-up"></i> double-angle-up</a></li>
    <li><a href="#search/icon-double-angle-down"><i class="icon-double-angle-down"></i> double-angle-down</a></li>
    <li><a href="#search/icon-angle-left"><i class="icon-angle-left"></i> angle-left</a></li>
    <li><a href="#search/icon-angle-right"><i class="icon-angle-right"></i> angle-right</a></li>
    <li><a href="#search/icon-angle-up"><i class="icon-angle-up"></i> angle-up</a></li>
    <li><a href="#search/icon-angle-down"><i class="icon-angle-down"></i> angle-down</a></li>
    <li><a href="#search/icon-desktop"><i class="icon-desktop"></i> desktop</a></li>
    <li><a href="#search/icon-laptop"><i class="icon-laptop"></i> laptop</a></li>
    <li><a href="#search/icon-tablet"><i class="icon-tablet"></i> tablet</a></li>
    <li><a href="#search/icon-mobile"><i class="icon-mobile"></i> mobile</a></li>
    <li><a href="#search/icon-circle-blank"><i class="icon-circle-blank"></i> circle-blank</a></li>
    <li><a href="#search/icon-quote-left"><i class="icon-quote-left"></i> quote-left</a></li>
    <li><a href="#search/icon-quote-right"><i class="icon-quote-right"></i> quote-right</a></li>
    <li><a href="#search/icon-spinner"><i class="icon-spinner"></i> spinner</a></li>
    <li><a href="#search/icon-circle"><i class="icon-circle"></i> circle</a></li>
    <li><a href="#search/icon-reply"><i class="icon-reply"></i> reply</a></li>
    <li><a href="#search/icon-folder-close-alt"><i class="icon-folder-close-alt"></i> folder-close-alt</a></li>
    <li><a href="#search/icon-folder-open-alt"><i class="icon-folder-open-alt"></i> folder-open-alt</a></li>
    <li><a href="#search/icon-expand-alt"><i class="icon-expand-alt"></i> expand-alt</a></li>
    <li><a href="#search/icon-collapse-alt"><i class="icon-collapse-alt"></i> collapse-alt</a></li>
    <li><a href="#search/icon-smile"><i class="icon-smile"></i> smile</a></li>
    <li><a href="#search/icon-frown"><i class="icon-frown"></i> frown</a></li>
    <li><a href="#search/icon-meh"><i class="icon-meh"></i> meh</a></li>
    <li><a href="#search/icon-gamepad"><i class="icon-gamepad"></i> gamepad</a></li>
    <li><a href="#search/icon-keyboard"><i class="icon-keyboard"></i> keyboard</a></li>
    <li><a href="#search/icon-flag-alt"><i class="icon-flag-alt"></i> flag-alt</a></li>
    <li><a href="#search/icon-flag-checkered"><i class="icon-flag-checkered"></i> flag-checkered</a></li>
    <li><a href="#search/icon-terminal"><i class="icon-terminal"></i> terminal</a></li>
    <li><a href="#search/icon-code"><i class="icon-code"></i> code</a></li>
    <li><a href="#search/icon-reply-all"><i class="icon-reply-all"></i> reply-all</a></li>
    <li><a href="#search/icon-star-half-full"><i class="icon-star-half-full"></i> star-half-full</a></li>
    <li><a href="#search/icon-location-arrow"><i class="icon-location-arrow"></i> location-arrow</a></li>
    <li><a href="#search/icon-crop"><i class="icon-crop"></i> crop</a></li>
    <li><a href="#search/icon-code-fork"><i class="icon-code-fork"></i> code-fork</a></li>
    <li><a href="#search/icon-unlink"><i class="icon-unlink"></i> unlink</a></li>
    <li><a href="#search/icon-question"><i class="icon-question"></i> question</a></li>
    <li><a href="#search/icon-info"><i class="icon-info"></i> info</a></li>
    <li><a href="#search/icon-shield"><i class="icon-shield"></i> shield</a></li>
    <li><a href="#search/icon-calendar-empty"><i class="icon-calendar-empty"></i> calendar-empty</a></li>
    <li><a href="#search/icon-rocket"><i class="icon-rocket"></i> rocket</a></li>
    <li><a href="#search/icon-chevron-sign-left"><i class="icon-chevron-sign-left"></i> chevron-sign-left</a></li>
    <li><a href="#search/icon-chevron-sign-right"><i class="icon-chevron-sign-right"></i> chevron-sign-right</a></li>
    <li><a href="#search/icon-chevron-sign-up"><i class="icon-chevron-sign-up"></i> chevron-sign-up</a></li>
    <li><a href="#search/icon-chevron-sign-down"><i class="icon-chevron-sign-down"></i> chevron-sign-down</a></li>
    <li><a href="#search/icon-html5"><i class="icon-html5"></i> html5</a></li>
    <li><a href="#search/icon-anchor"><i class="icon-anchor"></i> anchor</a></li>
    <li><a href="#search/icon-unlock-alt"><i class="icon-unlock-alt"></i> unlock-alt</a></li>
    <li><a href="#search/icon-bullseye"><i class="icon-bullseye"></i> bullseye</a></li>
    <li><a href="#search/icon-ellipsis-h"><i class="icon-ellipsis-h"></i> ellipsis-h</a></li>
    <li><a href="#search/icon-ellipsis-v"><i class="icon-ellipsis-v"></i> ellipsis-v</a></li>
    <li><a href="#search/icon-rss-sign"><i class="icon-rss-sign"></i> rss-sign</a></li>
    <li><a href="#search/icon-play-sign"><i class="icon-play-sign"></i> play-sign</a></li>
    <li><a href="#search/icon-minus-sign-alt"><i class="icon-minus-sign-alt"></i> minus-sign-alt</a></li>
    <li><a href="#search/icon-check-minus"><i class="icon-check-minus"></i> check-minus</a></li>
    <li><a href="#search/icon-level-up"><i class="icon-level-up"></i> level-up</a></li>
    <li><a href="#search/icon-level-down"><i class="icon-level-down"></i> level-down</a></li>
    <li><a href="#search/icon-check-sign"><i class="icon-check-sign"></i> check-sign</a></li>
    <li><a href="#search/icon-edit-sign"><i class="icon-edit-sign"></i> edit-sign</a></li>
    <li><a href="#search/icon-external-link-sign"><i class="icon-external-link-sign"></i> external-link-sign</a></li>
    <li><a href="#search/icon-share-sign"><i class="icon-share-sign"></i> share-sign</a></li>
    <li><a href="#search/icon-compass"><i class="icon-compass"></i> compass</a></li>
    <li><a href="#search/icon-collapse"><i class="icon-collapse"></i> collapse</a></li>
    <li><a href="#search/icon-collapse-top"><i class="icon-collapse-top"></i> collapse-top</a></li>
    <li><a href="#search/icon-expand"><i class="icon-expand"></i> expand</a></li>
    <li><a href="#search/icon-dollar"><i class="icon-dollar"></i> dollar</a></li>
    <li><a href="#search/icon-yen"><i class="icon-yen"></i> yen</a></li>
    <li><a href="#search/icon-file"><i class="icon-file"></i> file</a></li>
    <li><a href="#search/icon-file-text"><i class="icon-file-text"></i> file-text</a></li>
    <li><a href="#search/icon-sort-by-alphabet"><i class="icon-sort-by-alphabet"></i> sort-by-alphabet</a></li>
    <li><a href="#search/icon-sort-by-alphabet-alt"><i class="icon-sort-by-alphabet-alt"></i> sort-by-alphabet-alt</a></li>
    <li><a href="#search/icon-sort-by-attributes"><i class="icon-sort-by-attributes"></i> sort-by-attributes</a></li>
    <li><a href="#search/icon-sort-by-attributes-alt"><i class="icon-sort-by-attributes-alt"></i> sort-by-attributes-alt</a></li>
    <li><a href="#search/icon-sort-by-order"><i class="icon-sort-by-order"></i> sort-by-order</a></li>
    <li><a href="#search/icon-sort-by-order-alt"><i class="icon-sort-by-order-alt"></i> sort-by-order-alt</a></li>
    <li><a href="#search/icon-thumbs-up"><i class="icon-thumbs-up"></i> thumbs-up</a></li>
    <li><a href="#search/icon-thumbs-down"><i class="icon-thumbs-down"></i> thumbs-down</a></li>
    <li><a href="#search/icon-long-arrow-down"><i class="icon-long-arrow-down"></i> long-arrow-down</a></li>
    <li><a href="#search/icon-long-arrow-up"><i class="icon-long-arrow-up"></i> long-arrow-up</a></li>
    <li><a href="#search/icon-long-arrow-left"><i class="icon-long-arrow-left"></i> long-arrow-left</a></li>
    <li><a href="#search/icon-long-arrow-right"><i class="icon-long-arrow-right"></i> long-arrow-right</a></li>
    <li><a href="#search/icon-apple"><i class="icon-apple"></i> apple</a></li>
    <li><a href="#search/icon-windows"><i class="icon-windows"></i> windows</a></li>
    <li><a href="#search/icon-android"><i class="icon-android"></i> android</a></li>
    <li><a href="#search/icon-linux"><i class="icon-linux"></i> linux</a></li>
    <li><a href="#search/icon-sun"><i class="icon-sun"></i> sun</a></li>
    <li><a href="#search/icon-moon"><i class="icon-moon"></i> moon</a></li>
    <li><a href="#search/icon-archive"><i class="icon-archive"></i> archive</a></li>
    <li><a href="#search/icon-bug"><i class="icon-bug"></i> bug</a></li>
    <li><a href="#search/icon-zhifubao"><i class="icon-zhifubao"></i> zhifubao</a></li>
    <li><a href="#search/icon-zhifubao-square"><i class="icon-zhifubao-square"></i> zhifubao-square</a></li>
    <li><a href="#search/icon-taobao"><i class="icon-taobao"></i> taobao</a></li>
    <li><a href="#search/icon-weibo"><i class="icon-weibo"></i> weibo</a></li>
    <li><a href="#search/icon-renren"><i class="icon-renren"></i> renren</a></li>
    <li><a href="#search/icon-chrome"><i class="icon-chrome"></i> chrome</a></li>
    <li><a href="#search/icon-firefox"><i class="icon-firefox"></i> firefox</a></li>
    <li><a href="#search/icon-ie"><i class="icon-ie"></i> ie</a></li>
    <li><a href="#search/icon-opera"><i class="icon-opera"></i> opera</a></li>
    <li><a href="#search/icon-safari"><i class="icon-safari"></i> safari</a></li>
    <li><a href="#search/icon-node"><i class="icon-node"></i> node</a></li>
    <li><a href="#search/icon-layout"><i class="icon-layout"></i> layout</a></li>
    <li><a href="#search/icon-usecase"><i class="icon-usecase"></i> usecase</a></li>
    <li><a href="#search/icon-stack"><i class="icon-stack"></i> stack</a></li>
    <li><a href="#search/icon-branch"><i class="icon-branch"></i> branch</a></li>
    <li><a href="#search/icon-chat"><i class="icon-chat"></i> chat</a></li>
    <li><a href="#search/icon-chat-line"><i class="icon-chat-line"></i> chat-line</a></li>
    <li><a href="#search/icon-chat-dot"><i class="icon-chat-dot"></i> chat-dot</a></li>
    <li><a href="#search/icon-cube"><i class="icon-cube"></i> cube</a></li>
    <li><a href="#search/icon-database"><i class="icon-database"></i> database</a></li>
    <li><a href="#search/icon-align-left"><i class="icon-align-left"></i> align-left</a></li>
   </ul>
  </div>
 </body>
 <script src="${staticUrl}/plugins/jquery-1.12.4.min.js"></script>
 <script type="text/javascript">
 	$(function(){
 		$('li').on('click', function () {
            var className = $(this).find('i').get(0).className;
            console.log(className);
            if (className) {
            	top.rightContent.$('[name="${param.field}"]').val(className).trigger('change');
                var index = top.layer.getFrameIndex(window.name);
                top.layer.close(index);
            }
        });
 	});
 </script>
</html>