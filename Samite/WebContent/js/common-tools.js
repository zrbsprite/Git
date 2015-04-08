/**parseInt可以将字符串转换成数值，更好的方式是使用一元运算符+*/
/*;(function(){
	var str = '010';
	var intStr = +str;
	console.log(str);
	console.log(intStr);
	var arrayA = ['chen','zhang','li'];
	var arrayB = ['chen',1,2,3];
	//合并数组
	var arrayC = $.merge(arrayA,arrayB);
	console.log(arrayC);
	//is array contains the element
	console.log($.inArray('chen',arrayC));//0
	console.log($.inArray('gong',arrayC));//-1
	//$.unique可以去除dom数组中的重复元素
	//遍历数组
	$.each(arrayC,function(index,value){
		console.log('index is :'+index + ",value is :"+value);
	});
	//对数字进行一定规则的处理，并返回处理后的数组
	var arrayD = $.map(arrayC,function(value,index){
		return value+10;
	});
	console.log(arrayD);
	//检测浏览器是否支持ajax或者说xmlHttpRequest
	console.info($.support.ajax);
	//获取当前时间的数值类型：new Date().getTime();
	console.log($.now());
})();*/

$(document).ready(function(){
	var url = "http://mall.cmbchina.com/Ajax/Customer/AjaxSystemAreaAddressInfo.aspx";
	$("#select_province").load('../../html/jQuery/options.html')
		.bind('change',function(){
			/*$.ajax({
                type: "post",
                dataType: "json",
                url: url,
                data: {'CityID':-1,'ProvinceID':$(this).val()},
                success: function(data, textStatus) {
                	var needData = data.Data.AreaList;
    				var options = "";
    				for(var j=0;j<needData.length;j++){
    					var id = needData[j].ID;
    					var name = needData[j].Name;
    					var option = '<option value="'+id+'">'+name+'</option>';
    					options += option;
    				}
    				$('#select_city').append(options);
                }
            });*/

			$.post(url,{'CityID':-1,'ProvinceID':$(this).val()},function(data){
				var json = $.parseJSON(data);
				var needData = json.Data.AreaList;
				var options = "";
				for(var j=0;j<needData.length;j++){
					var id = needData[j].ID;
					var name = needData[j].Name;
					var option = '<option value="'+id+'">'+name+'</option>';
					options += option;
				}
				$('#select_city').append(options);
			});
		});
});