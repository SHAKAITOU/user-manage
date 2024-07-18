//-------------------------------------------------------------------------------------------
//-----------------menu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
UserCerti = function(dataMap){
	this.mainForm = $('#main_form');
    this.form = $('#userCertiForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.clearBtn = true;

};
ShaUtil.other.inherits(UserCerti, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
UserCerti.prototype.ID = {
	
	DIV_MAINBODY                  : "mainBody",

};
//------------------------------------------]

//---------------method define--------------[
//init 
UserCerti.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
UserCerti.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
};


//----------------------------------------------------------------------------]
