//-------------------------------------------------------------------------------------------
//-----------------OrderList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
OrderList = function(dataMap){
    this.form = $('#user_order_list_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
};
ShaUtil.other.inherits(OrderList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
OrderList.prototype.ID = {
	
    //div
    DIV_MAINBODY                    : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
OrderList.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
OrderList.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;

};

//----------------------------------------------------------------------------]
