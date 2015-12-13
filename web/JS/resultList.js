/**
 * 
 */
/*返回时间的时和分*/
function formatTime(time){
	var pattern = /^[1-2]\d{3}-[0-1]\d{1}-\d{2}\s(\d{2}:\d{2}):\d{2}$/;
	var matches = pattern.exec(time);
	return matches[1];
}
(function(){
	//列表左移
	$('div#resultList').css("left","0%");
	//绑定左上角按钮
	$('#returnLast2').click(function(){
		$('#resultList').css('left','100%');
	});
})();
(function(){
	//载入数据
	$.each(rData.flights,function(){
		var text = '<ul class="f_list_tab"><li class="f_info js_flight_item" style="box-sizing: border-box;width:100%;" data-key="0" data-cabin-key="0"><div class="flight-list-start"><p><span class="fnt-18">';
		//出发时间
		text += formatTime(this.dTime.toString());
		text += "</span></p><p>";
		//出发机场
		text += this.dAirPort.toString();
		text += '</p></div><div class="flight-list-middle"><i class="flight-icon-fx"></i></div><div class="flight-list-end"><p><span class="fnt-18">';
		//达到时间
		text += formatTime(this.aTime.toString());
		text += '</span></p><p>';
		//到达地点
		text += this.aAirPort.toString();
		text += '</p></div><div class="flight-list-hs" style="width:95%;white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><img src="http://pic.c-ctrip.com/flight_intl/airline_logo/40x35/DZ.png"><span>';
		//公司
		text += this.airLine.toString();
		//航班号
		text += this.flightNo.toString();
		//航班规模
		text += '</span><span>';
		text += (this.ft + '('+this.ftSize+')');
		text += '</span></div></li><li class="f_price js_flight_item" data-key="0" data-cabin-key="0"><p data-insurance="0.00" data-ticket="0.00"><dfn>¥</dfn><strong>';
		//加载最低票价
		text += this.lowPrice.toString();
		text += '</strong></p><p>经济舱</p></li></ul>';
		$("#resultList").append(text);
		//		var len = this.tickets.length;
//		for(var i = 0;i<len;i++){
//			text += this.tickets[i].price.toString();
//			text += '</strong></p><p>';
//			text += this.tickets[i].classType.toString();
//			text += ' </p></li></ul>';
//			console.log(text);
//			$("#resultList").append(text);
//		}
		
	});
	$('#resultList ul').click(function(){
		window.open("http://m.ctrip.com/webapp/flight/"); 
	});
})();