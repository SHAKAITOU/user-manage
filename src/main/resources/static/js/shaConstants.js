//*****************************************************************************
// ShaAjax.constants
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaConstants;
}catch(e){
	ShaConstants = {};
}

(function($shaConstants) {
	
	//*****************************************************************************
	// common constants define
	//*****************************************************************************
		
		if($shaConstants.constants) { 
			return $shaUtil.constants; 
		}	
		
		$shaConstants.constants = {
			//root path
			ROOT_PATH : '',	
			//requeat status msg
			HTTP_STATUS_400_MSG : 'HTTP 400 BAD REQUEST ERROR!',
			HTTP_STATUS_403_MSG : 'HTTP 403 PAGE NOT FOUND ERROR!',
			HTTP_STATUS_404_MSG : 'HTTP 404 PAGE NOT FOUND ERROR!',
			HTTP_STATUS_405_MSG : 'HTTP 405 WORNING METHOD ERROR!',
			HTTP_STATUS_500_ACCESS_DENY_MSG : 'not login, access denied!',
			HTTP_STATUS_500_MSG : 'HTTP 500 INTERNAL SERVER ERROR!',
			//check msg
			NOT_BLANK_MSG 		: ' must not be blank.',
			NUMBER_ONLY_MSG     : ' only can be number.',
			NOT_DUPLICATE_MSG 	: ' is duplicated.',
			MAX_LENGTH_MSG 		: ' should not longer than {0} char.',
			MIN_LENGTH_MSG		: ' should not less than {0} char.',
			
			//date time format
			DATETIME_FORMAT : 'yyyy/MM/dd HH:mm:ss',
			DAYTYPE_DAYNAME : ['SUN','MON','TUE','WED','THU','FRI','SAT'],
			
			//table td height
			TABLE_TD_HEIGHT : 30,
		}
})(ShaConstants);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});
