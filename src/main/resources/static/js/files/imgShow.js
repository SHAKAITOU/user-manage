//-------------------------------------------------------------------------------------------
//-----------------imgShow.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
ImgShow = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#img_show_form');
	
};
ShaUtil.other.inherits(ImgShow, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
ImgShow.prototype.ID = {
	BTN_ORG_SIZE		: 'btnShowImgOrgSize',
	BTN_FULL_SIZE		: 'btnShowImgFullSize',
	ITEM_IMGSHOW		: 'imgShow',
};
//------------------------------------------]

//---------------method define--------------[
//init 
ImgShow.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//init bond event to btn
	self.initEvent();

	//init click to view
	self.getObject(self.ID.BTN_FULL_SIZE).click();
};

// init event
ImgShow.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	self.getObject(self.ID.ITEM_IMGSHOW).attr("src", Pos.constants.showImgOrgObj.attr("src"));
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ORG_SIZE), 
		function(event) {
			self.getObject(self.ID.ITEM_IMGSHOW).removeClass("imgFull");
		}
	);
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_FULL_SIZE), 
		function(event) {
			self.getObject(self.ID.ITEM_IMGSHOW).addClass("imgFull");
		}
	);
	
};
//----------------------------------------------------------------------------]
