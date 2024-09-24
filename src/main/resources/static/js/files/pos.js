//*****************************************************************************
// --Pos.constants--
// Pos.constants.setInfo
// --Pos.init--
// Pos.init.init()
// --Pos.common--
// Pos.common.refreshMainMenu()
// Pos.common.showProductSearch(title, callBackFunctionName, storeId)
// Pos.common.showUserPasswordCheck(callBackFunctionName)
// Pos.common.showCheckRegisterOpen(callBackFunction)
// Pos.common.showCheckRegisterClose(callBackFunction)
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************
try{
	Pos;
}catch(e){
	Pos = {};
}

(function($pos) {
	
//*****************************************************************************
// common constants define
//*****************************************************************************
	
	if($pos.constants) { 
		return $pos.constants; 
	}	
	
	$pos.constants = {
		setInfo : {},
		/** 大分類選択する時のattach func */
		bidClickAttach : {
			callBackFunc : null,
			ctrlClass : null,
			call : function() {
				if (Pos.constants.bidClickAttach.callBackFunc != null) {
					Pos.constants.bidClickAttach.callBackFunc(Pos.constants.bidClickAttach.ctrlClass);
				}
			}
		},
		/** 中分類選択する時のattach func */
		ctgryClickAttach : {
			callBackFunc : null,
			ctrlClass : null,
			call : function() {
				if (Pos.constants.ctgryClickAttach.callBackFunc != null) {
					Pos.constants.ctgryClickAttach.callBackFunc(Pos.constants.ctgryClickAttach.ctrlClass);
				}
			}
		},
		/** 小分類選択する時のattach func */
		kindClickAttach : {
			callBackFunc : null,
			ctrlClass : null,
			call : function() {
				if (Pos.constants.kindClickAttach.callBackFunc != null) {
					Pos.constants.kindClickAttach.callBackFunc(Pos.constants.kindClickAttach.ctrlClass);
				}
			}
		},
		
		/** レシート写真解析時のattach func */
		receiptCallBackAttach : {
			callBackFunc : null,
			ctrlClass : null,
			call : function() {
				if (Pos.constants.receiptCallBackAttach.callBackFunc != null) {
					Pos.constants.receiptCallBackAttach.callBackFunc(Pos.constants.receiptCallBackAttach.ctrlClass);
				}
			}
		},
		
		bidTableList : null,
		ctgryTableList : null,
		kindTableList : null,
		storeManage : null,
		showImgOrgObj : null,
		productManage : null,
		
		accountManage : null,
		payContactManage : null,
		familyBudgetManage : null,
		familyBudgetBillManage : null,
		familyBudgetBillDetail: null,
		
		receiptImgAnalysisList : null,
	}
	
//*****************************************************************************
// common init define
//*****************************************************************************
	
	if($pos.init) { 
		return $pos.init; 
	}	
	
	$pos.init = {
			
		init : function(jsInfo){
			Pos.init.setJavaScriptSetInfo(jsInfo);
			Pos.init._initConstants();
			Pos.init._initRootPath();
		},
		
		_initConstants : function(){
			
			//http status msg init
			ShaConstants.constants.HTTP_STATUS_400_MSG 				= Pos.constants.setInfo.httpStatus.httpStatus400;
			ShaConstants.constants.HTTP_STATUS_403_MSG 				= Pos.constants.setInfo.httpStatus.httpStatus403;
			ShaConstants.constants.HTTP_STATUS_404_MSG 				= Pos.constants.setInfo.httpStatus.httpStatus404;
			ShaConstants.constants.HTTP_STATUS_405_MSG 				= Pos.constants.setInfo.httpStatus.httpStatus405;
			ShaConstants.constants.HTTP_STATUS_500_MSG 				= Pos.constants.setInfo.httpStatus.httpStatus500;
			ShaConstants.constants.HTTP_STATUS_500_ACCESS_DENY_MSG  = Pos.constants.setInfo.httpStatus.httpStatus500AccessDeny;

			//check msg init
			ShaConstants.constants.NOT_BLANK_MSG 	= Pos.constants.setInfo.common.checkBlankMsg;
			ShaConstants.constants.NUMBER_ONLY_MSG 	= Pos.constants.setInfo.common.checkNumberOnlyMsg;
			ShaConstants.constants.MAX_LENGTH_MSG 	= Pos.constants.setInfo.common.checkMaxLengthMsg;
			ShaConstants.constants.MIN_LENGTH_MSG 	= Pos.constants.setInfo.common.checkMinLengthMsg;
			ShaConstants.constants.MAX_VALUE_MSG 	= Pos.constants.setInfo.common.checkMaxNumberMsg;
			ShaConstants.constants.MIN_VALUE_MSG 	= Pos.constants.setInfo.common.checkMinNumberMsg;
			ShaConstants.constants.REQUIRED_NUMBER_VAL_MSG 	= Pos.constants.setInfo.common.checkRequiredNumberValMsg;
			
			ShaConstants.constants.MAX_BILL_AMOUNT 	= Pos.constants.setInfo.common.max_bill_amount;
			ShaConstants.constants.MIN_BILL_AMOUNT 	= Pos.constants.setInfo.common.min_bill_amount;
			ShaConstants.constants.IPHONE_NUMBER_MSG= Pos.constants.setInfo.common.checkPhoneNumberMsg;
			ShaConstants.constants.EMAIL_MSG 	    = Pos.constants.setInfo.common.checkEmailMsg;
			ShaConstants.constants.CREDIT_CODE_MSG 	    = Pos.constants.setInfo.common.checkCreditCodeMsg;
			ShaConstants.constants.IPHONE_NUMBER_EXISTED_MSG= Pos.constants.setInfo.common.checkPhoneNumberExistedMsg;
			ShaConstants.constants.IPHONE_NUMBER_NOT_EXISTED_MSG= Pos.constants.setInfo.common.checkPhoneNumberNotExistedMsg;
			ShaConstants.constants.EMAIL_EXISTED_MSG= Pos.constants.setInfo.common.checkEmailExistedMsg;
			ShaConstants.constants.AUTHCODE_ERROR_MSG= Pos.constants.setInfo.common.checkAuthCodeErrorMsg;
			ShaConstants.constants.USER_CODE_EXISTED_MSG= Pos.constants.setInfo.common.checkUserCodeExistedMsg;
			ShaConstants.constants.USER_CODE_MSG= Pos.constants.setInfo.common.checkUserCodeMsg;
			//date time format
			ShaConstants.constants.DATETIME_FORMAT = Pos.constants.setInfo.common.datetimeFormat;
			
			var days = Pos.constants.setInfo.common.dayTypeName.split(',');
			ShaConstants.constants.DAYTYPE_DAYNAME = days;
			
		},
		//------------------------------------------------------------------------------
		// initialize [trigger] element
		//------------------------------------------------------------------------------	
		_initRootPath : function() {
			ShaConstants.constants.ROOT_PATH = Pos.constants.setInfo.common.urlBasePath;
		},
		
		setJavaScriptSetInfo : function(jsInfo){
			Pos.constants.setInfo = 
				ShaUtil.json.parseStringToJson(jsInfo);
		}
	}
	
//*****************************************************************************
// common user util define
//*****************************************************************************
	
	if($pos.common) { 
		return $pos.common; 
	}	
	
	$pos.common = {
		//------------------------------------------------------------------------------
		// refreshMenu
		//------------------------------------------------------------------------------	
		refreshMainMenu : function() {
			ShaRestful.restful.get(Pos.constants.setInfo.jsView.menu.url_init);
		},	
		//------------------------------------------------------------------------------
		// showMailAddressSelect
		//------------------------------------------------------------------------------	
		showMailAddressSelect : function(callBackFunctionName) {
			var postData = ShaUtil.json.create();
		    ShaUtil.json.setJsonData(postData, 
		    		Pos.constants.setInfo.jsView.commonMailAddressSelect.param_callbackfunctionname, 
		    		callBackFunctionName);

			ShaAjax.pop.postSubSubSubDialogLargeCenter(
				Pos.constants.setInfo.jsView.commonMailAddressSelect.btn_manage,
				Pos.constants.setInfo.jsView.commonMailAddressSelect.url_init, 
				postData
            );
		},
		
		//------------------------------------------------------------------------------
		// showUserPasswordCheck
		//------------------------------------------------------------------------------	
		showUserPasswordCheck : function(callBackFunctionName) {
			$postData = ShaUtil.json.create();
	    	/*
			ShaUtil.json.setJsonData($postData, 'callBackFunctionName', callBackFunctionName);
			ShaAjax.pop.postSubDialogMiddleCenter(
					Pos.constants.setInfo.jsView.commonUserPasswordCheck.title_passwordCheck,
					Pos.constants.setInfo.jsView.commonUserPasswordCheck.url_check_init, 
					$postData);
			*/
			ShaUtil.util.executeFunctionByName(callBackFunctionName, $postData);
		},
		
		//------------------------------------------------------------------------------
		// showSendMail
		//------------------------------------------------------------------------------	
		showSendMail : function(callBackFunctionName) {
			var postData = ShaUtil.json.create();
		    ShaUtil.json.setJsonData(postData, 
		    		Pos.constants.setInfo.jsView.commonSendMail.param_callbackfunctionname, 
		    		callBackFunctionName);

			ShaAjax.pop.postSubSubDialogLargeCenter(
				Pos.constants.setInfo.jsView.commonSendMail.btn_add,
				Pos.constants.setInfo.jsView.commonSendMail.url_add_init, 
				postData
            );
		},
		
		showSendMailByUserId : function(callBackFunctionName, selectUserIds) {
			var postData = ShaUtil.json.create();
		    ShaUtil.json.setJsonData(postData, 
		    		Pos.constants.setInfo.jsView.commonSendMail.param_callbackfunctionname, 
		    		callBackFunctionName);
		    ShaUtil.json.setJsonData(postData, 
		    		Pos.constants.setInfo.jsView.commonSendMail.param_userIds, 
		    		selectUserIds);
			ShaAjax.pop.postSubSubDialogLargeCenter(
				Pos.constants.setInfo.jsView.commonSendMail.btn_add,
				Pos.constants.setInfo.jsView.commonSendMail.url_add_init, 
				postData
            );
		},
	}
	
	
	
})(Pos);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************
	
});
