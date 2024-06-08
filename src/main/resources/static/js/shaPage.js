//*****************************************************************************
// --ShaPage.pageLink--
// ShaPage.pageLink.initPageLink(pageLinkIdPrefix, initSetting, excuteFunc)
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaPage;
}catch(e){
	ShaPage = {};
}

(function($shaPage) {

//*****************************************************************************
// common pageLink define
//*****************************************************************************
	
	if($shaPage.pageLink) { 
		return $shaPage.pageLink; 
	}	
	
	$shaPage.pageLink = {
		initPageLink : function(pageLinkIdPrefix, initSetting, excuteFunc){
			var linkObject;
			linkObject = $('[id^='+pageLinkIdPrefix+']');
			linkObject.each(function(i, elem) {
				$(elem).on('click', function(event) {
					event.preventDefault();
					var doFlag = true;
					if(initSetting != null){
						doFlag = initSetting();
					}
					
					if(doFlag){
						$('#'+pageLinkIdPrefix+'Index').val(
								ShaPage.pageLink._setLinkedPageIndex($(elem).attr('id'), pageLinkIdPrefix));
						excuteFunc();
					}
				});
				
			});
		},
		_setLinkedPageIndex : function(linkedPageId, pageLinkIdPrefix){
			if(linkedPageId == (pageLinkIdPrefix+'Prev')){
				return parseInt($('#'+pageLinkIdPrefix+'Index').val()) - 1;
			}else if(linkedPageId == (pageLinkIdPrefix+'Next')){
				return parseInt($('#'+pageLinkIdPrefix+'Index').val()) + 1;
			}else{
				return parseInt(linkedPageId.replace( pageLinkIdPrefix, '' ))-1;
			}
		},
	}
})(ShaPage);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});