/*************************************************
 * javascript�е�˽����</br>
 * ȱ�㣺����ͨ�����췽�����ܷ���,�� var test = new privateFun();
 * test.getName();</br>
 * ����ͨ����̬˽�б������Ա���
 *************************************************/
var privateFun = function(){
	//�൱�ھֲ�˽�б���������޷�����
	var functionName = "privateFun";
	
	//�൱��˽�з���������޷�����
	function innerFun(){
		return functionName;
	}
	
	//�ṩ������Է��ʵķ���,�൱��public�ķ�������˵����
	this.getName = function(){
		return functionName;
	};
}
/*************************************************
 * javascript�еĿ��������
 *************************************************/
(function(){
	//���������
})();
/*************************************************
 * javascript�еľ�̬����,��̬˽�б���,����ģʽ���£�
 *************************************************/
(function(){
	var name='static private';
	function getName(){
		return name;
	}
	//���췽��
	Person = function(){};
	//���У���Ȩ����
	Person.prototype.publicMethod = function(){
		name += " method";
		return getName();
	};
})();
/*************************************************
 * javascript�еıհ�,���Ǵ����д��
 *************************************************/
function closePackagError(){
	var array = new Array();
	for(var i=0;i<10;i++){
		//�հ�
		array[i] = function(num){
			return i;
		};
	}
	return array;
}
/*************************************************
 * javascript�еıհ�,�հ�ֻ��ȡ�ð����������κα��������һ��ֵ
 *************************************************/
function closePackag(){
	var array = new Array();
	for(var i=0;i<10;i++){
		//�հ�
		array[i] = function(num){
			//�հ�
			return function(){
				return num;
			};
		}(i);
	}
	return array;
}
/*************************************************
 * javascript-ģ��ģʽ,
 * ��ʽ��var obj = {name:"zrb",
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
 * javascript-��ǿģ��ģʽ,
 * ���singleton������ĳ�������ʵ�������
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
	
	//���ظ���
	return component;
}();