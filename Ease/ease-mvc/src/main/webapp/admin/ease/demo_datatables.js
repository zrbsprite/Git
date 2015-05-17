$(function(){
	$('#mine').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"sDom": '<""l>t<"F"fp>',
		"bFilter":false,
		"bLengthChange":false,
		"bSort":false,
		"bStateSave":false,
		"bServerSide":true,
		"bPaginate ":true,
		"bDestroy":true,
		"bAutoWidth":false,
		"bProcessing":false,//这里是关键，要不报var c = a.aanFeatures.r, d = 0, g = c.length; d < g; d++)错误
		"sAjaxSource":"http://localhost:8080/SOS/servlet/json",
		"sServerMethod":"POST",
		"iDisplayLength":10,
		"aoColumns":[
		      {"sTitle":"Rendering engine"},
		      {"sTitle":"Browser","bVisible":false},
		      {"sTitle":"Platform"},
		      {"sTitle":"Engine version"},
		      {"sTitle":"operation","fnRender":function(data){
		    	  /*{
		                iDataRow: b,
		                iDataColumn: c,
		                oSettings: a,
		                aData: a.aoData[b]._aData,
		                mDataProp: d.mDataProp
		            }*/
		    	  //return "<button class='btn btn-mini'><i class='icon-pencil icon-white'></i>"+data.aData[1]+"</botton>";
		    	  //return "<button class='btn'><i class='icon-pencil'></i>"+data.aData[1]+"</botton>";
		    	  //return "<button class='btn'>"+data.aData[1]+"</botton>";
		    	  return '<a href="#myAlert" data-toggle="modal" class="btn">'+data.aData[1]+'</a>&nbsp;<a href="#myModal" data-toggle="modal" class="btn btn-primary">Modal dialog</a>&nbsp;<button class="btn btn-primary" onclick="openLayer();">Icon</button>';
		    	  //return '<div class="comments"><a class="btn btn-primary btn-mini" href="#">Edit</a><a class="btn btn-success btn-mini" href="#">Approve</a><a class="btn btn-warning btn-mini" href="#">Mark as spam</a><a class="btn btn-danger btn-mini" href="#">Delete</a></div>';
		      }}
		],
		"oLanguage": {
			"sLengthMenu": "每页显示 _MENU_ 条记录",
			"sZeroRecords": "对不起，查询不到任何相关数据",
			"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoEmtpy": "找不到相关数据",
			"sProcessing": "正在获取数据，请稍后...",
			"sSearch": "搜索:",
			"sInfoEmpty": "显示 0 至 0 共 0 项",
			"oPaginate": { "sFirst": "第一页", "sPrevious": "上一页 ", "sNext": "下一页 ", "sLast": "末页 " }
		},
	});
});