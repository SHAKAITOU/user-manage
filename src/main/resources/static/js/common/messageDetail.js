//-------------------------------------------------------------------------------------------
//-----------------MessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
MessageDetail = function(dataMap){
    this.form = $('#MessageDetailForm');
    this.mainForm = $('#main_form');
    this.menuForm = $('#menu_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(MessageDetail, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
MessageDetail.prototype.ID = {
    
	BTN_CLOSE          : "btnClose",
    //div
    DIV_MAINBODY       : 'mainBody',
	UN_READ_MSGCNT_DIV : ".unReadMsgCntDiv"

};
//------------------------------------------]

//---------------method define--------------[
//init 
MessageDetail.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();


};

// init event
MessageDetail.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
	
    //init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);
	
	$unReadMsgCntDivList = self.menuForm.find(self.ID.UN_READ_MSGCNT_DIV);
	if ($unReadMsgCntDivList.length > 0) {
		ShaAjax.ajax.get(
            self.jsContext.common.messageDetail.url_getUnReadCnt, 
            null, 
            function(data){
				$unReadMsgCntDivList.each(function(i, elem){
	                if (data > 0) {
						$(elem).show();
					} else {
						$(elem).hide();
					}
					$(elem).html(data);
				});
				
				//refresh searchmessage when user role
				ShaAjax.ajax.get(
	                self.jsContext.jsView.messageSearch.url_init, 
	                null, 
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            );
            }
        ); 
	}
};

//----------------------------------------------------------------------------]
