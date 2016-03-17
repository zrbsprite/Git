//匿名函数
(function(window){
	var _id = 10;
	//定义构造函数,构造函数必须大写
	var Loader = function(opt){
		//这里可以定义私有属性
		var isCome=true;
		
		//公有属性
		this.to='B';
		if(typeof(opt)=='undefined'){
			opt = {};
		}
		this.init(opt);
	};
	//获取原型
	var p = Loader.prototype;
	p.init = function(opt){
		this._name=opt.name;
		this._age=opt.age;
		_id = opt.id;
	};
	//公有属性
	p._name='zrb';
	p._age='27';
	//公有方法
	p.getName = function(){
		return this._name;
	};
	p.getAge = function(){
		return this._age;
	};
	
	//静态成员
	Loader.from='A';
	
	//将Loader赋给window成为window的属性，可以通过new 的方式产生对象
	window.Loader = Loader;
}(window));