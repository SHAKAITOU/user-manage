//*****************************************************************************
// --Base js controller class--
// ShaInput.keyPress.enterPress(obj, callFunc)
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

//----------------- view js controll class define ----------------------------[

//------------constructor define------------[
BaseJsController = function(form, jsContext){
	this.form = form;
	this.jsContext = jsContext;
};
//------------------------------------------]

//---------------method define--------------[
BaseJsController.prototype.getForm = function(){
	return this.form;
};

BaseJsController.prototype.getObject = function(id){
	return this.getForm().find(ShaUtil.util.convertToJqueryId(id));
};
BaseJsController.prototype.getObjectList = function(className){
	return this.getForm().find("." + className);
};
BaseJsController.prototype.getObjectInForm = function(form, id){
	return form.find(ShaUtil.util.convertToJqueryId(id));
};
BaseJsController.prototype.getObjectListInForm = function(form, className){
	return form.find("." + className);
};

BaseJsController.prototype.getJsContext = function(){
	return this.jsContext;
};

BaseJsController.prototype.getId = function(object){
	return object.attr('id');
};