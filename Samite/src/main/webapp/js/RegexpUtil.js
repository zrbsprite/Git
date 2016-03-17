/***************************
 ******** ������ʽ����*********
 **************************/
/**
 * Ϊstring�����trim����
 */
String.prototype.trim = function(){return this.replace(/(^\s*)|(\s*$)/g, "");};
/**
 * ��trim
 */
String.prototype.ltrim = function(){return this.replace(/(^\s*)/g, "");};
/**
 * ��trim
 */
String.prototype.rtrim = function(){return this.replace(/(\s*$)/g, "");};
/**
 * string to JSON<br/>
 * JSON string must be right ,such as '{"name":"zhangsan"}'
 */
var toJSONObj = (function(){return function(str){try{return JSON.parse(str);}catch(e){return e.toLocaleString();}};})();

/**
 * String to JSON not strict,but this may be not safety.If you have to use this function, just use this;
 * */
var toJSONObjSoft = (function(){return function(str){var temp = eval("("+str+")");return temp;};})();
/**
 * ���ڱȽ�,ǰ����������ȷ�������ַ���
 * */
var compareDate = function(dateLeft,dateRight){
	dateLeft = new Date(dateLeft);
	dateLeft.getTime();
	dateRight = new Date(dateRight);
	return dateRight>dateLeft;
};
/**
 * ��ȡ��������֮��Ĳ��
 */
var dateDiff = {
	diffType : {"day":0,"hour":1,"minute":2,"second":3,"millisecond":"4"},
	getDateDiff : function(startTime,endTime,type){
		if(typeof(startTime)=="string"){
			startTime = startTime.replace(/\-/g,"/");
			startTime = new Date(startTime);
		}else if(startTime.constructor!="Date"){
			return  new Error("�����ʱ��������ܱ�����ΪDate����");
		}
		if(typeof(endTime)=="string"){
			endTime = endTime.replace(/\-/g,"/");
			endTime = new Date(endTime);
		}else if(endTime.constructor!="Date"){
			return  new Error("�����ʱ��������ܱ�����ΪDate����");
		}
		var changeNo = 1;
		if(typeof(type)=='string'){
			switch(type){
				case "day":
					changeNo=1*24*60*60*1000;
					break;
				case "hour":
					changeNo=1*60*60*1000;
					break;
				case "minute":
					changeNo=1*60*1000;
					break;
				case "second":
					changeNo=1*1000;
					break;
				case "millisecond":
					break;
				default:
					changeNo=1*24*60*60*1000;
			}
		}else if(typeof(type)=='number'){
			switch(type){
				case 0:
					changeNo=1*24*60*60*1000;
					break;
				case 1:
					changeNo=1*60*60*1000;
					break;
				case 2:
					changeNo=1*60*1000;
					break;
				case 3:
					changeNo=1*1000;
					break;
				case 4:
					break;
				default:
					changeNo=1*24*60*60*1000;
			}
		}else{
			changeNo=1*24*60*60*1000;
		}
		var startNo = startTime.getTime();
		var endNo = endTime.getTime();
		var cha = startNo-endNo;
		cha = cha/changeNo;
		return cha>0?cha:-cha;
	}
};
/**
 * ΪDate�������format�����������Ҫ�����ڸ�ʽ<br>
 * ����ig��new Date().format('yyyy-MM-dd HH:mm:ss.S'); 
 */
Date.prototype.format = function(format){
	//ѡ����
	if(/(y+)/i.test(format)){
		//�õ�������ʽ�ĵ�һ��
		var year = RegExp.$1;
		format = format.replace(year,(this.getFullYear()+"").substr(4-year.length));
		//�ȵ��������������������ʽ
		var o = {
	        "M+" :  this.getMonth()+1,  //month
	        "d+" :  this.getDate(),     //day
	        "h+" :  this.getHours(),    //hour
	        "H+" :  this.getHours(),    //hour
	        "m+" :  this.getMinutes(),  //minute
	        "s+" :  this.getSeconds(), //second
	        "S"  :  this.getMilliseconds() //millisecond
	    };
	    for(var k in o) {
	        if(new RegExp("("+ k +")").test(format)) {
	        	//o[k] = k.M+.....
	            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
	        }
	    }
	    return format;
	}
};