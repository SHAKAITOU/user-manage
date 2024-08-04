//-------------------------------------------------------------------------------------------
//-----------------MessageDetail.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
UserReview = function(dataMap){
    this.mainForm = $('#main_form');
    this.menuForm = $('#menu_form');
    this.form = $('#UserReviewForm');
    this.jsContext = Pos.constants.setInfo;
    this.i18n = JSON.parse(this.jsContext.i18n);
    this.dataMap = dataMap;
};
ShaUtil.other.inherits(UserReview, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
UserReview.prototype.ID = {
    //div
    DIV_MAINBODY             : 'mainBody',

};
//------------------------------------------]

//---------------method define--------------[
//init 
UserReview.prototype.init = function(){
    //keep self instance for call back
    var self = this;
    
    $('[data-toggle="tooltip"]').tooltip();
    
    //init bond event to btn
    self.initEvent();


};

// init event
UserReview.prototype.initEvent = function(){
    
    //keep self instance for call back
    var self = this;
	
};

//checkValue
UserReview.prototype.checkValue = function(){
	//keep self instance for call back
	var self = this;


    return false;
};
//----------------------------------------------------------------------------]
