//*****************************************************************************
// ShaRestful.constants
// --ShaRestful.restful--
// ShaRestful.restful.get(url, parameters)
// ShaRestful.restful.post(url, formObj)
// ShaRestful.restful.postWithNewWindow(targetWindowName, url, formObj)
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaRestful;
}catch(e){
	ShaRestful = {};
}

(function($shaRestful) {
//*****************************************************************************
// common restful define
//*****************************************************************************
	
	if($shaRestful.restful) { 
		return $shaRestful.restful; 
	}	
	
	$shaRestful.restful = {
		get : function(url, parameters) {
			var paras = "";
			if(parameters != null) {
				for( var i = 0; i < parameters.length; i++ ){
					if(i == 0) {
						paras += "?" + parameters[i].name + "=" + parameters[i].value;
					} else {
						paras += "&" + parameters[i].name + "=" + parameters[i].value;
					}
				}
			}
			window.location.href = ShaUtil.util.getFullUrl(url)+paras;
			ShaDialog.dialogs.progress(true);
		},
		
		post : function(url, formObj) {
			formObj.attr('action', ShaUtil.util.getFullUrl(url));
			formObj.submit();
			ShaDialog.dialogs.progress(true);
		},
		
		postWithNewWindow : function(targetWindowName, url, formObj) {
			window.open("about:blank",targetWindowName,"");
			formObj.attr('action', ShaUtil.util.getFullUrl(url));
			formObj.target = targetWindowName;
			formObj.submit();
		},
		
		postPdf : function(url, formObj) {
			formObj.attr('action', ShaUtil.util.getFullUrl(url));
			formObj.submit();
		},
	}
	
	
})(ShaRestful);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});

