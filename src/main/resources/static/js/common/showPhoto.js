//-------------------------------------------------------------------------------------------
//-----------------loginBlock.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ShowPhoto = function(dataMap){
	this.form = $('#showPhotoForm');
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
};
ShaUtil.other.inherits(ShowPhoto, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ShowPhoto.prototype.ID = {
	
	IMG_ID    : "showImg",
	//btn
	BTN_CLOSE : "btnClose",
	BTN_ADD   : "btnAdd",
	BTN_MINUS : "btnMinus",
};
//------------------------------------------]

//---------------method define--------------[
//init
ShowPhoto.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.initEvent();

};
// init event
ShowPhoto.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	//init event to BTN_BACK
	ShaInput.button.onClick(self.getObject(self.ID.BTN_CLOSE), 
		function(event) {
			ShaDialog.dialogs.dialogClose();
	    }
	);
	
	//init event to BTN_ADD
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD), 
		function(event) {
			var width = parseInt(self.getObject(self.ID.IMG_ID).css("width").replaceAll("px", ""));
			var height = parseInt(self.getObject(self.ID.IMG_ID).css("height").replaceAll("px", ""));
			self.getObject(self.ID.IMG_ID).css("width", (width*1.1) + "px");
			self.getObject(self.ID.IMG_ID).css("height", (height*1.1) + "px")
	    }
	);
	
	//init event to BTN_MINUS
	ShaInput.button.onClick(self.getObject(self.ID.BTN_MINUS), 
		function(event) {
			var width = parseInt(self.getObject(self.ID.IMG_ID).css("width").replaceAll("px", ""));
			var height = parseInt(self.getObject(self.ID.IMG_ID).css("height").replaceAll("px", ""));
			self.getObject(self.ID.IMG_ID).css("width", (width/1.1) + "px");
			self.getObject(self.ID.IMG_ID).css("height", (height/1.1) + "px")
	    }
	);

};


//----------------------------------------------------------------------------]
