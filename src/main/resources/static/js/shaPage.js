//*****************************************************************************
// --ShaPage.pageLink--
// ShaPage.pageLink.initPageLink(pageLinkIdPrefix, initSetting, excuteFunc)
// --ShaPage.pageCom--
// ShaPage.pageCom.refreshOrderCnt(jsContext, menuForm, callBackFunc)
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
	
//*****************************************************************************
// common pageCom define
//*****************************************************************************	
	
	if($shaPage.pageCom) { 
		return $shaPage.pageCom; 
	}	
	
	$shaPage.pageCom = {
		//订单状态数更新
		refreshOrderCnt : function(jsContext, menuForm, callBackFunc) {
		    var ORDER_WAIT_CNT_DIV          = ".orderWaitCntDiv";
		    var ORDER_REVIEW_CNT_DIV        = ".orderReviewCntDiv";
		    var ORDER_NOT_FINISH_CNT_DIV    = ".orderNotFinishCntDiv";
			ShaAjax.ajax.get(
	            jsContext.common.orderDetail.url_get_not_finfish_cnt, 
	            null, 
	            function(data){
					$orderWaitCntDivList = menuForm.find(ORDER_WAIT_CNT_DIV);
					$orderWaitCntDivList.each(function(i, elem){
		                if (data[0] > 0) {
							$(elem).show();
						} else {
							$(elem).hide();
						}
						$(elem).html(data[0]);
					});
					
					$orderReviewCntDivList = menuForm.find(ORDER_REVIEW_CNT_DIV);
					$orderReviewCntDivList.each(function(i, elem){
		                if (data[1] > 0) {
							$(elem).show();
						} else {
							$(elem).hide();
						}
						$(elem).html(data[1]);
					});
					
					$orderNotFinishCntDivList = menuForm.find(ORDER_NOT_FINISH_CNT_DIV);
					$orderNotFinishCntDivList.each(function(i, elem){
		                if ((data[0] + data[1]) > 0) {
							$(elem).show();
						} else {
							$(elem).hide();
						}
						$(elem).html((data[0] + data[1]));
					});
					
					callBackFunc();
	            }
	        );
		}
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