//-------------------------------------------------------------------------------------------
//-----------------MessageSearch.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
MessageSearch = function(dataMap){
    this.form = $('#MessageSearchForm');
    this.mainForm = $('#main_form');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
    this.dateFormat = 'yyyy-mm-dd';
    this.clearBtn = true;
    this.language = 'zh';
};
ShaUtil.other.inherits(MessageSearch, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
MessageSearch.prototype.ID = {
    
    ADD_BTN_ID               : "btnAdd",
    SHOW_MORE_BTN_ID         : "showMore",
    SEARCH_BTN_ID            : "searchBtn",

    TABLE_BTN_DETAIL         : ".detail",
    
    //div
    DIV_REFRESH_BODY         : "messageListRefreshBody",
    LIST_TABLE_ID            : "messageListTable",
    
    PAGE_LINK_ID_PREFIX      : "messagePageLinkIdPrefix",
    //div
    DIV_MAINBODY             : 'mainBody',
};
//------------------------------------------]

//---------------method define--------------[
//init 
MessageSearch.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();
};

// init event
MessageSearch.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
    
    ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.LIST_TABLE_ID);
    ShaInput.table.addClickActiveToTr(self.getForm(), self.ID.LIST_TABLE_ID);
    
    ShaInput.button.onClick(self.getObject(self.ID.SEARCH_BTN_ID),
    	function(event) {
			ShaAjax.ajax.post(
                self.jsContext.jsView.messageSearch.url_message_list, 
                self.getForm().serializeArray(), 
                function(data){
                    self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
                    $('[data-toggle="tooltip"]').tooltip();
                }
            ); 
		}
    );
    
    ShaInput.button.onClick(self.getObject(self.ID.ADD_BTN_ID),
    	function(event) {
			ShaAjax.pop.postDialogMiddleCenter(
				self.i18n["admin.messageList.sendAll"],
				self.jsContext.jsView.messageSearchPush.url_init, 
				self.getForm().serializeArray());
		}
    );
    
    $tableBtnList = self.getObject(self.ID.LIST_TABLE_ID).find(self.ID.TABLE_BTN_DETAIL);
    $tableBtnList.each(function(i, elem){
		//check box init
    	ShaInput.button.onClick($(elem),
	    	function(event) {
				ShaAjax.pop.postDialogMiddleCenter(
					self.i18n["admin.message.title.detail"],
					self.jsContext.common.messageDetail.url_init,  
					[{name:"id", value:$(elem).attr("data")}]);
			}
	    );
    	
    });
    
    //initPageLink
    ShaPage.pageLink.initPageLink(self.ID.PAGE_LINK_ID_PREFIX,
    	function(){return true;},
    	function(){
    		self.doPageLink();
    	}
    );
    
};

//doPageLink
MessageSearch.prototype.doPageLink = function(){
	//keep self instance for call back
	var self = this;
	ShaAjax.ajax.post(
        self.jsContext.jsView.messageSearch.url_message_list_growing, 
        self.getForm().serializeArray(), 
        function(data){
            self.getObjectInForm(self.mainForm, self.ID.DIV_MAINBODY).html(data);
            $('[data-toggle="tooltip"]').tooltip();
        }
    );;  
};

//----------------------------------------------------------------------------]
