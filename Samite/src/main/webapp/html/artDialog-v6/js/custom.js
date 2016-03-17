function simple(){
	var d = dialog({
	    title: '欢迎',
	    content: '欢迎使用 artDialog 对话框组件！'
	});
	d.show();
}

/**
 * 模态窗口
 */
function modal(){
	var d = dialog({
	    title: 'message',
	    content: '<input autofocus />'
	});
	d.showModal();
}

/**
 *点击空白关闭窗口 
 */
function blankClose(){
	var d = dialog({
	    title: '信息',
	    content: '点击空白区域试试！',
	    //此时无论是不是模态窗口都不能显示为模态，因为这主要是用来针对tip效果的
	    quickClose : true
	});
//	/d.show();
	d.showModal();
}
