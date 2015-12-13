function calcPageHeight(doc) {
        var cHeight = Math.max(doc.body.clientHeight, doc.documentElement.clientHeight);
        var sHeight = Math.max(doc.body.scrollHeight, doc.documentElement.scrollHeight);
        var height  = Math.max(cHeight, sHeight);
        return height;
    }
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
//使iframe自适应
function shiying(){
	var height = calcPageHeight(document);
    parent.document.getElementById('iframe').style.height = height + 'px'; 
}
addLoadEvent(shiying);

//初始化折叠栏
function init(){
	var divList = document.querySelectorAll('body>div>h2+*');
	for(var i=0;i<divList.length;i++){
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
}
addLoadEvent(init);
