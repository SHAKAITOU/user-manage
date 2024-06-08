//-------------------------------------------------------------------------------------------
//-----------------mapShow.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
MapShow = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#map_show_form');
	
};
ShaUtil.other.inherits(MapShow, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
MapShow.prototype.ID = {
	BTN_ADD_MAP		: 'btnAddMap',
};
//------------------------------------------]

//---------------method define--------------[
//init 
MapShow.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	//self.initMap();
	
	//init bond event to btn
	self.initEvent();

	//init click to view

};

// init event
MapShow.prototype.initEvent = function(){
	
	//keep self instance for call back
	var self = this;
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_ADD_MAP), 
		function(event) {
		}
	);
	
};


MapShow.prototype.initMap = function() {
    // The location of Uluru
    const uluru = { lat: -25.344, lng: 131.036 };
    // The map, centered at Uluru
    const map = new google.maps.Map(document.getElementById("googleMap"), {
      zoom: 4,
      center: uluru,
    });
    // The marker, positioned at Uluru
    const marker = new google.maps.Marker({
      position: uluru,
      map: map,
    });
  }
//----------------------------------------------------------------------------]
