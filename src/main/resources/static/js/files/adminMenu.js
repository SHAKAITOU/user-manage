//-------------------------------------------------------------------------------------------
//-----------------adminMenu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminMenu = function(dataMap){
	this.form = $('#menu_form');
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
};
ShaUtil.other.inherits(AdminMenu, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminMenu.prototype.ID = {
	
	BTN_LOGOUT								: 'btnLogoutInit',
	
	MENU_TYPE_ACCOUNT				: '1',
	MENU_TYPE_CONTACT				: '2',
	MENU_TYPE_WORK					: '3',
	MENU_TYPE_OTHER					: '4',
	MENU_TYPE_SYSTEM				: '5',
	MENU_TYPE_LOGOUT				: '6',
	
	ITEM_SELECT						: 'menuSelect',

	ITEM_LANGUAGE					: 'language',
	//div
	DIV_MENUBODY					: 'menuBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminMenu.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	$('[data-toggle="tooltip"]').tooltip({ boundary: 'window' });
    
	ShaUtil.time.showTime();
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	//self.getObject(self.ID.ITEM_SELECT).val(self.ID.MENU_TYPE_WORK);
	self.getObject(self.ID.ITEM_SELECT).change();

};

// init event
AdminMenu.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_SELECT), 
		function(event) {
			var url = self.getJsContext().adminJsView.adminAccount.url_menu_account;
			if(self.getObject(self.ID.ITEM_SELECT).val() == self.ID.MENU_TYPE_ACCOUNT){
				url = self.getJsContext().adminJsView.adminAccount.url_menu_account;
			} else if(self.getObject(self.ID.ITEM_SELECT).val() == self.ID.MENU_TYPE_CONTACT){
				url = self.getJsContext().adminJsView.adminPayContact.url_menu_contact;
			} else if(self.getObject(self.ID.ITEM_SELECT).val() == self.ID.MENU_TYPE_WORK){
				url = self.getJsContext().adminJsView.adminMenu.url_menu_work;
			} else if(self.getObject(self.ID.ITEM_SELECT).val() == self.ID.MENU_TYPE_OTHER){
				url = self.getJsContext().adminJsView.adminMenu.url_menu_other;
			} else if(self.getObject(self.ID.ITEM_SELECT).val() == self.ID.MENU_TYPE_SYSTEM){
				url = self.getJsContext().adminJsView.adminMenu.url_menu_system;
			} else if(self.getObject(self.ID.ITEM_SELECT).val() == self.ID.MENU_TYPE_LOGOUT){
				url = self.getJsContext().adminJsView.adminMenu.url_menu_logout;
			}
			
			ShaAjax.ajax.get(
				url, 
				null, 
				function(data){
					self.getObject(self.ID.DIV_MENUBODY).html(data);
				}
			); 
	    }
	);
	
};
//----------------------------------------------------------------------------]
