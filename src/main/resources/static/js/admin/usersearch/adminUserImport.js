//-------------------------------------------------------------------------------------------
//-----------------AdminUserImport.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserImport = function(dataMap){
	this.fatherForm = $('#userSearchForm');
    this.form = $('#AdminUserImportForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminUserImport, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserImport.prototype.ID = {
	SEARCH_BTN_ID                : "searchBtn",
	BTN_CLOSE                    : "btnClose",
    BTN_IMPORT                   : "btnImport",
    
	ITEM_IMPORT_FILE             : "importFile",
	ITEM_IMPORT_FILE_LBL         : "importFileLbl",
	ITEM_IMPORT_FILE_NAME        : "importFileName",

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminUserImport.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
AdminUserImport.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.obj.disabled(self.getObject(self.ID.ITEM_IMPORT_FILE_NAME));
	
	ShaInput.button.onChange(self.getObject(self.ID.ITEM_IMPORT_FILE), 
		function(event) {
    		var files = self.getObject(self.ID.ITEM_IMPORT_FILE).prop('files');
    		var fileExtension = ['xlsx'];
	        if ($.inArray(files[0].name.split('.').pop().toLowerCase(), fileExtension) == -1) {
	            ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongExt"]);
	            self.getObject(self.ID.ITEM_IMPORT_FILE).val("");
	        } else if(files[0].size > (1024*1024*5)) {
				ShaDialog.dialogs.alert(self.i18n["common.check.file.wrongSize"]);
	            self.getObject(self.ID.ITEM_IMPORT_FILE).val("");
			} else {
				self.getObject(self.ID.ITEM_IMPORT_FILE_NAME).val(files[0].name);
			}
	    }
	);
	
	//init event to BTN_IMPORT
	ShaInput.button.onClick(self.getObject(self.ID.BTN_IMPORT), 
		function(event) {
			if(self.check()) {
	            return;
	        }
	        ShaDialog.dialogs.confirm(
				self.i18n["dialogs.confirm.import.title"], 
				self.i18n["dialogs.confirm.import.msg"], 
				function () {
	        		self.getObject(self.ID.BTN_CLOSE).click();
					ShaAjax.ajax.postWithUploadFile(
						self.jsContext.adminJsView.adminUserImport.url_import, 
						"AdminUserImportForm", 
						function (data) {
							if (data.executeReturnType == Pos.constants.setInfo.common.executeReturnTypeOk){
								ShaDialog.dialogs.success(self.i18n["userImport.success.msg"]+"\n"+data.result);
							}else{
								ShaDialog.dialogs.alert(data.result);
							}
							self.getObjectInForm(self.fatherForm, self.ID.SEARCH_BTN_ID).click();
						}
					);
				}
			);
	    }
	);
	
	
    //init event to BTN_CLOSE
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);

};

//checkValue
AdminUserImport.prototype.check = function(){
	//keep self instance for call back
	var self = this;

    var inputCheckItemList = [
        [ self.i18n["userImport.uploadFile"], 	self.getObject(self.ID.ITEM_IMPORT_FILE_NAME)], 
    ];
    
    return ShaCheck.check.checkNotBlank(inputCheckItemList);
};


//----------------------------------------------------------------------------]
