//-------------------------------------------------------------------------------------------
//-----------------AdminPayContactManage.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminPayContactManage = function(dataMap){
	this.form = $('#menu_form');
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminPayContactManage, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminPayContactManage.prototype.ID = {
		
	DIV_MENUBODY					: 'menuBody',	
	
	BTN_CONFIRM_INIT				: 'btnPayContactConfirmInit',
	
	BTN_REFRESH						: "btnPayContactRefresh",
	
	TABLE_LIST					: 'payContactTable',
	
	ITEM_PAGELINKIDPREFIX 		: 'payContact_pageLinkIdPrefix',
	
	ITEM_SELECT_PAY_CONTACT_ID : '',

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminPayContactManage.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	Pos.constants.payContactManage = self;

	ShaInput.obj.disabled(self.getObject(self.ID.BTN_CONFIRM_INIT));
	
	//init bond event to btn
	self.initEvent();

	ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

// init event
AdminPayContactManage.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CONFIRM_INIT), 
		function(event) {
			if (self.ID.ITEM_SELECT_PAY_CONTACT_ID == '') {
				return;
			}
			
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'selectPayContactId', self.ID.ITEM_SELECT_PAY_CONTACT_ID);
    		ShaAjax.pop.postDialogLargeCenter(
				self.getJsContext().adminJsView.adminPayContact.a_contact_confirm_init_title,
				self.getJsContext().adminJsView.adminPayContact.url_a_contact_confirm_init, 
				sendData
            );
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_REFRESH), 
		function(event) {
			self.doPageLink();
		}
	);
	
	self.initPageLink();
	
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
    ShaInput.table.addClickEventToTr(self.getForm(), self.ID.TABLE_LIST, 
    	function(tr, trIndex){
    		self.ID.ITEM_SELECT_PAY_CONTACT_ID = tr.attr("rowDataKey");
    		ShaInput.obj.enabled(self.getObject(self.ID.BTN_CONFIRM_INIT));
        	return;
    	}
    );
	
};


//initPageLink
AdminPayContactManage.prototype.initPageLink = function(){
	//keep self instance for call back
	var self = this;
	
	ShaPage.pageLink.initPageLink(
		self.getObject(self.ID.ITEM_PAGELINKIDPREFIX).val(),
  	function(){return true;},
  	function(){
  		self.doPageLink();
  	}
  ); 
};

//doCompanyPageLink
AdminPayContactManage.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.post(
			self.getJsContext().adminJsView.adminPayContact.url_menu_contact_search, 
			self.getForm().serializeArray(), 
			function(data){
				self.getObject(self.ID.DIV_MENUBODY).html(data);
			}
	); 
};

//----------------------------------------------------------------------------]
