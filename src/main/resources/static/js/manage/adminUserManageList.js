//-------------------------------------------------------------------------------------------
//-----------------AdminUserManageList.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserManageList = function(dataMap){
    this.form = $('#user_list_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminUserManageList, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserManageList.prototype.ID = {
    
    REGIST_DATE_FROM_INPT    : "user_list_form_regist_date_from",
    REGIST_DATE_TO_INPT      : "user_list_form_regist_date_to",
    VALID_END_DATE_FROM_INPT : "user_list_form_valid_end_date_from",
    VALID_END_DATE_TO_INPT   : "user_list_form_valid_end_date_to",
    SEARCH_BTN_ID            : "searchBtn",
    SHOW_SEARCH_PANEL_BTN_ID : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID : "hideSearchPanelBtn",
    
    //div
    DIV_REFRESH_BODY         : "userListRefreshBody",
    SEARCH_PANEL_ID          : "searchPanel",
    USER_LIST_TABLE_ID       : "userListTable",
    

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminUserManageList.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();

    //init click to view
    //self.getObject(self.ID.ITEM_SELECT).val(self.ID.MENU_TYPE_WORK);

};

// init event
AdminUserManageList.prototype.initEvent = function(){
    
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
			self.getObject(self.ID.SEARCH_PANEL_ID).show();
			self.getObject(self.ID.USER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenShowSearch + "px");
			
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.HIDE_SEARCH_PANEL_BTN_ID),
    	function(event) {
			self.getObject(self.ID.SEARCH_PANEL_ID).hide();
			self.getObject(self.ID.USER_LIST_TABLE_ID).find("tbody").height(self.dataMap.tableHeightWhenHideSearch + "px");
		}
    );
    
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
    
};
//----------------------------------------------------------------------------]
