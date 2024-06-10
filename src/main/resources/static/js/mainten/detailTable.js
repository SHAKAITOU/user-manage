//-------------------------------------------------------------------------------------------
//-----------------detailTable.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
DetailTable = function(){
	this.form = $('#detailTable_form');
};
ShaUtil.other.inherits(DetailTable, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
//editData
DetailTable.prototype.ID = {
	//btn
	//table
	COL_TABLE_LIST 						: 'columnsTable',
	IDX_TABLE_LIST 						: 'indexsTable',
};
//------------------------------------------]

//---------------method define--------------[
//init 
DetailTable.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	self.initEvent();
    
    //set init focus item when page loaded
	self.initFocus();
};

// init event
DetailTable.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.COL_TABLE_LIST);
	
	ShaInput.table.adjustCellWidthToFitHead(self.getForm(), self.ID.IDX_TABLE_LIST);
		
	//override enter key press event
    ShaUtil.util.addEnterChangeTabListenerEvent(self.getForm(), true);
    
    ShaDialog.tooltip.boundleTooltips(self.getForm());
};

//initFocus
DetailTable.prototype.initFocus = function(){
	//keep self instance for call back
	var self = this;
	
};


//----------------------------------------------------------------------------]
