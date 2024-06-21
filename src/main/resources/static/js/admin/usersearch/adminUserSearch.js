//-------------------------------------------------------------------------------------------
//-----------------AdminUserSearch.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserSearch = function(dataMap){
    this.form = $('#userSearchForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminUserSearch, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserSearch.prototype.ID = {
    
    REGIST_DATE_FROM_INPT    : "registDateFrom",
    REGIST_DATE_TO_INPT      : "registDateTo",
    VALID_END_DATE_FROM_INPT : "validEndDateFrom",
    VALID_END_DATE_TO_INPT   : "validEndDateTo",
    
    SHOW_MORE_BTN_ID         : "showMore",
    SEARCH_BTN_ID            : "searchBtn",
    SHOW_SEARCH_PANEL_BTN_ID : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID : "hideSearchPanelBtn",
    HID_HIDE_SEARCH          : "hideSearch",
    
    USER_LIST_CHECK_ALL_ID   : "user_check_all",
    USER_CHECK_PREF_ID       : ".user_check_",
    TABLE_BTN_RESETPW        : ".restPw",
    TABLE_BTN_DETAIL         : ".detail",
    
    //div
    DIV_REFRESH_BODY         : "userListRefreshBody",
    SEARCH_PANEL_ID          : "searchPanel",
    USER_LIST_TABLE_ID       : "userListTable",
    

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminUserSearch.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();

	if (self.getObject(self.ID.HID_HIDE_SEARCH).val() === "true") {
		self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID).click();
	} else {
		self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID).click();
	}

};

// init event
AdminUserSearch.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
    self.getObject(self.ID.REGIST_DATE_FROM_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
    
    self.getObject(self.ID.REGIST_DATE_TO_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
    
    self.getObject(self.ID.VALID_END_DATE_FROM_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
    
    self.getObject(self.ID.VALID_END_DATE_TO_INPT).datepicker({
        format   : self.dateFormat,
        language : self.language,
        clearBtn : self.clearBtn
    });
    
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("false");
			self.getObject(self.ID.SEARCH_PANEL_ID).show();
			self.getObject(self.ID.USER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenShowSearch + "px");
			
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.HID_HIDE_SEARCH).val("true");
			self.getObject(self.ID.SEARCH_PANEL_ID).hide();
			self.getObject(self.ID.USER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenHideSearch + "px");
		}
    );
    
    ShaInput.button.onChange(self.getObject(self.ID.USER_LIST_CHECK_ALL_ID),
    	function(event) {
			$checkBoxList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.USER_CHECK_PREF_ID);
			if (self.getObject(self.ID.USER_LIST_CHECK_ALL_ID).is(":checked")) {
				$checkBoxList.each(function(i, elem){
					$(elem).prop('checked', true);
					$(elem).change();
				});
			} else {
				$checkBoxList.each(function(i, elem){
					$(elem).prop('checked', false);
					$(elem).change();
				});
			}
		}
    );
    $checkBoxList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.USER_CHECK_PREF_ID);
    $checkBoxList.each(function(i, elem){
		//init
		self.setTableBtnStatus(i, false);
		//check box init
    	ShaInput.button.onChange($(elem),
	    	function(event) {
				if ($(elem).is(":checked")) {
					self.setTableBtnStatus(i, true);
				} else {
					self.setTableBtnStatus(i, false);
				}
			}
	    );
    	
    });
    
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.USER_LIST_TABLE_ID);
    
    ShaInput.button.onClick(self.getObject(self.ID.SEARCH_BTN_ID),
    	function(event) {
			ShaAjax.ajax.post(
                self.jsContext.adminJsView.adminUserSearch.url_user_list, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.getForm(), self.ID.DIV_REFRESH_BODY).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.SHOW_MORE_BTN_ID),
    	function(event) {
			ShaAjax.ajax.post(
                self.jsContext.adminJsView.adminUserSearch.url_user_list_growing, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.getForm(), self.ID.DIV_REFRESH_BODY).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
};

AdminUserSearch.prototype.setTableBtnStatus = function(rowIdx, enabled){
    //keep self instance for call back
    var self = this;
    if (enabled) {
		ShaInput.obj.enabled($(self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_RESETPW)[rowIdx]));
		ShaInput.obj.enabled($(self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL)[rowIdx]));
	} else {
		ShaInput.obj.disabled($(self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_RESETPW)[rowIdx]));
		ShaInput.obj.disabled($(self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL)[rowIdx]));
	}
};	
//----------------------------------------------------------------------------]