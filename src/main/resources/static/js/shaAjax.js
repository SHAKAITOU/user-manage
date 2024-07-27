//*****************************************************************************
// --ShaAjax.ajax-----------------
// ShaAjax.ajax.get(url, formData, callBackOk)
// ShaAjax.ajax.post(url, formData, callBackOk)
// ShaAjax.ajax.postWithUploadFile(url, formId, callBack)
// --ShaAjax.pop-------------------
// ShaAjax.pop.postDialogSmall(title, url, formData)
// ShaAjax.pop.postDialogMiddle(title, url, formData)
// ShaAjax.pop.postDialogLarge(title, url, formData)
// ShaAjax.pop.postDialogFull(title, url, formData)
// ShaAjax.pop.postDialogSmallCenter(title, url, formData)
// ShaAjax.pop.postDialogMiddleCenter(title, url, formData)
// ShaAjax.pop.postDialogLargeCenter(title, url, formData)
// ShaAjax.pop.postDialogFullCenter(title, url, formData)
// ShaAjax.pop.postSubDialogSmall(title, url, formData)
// ShaAjax.pop.postSubDialogMiddle(title, url, formData)
// ShaAjax.pop.postSubDialogLarge(title, url, formData)
// ShaAjax.pop.postSubDialogFull(title, url, formData)
// ShaAjax.pop.postSubDialogSmallCenter(title, url, formData)
// ShaAjax.pop.postSubDialogMiddleCenter(title, url, formData)
// ShaAjax.pop.postSubDialogLargeCenter(title, url, formData)
// ShaAjax.pop.postSubDialogFullCenter(title, url, formData)
// ShaAjax.pop.postSubSubDialogSmall(title, url, formData)
// ShaAjax.pop.postSubSubDialogMiddle(title, url, formData)
// ShaAjax.pop.postSubSubDialogLarge(title, url, formData)
// ShaAjax.pop.postSubSubDialogSmallCenter(title, url, formData)
// ShaAjax.pop.postSubSubDialogMiddleCenter(title, url, formData)
// ShaAjax.pop.postSubSubDialogLargeCenter(title, url, formData)
// ShaAjax.pop.postSubSubSubDialogSmall(title, url, formData)
// ShaAjax.pop.postSubSubSubDialogMiddle(title, url, formData)
// ShaAjax.pop.postSubSubSubDialogLarge(title, url, formData)
// ShaAjax.pop.postSubSubSubDialogSmallCenter(title, url, formData)
// ShaAjax.pop.postSubSubSubDialogMiddleCenter(title, url, formData)
// ShaAjax.pop.postSubSubSubDialogLargeCenter(title, url, formData)
// 
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaAjax;
}catch(e){
	ShaAjax = {};
}

(function($shaAjax) {
//*****************************************************************************
// common ajax with page refresh define
//*****************************************************************************
	
	if($shaAjax.ajax) { 
		return $shaAjax.ajax; 
	}	
	
	$shaAjax.ajax = {
			
		//------------------------------------------------------------------------------
		// execute get ajax
		//------------------------------------------------------------------------------
		get : function (url, formData, callBackOk) {
			
			ShaDialog.dialogs.progress(true);
			
			var paramData = null;
			if(arguments.length === 2){
				paramData = formData;
			}
			
			$.ajax({
				   type: "GET",
				   url: ShaUtil.util.getFullUrl(url),
				   data: paramData,
				   statusCode: {
					    200: function(data){
					    	ShaDialog.dialogs.progress(false);
					    	callBackOk(data);
					    },
					    400: function(data) {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(data.responseJSON.message);
					    },
					    403: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_403_MSG);
					    },
					    404: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_404_MSG);
					    },
					    405: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_405_MSG);
					    },
					    500: function(data){
					    	ShaDialog.dialogs.progress(false);
					    	//alert(JSON.stringify(data));
					    	if(data.responseJSON.message == 'not login, access denied!'){
					    		ShaDialog.dialogs.alertWithCallBack(
					    				ShaConstants.constants.HTTP_STATUS_500_ACCESS_DENY_MSG,
					    				function(){
					    					ShaRestful.restful.get('/logout');
					    				});
					    		
					    	}else if(data.responseText != ''){
					    		var textLength = data.responseText.length;
					    		if(textLength > 500){
					    			textLength = 500;
					    		}
					    		ShaDialog.dialogs.alert(data.responseText.substr(0, textLength));
					    	}else{
					    		var textLength = data.responseJSON.message.length;
					    		if(textLength > 500){
					    			textLength = 500;
					    		}
					    		ShaDialog.dialogs.alert(data.responseJSON.message.substr(0, textLength));
					    	}
					    }
				   },
				   timeout: 86400000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post ajax
		//------------------------------------------------------------------------------
		post : function (url, formData, callBackOk, async=true) { 
			
			ShaDialog.dialogs.progress(true);
			
			$.ajax({
				   type: "POST",
				   url: ShaUtil.util.getFullUrl(url),
				   data: formData,
				   async:async,
				   statusCode: {
					    200: function(data){
					    	ShaDialog.dialogs.progress(false);
					    	callBackOk(data);
					    },
					    400: function(data) {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(data.responseJSON.message);
					    },
					    403: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_403_MSG);
					    },
					    404: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_404_MSG);
					    },
					    405: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_405_MSG);
					    },
					    500: function(data){
					    	ShaDialog.dialogs.progress(false);
					    	//alert(JSON.stringify(data));
					    	if(data.responseJSON.message == 'not login, access denied!'){
					    		ShaDialog.dialogs.alertWithCallBack(
					    				ShaConstants.constants.HTTP_STATUS_500_ACCESS_DENY_MSG,
					    				function(){
					    					ShaRestful.restful.get('/logout');
					    				});
					    		
					    	}
					    	else if(data.responseText != ''){
					    		var textLength = data.responseText.length;
					    		if(textLength > 500){
					    			textLength = 500;
					    		}
					    		ShaDialog.dialogs.alert(data.responseText.substr(0, textLength));
					    	}else{
					    		var textLength = data.responseJSON.message.length;
					    		if(textLength > 500){
					    			textLength = 500;
					    		}
					    		ShaDialog.dialogs.alert(data.responseJSON.message.substr(0, textLength));
					    	}
					    }
				   },
				   timeout: 86400000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post ajax with upload files, return a json object
		//------------------------------------------------------------------------------
		postWithUploadFile : function (url, formId, callBackOk) { 
			
			ShaDialog.dialogs.progress(true);
			
			var formData;
			var form;
			var jqueryFormId = ShaUtil.util.convertToJqueryId(formId);
			form = $(jqueryFormId);
			formData = new FormData(form.get()[0]);
			
			/*
			var $files = form.find('input[type="file"]');
			// get file data by .prop() and set the file information to formData
		    $files.each(function(){
			    formData.append( 'file', $(this).prop("files")[0] );
		    });
		    */
		    
		    
			$.ajax({
				   type: "POST",
				   url : ShaUtil.util.getFullUrl(url),
				   data: formData,
				   enctype: 'multipart/form-data',
				   processData: false,
				   contentType: false,
				   statusCode: {
					    200: function(data){
					    	ShaDialog.dialogs.progress(false);
					    	callBackOk(data);
					    },
					    400: function(data) {
					    	alert(JSON.stringify(data));
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(data.responseJSON.message);
					    },
					    403: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_403_MSG);
					    },
					    404: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_404_MSG);
					    },
					    405: function() {
					    	ShaDialog.dialogs.progress(false);
					    	ShaDialog.dialogs.alert(ShaConstants.constants.HTTP_STATUS_405_MSG);
					    },
					    500: function(data){
					    	ShaDialog.dialogs.progress(false);
					    	//alert(JSON.stringify(data));
					    	if(data.responseJSON.message == 'not login, access denied!'){
					    		ShaDialog.dialogs.alertWithCallBack(
					    				ShaConstants.constants.HTTP_STATUS_500_ACCESS_DENY_MSG,
					    				function(){
					    					ShaRestful.restful.get('/logout');
					    				});
					    		
					    	}else if(data.responseText != ''){
					    		var textLength = data.responseText.length;
					    		if(textLength > 500){
					    			textLength = 500;
					    		}
					    		ShaDialog.dialogs.alert(data.responseText.substr(0, textLength));
					    	}else{
					    		var textLength = data.responseJSON.message.length;
					    		if(textLength > 500){
					    			textLength = 500;
					    		}
					    		ShaDialog.dialogs.alert(data.responseJSON.message.substr(0, textLength));
					    	}
					    }
				   },
				   timeout: 86400000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post ajax to main div body
		//------------------------------------------------------------------------------
		
		postToMain : function (url, formData) { 
			this.post(url,
					formData,
	        	function(resposeData) {
	        		Pos.constants.BODY_DIV.html(resposeData);
	        	});
		},
		

		//------------------------------------------------------------------------------
		// execute post ajax to main div body
		//------------------------------------------------------------------------------
		
		postToDiv : function (url, formData, divObj) { 
			this.post(url,
					formData,
	        	function(resposeData) {
					divObj.html(resposeData);
	        	});
		},
	}
	
//*****************************************************************************
// common ajax with page refresh define
//*****************************************************************************
	
	if($shaAjax.pop) { 
		return $shaAjax.pop; 
	}	
	
	$shaAjax.pop = {
		//------------------------------------------------------------------------------
		// execute post ajax, return a html context in dialog
		//------------------------------------------------------------------------------
		postDialogSmall : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.dialogSmall(title,data);
	    	});
		},
		
		postDialogMiddle : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.dialogMiddle(title,data);
	    	});
		},
		
		postDialogLarge : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.dialogLarge(title,data);
	    	});
		},
		
		postDialogFull : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.dialogFull(title,data);
	    	});
		},
		
		postDialogSmallCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.dialogSmallCenter(title,data);
	    	});
		},
		
		postDialogMiddleCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.dialogMiddleCenter(title,data);
	    	});
		},
		
		postDialogLargeCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.dialogLargeCenter(title,data);
	    	});
		},
		
		postDialogFullCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.dialogFullCenter(title,data);
	    	});
		},
		
		postSubDialogSmall : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subDialogSmall(title,data);
	    	});
		},
		
		postSubDialogMiddle : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subDialogMiddle(title,data);
	    	});
		},
		
		postSubDialogLarge : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subDialogLarge(title,data);
	    	});
		},
		
		postSubDialogFull : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subDialogFull(title,data);
	    	});
		},
		
		postSubDialogSmallCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subDialogSmallCenter(title,data);
	    	});
		},
		
		postSubDialogMiddleCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subDialogMiddleCenter(title,data);
	    	});
		},
		
		postSubDialogLargeCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subDialogLargeCenter(title,data);
	    	});
		},
		
		postSubDialogFullCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subDialogFullCenter(title,data);
	    	});
		},

		postSubSubDialogSmall : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubDialogSmall(title,data);
	    	});
		},
		
		postSubSubDialogMiddle : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubDialogMiddle(title,data);
	    	});
		},
		
		postSubSubDialogLarge : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubDialogLarge(title,data);
	    	});
		},
		
		postSubSubDialogSmallCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubDialogSmallCenter(title,data);
	    	});
		},
		
		postSubSubDialogMiddleCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubDialogMiddleCenter(title,data);
	    	});
		},
		
		postSubSubDialogLargeCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubDialogLargeCenter(title,data);
	    	});
		},
		
		postSubSubSubDialogSmall : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubSubDialogSmall(title,data);
	    	});
		},
		
		postSubSubSubDialogMiddle : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubSubDialogMiddle(title,data);
	    	});
		},
		
		postSubSubSubDialogLarge : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubSubDialogLarge(title,data);
	    	});
		},
				
		postSubSubSubDialogSmallCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubSubDialogSmallCenter(title,data);
	    	});
		},
		
		postSubSubSubDialogMiddleCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubSubDialogMiddleCenter(title,data);
	    	});
		},
		
		postSubSubSubDialogLargeCenter : function (title, url, formData) { 
			
			ShaAjax.ajax.post(url, formData, function(data){
				ShaDialog.dialogs.subSubSubDialogLargeCenter(title,data);
	    	});
		},
		
		postSubDialogMiddleCenterWithUploadFile : function (title, url, formId) { 
			ShaAjax.ajax.postWithUploadFile(url, formId, function(data){
				ShaDialog.dialogs.subDialogMiddleCenter(title,data);
	    	});
		},
	}

})(ShaAjax);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});
