/*************************************************
 * javascript中的私有域，</br>
 * 缺点：必须通过构造方法才能访问,如 var test = new privateFun();
 * test.getName();</br>
 * 不过通过静态私有变量可以避免
 *************************************************/
var privateFun = function(){
	//相当于局部私有变量，外界无法访问
	var functionName = "privateFun";
	
	//相当于私有方法，外界无法访问
	function innerFun(){
		return functionName;
	}
	
	//提供对外可以访问的方法,相当于public的方法或者说属性
	this.getName = function(){
		return functionName;
	};
}
/*************************************************
 * javascript中的块儿级代码
 *************************************************/
(function(){
	//块儿级代码
})();
/*************************************************
 * javascript中的静态方法,静态私有变量,基本模式如下：
 *************************************************/
(function(){
	var name='static private';
	function getName(){
		return name;
	}
	//构造方法
	Person = function(){};
	//共有，特权方法
	Person.prototype.publicMethod = function(){
		name += " method";
		return getName();
	};
})();
/*************************************************
 * javascript中的闭包,这是错误的写法
 *************************************************/
function closePackagError(){
	var array = new Array();
	for(var i=0;i<10;i++){
		//闭包
		array[i] = function(num){
			return i;
		};
	}
	return array;
}
/*************************************************
 * javascript中的闭包,闭包只能取得包含函数中任何变量的最后一个值
 *************************************************/
function closePackag(){
	var array = new Array();
	for(var i=0;i<10;i++){
		//闭包
		array[i] = function(num){
			//闭包
			return function(){
				return num;
			};
		}(i);
	}
	return array;
}
/*************************************************
 * javascript-模块模式,
 * 形式：var obj = {name:"zrb",
 * 					getName:function(){return ""}};
 *************************************************/
var singleton = function(){
	var name = "singleton";
	function singletonFun(){
		return name;
	};
	return {
		name:singletonFun(),
		singletonFun:function(){
			return singletonFun();
		}		
	};
}();
/*************************************************
 * javascript-增强模块模式,
 * 针对singleton必须是某个对象的实例的情况
 *************************************************/
var singleton = function(){
	var name = "singleton";
	function singletonFun(){
		return name;
	};
	var component = new BaseComponet();
	component.getCom = function(){
		return name;
	};
	component.setCom = function(com){
		name = com;
	};
	
	//返回副本
	return component;
}();