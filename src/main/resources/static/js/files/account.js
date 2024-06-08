//-------------------------------------------------------------------------------------------
//-----------------account.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
Account = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#accountForm');
};
ShaUtil.other.inherits(Account, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
Account.prototype.ID = {

		//tab

};
//------------------------------------------]

//---------------method define--------------[
//init 
Account.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//override enter key press event
	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
Account.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;


};

//----------------------------------------------------------------------------]
