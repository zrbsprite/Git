/**parseInt���Խ��ַ���ת������ֵ�����õķ�ʽ��ʹ��һԪ�����+*/
/*;(function(){
	var str = '010';
	var intStr = +str;
	console.log(str);
	console.log(intStr);
	var arrayA = ['chen','zhang','li'];
	var arrayB = ['chen',1,2,3];
	//�ϲ�����
	var arrayC = $.merge(arrayA,arrayB);
	console.log(arrayC);
	//is array contains the element
	console.log($.inArray('chen',arrayC));//0
	console.log($.inArray('gong',arrayC));//-1
	//$.unique����ȥ��dom�����е��ظ�Ԫ��
	//��������
	$.each(arrayC,function(index,value){
		console.log('index is :'+index + ",value is :"+value);
	});
	//�����ֽ���һ������Ĵ��������ش���������
	var arrayD = $.map(arrayC,function(value,index){
		return value+10;
	});
	console.log(arrayD);
	//���������Ƿ�֧��ajax����˵xmlHttpRequest
	console.info($.support.ajax);
	//��ȡ��ǰʱ�����ֵ���ͣ�new Date().getTime();
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