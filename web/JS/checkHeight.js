//EventUtil对象定义
var EventUtil={
		
   addHandler:function(element,type,handler){ //添加事件
      if(element.addEventListener){ 
         element.addEventListener(type,handler,false);  //使用DOM2级方法添加事件
      }else if(element.attachEvent){                    //使用IE方法添加事件
         element.attachEvent("on"+type,handler);
      }else{
         element["on"+type]=handler;          //使用DOM0级方法添加事件
      }
   },  

   removeHandler:function(element,type,handler){  //取消事件
      if(element.removeEventListener){
         element.removeEventListener(type,handler,false);
      }else if(element.detachEvent){
         element.detachEvent("on"+type,handler);
      }else{
         element["on"+type]=null;
      }
   },

   getEvent:function(event){  //使用这个方法跨浏览器取得event对象
      return event?event:window.event;
   },
	
   getTarget:function(event){  //返回事件的实际目标
      return event.target||event.srcElement;
   },
	
   preventDefault:function(event){   //阻止事件的默认行为
      if(event.preventDefault){
         event.preventDefault(); 
      }else{
         event.returnValue=false;
      }
   },

   stopPropagation:function(event){  //立即停止事件在DOM中的传播
                                     //避免触发注册在document.body上面的事件处理程序
      if(event.stopPropagation){
         event.stopPropagation();
      }else{
         event.cancelBubble=true;
      }
   },
		
   getRelatedTarget:function(event){  //获取mouseover和mouseout相关元素
      if(event.relatedTarget){
         return event.relatedTarget;
      }else if(event.toElement){      //兼容IE8-
         return event.toElement;
      }else if(event.formElement){
         return event.formElement;
      }else{
         return null;
      }
   },
		
   getButton:function(event){    //获取mousedown或mouseup按下或释放的按钮是鼠标中的哪一个
      if(document.implementation.hasFeature("MouseEvents","2.0")){
         return event.button;
      }else{
         switch(event.button){   //将IE模型下的button属性映射为DOM模型下的button属性
            case 0:
            case 1:
            case 3:
            case 5:
            case 7:
               return 0;  //按下的是鼠标主按钮（一般是左键）
            case 2:
            case 6:
               return 2;  //按下的是中间的鼠标按钮
            case 4:
               return 1;  //鼠标次按钮（一般是右键）
         }
      }
   },
		
   getWheelDelta:function(event){ //获取表示鼠标滚轮滚动方向的数值
      if(event.wheelDelta){
         return event.wheelDelta;
      }else{
         return -event.detail*40;
      }
   },
		
   getCharCode:function(event){   //以跨浏览器取得相同的字符编码，需在keypress事件中使用
      if(typeof event.charCode=="number"){
         return event.charCode;
      }else{
         return event.keyCode;
      }
   }
		
};
//*******************************iframe自适应函数*******************************
function calcPageHeight(doc) {
        var cHeight = Math.max(doc.body.clientHeight, doc.documentElement.clientHeight);
        var sHeight = Math.max(doc.body.scrollHeight, doc.documentElement.scrollHeight);
        var height  = Math.max(cHeight, sHeight);
        return height;
    }
//*******************************addLoadEvent函数*******************************
function addLoadEvent(func){
	var old = window.onload;
	if(window.onload!='function'){
		window.onload = func;
	}else{
		window.onload = function(){
			old();
			func();
		}
	}
}
//*******************************初始化折叠栏*******************************
(function init(){
	var divList = document.querySelectorAll('body>div>h2+*');
	//显示第一个折叠栏隐藏后面的折叠栏
	divList[0].style.display = "block";
	for(var i=1;i<divList.length;i++){
		divList[i].style.display = "none";
	}
	//为折叠栏添加事件
	for(i=0;i<divList.length;i++){
		divList[i].parentNode.getElementsByTagName('h2')[0].onclick = function(){
			var next = this.nextSibling;
			while(next.nodeType!==1)
				next = next.nextSibling;
			if(next.style.display=="none"){
				next.style.display = "block";
				this.getElementsByTagName('i')[0].style.backgroundImage = "url('images/down.png')";
			}
			else{
				next.style.display = "none";
				this.getElementsByTagName('i')[0].style.backgroundImage = "url('images/up.png')";
			}
		}
	}
})();
//*******************************创建自适应的canvas*******************************
function createCanvas(num){
	//参数错误
	if(typeof num !== 'number') 
		return;
	//循环构建num个canvas
	var pic = document.getElementById('picture');
	pic.className = "picture";
	var width = parseInt(pic.clientWidth/3);
	for(var i=0;i<num;i++){
		var canvas = document.createElement('canvas');
		//设置canvas的id
		canvas.id = "cvs"+i;
		canvas.style.background="url(images/clock.png) no-repeat center center";
		// canvas.style.backgroundSize = "90% 90%";
		canvas.style.backgroundSize = "90% 90%";
		pic.appendChild(canvas);
		canvas.height = width;
		canvas.width = width;
	}
	//创建div包裹三个span对Canvas进行描述
	var div = document.createElement('div');
	div.style.width = "100%";
	div.style.overflow = "hidden";
	var span = document.createElement('span');
	var text = document.createTextNode("北京时间");
	var span2 = document.createElement('span');
	var text2 = document.createTextNode("伦敦时间");
	var span3 = document.createElement('span');
	var text3 = document.createTextNode("香港时间");
	span.appendChild(text);
	span.id="span0";
	span.style.width = width+"px";
	span.style.display = "block";
	span.style.textAlign = "center";
	span.style.float = "left";
	span2.appendChild(text2);
	span2.id="span1";
	span2.style.width = width+"px";
	span2.style.display = "block";
	span2.style.textAlign = "center";
	span2.style.float = "left";
	span3.appendChild(text3);
	span3.id="span2";
	span3.style.width = width+"px";
	span3.style.display = "block";
	span3.style.textAlign = "center";
	span3.style.float = "left";
	div.appendChild(span);
	div.appendChild(span2);
	div.appendChild(span3);
	pic.appendChild(div);
}

//*******************根据时间和长度绘制指针*******************************
function drawClock(obj,date){
	//获取时分秒，当时大于12的时候转化为小于12的
	var hour = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();
	var flag = false; //true为上午，false为下午
	if(hour>=12){
		hour-=12;
	}else{
		flag = true;
	}
	hour+=min/60;
	var wid = obj.style.width||parseInt(document.getElementById('picture').clientWidth/3);
	var half = (wid)/2;
	//获取2D对象开始画图
	var cxt = obj.getContext('2d');
	if(!cxt) return;
	cxt.clearRect(0,0,wid,wid);
	//画时针
	cxt.save();
	cxt.lineWidth=wid/50;//根据宽度确定指针大小
	cxt.strokeStyle="#000";
	cxt.translate(half,half);
	cxt.rotate(hour*30*Math.PI/180);//单位是弧度,2π弧度为360度
	cxt.beginPath();
	cxt.moveTo(0,wid/20);
	cxt.lineTo(0,-wid/6);
	cxt.stroke();
	cxt.closePath();
	cxt.restore();
	//画分针
	cxt.save();
	cxt.lineWidth=wid/80;//根据宽度确定指针大小
	cxt.strokeStyle="#000";
	cxt.translate(half,half);
	cxt.rotate(min*6*Math.PI/180);//单位是弧度,2π弧度为360度
	cxt.beginPath();
	cxt.moveTo(0,wid/10);
	cxt.lineTo(0,-wid/3);
	cxt.stroke();
	cxt.closePath();
	cxt.restore();
	//画秒针
	cxt.save();
	cxt.lineWidth=1;//根据宽度确定指针大小
	cxt.strokeStyle="#f00";
	cxt.translate(half,half);
	cxt.rotate(sec*6*Math.PI/180);//单位是弧度,2π弧度为360度
	cxt.beginPath();
	cxt.moveTo(0,wid/10);
	cxt.lineTo(0,-wid/3);
	cxt.stroke();
	cxt.closePath();
	cxt.restore();
	//显示上下午
	cxt.save();
	cxt.font="12px Arial";
	cxt.fillStyle='#000';
	//flag值true为上午，false为下午
	if(flag){
		cxt.fillText("AM",wid*0.55,wid*0.7);
	}
	else{
		cxt.fillText("PM",wid*0.55,wid*0.7);
	}
	cxt.restore();

}
//*******************************对创建的canvas进行时间绘制*******************************
function InitCanvas(){
	var time = getLocalTime(8);
	var cvs = document.getElementById('cvs0');
	if(cvs)
		drawClock(cvs,time);
	var cvs1 = document.getElementById('cvs1');
	//伦敦时间
	var time1 = getLocalTime(0+1);
	if(cvs1)
		drawClock(cvs1,time1);
	//纽约时间,因为是夏令时
	var time2 = getLocalTime(8);
	var cvs2 = document.getElementById('cvs2');
	if(cvs2)
		drawClock(cvs2,time2);


}
//*******************************得到标准时区的时间的函数*******************************
function getLocalTime(i){
	//参数i为时区值数字，比如北京为东八区则输进8,西5输入-5
	if(typeof i !== 'number') return;
	var d = new Date();
	//得到1970年一月一日到现在的秒数
	var len = d.getTime();
	//本地时间与GMT时间的时间偏移差
	var offset = d.getTimezoneOffset()*60000;
	//得到现在的格林尼治时间
	var utcTime = len+offset;
	return new Date(utcTime+3600000*i);
}
//创建3个canvas
addLoadEvent(createCanvas(3));
InitCanvas();
setInterval("InitCanvas()",1000);
//定义机票查询为明天
(function(){
	var dDate = document.getElementById('dDate');
	var myDate = getNowFormatDate();
	// dDate.valueAsDate = myDate; 
	dDate.setAttribute('value',myDate);
	//默认到达地点和起飞地点为北京
	$('input[name=dCity]').attr('data-address',"|北京(BJS)||BJS");
	$('input[name=aCity]').attr('data-address',"|北京(BJS)||BJS");
})();
//绑定机票查询确定按钮事件
var dDate = document.querySelector('input[type=submit]');
function getNowFormatDate() 
{ 
	var day = new Date(); 
	var Year = 0; 
	var Month = 0; 
	var Day = 0; 
	var CurrentDate = ""; 
	Year= day.getFullYear();
	Month= day.getMonth()+1; 
	Day = day.getDate();  
	CurrentDate += Year + "-"; 
	if (Month >= 10 ) 
	{ 
	CurrentDate += Month + "-"; 
	} 
	else 
	{ 
	CurrentDate += "0" + Month + "-"; 
	} 
	if (Day >= 10 ) 
	{ 
	CurrentDate += Day ; 
	} 
	else 
	{ 
	CurrentDate += "0" + Day ; 
	} 
	return CurrentDate; 
}
//调整窗口时刷新页面
EventUtil.addHandler(window, "resize", function(event){
	window.location.replace(window.location.href);
});
//机票查询更多选项折叠卡
var moreButton = document.querySelector("a.highSearchA");
EventUtil.addHandler(moreButton,"click",function(event){
	event = EventUtil.getEvent(event);
	EventUtil.stopPropagation(event);//阻止默认点击
	var divNoneLists = document.querySelectorAll("div.highSelect");
	for(var a=0;a<3;a++){
		divNoneLists[a].style.display = "block";
	}
	moreButton.parentNode.removeChild(moreButton);
});
//绑定出发城市和到达城市文本框
var tempCity = null;
var dCity = document.getElementsByName("dCity");
EventUtil.addHandler(dCity[0],"click",function(event){
	event = EventUtil.getEvent(event);
	var target = EventUtil.getTarget(event);
	tempCity = target;
	showCitys();
});
var aCity = document.getElementsByName("aCity");
EventUtil.addHandler(aCity[0],"click",function(event){
	event = EventUtil.getEvent(event);
	var target = EventUtil.getTarget(event);
	tempCity = target;
	showCitys();
});
function showCitys(){//执行多次
	var newdiv = document.getElementById('newdiv');
	newdiv.style.left = "0%";
	tempCity.blur();
	EventUtil.addHandler(newdiv,'click',function(event){
		event = EventUtil.getEvent(event);
		var target = EventUtil.getTarget(event);
		newdiv.style.left = "100%";
		tempCity.value = target.innerHTML; //文本框的值赋值为选择的地点的值
		tempCity.setAttribute('data-address',target.getAttribute('data-address'));
		window.location.hash = "#iframe"; //定位到页面顶端
		this.onclick = null;
	});

}
function removeEvent(){
	var newdiv = document.getElementById('newdiv');
	var dds = newdiv.getElementsByTagName('dd');
	for(var i=0; i<dds.length; i++){
		EventUtil.removeHandler(dds[i],'click',null);
	}
}
//立即执行函数加载页面数据
(function(){
	var jsonData = {'热门':[{display:"北京",data:"|北京(BJS)||BJS"},{display:"上海",data:"|上海(SHA)||SHA"},{display:"广州",data:"|广州(CAN)||CAN"},{display:"深圳",data:"|深圳(SZX)||SZX"},{display:"成都",data:"|成都(CTU)||CTU"},{display:"杭州",data:"|杭州(HGH)||HGH"},{display:"武汉",data:"|武汉(WUH)||WUH"},{display:"西安",data:"|西安(SIA)||SIA"},{display:"重庆",data:"|重庆(CKG)||CKG"},{display:"青岛",data:"|青岛(TAO)||TAO"},{display:"长沙",data:"|长沙(CSX)||CSX"},{display:"南京",data:"|南京(NKG)||NKG"},{display:"厦门",data:"|厦门(XMN)||XMN"},{display:"昆明",data:"|昆明(KMG)||KMG"},{display:"大连",data:"|大连(DLC)||DLC"},{display:"天津",data:"|天津(TSN)||TSN"},{display:"郑州",data:"|郑州(CGO)||CGO"},{display:"三亚",data:"|三亚(SYX)||SYX"},{display:"济南",data:"|济南(TNA)||TNA"},{display:"福州",data:"|福州(FOC)||FOC"}],'ABCDEF':{'A':[{display:"阿勒泰",data:"|阿勒泰(AAT)||AAT"},{display:"安康",data:"|安康(AKA)||AKA"},{display:"阿克苏",data:"|阿克苏(AKU)||AKU"},{display:"鞍山",data:"|鞍山(AOG)||AOG"},{display:"安庆",data:"|安庆(AQG)||AQG"},{display:"安顺",data:"|安顺(AVA)||AVA"},{display:"阿拉善左旗",data:"|阿拉善左旗(AXF)||AXF"},{display:"阿里",data:"|阿里(NGQ)||NGQ"},{display:"阿拉善右旗",data:"|阿拉善右旗(RHT)||RHT"},{display:"阿尔山",data:"|阿尔山(YIE)||YIE"}],'B':[{display:"百色",data:"|百色(AEB)||AEB"},{display:"包头",data:"|包头(BAV)||BAV"},{display:"毕节",data:"|毕节(BFJ)||BFJ"},{display:"北海",data:"|北海(BHY)||BHY"},{display:"博乐",data:"|博乐(BPL)||BPL"},{display:"保山",data:"|保山(BSD)||BSD"},{display:"北京",data:"|北京(BJS)||BJS"},{display:"巴彦淖尔",data:"|巴彦淖尔(RLK)||RLK"},{display:"北京(南苑机场)",data:"|北京(南苑机场)(NAY)||BJS,NAY"},{display:"北京(首都国际机场)",data:"|北京(首都国际机场)(PEK)||BJS,PEK"}],'C':[{display:"昌都",data:"|昌都(BPX)||BPX"},{display:"常德",data:"|常德(CGD)||CGD"},{display:"长春",data:"|长春(CGQ)||CGQ"},{display:"朝阳",data:"|朝阳(CHG)||CHG"},{display:"赤峰",data:"|赤峰(CIF)||CIF"},{display:"长治",data:"|长治(CIH)||CIH"},{display:"重庆",data:"|重庆(CKG)||CKG"},{display:"长海",data:"|长海(CNI)||CNI"},{display:"长沙",data:"|长沙(CSX)||CSX"},{display:"成都",data:"|成都(CTU)||CTU"},{display:"常州",data:"|常州(CZX)||CZX"},{display:"池州",data:"|池州(JUH)||JUH"},{display:"长白山",data:"|长白山(NBS)||NBS"}],'D':[{display:"大同",data:"|大同(DAT)||DAT"},{display:"达县",data:"|达县(DAX)||DAX"},{display:"稻城",data:"|稻城(DCY)||DCY"},{display:"丹东",data:"|丹东(DDG)||DDG"},{display:"迪庆",data:"|迪庆(DIG)||DIG"},{display:"大连",data:"|大连(DLC)||DLC"},{display:"大理市",data:"|大理市(DLU)||DLU"},{display:"敦煌",data:"|敦煌(DNH)||DNH"},{display:"东营",data:"|东营(DOY)||DOY"},{display:"大庆",data:"|大庆(DQA)||DQA"},{display:"德令哈",data:"|德令哈(HXD)||HXD"},{display:"德宏",data:"|德宏(LUM)||LUM"}],'E':[{display:"鄂尔多斯",data:"|鄂尔多斯(DSN)||DSN"},{display:"额济纳旗",data:"|额济纳旗(EJN)||EJN"},{display:"恩施",data:"|恩施(ENH)||ENH"},{display:"二连浩特",data:"|二连浩特(ERL)||ERL"}],'F':[{display:"福州",data:"|福州(FOC)||FOC"},{display:"阜阳",data:"|阜阳(FUG)||FUG"},{display:"佛山",data:"|佛山(FUO)||FUO"},{display:"抚远",data:"|抚远(FYJ)||FYJ"}]},'GHIJ':{'G':[{display:"广州",data:"|广州(CAN)||CAN"},{display:"广汉",data:"|广汉(GHN)||GHN"},{display:"格尔木",data:"|格尔木(GOQ)||GOQ"},{display:"广元",data:"|广元(GYS)||GYS"},{display:"固原",data:"|固原(GYU)||GYU"},{display:"赣州",data:"|赣州(KOW)||KOW"},{display:"贵阳",data:"|贵阳(KWE)||KWE"},{display:"桂林",data:"|桂林(KWL)||KWL"},{display:"光化",data:"|光化(LHK)||LHK"}],'H':[{display:"红原",data:"|红原(AHJ)||AHJ"},{display:"海口",data:"|海口(HAK)||HAK"},{display:"河池",data:"|河池(HCJ)||HCJ"},{display:"邯郸",data:"|邯郸(HDG)||HDG"},{display:"黑河",data:"|黑河(HEK)||HEK"},{display:"呼和浩特",data:"|呼和浩特(HET)||HET"},{display:"合肥",data:"|合肥(HFE)||HFE"},{display:"杭州",data:"|杭州(HGH)||HGH"},{display:"淮安",data:"|淮安(HIA)||HIA"},{display:"怀化",data:"|怀化(HJJ)||HJJ"},{display:"海拉尔",data:"|海拉尔(HLD)||HLD"},{display:"哈密市",data:"|哈密市(HMI)||HMI"},{display:"哈尔滨",data:"|哈尔滨(HRB)||HRB"},{display:"和田市",data:"|和田市(HTN)||HTN"},{display:"汉中",data:"|汉中(HZG)||HZG"},{display:"黄山",data:"|黄山(TXN)||TXN"}],'J':[{display:"景德镇",data:"|景德镇(JDZ)||JDZ"},{display:"加格达奇",data:"|加格达奇(JGD)||JGD"},{display:"嘉峪关",data:"|嘉峪关(JGN)||JGN"},{display:"井冈山",data:"|井冈山(JGS)||JGS"},{display:"金昌",data:"|金昌(JIC)||JIC"},{display:"九江",data:"|九江(JIU)||JIU"},{display:"晋江",data:"|晋江(JJN)||JJN"},{display:"佳木斯",data:"|佳木斯(JMU)||JMU"},{display:"济宁",data:"|济宁(JNG)||JNG"},{display:"锦州",data:"|锦州(JNZ)||JNZ"},{display:"鸡西",data:"|鸡西(JXA)||JXA"},{display:"九寨沟",data:"|九寨沟(JZH)||JZH"},{display:"揭阳",data:"|揭阳(SWA)||SWA"},{display:"济南",data:"|济南(TNA)||TNA"}]},'KLMN':{'K':[{display:"库车",data:"|库车(KCA)||KCA"},{display:"康定",data:"|康定(KGT)||KGT"},{display:"喀什市",data:"|喀什市(KHG)||KHG"},{display:"凯里",data:"|凯里(KJH)||KJH"},{display:"喀纳斯",data:"|喀纳斯(KJI)||KJI"},{display:"昆明",data:"|昆明(KMG)||KMG"},{display:"库尔勒",data:"|库尔勒(KRL)||KRL"},{display:"克拉玛依",data:"|克拉玛依(KRY)||KRY"}],'L':[{display:"黎平",data:"|黎平(HZH)||HZH"},{display:"龙岩",data:"|龙岩(LCX)||LCX"},{display:"兰州",data:"|兰州(LHW)||LHW"},{display:"梁平",data:"|梁平(LIA)||LIA"},{display:"丽江",data:"|丽江(LJG)||LJG"},{display:"荔波",data:"|荔波(LLB)||LLB"},{display:"吕梁",data:"|吕梁(LLV)||LLV"},{display:"临沧",data:"|临沧(LNJ)||LNJ"},{display:"拉萨",data:"|拉萨(LXA)||LXA"},{display:"林西",data:"|林西(LXI)||LXI"},{display:"洛阳",data:"|洛阳(LYA)||LYA"},{display:"连云港",data:"|连云港(LYG)||LYG"},{display:"临沂",data:"|临沂(LYI)||LYI"},{display:"柳州",data:"|柳州(LZH)||LZH"},{display:"泸州",data:"|泸州(LZO)||LZO"},{display:"林芝",data:"|林芝(LZY)||LZY"}],'M':[{display:"牡丹江",data:"|牡丹江(MDG)||MDG"},{display:"绵阳",data:"|绵阳(MIG)||MIG"},{display:"梅州",data:"|梅州(MXZ)||MXZ"},{display:"满洲里",data:"|满洲里(NZH)||NZH"},{display:"漠河",data:"|漠河(OHE)||OHE"}],'N':[{display:"南昌",data:"|南昌(KHN)||KHN"},{display:"南充",data:"|南充(NAO)||NAO"},{display:"宁波",data:"|宁波(NGB)||NGB"},{display:"南京",data:"|南京(NKG)||NKG"},{display:"那拉提",data:"|那拉提(NLT)||NLT"},{display:"南宁",data:"|南宁(NNG)||NNG"},{display:"南阳",data:"|南阳(NNY)||NNY"},{display:"南通",data:"|南通(NTG)||NTG"}]},'PQRSTUVW':{'P':[{display:"攀枝花",data:"|攀枝花(PZI)||PZI"},{display:"普洱",data:"|普洱(SYM)||SYM"}],'Q':[{display:"且末",data:"|且末(IQM)||IQM"},{display:"庆阳",data:"|庆阳(IQN)||IQN"},{display:"黔江",data:"|黔江(JIQ)||JIQ"},{display:"衢州",data:"|衢州(JUZ)||JUZ"},{display:"齐齐哈尔",data:"|齐齐哈尔(NDG)||NDG"},{display:"秦皇岛",data:"|秦皇岛(SHP)||SHP"},{display:"青岛",data:"|青岛(TAO)||TAO"}],'R':[{display:"日喀则",data:"|日喀则(RKZ)||RKZ"}],'S':[{display:"神农架",data:"|神农架(HPG)||HPG"},{display:"上海",data:"|上海(SHA)||SHA"},{display:"沈阳",data:"|沈阳(SHE)||SHE"},{display:"沙市",data:"|沙市(SHS)||SHS"},{display:"石家庄",data:"|石家庄(SJW)||SJW"},{display:"三亚",data:"|三亚(SYX)||SYX"},{display:"深圳",data:"|深圳(SZX)||SZX"},{display:"上海(浦东国际机场)",data:"|上海(浦东国际机场)(PVG)||SHA,PVG"},{display:"上海(虹桥国际机场)",data:"|上海(虹桥国际机场)(SHA)||SHA,SHA"}],'T':[{display:"台州",data:"|台州(HYN)||HYN"},{display:"塔城",data:"|塔城(TCG)||TCG"},{display:"腾冲",data:"|腾冲(TCZ)||TCZ"},{display:"铜仁市",data:"|铜仁市(TEN)||TEN"},{display:"通辽",data:"|通辽(TGO)||TGO"},{display:"天水",data:"|天水(THQ)||THQ"},{display:"吐鲁番",data:"|吐鲁番(TLQ)||TLQ"},{display:"通化",data:"|通化(TNH)||TNH"},{display:"天津",data:"|天津(TSN)||TSN"},{display:"唐山",data:"|唐山(TVS)||TVS"},{display:"太原",data:"|太原(TYN)||TYN"}],'W':[{display:"乌兰浩特",data:"|乌兰浩特(HLH)||HLH"},{display:"乌鲁木齐",data:"|乌鲁木齐(URC)||URC"},{display:"潍坊",data:"|潍坊(WEF)||WEF"},{display:"威海",data:"|威海(WEH)||WEH"},{display:"文山县",data:"|文山县(WNH)||WNH"},{display:"温州",data:"|温州(WNZ)||WNZ"},{display:"乌海",data:"|乌海(WUA)||WUA"},{display:"武汉",data:"|武汉(WUH)||WUH"},{display:"武夷山",data:"|武夷山(WUS)||WUS"},{display:"无锡",data:"|无锡(WUX)||WUX"},{display:"梧州",data:"|梧州(WUZ)||WUZ"},{display:"万州",data:"|万州(WXN)||WXN"}]},'XYZ':{'X':[{display:"兴义",data:"|兴义(ACX)||ACX"},{display:"夏河",data:"|夏河(GXH)||GXH"},{display:"西双版纳",data:"|西双版纳(JHG)||JHG"},{display:"襄阳",data:"|襄阳(XFN)||XFN"},{display:"西昌",data:"|西昌(XIC)||XIC"},{display:"锡林浩特",data:"|锡林浩特(XIL)||XIL"},{display:"西安",data:"|西安(SIA)||SIA"},{display:"厦门",data:"|厦门(XMN)||XMN"},{display:"西宁",data:"|西宁(XNN)||XNN"},{display:"徐州",data:"|徐州(XUZ)||XUZ"}],'Y':[{display:"延安",data:"|延安(ENY)||ENY"},{display:"银川",data:"|银川(INC)||INC"},{display:"伊春",data:"|伊春(LDS)||LDS"},{display:"永州",data:"|永州(LLF)||LLF"},{display:"榆林",data:"|榆林(UYN)||UYN"},{display:"宜宾",data:"|宜宾(YBP)||YBP"},{display:"运城",data:"|运城(YCU)||YCU"},{display:"宜春",data:"|宜春(YIC)||YIC"},{display:"宜昌",data:"|宜昌(YIH)||YIH"},{display:"伊宁市",data:"|伊宁市(YIN)||YIN"},{display:"义乌",data:"|义乌(YIW)||YIW"},{display:"延吉",data:"|延吉(YNJ)||YNJ"},{display:"烟台",data:"|烟台(YNT)||YNT"},{display:"盐城",data:"|盐城(YNZ)||YNZ"},{display:"扬州",data:"|扬州(YTY)||YTY"},{display:"玉树县",data:"|玉树县(YUS)||YUS"}],'Z':[{display:"郑州",data:"|郑州(CGO)||CGO"},{display:"张家界",data:"|张家界(DYG)||DYG"},{display:"舟山",data:"|舟山(HSN)||HSN"},{display:"张掖",data:"|张掖(YZY)||YZY"},{display:"昭通",data:"|昭通(ZAT)||ZAT"},{display:"中山",data:"|中山(ZGN)||ZGN"},{display:"湛江",data:"|湛江(ZHA)||ZHA"},{display:"中卫",data:"|中卫(ZHY)||ZHY"},{display:"张家口",data:"|张家口(ZQZ)||ZQZ"},{display:"珠海",data:"|珠海(ZUH)||ZUH"},{display:"遵义",data:"|遵义(ZYI)||ZYI"}]}};
	var obj = eval(jsonData); //解析JSON数据	

	// 创建热门城市选项卡
	var newEle = document.createElement('dt');
	var text = document.createTextNode('热门城市');
	newEle.appendChild(text);
	var hotCity = document.getElementById('hotCity'); //获取hotCity
	hotCity.appendChild(newEle);
	var i,j,a,b,temp;//递增量
	// 创建热门地点列表
	for(i=0; i<obj['热门'].length; i++){
		newEle = document.createElement('dd');
		newEle.setAttribute('data-address',obj['热门'][i].data); 
		text = document.createTextNode(obj['热门'][i].display);
		newEle.appendChild(text);
		hotCity.appendChild(newEle);
	}
	var newdiv = document.getElementById('newdiv'); //获取newdiv
	//创建a-f
	temp = obj['ABCDEF'];
	for(i in temp){
		newEle = document.createElement('dl');
		a = document.createElement('dt');
		text = document.createTextNode(i);
		a.appendChild(text);
		newEle.appendChild(a);
		for(j=0; j<temp[i].length; j++){
			b = document.createElement('dd');
			b.setAttribute('data-address',temp[i][j].data);
			text = document.createTextNode(temp[i][j].display);
			// console.log(temp[i][j].display);	
			b.appendChild(text);
			newEle.appendChild(b);
		}
		newdiv.appendChild(newEle);
	}
	//创建g-j
	temp = obj['GHIJ'];
	for(i in temp){
		newEle = document.createElement('dl');
		a = document.createElement('dt');
		text = document.createTextNode(i);
		a.appendChild(text);
		newEle.appendChild(a);
		for(j=0; j<temp[i].length; j++){
			b = document.createElement('dd');
			b.setAttribute('data-address',temp[i][j].data);
			text = document.createTextNode(temp[i][j].display);
			// console.log(temp[i][j].display);	
			b.appendChild(text);
			newEle.appendChild(b);
		}
		newdiv.appendChild(newEle);
	}
	//创建k-n
	temp = obj['KLMN'];
	for(i in temp){
		newEle = document.createElement('dl');
		a = document.createElement('dt');
		text = document.createTextNode(i);
		a.appendChild(text);
		newEle.appendChild(a);
		for(j=0; j<temp[i].length; j++){
			b = document.createElement('dd');
			b.setAttribute('data-address',temp[i][j].data);
			text = document.createTextNode(temp[i][j].display);
			// console.log(temp[i][j].display);	
			b.appendChild(text);
			newEle.appendChild(b);
		}
		newdiv.appendChild(newEle);
	}
	//创建p-w
	temp = obj['PQRSTUVW'];
	for(i in temp){
		newEle = document.createElement('dl');
		a = document.createElement('dt');
		text = document.createTextNode(i);
		a.appendChild(text);
		newEle.appendChild(a);
		for(j=0; j<temp[i].length; j++){
			b = document.createElement('dd');
			b.setAttribute('data-address',temp[i][j].data);
			text = document.createTextNode(temp[i][j].display);
			b.appendChild(text);
			newEle.appendChild(b);
		}
		newdiv.appendChild(newEle);
	}
	//创建x-z
	temp = obj['XYZ'];
	for(i in temp){
		newEle = document.createElement('dl');
		a = document.createElement('dt');
		text = document.createTextNode(i);
		a.appendChild(text);
		newEle.appendChild(a);
		for(j=0; j<temp[i].length; j++){
			b = document.createElement('dd');
			// console.log(typeof temp[i][j].data);
			b.setAttribute('data-address',temp[i][j].data);
			text = document.createTextNode(temp[i][j].display);
			// console.log(temp[i][j].display);	
			b.appendChild(text);
			newEle.appendChild(b);
		}
		newdiv.appendChild(newEle);
	}
})();
//绑定选项卡左移事件
(function(){
	var returnLast = document.getElementById('returnLast');
	EventUtil.addHandler(returnLast,'click',function(){
		var newdiv = document.getElementById('newdiv');
		newdiv.style.left = "100%";
	});
	var newdiv = document.getElementById('newdiv');
	var dts = newdiv.getElementsByTagName('dt');
	for(var i=0;i<dts.length;i++){
		EventUtil.addHandler(dts[i],'click',function(){
			var dds = this.parentNode.getElementsByTagName('dd');
			if(dds[0].style.display==='block'||dds[0].style.display===""){
				for(var j=0;j<dds.length;j++){
					dds[j].style.display = "none";
				}
			}else{
				for(var j=0;j<dds.length;j++){
					dds[j].style.display = "block";
				}
			}
		});
	}
})();
//******************ajax***************
$('form#ticketForm').submit(function(e){
	e.preventDefault(); //阻止表单提交
	var json = {
		"dCity":$('input[name=dCity]').attr('data-address'),
		"aCity":$('input[name=aCity]').attr('data-address'),
		"dDate":$('input[name=dDate]').val(),
		"num":$('input[name=num]').val()||1,
		"pType":$('select[name=pType]  option:selected').val()||"adu",
		"cType":$('select[name=cType]  option:selected').val()||'y'
	}
//	console.log(JSON.stringify(json));
	 var returnJson = $.ajax({
	 	type:"post",
	 	data:{"json":JSON.stringify(json)},
	 	url:location.origin+"/SmartHotel/FlightTicketCralwer",
	 	dataType:"json",
	 	success:handleData,
	 	error:function(xhr,status,errMsg){
	 		console.log("错误信息："+errMsg);
	 	}
	 });
});
/***加载JS文件或者CSS文件****/
/***@src为文件的路径*****/
/***@type为文件的类型****/
function loadFile(src,type){
	if(type.toLowerCase()==='js'||type.toLowerCase()==='javascript'){
		var sc = document.createElement('script');
		sc.type="text/javascript";
		sc.src = src;
		document.body.appendChild(sc);
	}else if(type.toLowerCase()==='css'){
		var cs = document.createElement('link');
		cs.href = src;
		cs.rel = "stylesheet";
		cs.type = "text/css";
		var head = document.getElementsByTagName('head')[0];
		head.appendChild(cs);
	}
}
var rData = null;
function handleData(data){
	//返回数据后加载JS文件和CSS文件
	loadFile("JS/resultList.js","js");
	loadFile("CSS/resultList.css","CSS");
	rData = data;
}
