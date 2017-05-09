package com.ironchain.intfc.web;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.util.HtmlUtils;

import com.ironchain.common.base.BaseController;

public class ApiBaseController extends BaseController {
	
	@Override
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : HtmlUtils.htmlEscape(text.trim()));
			}
		});
	}
	public static void main(String[] args) {
		String a = "<textarea id=\"news_banner_data\" style=\"display:none;\">{\"isViewed\": \"0\",\"url\": \"\",\"img\": \"\",\"title\": \"\",\"bannerId\": \"\"}</textarea>  <div class=news-flash-tips> <div class=\"left-line s-opacity-blank8\"></div> <div class=tips-content> <div class=tips-icon></div><span>以下信息根据您的兴趣推荐</span> </div> <div class=\"right-line s-opacity-blank8\"></div> </div><div class=water-container><div class=s-news-list-wrapper><div class=\"s-news-special s-news-item s-news-special-item-tpl-1 s-opacity-blank8\" data-url=\"http://news.sohu.com/20170508/n492175861.shtml\" data-rid=\"7426306111941991629\" data-relatewords=\"1\" data-log=\"stype:0;picNum:3;title:重庆现平流雾美景 犹如童话世界;pos:0;st:news;\" data-extra=\"\">  <h2><a href=\"http://news.sohu.com/20170508/n492175861.shtml\" title=\"重庆现平流雾美景 犹如童话世界\" target=_blank data-title=\"重庆现平流雾美景 犹如童话世界\" data-link=\"http://news.sohu.com/20170508/n492175861.shtml\" data-click=LOG_LINK class=s-title-yahei>重庆现平流雾美景 犹如童话世界</a><a href=\"javascript:;\" class=del></a></h2>     <div class=s-pic-content> <ul class=\"s-news-content-imgs clearfix\"> <li class=item-img-wrap> <a href=\"http://news.sohu.com/20170508/n492175861.shtml\" target=_blank data-click=LOG_LINK title=\"重庆现平流雾美景 犹如童话世界\" data-title=\"重庆现平流雾美景 犹如童话世界\"> <img class=s-news-img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1425813268,1539713561&amp;fm=80&amp;w=179&amp;h=119&amp;img.JPEG\" height=119 width=179> </a>   <li class=item-img-wrap> <a href=\"http://news.sohu.com/20170508/n492175861.shtml\" target=_blank data-click=LOG_LINK title=\"重庆现平流雾美景 犹如童话世界\" data-title=\"重庆现平流雾美景 犹如童话世界\"> <img class=s-news-img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1427407591,1547332019&amp;fm=80&amp;w=179&amp;h=119&amp;img.JPEG\" height=119 width=179> </a>   <li class=item-img-wrap> <a href=\"http://news.sohu.com/20170508/n492175861.shtml\" target=_blank data-click=LOG_LINK title=\"重庆现平流雾美景 犹如童话世界\" data-title=\"重庆现平流雾美景 犹如童话世界\"> <img class=s-news-img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1429001914,1554950477&amp;fm=80&amp;w=179&amp;h=119&amp;img.JPEG\" height=119 width=179> </a>   </ul></div>   <div class=from>  <span class=src-net> <a href=\"http://news.sohu.com\" target=_blank data-src=1 data-click=LOG_LINK> 搜狐新闻 </a> </span>  <span class=src-time>05-08 13:08</span>  <span class=hot-point>热点</span>  <div class=dustbin data-click=LOG_BTN_DUSTBIN></div></div>   </div><div class=\"s-news-special s-news-item s-news-special-item-tpl-2 s-opacity-blank8\" data-url=\"http://news.ifeng.com/a/20170508/51058740_0.shtml\" data-rid=\"17283106297364571301\" data-relatewords=\"1\" data-log=\"stype:0;picNum:1;title:韩国防部：新政府若有新政策 萨德部署可以再考虑;pos:1;st:news;\" data-extra=\"\">   <div class=s-pic-content><div class=s-pic-content-own> <ul class=\"s-news-content-imgs clearfix\"> <li class=item-img-wrap> <a href=\"http://news.ifeng.com/a/20170508/51058740_0.shtml\" target=_blank data-click=LOG_LINK title=\"韩国防部：新政府若有新政策 萨德部署可以再考虑\" data-title=\"韩国防部：新政府若有新政策 萨德部署可以再考虑\"> <img class=s-news-img src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2881499567,330824394&amp;fm=80&amp;w=179&amp;h=119&amp;img.JPEG\" height=119 width=179> </a>   </ul></div></div> <div class=s-block-container> <div class=s-block-container-own> <div class=s-text-content> <h2><a href=\"http://news.ifeng.com/a/20170508/51058740_0.shtml\" title=\"韩国防部：新政府若有新政策 萨德部署可以再考虑\" target=_blank data-title=\"韩国防部：新政府若有新政策 萨德部署可以再考虑\" data-link=\"http://news.ifeng.com/a/20170508/51058740_0.shtml\" data-click=LOG_LINK class=s-title-yahei>韩国防部：新政府若有新政策 萨德部署可以再考虑</a><a href=\"javascript:;\" class=del></a></h2>  </div> <div class=from>  <span class=src-net> <a href=\"http://news.ifeng.com\" target=_blank data-src=1 data-click=LOG_LINK> 凤凰网 </a> </span>  <span class=src-time>05-08 13:31</span>  <span class=hot-point>热点</span>  <div class=dustbin data-click=LOG_BTN_DUSTBIN></div></div> </div> </div</div><div class=\"s-news-special s-news-item s-news-special-item-tpl-2 s-opacity-blank8\" data-url=\"http://news.sohu.com/20170508/n492171894.shtml\" data-rid=\"7570290883393988598\" data-relatewords=\"1\" data-log=\"stype:0;picNum:1;title:证监会：前4月罚没金额54.72亿元 超去年总额;pos:2;st:news;\" data-extra=\"\">   <div class=s-pic-content><div class=s-pic-content-own> <ul class=\"s-news-content-imgs clearfix\"> <li class=item-img-wrap> <a href=\"http://news.sohu.com/20170508/n492171894.shtml\" target=_blank data-click=LOG_LINK title=\"证监会：前4月罚没金额54.72亿元 超去年总额\" data-title=\"证监会：前4月罚没金额54.72亿元 超去年总额\"> <img class=s-news-img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1479060322,1597621468&amp;fm=80&amp;w=179&amp;h=119&amp;img.JPEG\" height=119 width=179> </a>   </ul></div></div> <div class=s-block-container> <div class=s-block-container-own> <div class=s-text-content> <h2><a href=\"http://news.sohu.com/20170508/n492171894.shtml\" title=\"证监会：前4月罚没金额54.72亿元 超去年总额\" target=_blank data-title=\"证监会：前4月罚没金额54.72亿元 超去年总额\" data-link=\"http://news.sohu.com/20170508/n492171894.shtml\" data-click=LOG_LINK class=s-title-yahei>证监会：前4月罚没金额54.72亿元 超去年总额</a><a href=\"javascript:;\" class=del></a></h2>  </div> <div class=from>  <span class=src-net> <a href=\"http://news.sohu.com\" target=_blank data-src=1 data-click=LOG_LINK> 搜狐新闻 </a> </span>  <span class=src-time>05-08 12:29</span>  <span class=hot-point>热点</span>  <div class=dustbin data-click=LOG_BTN_DUSTBIN></div></div> </div> </div>  </div></div></div></div><script id=s_js_news data-src=\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/mantpl/js/news/index_5c4b5980.js\">_manCard.asynLoad(\"s_js_news\")</script></div><div class=\"s-loading s-opacity-border4-top\" style=\"display:none;border:none;\"><img src=\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/mantpl/img/base/loading_72b1da62.gif\"><div class=load-text>内容加载中...</div></div></div></div><div class=s-more-bar style=visibility:hidden><div class=load-text>滚动查看更多</div><div class=rect> <div class=animate-arrow></div> </div></div><textarea id=\"s_menus_textarea\" style=\"display:none;\">{\"data\": [{\"id\": 2,\"name\": \"推荐\",\"state\": \"1\",\"ismenu\": \"1\"},{\"id\": 1,\"name\": \"导航\",\"state\": \"1\",\"ismenu\": \"1\"},{\"id\": 15,\"name\": \"视频\",\"state\": \"1\",\"ismenu\": \"1\"},{\"id\": 5,\"name\": \"购物\",\"state\": \"1\",\"ismenu\": \"1\"},{\"id\": 100,\"name\": \"我的关注\",\"state\": \"1\",\"ismenu\": \"0\"},{\"id\": 3,\"name\": \"音乐\",\"state\": \"1\",\"ismenu\": \"1\"},{\"id\": 23,\"name\": \"体育\",\"state\": \"\",\"ismenu\": \"1\"},{\"id\": 20,\"name\": \"小说\",\"state\": \"\",\"ismenu\": \"1\"}],\"isEnd\": \"0\",\"illegal\": \"\"}</textarea><div id=s_top_feed class=\"top-feed\"> <div class=to-top data-tool=1 style=\"visibility:hidden;\"> <div class=icon-mask> <div class=text> 回到顶部 </div> </div> <span class=icon></span> </div> </div></div> <script>window._card_sync = 1;window._xman_speed && _xman_speed.imgLoadTime(2);</script>  </div>   <textarea id=\"s_sync_data\" style=\"display:none;\"></textarea></div><div id=bottom_container class=s-bottom-ctner> <div class=\"ftCon-Wrapper\"> <div class=qrcode-layer> <div class=\"qrcode-item qrcode-item-1\"> <div class=qrcode-img></div><div class=qrcode-text> <p><b>手机百度</b></p><p><span>快人一步</span></p> </div> </div><div class=\"qrcode-item qrcode-item-2\"> <div class=qrcode-img></div><div class=qrcode-text> <p><b>百度糯米</b></p><p><span>我的生活</span></p> </div> </div> </div> <div class=no-qrcode-layer> <div> <p class=lh> <a class=sethome href=//www.baidu.com/cache/sethelp/index.html target=_blank> 设为首页 </a> </p> <span class=copyright-text><span>&#169;2017&nbsp;Baidu&nbsp;</span><a href=//www.baidu.com/duty/ target=_blank>使用百度前必读</a>&nbsp;<a href=http://jianyi.baidu.com target=_blank>意见反馈</a>&nbsp;<span>京ICP证030173号&nbsp;</span><img width=13 height=16 src=\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/copy_rignt_24.png\" > </span> </div> <div class=recordcode><a href=http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11000002000001 target=_blank><i></i>京公网安备11000002000001号</a></div> </div> </div></div><div class=\"s-newmusic s-isindex-wrap\" id=s_mancard_newmusic style=\"display:none;\"> <div class=\"p-panel clearfix\" id=p_panel> <div class=p-main-wrapper> <div class=\"p-img js-p-img\"><a class=cur-img target=_blank><img height=50px width=50px src=\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/newmusic/img/default_8a5b42b2.png\"></a></div> <div class=p-main-area> <div class=p-main> <div class=p-music-info> <div class=p-chanel> <span class=\"p-chanel-btn js-change-sence\"><span class=change-bg></span><span class=\"p-chanel-txt js-chanel-name\"></span></span> </div> <div class=p-songinfo><div class=p-songinfo-scroll><span class=song-name></span><span class=sep>-</span><span class=song-artist></span></div></div> </div> <div class=\"control-btn clearfix\" style=\"float:left;\"> <div class=\"common-music-btn icon-pic p-prev-btn js-play-prev\" title=\"上一首\"></div> <div class=\"common-music-btn icon-pic p-status js-play-pause icon-play-pause icon-play-playing\"></div> <div class=\"common-music-btn icon-pic p-next-btn js-play-next\" title=\"下一首\"></div> <div class=\"common-music-btn icon-pic p-switch-btn icon-play-mode-normal js-switch-mode\" title=\"循环播放\"></div> <div class=\"js-scroll-wrapper p-progress-wrapper\"> <div class=\"p-progress js-scroll-root hide-icon\"> <div class=\"past js-scroll-indicator\"><span class=\"icon-pic progress-icon js-scroll-ctrl\"></span></div> </div> </div> <div class=time-total>00:00</div> <div class=\"common-music-btn other-btn icon-pic p-collect-btn icon-song-collect js-song-collect\" title=\"收藏\"></div> <div class=\"common-music-btn other-btn icon-pic lrc-btn js-song-lrc\" title=\"歌词\"></div> <div class=\"p-volume-wrapper js-volume-hover clearfix\"> <div class=p-volume-bg> <div class=\"p-volume-line js-volume-line\"> <div class=\"p-volume js-scroll-root\"> <div class=\"past js-scroll-indicator\"><a class=\"icon-pic progress-icon js-scroll-ctrl\" style=\"display:none;\"></a></div> </div> </div> <div class=\"common-music-btn other-btn icon-pic p-volume-btn js-volume-onoff icon-volume-on\" title=\"音量\"></div> </div> </div> <div class=\"p-more-wrapper js-more-hover\"> <div class=p-more-panel> <ul> <li class=js-delete-song> <div class=\"common-music-btn icon-pic p-delete-btn \" title=\"删除\"></div><span class=text>删除</span>  <li class=js-song-share> <div class=\"common-music-btn icon-pic p-share-btn\" title=\"分享\"></div><span class=text>分享</span> <div class=share-panel-wrapper id=share_panel> <div class=share-body> <a href=\"###\" target=_blank data-type=tsina class=share-icons-tsina></a> <a href=\"###\" target=_blank data-type=tqq class=share-icons-tqq></a> <a href=\"###\" target=_blank data-type=qzone class=share-icons-qzone></a> <a href=\"###\" target=_blank data-type=sqq class=share-icons-sqq></a> <a href=\"###\" target=_blank data-type=tieba class=share-icons-tieba></a> </div> <div class=triangle-right></div> </div>  </ul> <div class=triangle-down></div> </div> <div class=\"common-music-btn other-btn icon-pic p-more-btn\" title=\"更多\"></div> </div> </div> <div class=p-close-bg></div> <div class=\"common-music-btn icon-pic p-close-btn js-player-close down\" title=\"收起\"></div> </div> </div> </div> <div class=bg-group> <div class=bg></div> <div class=bg-mask></div> <div class=bg-base></div> <div class=bg-ie></div> </div></div><div class=result-tips-wrapper><div class=result-tips-area><span class=icon-pic></span><p>歌曲加载失败</p></div></div> <div class=\"s-panel js-panel\" id=s_panel> <div class=s-panel-bg></div> <div class=s-panel-wrapper> <div class=\"head clearfix\"><p class=title>推荐电台(<span class=count>48</span>)</p><p class=mylike><a class=js-formy href=\"http://play.baidu.com/?__t=list_allfavor&from=bd_card\" target=_blank>我的收藏 (<span class=\"like-count js-like-count\"></span>)</a></p></div>   <div class=\"s-list-wrapper clearfix slider-wrapper\"> <div class=\"prev-page-btn control-left\" title=\"上一页\"><span class=\"icon-pic prev-page-icon\"></span></div> <div class=\"next-page-btn control-right\" title=\"下一页\"><span class=\"icon-pic next-page-icon\"></span></div> <div style=\"position:relative;overflow:hidden;width:100%;\"> <div style=\"position:relative;left:0px;width:60000px;\" class=\"clearfix mui-slider-scroll-container\">  <ul class=\"s-list clearfix\" data-index=\"0\"\"> <li data-id=\"public_tuijian_suibiantingting\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/suibiantingting.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>随便听听</div>     <li data-id=\"lovesongs\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/lovesong.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>红心频道</div>     <li data-id=\"public_tuijian_chengmingqu\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/chengmingqu.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>成名曲</div>     <li data-id=\"public_shiguang_jingdianlaoge\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/jingdianlaoge.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>经典老歌</div>     <li data-id=\"public_tuijian_yingshi\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/yingshi.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>影视</div>     <li data-id=\"public_fengge_qingyinyue\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/qingyinyue.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>轻音乐</div>     <li data-id=\"public_xinqing_qingge\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/danshen.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>单身情歌</div>     <li data-id=\"156\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/wangluogequ.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>网络歌曲</div>     <li data-id=\"42\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/chinese.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>国语</div>  </ul>  <ul class=\"s-list clearfix\" data-index=\"1\"\"> <li data-id=\"71\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/2000nian.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>2000年代</div>     <li data-id=\"public_fengge_yaogun\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/yaogun.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>摇滚</div>     <li data-id=\"44\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/english.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>英文</div>     <li data-id=\"public_tuijian_ktv\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/KTV.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>KTV金曲</div>     <li data-id=\"public_shiguang_90hou\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/90.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>90后</div>     <li data-id=\"public_fengge_zhongguofeng\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/zhongguofeng.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>中国风</div>     <li data-id=\"public_shiguang_80hou\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/80.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>80后</div>     <li data-id=\"public_fengge_xiaoqingxin\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/xiaoqingxin.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>小清新</div>     <li data-id=\"public_yuzhong_hanyu\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/hanyu.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>韩语</div>  </ul>  <ul class=\"s-list clearfix\" data-index=\"2\"\"> <li data-id=\"public_fengge_dj\" data-type=\"other\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss1.bdstatic.com/kvoZeXSm1A5BphGlnYG/newmusic/DJ.png?v=md5\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>DJ舞曲</div>     <li data-id=\"10\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/single.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>独处</div>     <li data-id=\"5\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/morining.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>清晨</div>     <li data-id=\"40\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/relax.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>轻松时刻</div>     <li data-id=\"15\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/afternoon.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>慵懒午后</div>     <li data-id=\"33\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/newsong.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>新歌试听</div>     <li data-id=\"38\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/happy.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>欢乐旋律</div>     <li data-id=\"41\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/miss.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>思念</div>     <li data-id=\"37\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/zhiyu.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>治愈系</div>  </ul>  <ul class=\"s-list clearfix\" data-index=\"3\"\"> <li data-id=\"81\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/slience.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>安静</div>     <li data-id=\"36\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/sad.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>伤感</div>     <li data-id=\"35\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/lonly.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>寂寞电波</div>     <li data-id=\"43\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/yueyu.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>粤语</div>     <li data-id=\"39\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/zuile.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>醉了</div>     <li data-id=\"9\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/sleep.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>睡眠</div>     <li data-id=\"16\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/study.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>学习</div>     <li data-id=\"1\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/sport.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>运动</div>     <li data-id=\"13\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/evening.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>傍晚</div>  </ul>  <ul class=\"s-list clearfix\" data-index=\"4\"\"> <li data-id=\"12\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/work.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>工作</div>     <li data-id=\"48\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/pop.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>流行风</div>     <li data-id=\"31\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/household.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>家务</div>     <li data-id=\"23\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/coffee.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>咖啡时光</div>     <li data-id=\"28\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/home.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>宅</div>     <li data-id=\"2\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/party.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>聚会</div>     <li data-id=\"29\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/net.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>上网</div>     <li data-id=\"21\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/haichuang.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>赖床</div>     <li data-id=\"20\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/wake.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>懒洋洋</div>  </ul>  <ul class=\"s-list clearfix\" data-index=\"5\"\"> <li data-id=\"34\" data-type=\"\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/jidong.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>活力四射</div>     <li data-id=\"26\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/son.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>亲子时光</div>     <li data-id=\"30\" data-type=\"default\" class=\"s-item js-play-songlist\"> <span class=sence-block-hover></span> <span href=\"javascript:void(0);\" class=sence-block> <img width=70px height=70px data-src=\"https://ss0.bdstatic.com/k4oZeXSm1A5BphGlnYG/newmusic/yujia.png\"> </span> <div class=\"icon-pic s-play-btn js-splay-btn\"></div> <div class=\"icon-pic s-playing-wrapper\"> </div> <div class=desc>瑜伽</div>  </ul>  </div> </div> </div> </div> <span class=\"icon-pic close-btn js-sence-close\" title=\"关闭\"></span></div> <div class=l-panel-wrapper id=lyc_panel> <div class=l-panel-bg></div> <div class=l-panel-area data-tid=7777> <div class=lyc-wrapper> <div class=l-panel id=s_mancard_newmusic_playingLrc></div> </div> <div class=l-header> <div class=\"icon-pic close-btn js-lyc-close\" title=\"关闭\"></div> </div> </div></div> <div class=\"p-sidebar js-player-open\"> <div class=bar-bg data-tid=7778></div> <div class=bar-mask></div> <span class=bar-text>音乐</span> <span class=bar-playing style=\"display:none;\"></span> </div> <link rel=stylesheet type=text/css href=\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/newmusic/css/newmusic_min_0048b8c6.css\"> <script id=s_js_newmusic data-src=\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/newmusic/js/newmusic_min_cea0dbda.js\">";
		long start = System.currentTimeMillis();
		System.out.println(StringEscapeUtils.escapeHtml4(a));
//		for (int i = 0; i < 10000; i++) {
//			StringEscapeUtils.escapeHtml4(a);//25863
//			HtmlUtils.htmlEscape(a);//4700
//		}
		System.out.println(System.currentTimeMillis() - start);
	}
}