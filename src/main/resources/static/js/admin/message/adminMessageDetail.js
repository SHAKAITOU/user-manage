//-------------------------------------------------------------------------------------------
//-----------------AdminMessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminMessageDetail = function(dataMap){
    this.form = $('#AdminMessageDetailForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminMessageDetail, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminMessageDetail.prototype.ID = {
    
	BTN_CLOSE       : "btnClose",

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminMessageDetail.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();


};

// init event
AdminMessageDetail.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
	
    //init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);
};

//----------------------------------------------------------------------------]
