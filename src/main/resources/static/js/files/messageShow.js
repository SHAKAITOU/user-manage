//-------------------------------------------------------------------------------------------
//-----------------MessageShow.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
MessageShow = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = null;
	
};
ShaUtil.other.inherits(MessageShow, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
MessageShow.prototype.ID = {

};
//------------------------------------------]

//---------------method define--------------[
//init 
MessageShow.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
MessageShow.prototype.initEvent = function(){
	
	
};
//----------------------------------------------------------------------------]
