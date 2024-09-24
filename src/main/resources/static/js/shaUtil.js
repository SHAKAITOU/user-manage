//*****************************************************************************
// --ShaUtil.number--
// ShaUtil.number.getRandomInt(min, max)
// ShaUtil.number.getAverageInt(numbers)
// ShaUtil.number.getPriceFromPriceIn(priceIn, taxRate, roundType, scale)
// ShaUtil.number.getPriceInFromPrice(price, taxRate, roundType, scale)
// ShaUtil.number.formatCurrency(nStr)
// ShaUtil.number.disFormatCurrency(nStr)
// --ShaUtil.util--
// ShaUtil.util.getFullUrl(url)
// ShaUtil.util.convertToJqueryId(objectId)
// ShaUtil.util.getWindowSize()
// ShaUtil.util.toAlignVerticalCenter(component)
// ShaUtil.util.toVerticalTop(top, component)
// ShaUtil.util.toAlignVerticalCenterDialog (component)
// ShaUtil.util.toVerticalTopDialog (top, component)
// ShaUtil.util.format(msg)
// ShaUtil.util.executeFunctionByNameWithContext(functionName, context /*, args */)
// ShaUtil.util.executeFunctionByName(functionName /*, args */)
// ShaUtil.util.addEnterChangeTabListenerEvent(form, withButton)
// ShaUtil.util.getInstance(className, args)
// --ShaUtil.json--
// ShaUtil.json.create()
// ShaUtil.json.setJsonData (jsonObject,key,value)
// ShaUtil.json.getJsonData (jsonObject,key)
// ShaUtil.json.parseStringToJson(strContext)
// --ShaUtil.time--
// ShaUtil.time.formatDate(date, format)
// ShaUtil.time.parseYYYYSMMSDD(dateStr)
// ShaUtil.time.showTime ()
// ShaUtil.time.showDateTime ()
// --ShaUtil.other--
// ShaUtil.other.fitFormatForDatePicker(context)
// ShaUtil.other.setFocus(obj)
// ShaUtil.other.isIpad()

// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaUtil;
}catch(e){
	ShaUtil = {};
}

(function($shaUtil) {
	
//*****************************************************************************
// common number util define
//*****************************************************************************
	
	if($shaUtil.number) { 
		return $shaUtil.number; 
	}	
	
	$shaUtil.number = {
			
		//------------------------------------------------------------------------------
		// get random int between min and max int
		//------------------------------------------------------------------------------
		getRandomInt : function (min, max) {
			return Math.floor( Math.random() * (max - min + 1) ) + min;
		},
		
		getAverageInt : function (numbers) {
			var sum = 0;
			for( var i = 0; i < numbers.length; i++ ){
				//don't forget to add the base
			    sum += parseInt( numbers[i], 10 ); //10進
			}
			var avg = Math.floor(sum/numbers.length);
			return avg;
		},
		
		triggerWinResize : function(callFun) {
			$(window).resize(function() {
				callFun();
			}).trigger("resize");
		},
		
		getPriceFromPriceIn : function(priceIn, taxRate, roundType, scale){
			if(isNaN(priceIn)){
				return 0;
			}
			
			var priceInF = parseFloat(priceIn);
			var taxRateF = parseFloat(taxRate);
			var roundTypeF = parseInt(roundType);
			var scaleF = parseInt(scale);
			
			var price = priceInF/(100+taxRateF)*100;
			for(i=0; i<scaleF; i++){
				price = price*10;
			}
			if(roundTypeF == 0){// floor
				price = Math.floor(price);
			}else if(roundTypeF == 1){
				price = Math.round(price);
			}else{
				price = Math.ceil(price);
			}
			for(i=0; i<scaleF; i++){
				price = price/10;
			}
			return price.toFixed(scaleF);
		},
		
		getPriceInFromPrice : function(price, taxRate, roundType, scale){
			if(isNaN(price)){
				return 0;
			}
			
			var priceF = parseFloat(price);
			var taxRateF = parseFloat(taxRate);
			var roundTypeF = parseInt(roundType);
			var scaleF = parseInt(scale);
			
			var priceIn = priceF*(100+taxRateF)/100;
			for(i=0; i<scaleF; i++){
				priceIn = priceIn*10;
			}
			if(roundTypeF == 0){// floor
				priceIn = Math.floor(priceIn);
			}else if(roundTypeF == 1){
				priceIn = Math.round(priceIn);
			}else{
				priceIn = Math.ceil(priceIn);
			}
			for(i=0; i<scaleF; i++){
				priceIn = priceIn/10;
			}
			return priceIn.toFixed(scaleF);
			
		},
		//------------------------------------------------------------------------------
		// format number to a format string
		//------------------------------------------------------------------------------
		formatCurrency : function(nStr) {
			return ShaUtil.number._formatCurrency(nStr, '..,,');
		},
		
		disFormatCurrency : function(nStr) {
			return nStr.replace(/,/g, '');
		},
		
		_formatCurrency : function(nStr, format) {
			var inD = format.substr(0, 1);
			if(inD == 'N'){
				inD = '';
			}
			var outD = format.substr(1, 1);
			if(outD == 'N'){
				outD = '';
			}
			var inSep = format.substr(2, 1);
			if(inSep == 'N'){
				inSep = '';
			}
			var outSep = format.substr(3, 1);
			if(outSep == 'N'){
				outSep = '';
			}
			nStr += '';
			var dpos = nStr.indexOf(inD);
			var nStrEnd = '';
			if (dpos != -1) {
				nStrEnd = outD + nStr.substring(dpos + 1, nStr.length);
				nStr = nStr.substring(0, dpos);
			}
			
			if(inSep != '') {			
				var rgx = new RegExp(inSep, 'g');
				nStr = nStr.replace(rgx, '');
			}
			
			if(outSep != '') {			
				var rgx = /(\d+)(\d{3})/;
				while (rgx.test(nStr)) {
					nStr = nStr.replace(rgx, '$1' + outSep + '$2');
				}
			}
			return nStr + nStrEnd;
			
		},
	}
//*****************************************************************************
// common number util define
//*****************************************************************************
	
	if($shaUtil.util) { 
		return $shaUtil.util; 
	}	
	
	$shaUtil.util = {
		
		//------------------------------------------------------------------------------
		// get the full url
		//------------------------------------------------------------------------------	
		getFullUrl : function (url) {
			if (url.match(ShaConstants.constants.ROOT_PATH)) {
				return url;
			} else {
				return ShaConstants.constants.ROOT_PATH+url;
			}
		},
		
		convertToJqueryId : function(objectId) {
			if(objectId != null && objectId != '') {
				var firstChar = objectId.substr(0, 1);
				if(firstChar != '#') {
					return '#'+objectId;
				} 
				
				return objectId;
			}
			return objectId;
		},
		
		//------------------------------------------------------------------------------
		// get the windown size
		//------------------------------------------------------------------------------	
		getWindowSize : function () {
			var w = window.innerWidth ? window.innerWidth: $(window).width();
			var h = window.innerHeight ? window.innerHeight: $(window).height();
			return {width : w, height : h};
		},
		//------------------------------------------------------------------------------
		// show component to vertical center and align center
		//------------------------------------------------------------------------------
		toAlignVerticalCenter : function (component) {
			var winSize = ShaUtil.util.getWindowSize();
			var dialogTop =  winSize.height/2 - component.height()/2;
			var dialogLeft = (winSize.width/2) - component.width()/2;
			component.css('top', dialogTop);
			component.css('left', dialogLeft);
		},
		
		//------------------------------------------------------------------------------
		// show component to vertical top and align center
		//------------------------------------------------------------------------------
		toVerticalTop : function (top, component) {
			var winSize = ShaUtil.util.getWindowSize();
			var dialogTop =  top;
			var dialogLeft = (winSize.width/2) - component.width()/2;
			component.css('top', dialogTop);
			component.css('left', dialogLeft);
		},
		
		//------------------------------------------------------------------------------
		// show dialog to vertical center and align center
		//------------------------------------------------------------------------------
		toAlignVerticalCenterDialog : function (component) {
			var winSize = ShaUtil.util.getWindowSize();
			var dialogLeft = (winSize.width/2) - component.width()/2;
			component.find('.modal-dialog').addClass('vertical-align-center');
			component.css('left', dialogLeft);
		},
		
		//------------------------------------------------------------------------------
		// show dialog to vertical top and align center
		//------------------------------------------------------------------------------
		toVerticalTopDialog : function (top, component) {
			var winSize = ShaUtil.util.getWindowSize();
			var dialogTop =  top;
			var dialogLeft = (winSize.width/2) - component.width()/2;
			component.find('.modal-dialog').removeClass('vertical-align-center');
			component.css('top', dialogTop);
			component.css('left', dialogLeft);
		},
		
		format : function(msg){
			var args = [];
		    for (var i = 1; i < arguments.length; i++) {
		        args[i - 1] = arguments[i];
		    }
		    return msg.replace(/\{(\d+)\}/g, function (m, k) {
		        return args[k];
		    });
		},
		
		executeFunctionByNameWithContext : function(functionName, context /*, args */){
			var args = Array.prototype.slice.call(arguments, 2);
			var namespaces = functionName.split(".");
			var func = namespaces.pop();
			for(var i = 0; i < namespaces.length; i++) {
				context = context[namespaces[i]];
			}
			return context[func].apply(context, args);
		},
		
		executeFunctionByName : function(functionName /*, args */){
			var args = Array.prototype.slice.call(arguments, 1);
			var namespaces = functionName.split(".");
			var func = namespaces.pop();
			for(var i = 0; i < namespaces.length; i++) {
				window = window[namespaces[i]];
			}
			return window[func].apply(window, args);
		},
		
		addEnterChangeTabListenerEvent : function(form, withButton){
			// ② input要素の選択。但し、ボタンとhidden型は除く。
			var oObject;
			if(withButton){
				oObject = form.find(":input:not([type=hidden])");
			}else{
				oObject = form.find(":input:not([type=button]):not([type=hidden])");
			}
			var nLength = $(oObject).length;
			var cNext = 0;
			//oObject.eq(0).focus();
			oObject.each(function(index, elem) {
				$(elem).on('keypress', function(e){
					var c = e.which ? e.which : e.keyCode; // クロスブラウザ対応
				    if (c == 13) {
				    	e.preventDefault();
				    	if (index == nLength - 1) {
			                // 最後の項目なら、最初に移動。
				    		cNext = 0;
				        }else{
					    	for(i=index;i<nLength;i++){
					    		cNext = i+1;
					            if (oObject.eq(cNext).attr("readonly") != undefined && 
					            		oObject.eq(cNext).attr("readonly") == "readonly") {
					                continue;
					            }
					            // ③ 止まってはいけいない属性 disabled
					            else if (oObject.eq(cNext).prop("disabled") != undefined &&
					            		oObject.eq(cNext).prop("disabled") == true) {
					            	continue;
					            }
					            else break;
					        }
				        }
				        oObject.eq(cNext).focus();
				    }
				});
			});
		},
		
		getInstance : function(className, args){
			var arr = className.split(".");

			var instance = (window || this);
			for (var i = 0, len = arr.length; i < len; i++) {
				instance = instance[arr[i]];
			}

			if (typeof instance !== "function") {
				throw new Error("function not found");
			}
			
			if(arguments.length === 2){
				return new instance(args);
			} else {
				return new instance();
			}
		},
	}
	
//*****************************************************************************
// common json define
//*****************************************************************************
	
	if($shaUtil.json) { 
		return $shaUtil.json; 
	}	
	
	$shaUtil.json = {
		create : function(){
			return {};
		},
		
		setJsonData : function (jsonObject,key,value){
		    jsonObject[key] = value;
		},
		
		getJsonData : function (jsonObject,key){
			return jsonObject[key];
		},
		
		parseStringToJson : function(strContext){
			return JSON.parse(strContext);
		},
		parseSerializeArrayToJson : function(serializeArrayData){
			var returnJson = {};
			for (idx = 0; idx < serializeArrayData.length; idx++) {
				returnJson[serializeArrayData[idx].name] = serializeArrayData[idx].value
			}
			return returnJson;
		},
		
	}
//*****************************************************************************
// common time util define
//*****************************************************************************
	
	if($shaUtil.time) { 
		return $shaUtil.time; 
	}	
	
	$shaUtil.time = {
		//------------------------------------------------------------------------------
		// format date to a format string
		//------------------------------------------------------------------------------
		formatDate : function(date, format) {
			format = format.replace(/yyyy/g, date.getFullYear());
			format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
			format = format.replace(/dd/g, ('0' + date.getDate()).slice(-2));
			format = format.replace(/HH/g, ('0' + date.getHours()).slice(-2));
			format = format.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
			format = format.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));
			format = format.replace(/SSS/g, ('00' + date.getMilliseconds()).slice(-3));
			format = format.replace(/(DAY)/g, ShaConstants.constants.DAYTYPE_DAYNAME[date.getDay()]);
			format = format.replace(/M/g, (date.getMonth() + 1));
			format = format.replace(/d/g, (date.getDate()));
			format = format.replace(/H/g, (date.getHours()));
			format = format.replace(/m/g, (date.getMinutes()));
			format = format.replace(/s/g, (date.getSeconds()));
			format = format.replace(/S/g, (date.getMilliseconds()));
			return format;
		},
		
		parseYYYYSMMSDD : function(dateStr) {
			var date = dateStr.split('/');
			return new Date(date[0], date[1] - 1, date[2]);
		},
		
		showTime : function (){
		    var date = new Date();
		    var time = ShaUtil.time.formatDate(date, ShaConstants.constants.DATETIME_FORMAT);
		    $('#showTime').html(time);
		    
		    setTimeout(ShaUtil.time.showTime, 1000);
		},
		showDateTime : function (){
		    var date = new Date();
		    var h = date.getHours(); // 0 - 23
		    var m = date.getMinutes(); // 0 - 59
		    var s = date.getSeconds(); // 0 - 59
		    var session = "AM";
		    
		    if(h == 0){
		        h = 12;
		    }
		    
		    if(h > 12){
		        h = h - 12;
		        session = "PM";
		    }
		    
		    h = (h < 10) ? "0" + h : h;
		    m = (m < 10) ? "0" + m : m;
		    s = (s < 10) ? "0" + s : s;
		    
		    var time = h + ":" + m + ":" + s + " " + session;
		    $('#showTime').html(time);
		    
		    setTimeout(ShaUtil.time.showTime, 1000);
		},
		
		isAfter : function(orgDtStr, trgDtStr){
			var from = orgDtStr.split("/");
			var orgDt = new Date(from[0], from[1], from[2]);
			var to = trgDtStr.split("/");
			var trgDt = new Date(to[0], to[1], to[2]);
			
			return orgDt > trgDt;
		},
	}
	
//*****************************************************************************
// common other util define
//*****************************************************************************
	
	if($shaUtil.other) { 
		return $shaUtil.other; 
	}	
	
	$shaUtil.other = {
		fitFormatForDatePicker : function(context){
			context = context.replace(/\//g, '/');
			context = context.replace(/u/g, 'y');
			context = context.replace(/M/g, 'm');
			return context;
		},
		
		setFocus : function(obj){
			setTimeout(function(){
				obj.focus();
			}, 50);
		},
		
		isIpad : function(){
			var ua = navigator.userAgent;
			return /iPad/i.test(ua) || /iPhone OS 3_1_2/i.test(ua) || /iPhone OS 3_2_2/i.test(ua);
		},
		
		isChrome : function(){
			var ua = navigator.userAgent;
			return ua.indexOf("Chrome") > -1;
		},
		
		isSafari : function(){
			var ua = navigator.userAgent;
			return ua.indexOf("Safari") > -1;
		},
				
		isIE : function(){
			var ua = window.navigator.userAgent;

		    var msie = ua.indexOf('MSIE ');
		    if (msie > 0) {
		        // IE 10 or older => return version number
		        return true;
		    }

		    var trident = ua.indexOf('Trident/');
		    if (trident > 0) {
		        // IE 11 => return version number
		        var rv = ua.indexOf('rv:');
		        return true;
		    }

		    var edge = ua.indexOf('Edge/');
		    if (edge > 0) {
		       // Edge (IE 12+) => return version number
		    	return true;
		    }

		    // other browser
		    return false;
		},
		
		inherits : function(childCtor, parentCtor){
			Object.setPrototypeOf(childCtor.prototype, parentCtor.prototype);
		},
	}

	
})(ShaUtil);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});
