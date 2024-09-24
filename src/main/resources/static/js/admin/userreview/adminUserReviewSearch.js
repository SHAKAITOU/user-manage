//-------------------------------------------------------------------------------------------
//-----------------AdminUserReviewSearch.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
AdminUserReviewSearch = function(dataMap){
    this.form = $('#userReviewSearchForm');
    this.mainForm = $('#main_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(AdminUserReviewSearch, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
AdminUserReviewSearch.prototype.ID = {
    
    SEARCH_BTN_ID            : "searchBtn",
    SHOW_SEARCH_PANEL_BTN_ID : "showSearchPanelBtn",
    HIDE_SEARCH_PANEL_BTN_ID : "hideSearchPanelBtn",
    HID_HIDE_SEARCH          : "hideSearch",
	HID_SELECTED_USER_ID     : "selectedUserId",
	
	SEARCH_MODE              : "searchMode",
	WAITLIST_BTN_ID          : "waitListBtn",
	REVIEWEDLIST_BTN_ID      : "reviewedListBtn",
	REVIEWMULTI_BTN_ID       : "reviewMultiBtn",
	SEARCH_MODE_WAIT_LIST    : "waitList",
	SEARCH_MODE_REVIEWED_LIST: "reviewedList",
    
    USER_LIST_CHECK_ALL_ID   : "user_check_all",
    USER_CHECK_PREF_ID       : ".user_check_",
    TABLE_BTN_REVIEW         : ".review",
    TABLE_BTN_DETAIL         : ".detail",
    
    //div
    DIV_REFRESH_BODY         : "userListRefreshBody",
    SEARCH_PANEL_ID          : "searchPanel",
    USER_LIST_TABLE_ID       : "userListTable",
    
    PAGE_LINK_ID_PREFIX      : "userPageLinkIdPrefix",
    //div
    DIV_MAINBODY             : "mainBody",

};
//------------------------------------------]

//---------------method define--------------[
//init 
AdminUserReviewSearch.prototype.init = function(){
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
AdminUserReviewSearch.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
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
    
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.USER_LIST_TABLE_ID);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.USER_LIST_TABLE_ID);
    
    ShaInput.button.onClick(self.getObject(self.ID.SEARCH_BTN_ID),
    	function(event) {
			ShaAjax.ajax.get(
                self.jsContext.adminJsView.adminUserReviewSearch.url_user_list, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
    //initPageLink
    ShaPage.pageLink.initPageLink(self.ID.PAGE_LINK_ID_PREFIX,
    	function(){return true;},
    	function(){
    		self.doPageLink();
    	}
    ); 
	
	ShaInput.button.onClick(self.getObject(self.ID.REVIEWMULTI_BTN_ID),
			function(event) {
				var isChecked = false;
				checkBoxList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.USER_CHECK_PREF_ID);
				if (checkBoxList != null){
					$checkBoxList.each(function(i, elem){
						if ($(elem).prop('checked')){
							isChecked = true;
						}
					});
				}
				if (!isChecked){
					ShaDialog.dialogs.alert(self.i18n["admin.userReview.review.noSelected"]);
					return;
				}
				ShaAjax.pop.postDialogMiddleCenter(
					self.i18n["admin.userReview.review.multi.title"],
					self.jsContext.adminJsView.adminUserReviewMulti.url_init, 
					self.getForm().serializeArray());
			}
		);
	
	ShaInput.button.onClick(self.getObject(self.ID.WAITLIST_BTN_ID),
		function(event) {
			self.getObject(self.ID.SEARCH_MODE).val(self.ID.SEARCH_MODE_WAIT_LIST);
			ShaAjax.ajax.get(
	            self.jsContext.adminJsView.adminUserReviewSearch.url_user_list, 
	            self.getForm().serializeArray(), 
	            function(data){
	                self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                $('[data-toggle="tooltip"]').tooltip();
	            }
	        ); 
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.REVIEWEDLIST_BTN_ID),
		function(event) {
			self.getObject(self.ID.SEARCH_MODE).val(self.ID.SEARCH_MODE_REVIEWED_LIST);
			ShaAjax.ajax.get(
	            self.jsContext.adminJsView.adminUserReviewSearch.url_user_list, 
	            self.getForm().serializeArray(), 
	            function(data){
	                self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                $('[data-toggle="tooltip"]').tooltip();
	            }
	        ); 
		}
	);

	$tableBtnList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_REVIEW);
	$tableBtnList.each(function(i, elem){
		//check box init
	   	ShaInput.button.onClick($(elem),
	    	function(event) {
				self.getObject(self.ID.HID_SELECTED_USER_ID).val($(elem).attr("data"));
				ShaAjax.ajax.post(
	                self.jsContext.adminJsView.adminUserReview.url_init, 
	                [{name:"id",     value:$(elem).attr("data")}],
				    //self.getForm().serializeArray(),  
	                function(data){
	                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
	                }
	            ); 
			}
	    );
	});
			
	$tableBtnList = self.getObject(self.ID.USER_LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL);
	$tableBtnList.each(function(i, elem){
		//check box init
		ShaInput.button.onClick($(elem),
			function(event) {
				//refresh order list
				ShaAjax.ajax.get(
		            self.jsContext.adminJsView.adminUserReview.url_init, 
		            [{name:"id",     value:$(elem).attr("data")}], 
		            function(data){
		                self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
		            }
		        ); 
			}
		);
	});
   	
};

//doPageLink
AdminUserReviewSearch.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.post(
        self.jsContext.adminJsView.adminUserReviewSearch.url_user_list_growing, 
        self.getForm().serializeArray(), 
        function(data){
            self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
            $('[data-toggle="tooltip"]').tooltip();
        }
    ); 
};

AdminUserReviewSearch.prototype.setTableBtnStatus = function(rowIdx, enabled){
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
