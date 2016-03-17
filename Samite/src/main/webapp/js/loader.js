(function(window){
	var id='loader_bg_div_';
	var div,innerDiv,tempCL;
	var Loader = function(){
		div = document.createElement("div");
		innerDiv = document.createElement("div");
		innerDiv.style.position='relative';
		innerDiv.style['z-index']=999999;
		innerDiv.style.left="50%";
		innerDiv.style.top="40%";
		div.id = 'loader_bg_div_'+Number(new Date());
		id = div.id;
		var style = document.createElement("style");
		var css = document.createTextNode("#"+id+"{display: block;position: fixed;width: 100%;height: 100%;background-color: #393D49;filter:Alpha(opacity=20);opacity:0.2;z-index: 10000;top: 0px;left: 0px;cursor: wait;}");
		style.appendChild(css);
		document.head.appendChild(style);
		id = "inner_"+id;
		innerDiv.id = id;
		div.appendChild(innerDiv);
		document.body.appendChild(div);
		tempCL = new CanvasLoader(id, {id: "canvasLoader", safeVML: true});
		tempCL.setShape("roundRect");
		tempCL.setDiameter(80);
		tempCL.setDensity(20);
		tempCL.setSpeed(1);
		tempCL.setFPS(2);
		tempCL.setRange(0.95);
	}
	
	var p = Loader.prototype;
	p.show = function(){
		div.style['display']='block';
		tempCL.show();
	}
	p.hide = function(){
		tempCL.hide();
		div.style['display']='none';
	}
	window.Loader = Loader;
}(window));