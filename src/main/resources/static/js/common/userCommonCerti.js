//-------------------------------------------------------------------------------------------
//-----------------menu.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
UserCommonCerti = function(dataMap){
	this.mainForm = $('#main_form');
    this.form = $('#userCommonCertiForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.clearBtn = true;

};
ShaUtil.other.inherits(UserCommonCerti, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
UserCommonCerti.prototype.ID = {
	ITEM_USER_ID                  : "id",
	BTN_DOWNLOAD                  : "btnDownloadCert",
	
	DIV_MAINBODY                  : "mainBody",

};
//------------------------------------------]

//---------------method define--------------[
//init 
UserCommonCerti.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

};

// init event
UserCommonCerti.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_DOWNLOAD
	ShaInput.button.onClick(self.getObject(self.ID.BTN_DOWNLOAD), 
		function(event) {
			self.download("user_certificate");
	    }
	);
	
};

UserCommonCerti.prototype.download = function(fileType){
	var self = this;
	ShaAjax.ajax.getWithDownloadFile(
       self.jsContext.jsView.userDetail.url_user_detail_download+"/"+self.getObject(self.ID.ITEM_USER_ID).val()+"/"+fileType,
	   null,
       function(data, filename){
			if (data.size == 0){
				ShaDialog.dialogs.alert(self.i18n["dialogs.fail.title"]);
				return;
			}
            if (ShaUtil.other.isChrome() || ShaUtil.other.isSafari()){
              // chrome
              const link = document.createElement('a');
              link.href = window.URL.createObjectURL(data);
              link.download = filename;
              link.click();
			  window.URL.revokeObjectURL(link.href);
            } else if (ShaUtil.other.isIE()) {
              // IE
              //const blob = new Blob([data], {type: 'application/force-download'});
              window.navigator.msSaveBlob(data, filename);
            } else {
              // Firefox
              const file = new File([data], filename, {type: 'application/force-download'});
              window.open(URL.createObjectURL(file));
            }
       }
   ); 

//----------------------------------------------------------------------------]
}