//-------------------------------------------------------------------------------------------
//-----------------dbMaintenance.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
DbMaintenance = function(){
	this.form = $('#dbTableInfo_form');
};
ShaUtil.other.inherits(DbMaintenance, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
//editData
DbMaintenance.prototype.ID = {
	//btn
	BTN_CREATE_DDL					: 'btnCreateDdl',
	BTN_CREATE_INIT_DATA			: 'btnCreateInitData',
	BTN_DOWNLOAD_DATA				: 'btnDownloadData',
	//item
	ITEM_SELECT_TABLE_NAME			: 'selectTableName',
	//table
	TABLE_LIST 						: 'dbTableInfoTable',
};
//------------------------------------------]

//---------------method define--------------[
//init 
DbMaintenance.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	self.initEvent();
    
    //set init focus item when page loaded
	self.initFocus();
};

// init event
DbMaintenance.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CREATE_INIT_DATA), 
		function(event) {
			ShaDialog.dialogs.confirm(
            	"初期データ登録",
            	"初期データ登録しますか？",
            	function(){
        			ShaAjax.ajax.post(
        					window.GLOBAL.BASE_URL + '/init/data',
    	    			self.getForm().serializeArray(),
    	    			function(){ShaDialog.dialogs.success("登録しました。");}
    	    		);
            	}
            );

	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CREATE_DDL), 
		function(event) {
			ShaAjax.ajax.post(
					window.GLOBAL.BASE_URL + '/add/regist',
    			self.getForm().serializeArray(),
    			function(){ShaDialog.dialogs.success("作成しました。");}
    		);
	    }
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_DOWNLOAD_DATA), 
		function(event) {
			ShaRestful.restful.postPdf(
				window.GLOBAL.BASE_URL + '/backupData',
				self.getForm()
            );
	    }
	);
	
	ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.TABLE_LIST);
	ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.TABLE_LIST);
		
	$buttonList = self.getObject(self.ID.TABLE_LIST).find('button');
	$buttonList.each(function(i, elem){
		if($(elem).hasClass('detail')){
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem).attr('data'), i);
					ShaAjax.pop.postSubDialogLargeCenter(
						'DETAIL TABLE',
						window.GLOBAL.BASE_URL + '/detail', 
						self.getForm().serializeArray()
		            );
			    }
			);
		}else if($(elem).hasClass('sabun')){
			ShaInput.button.onClick($(elem), 
				function(event) {
					self.setPostData($(elem).attr('data'), i);
					ShaAjax.pop.postSubDialogLargeCenter(
						'SABUN SQL',
						window.GLOBAL.BASE_URL + '/edit/init', 
						self.getForm().serializeArray()
		            );
			    }
			);
		}
	});
	
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
};

//initFocus
DbMaintenance.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	
};

//setPostData
DbMaintenance.prototype.setPostData = function(tableName, trIndex){
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_SELECT_TABLE_NAME).val(tableName);
};

//----------------------------------------------------------------------------]
