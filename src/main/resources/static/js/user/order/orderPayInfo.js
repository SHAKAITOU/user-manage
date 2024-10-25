//-------------------------------------------------------------------------------------------
//-----------------Order.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
OrderPayInfo = function(dataMap){
    this.form = $('#OrderPayInfoForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(OrderPayInfo, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
OrderPayInfo.prototype.ID = {
	
	BTN_CLOSE : "btnClose",
    
};
//------------------------------------------]

//---------------method define--------------[
//init 
OrderPayInfo.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
OrderPayInfo.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
    //init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.subDialogClose();
	    }
	);

};

//----------------------------------------------------------------------------]
