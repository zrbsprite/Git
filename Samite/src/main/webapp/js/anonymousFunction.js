//写入注释，通过匿名函数写入
//这是注释
//这是注释
//这是注释
//这是注释
//这是注释
//这是注释
function showInfo(){
	alert("你点击了按钮");
}
function compareDate(dateLeft,dateRight){
	var strLefts = dateLeft.split("-");
	var strRights = dateRight.split("-");
	
	dateLeft = new Date(strLefts[0],strLefts[1],strLefts[2]);
	dateRight = new Date(strRights[0],strRights[1],strRights[2]);
	
	return dateRight > dateLeft;
}