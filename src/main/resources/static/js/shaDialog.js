//*****************************************************************************
// --ShaDialog.dialogs--
// ShaDialog.dialogs.
// ShaDialog.dialogs.progress(showFlg)
// ShaDialog.dialogs.alert(context)
// ShaDialog.dialogs.alertFocus(context, focusObj)
// ShaDialog.dialogs.alertWithCallBack(context, callBackOk)
// ShaDialog.dialogs.confirm(title, context, callBackOk)
// ShaDialog.dialogs.confirmClose()
// ShaDialog.dialogs.success(context)
// ShaDialog.dialogs.successWithCallBack(context, callBackOk)
// ShaDialog.dialogs.dialogSmall(title, context)
// ShaDialog.dialogs.dialogMiddle(title, context)
// ShaDialog.dialogs.dialogLarge(title, context)
// ShaDialog.dialogs.dialogFull(title, context)
// ShaDialog.dialogs.dialogSmallCenter(title, context)
// ShaDialog.dialogs.dialogMiddleCenter(title, context)
// ShaDialog.dialogs.dialogLargeCenter(title, context)
// ShaDialog.dialogs.dialogFullCenter(title, context)
// ShaDialog.dialogs.dialogClose()
// ShaDialog.dialogs.subDialogSmall(title, context)
// ShaDialog.dialogs.subDialogMiddle(title, context)
// ShaDialog.dialogs.subDialogLarge(title, context)
// ShaDialog.dialogs.subDialogFull(title, context)
// ShaDialog.dialogs.subDialogSmallCenter(title, context)
// ShaDialog.dialogs.subDialogMiddleCenter(title, context)
// ShaDialog.dialogs.subDialogLargeCenter(title, context)
// ShaDialog.dialogs.subDialogFullCenter(title, context)
// ShaDialog.dialogs.subDialogClose()
// ShaDialog.dialogs.subSubDialogSmall(title, context)
// ShaDialog.dialogs.subSubDialogMiddle(title, context)
// ShaDialog.dialogs.subSubDialogLarge(title, context)
// ShaDialog.dialogs.subSubDialogSmallCenter(title, context)
// ShaDialog.dialogs.subSubDialogMiddleCenter(title, context)
// ShaDialog.dialogs.subSubDialogLargeCenter(title, context)
// ShaDialog.dialogs.subSubDialogClose()
// ShaDialog.dialogs.subSubSubDialogSmall(title, context)
// ShaDialog.dialogs.subSubSubDialogMiddle(title, context)
// ShaDialog.dialogs.subSubSubDialogLarge(title, context)
// ShaDialog.dialogs.subSubSubDialogSmallCenter(title, context)
// ShaDialog.dialogs.subSubSubDialogMiddleCenter(title, context)
// ShaDialog.dialogs.subSubSubDialogLargeCenter(title, context)
// ShaDialog.dialogs.subSubSubDialogClose()
// ShaDialog.tooltip.boundleTooltip(obj, context)
// ShaDialog.tooltip.boundleTooltips(form)
// --------------------------------
// jQuery(function($){});  initialize when base page loading
//*****************************************************************************

try{
	ShaDialog;
}catch(e){
	ShaDialog = {};
}

(function($shaDialog) {
	
//*****************************************************************************
// common dialogs define
//*****************************************************************************
	
	if($shaDialog.dialogs) { 
		return $shaDialog.dialogs; 
	}	
	
	$shaDialog.dialogs = {
		
		_initZindex : function(){
			$('#subDialog').css('z-index', parseInt($('#dialog').css('z-index'))+2);
			$('#subSubDialog').css('z-index', parseInt($('#subDialog').css('z-index'))+2);
			$('#subSubSubDialog').css('z-index', parseInt($('#subSubDialog').css('z-index'))+2);
			$('#confirm').css('z-index', parseInt($('#subSubSubDialog').css('z-index'))+2);
			$('#alert').css('z-index', parseInt($('#confirm').css('z-index'))+2);
			$('#success').css('z-index', parseInt($('#confirm').css('z-index'))+2);
			$('#progress').css('z-index', parseInt($('#alert').css('z-index'))+2);
			$('#tooltip').css('z-index', parseInt($('#progress').css('z-index'))+2);
		},
		
		_showOverlay : function(dialogType){
			if(dialogType == 'subDialog'){
				$('#overlay-subDialog').css('z-index', parseInt($('#dialog').css('z-index'))+1);
				$('#overlay-subDialog').css('display', 'block');
			}
			else if(dialogType == 'subSubDialog'){
				$('#overlay-subSubDialog').css('z-index', parseInt($('#subDialog').css('z-index'))+1);
				$('#overlay-subSubDialog').css('display', 'block');
			}
			else if(dialogType == 'subSubSubDialog'){
				$('#overlay-subSubSubDialog').css('z-index', parseInt($('#subSubDialog').css('z-index'))+1);
				$('#overlay-subSubSubDialog').css('display', 'block');
			}
			else if(dialogType == 'confirm'){
				$('#overlay-confirm').css('z-index', parseInt($('#subSubSubDialog').css('z-index'))+1);
				$('#overlay-confirm').css('display', 'block');
			}
			else if(dialogType == 'alert'){
				$('#overlay-alert').css('z-index', parseInt($('#confirm').css('z-index'))+1);
				$('#overlay-alert').css('display', 'block');
			}
			else if(dialogType == 'success'){
				$('#overlay-success').css('z-index', parseInt($('#confirm').css('z-index'))+1);
				$('#overlay-success').css('display', 'block');
			}
			else if(dialogType == 'progress'){
				$('#overlay-progress').css('z-index', parseInt($('#alert').css('z-index'))+1);
				$('#overlay-progress').css('display', 'block');
			}
		},
		
		_closeOverlay : function(dialogType){
			if(dialogType == 'subDialog'){
				$('#overlay-subDialog').css('display', 'none');
			}
			else if(dialogType == 'subSubDialog'){
				$('#overlay-subSubDialog').css('display', 'none');
			}
			else if(dialogType == 'subSubSubDialog'){
				$('#overlay-subSubSubDialog').css('display', 'none');
			}
			else if(dialogType == 'confirm'){
				$('#overlay-confirm').css('display', 'none');
			}
			else if(dialogType == 'alert'){
				$('#overlay-alert').css('display', 'none');
			}
			else if(dialogType == 'success'){
				$('#overlay-success').css('display', 'none');
			}
			else if(dialogType == 'progress'){
				$('#overlay-progress').css('display', 'none');
			}
		},

		progress : function (showFlg) {
			ShaUtil.util.toAlignVerticalCenter($('#progress'));
			ShaDialog.dialogs._initZindex();
			if(showFlg){
				ShaDialog.dialogs._showOverlay('progress');
				return $('#progress').modal('show');
			}else{
				ShaDialog.dialogs._closeOverlay('progress');
				return $('#progress').modal('hide');
			}
		},
		alert : function (context) {
			$('#alert').find('#alertBody').html(context);
			ShaUtil.util.toAlignVerticalCenter($('#alert'));
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('alert');
			$('#alert').find('button').on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('alert');
			});
			$('#alert').modal({backdrop: 'static',keyboard: false});
			$('#alert').modal('show');
			ShaInput.sound.playSoundAlert();
			ShaUtil.other.setFocus($('#alert').find('#alertBtn'));
			return;
		},
		
		alertFocus : function (context, focusObj) {
			$('#alert').find('#alertBody').html(context);
			ShaUtil.util.toAlignVerticalCenter($('#alert'));
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('alert');
			$('#alert').find('button').on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('alert');
				focusObj.focus();
			});
			$('#alert').modal({backdrop: 'static',keyboard: false});
			$('#alert').modal('show');
			ShaInput.sound.playSoundAlert();
			ShaUtil.other.setFocus($('#alert').find('#alertBtn'));
			return;
		},
		
		alertWithCallBack : function (context, callBackOk) {
			$('#alert').find('#alertBody').html(context);
			ShaUtil.util.toAlignVerticalCenter($('#alert'));
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('alert');
			$('#alert').find('button').on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('alert');
				callBackOk();
			});
			$('#alert').modal({backdrop: 'static',keyboard: false});
			$('#alert').modal('show');
			ShaInput.sound.playSoundAlert();
			ShaUtil.other.setFocus($('#alert').find('#alertBtn'));
			return;
		},
		
		confirm : function (title, context, callBackOk, callBackNg) {
			$('#confirm').find('#confirmTitle').html(title);
			$('#confirm').find('#confirmBody').html(context);
			ShaUtil.util.toAlignVerticalCenter($('#confirm'));
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('confirm');
			$btn_obj = $('#confirm').find('#confirmBtnOk'); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('confirm');
				ShaDialog.dialogs.confirmClose();
				callBackOk();
			});
			
			if(arguments.length === 4){
				$btn_obj = $('#confirm').find('#confirmBtnNg'); 
				$btn_obj.unbind(); 
				$btn_obj.on('click', function(event) {
					event.preventDefault();
					ShaInput.sound.playSoundClick();
					ShaDialog.dialogs._closeOverlay('confirm');
					ShaDialog.dialogs.confirmClose();
					callBackNg();
				});
			}else{
				$btn_obj = $('#confirm').find("[data-dismiss='modal']"); 
				$btn_obj.unbind(); 
				$btn_obj.on('click', function(event) {
					event.preventDefault();
					ShaInput.sound.playSoundClick();
					ShaDialog.dialogs._closeOverlay('confirm');
				});
			}
			$('#confirm').modal({backdrop: 'static',keyboard: false});
			$('#confirm').modal('show');
		},
		confirmClose : function () {
			ShaInput.sound.playSoundClick();
			ShaDialog.dialogs._closeOverlay('confirm');
			$('#confirm').modal('hide');
		},
		success : function (context) {
			$('#success').find('#successBody').html(context);
			ShaUtil.util.toAlignVerticalCenter($('#success'));
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('success');
			$btn_obj = $('#success').find('button'); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('success');
			});
			$('#success').modal({backdrop: 'static',keyboard: false});
			$('#success').modal('show');
			ShaInput.sound.playSoundSuccess();
			ShaUtil.other.setFocus($('#success').find('button'));
		},
		
		successWithCallBack : function (context, callBackOk) {
			$('#success').find('#successBody').html(context);
			ShaUtil.util.toAlignVerticalCenter($('#success'));
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('success');
			$btn_obj = $('#success').find('button'); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('success');
				$('#success').modal('hide');
				callBackOk();
			});
			
			$('#success').modal({backdrop: 'static',keyboard: false});
			$('#success').modal('show');
			ShaInput.sound.playSoundSuccess();
			ShaUtil.other.setFocus($('#success').find('button'));
		},
		
		dialogSmall : function (title, context){
			//set width
			$('#dialog').find('.modal-dialog').removeClass('modal-md');
			$('#dialog').find('.modal-dialog').removeClass('modal-lg');
			$('#dialog').find('.modal-dialog').removeClass('modal-full');
			$('#dialog').find('.modal-dialog').addClass('modal-sm');
			ShaDialog.dialogs._dialog(3, title, context);
		},
		
		dialogMiddle : function (title, context){
			//set width
			$('#dialog').find('.modal-dialog').removeClass('modal-sm');
			$('#dialog').find('.modal-dialog').removeClass('modal-lg');
			$('#dialog').find('.modal-dialog').removeClass('modal-full');
			$('#dialog').find('.modal-dialog').addClass('modal-md');
			ShaDialog.dialogs._dialog(3, title, context);
		},
		
		dialogLarge : function (title, context){
			//set width
			$('#dialog').find('.modal-dialog').removeClass('modal-sm');
			$('#dialog').find('.modal-dialog').removeClass('modal-md');
			$('#dialog').find('.modal-dialog').removeClass('modal-full');
			$('#dialog').find('.modal-dialog').addClass('modal-lg');
			ShaDialog.dialogs._dialog(3, title, context);
		},
		
		dialogFull : function (title, context){
			//set width
			$('#dialog').find('.modal-dialog').removeClass('modal-sm');
			$('#dialog').find('.modal-dialog').removeClass('modal-md');
			$('#dialog').find('.modal-dialog').removeClass('modal-lg');
			$('#dialog').find('.modal-dialog').addClass('modal-full');
			ShaDialog.dialogs._dialog(3, title, context);
		},
		
		dialogSmallCenter : function (title, context){
			//set width
			$('#dialog').find('.modal-dialog').removeClass('modal-md');
			$('#dialog').find('.modal-dialog').removeClass('modal-lg');
			$('#dialog').find('.modal-dialog').removeClass('modal-full');
			$('#dialog').find('.modal-dialog').addClass('modal-sm');
			ShaDialog.dialogs._dialog(-1, title, context);
		},
		
		dialogMiddleCenter : function (title, context){
			//set width
			$('#dialog').find('.modal-dialog').removeClass('modal-sm');
			$('#dialog').find('.modal-dialog').removeClass('modal-lg');
			$('#dialog').find('.modal-dialog').removeClass('modal-full');
			$('#dialog').find('.modal-dialog').addClass('modal-md');
			ShaDialog.dialogs._dialog(-1, title, context);
		},
		
		dialogLargeCenter : function (title, context){
			//set width
			$('#dialog').find('.modal-dialog').removeClass('modal-sm');
			$('#dialog').find('.modal-dialog').removeClass('modal-md');
			$('#dialog').find('.modal-dialog').removeClass('modal-full');
			$('#dialog').find('.modal-dialog').addClass('modal-lg');
			ShaDialog.dialogs._dialog(-1, title, context);
		},
		
		dialogFullCenter : function (title, context){
			//set width
			$('#dialog').find('.modal-dialog').removeClass('modal-sm');
			$('#dialog').find('.modal-dialog').removeClass('modal-md');
			$('#dialog').find('.modal-dialog').removeClass('modal-lg');
			$('#dialog').find('.modal-dialog').addClass('modal-full');
			ShaDialog.dialogs._dialog(-1, title, context);
		},
		
		
		_dialog : function (top, title, context){
			//set width
			ShaDialog.dialogs._initZindex();
			$('#dialog').find('#dialogTitle').html(title);
			$('#dialog').find('#dialogBody').html(context);
			$btn_obj = $('#dialog').find("[data-dismiss='modal']"); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
			});
			if(top == -1){
				ShaUtil.util.toAlignVerticalCenterDialog($('#dialog'));
			}else{
				ShaUtil.util.toVerticalTopDialog(top, $('#dialog'));
			}
			$('#dialog').modal({backdrop: 'static',keyboard: false});
			$('#dialog').modal('show');
		},
		
		dialogClose : function () {
			$('#dialog').modal('hide');
		},
		
		subDialogSmall : function (title, context){
			//set width
			$('#subDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subDialog').find('.modal-dialog').removeClass('modal-full');
			$('#subDialog').find('.modal-dialog').addClass('modal-sm');
			ShaDialog.dialogs._subDialog(40, title, context);
		},
		
		subDialogMiddle : function (title, context){
			//set width
			$('#subDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subDialog').find('.modal-dialog').removeClass('modal-full');
			$('#subDialog').find('.modal-dialog').addClass('modal-md');
			ShaDialog.dialogs._subDialog(40, title, context);
		},
		
		subDialogLarge : function (title, context){
			//set width
			$('#subDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subDialog').find('.modal-dialog').removeClass('modal-full');
			$('#subDialog').find('.modal-dialog').addClass('modal-lg');
			ShaDialog.dialogs._subDialog(40, title, context);
		},
		subDialogFull : function (title, context){
			//set width
			$('#subDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subDialog').find('.modal-dialog').addClass('modal-full');
			ShaDialog.dialogs._subDialog(4, title, context);
		},
		
		subDialogSmallCenter : function (title, context){
			//set width
			$('#subDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subDialog').find('.modal-dialog').removeClass('modal-full');
			$('#subDialog').find('.modal-dialog').addClass('modal-sm');
			ShaDialog.dialogs._subDialog(-1, title, context);
		},
		
		subDialogMiddleCenter : function (title, context){
			//set width
			$('#subDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subDialog').find('.modal-dialog').removeClass('modal-full');
			$('#subDialog').find('.modal-dialog').addClass('modal-md');
			ShaDialog.dialogs._subDialog(-1, title, context);
		},
		
		subDialogLargeCenter : function (title, context){
			//set width
			$('#subDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subDialog').find('.modal-dialog').removeClass('modal-full');
			$('#subDialog').find('.modal-dialog').addClass('modal-lg');
			ShaDialog.dialogs._subDialog(-1, title, context);
		},
		
		subDialogFullCenter : function (title, context){
			//set width
			$('#subDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subDialog').find('.modal-dialog').addClass('modal-full');
			ShaDialog.dialogs._subDialog(-1, title, context);
		},
		
		_subDialog : function (top, title, context){
			//set width
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('subDialog');
			$('#subDialog').find('#subDialogTitle').html(title);
			$('#subDialog').find('#subDialogBody').html(context);
			$btn_obj = $('#subDialog').find("[data-dismiss='modal']"); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('subDialog');
			});
			if(top == -1){
				ShaUtil.util.toAlignVerticalCenterDialog($('#subDialog'));
			}else{
				ShaUtil.util.toVerticalTopDialog(top, $('#subDialog'));
			}
			$('#subDialog').modal({backdrop: 'static',keyboard: false});
			$('#subDialog').modal('show');
		},
		
		subDialogClose : function () {
			ShaDialog.dialogs._closeOverlay('subDialog');
			$('#subDialog').modal('hide');
		},

		subSubDialogSmall : function (title, context){
			//set width
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubDialog').find('.modal-dialog').addClass('modal-sm');
			ShaDialog.dialogs._subSubDialog(40, title, context);
		},
		
		subSubDialogMiddle : function (title, context){
			//set width
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubDialog').find('.modal-dialog').addClass('modal-md');
			ShaDialog.dialogs._subSubDialog(40, title, context);
		},
		
		subSubDialogLarge : function (title, context){
			//set width
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subSubDialog').find('.modal-dialog').addClass('modal-lg');
			ShaDialog.dialogs._subSubDialog(40, title, context);
		},
		
		subSubDialogSmallCenter : function (title, context){
			//set width
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubDialog').find('.modal-dialog').addClass('modal-sm');
			ShaDialog.dialogs._subSubDialog(-1, title, context);
		},
		
		subSubDialogMiddleCenter : function (title, context){
			//set width
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubDialog').find('.modal-dialog').addClass('modal-md');
			ShaDialog.dialogs._subSubDialog(-1, title, context);
		},
		
		subSubDialogLargeCenter : function (title, context){
			//set width
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subSubDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subSubDialog').find('.modal-dialog').addClass('modal-lg');
			ShaDialog.dialogs._subSubDialog(-1, title, context);
		},
		
		_subSubDialog : function (top, title, context){
			//set width
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('subSubDialog');
			$('#subSubDialog').find('#subSubDialogTitle').html(title);
			$('#subSubDialog').find('#subSubDialogBody').html(context);
			$btn_obj = $('#subSubDialog').find("[data-dismiss='modal']"); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('subSubDialog');
			});
			if(top == -1){
				ShaUtil.util.toAlignVerticalCenterDialog($('#subSubDialog'));
			}else{
				ShaUtil.util.toVerticalTopDialog(top, $('#subSubDialog'));
			}
			$('#subSubDialog').modal({backdrop: 'static',keyboard: false});
			$('#subSubDialog').modal('show');
		},
		
		subSubDialogClose : function () {
			ShaDialog.dialogs._closeOverlay('subSubDialog');
			$('#subSubDialog').modal('hide');
		},
		
		subSubSubDialogSmall : function (title, context){
			//set width
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubSubDialog').find('.modal-dialog').addClass('modal-sm');
			ShaDialog.dialogs._subSubSubDialog(40, title, context);
		},
		
		subSubSubDialogMiddle : function (title, context){
			//set width
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubSubDialog').find('.modal-dialog').addClass('modal-md');
			ShaDialog.dialogs._subSubSubDialog(40, title, context);
		},
		
		subSubSubDialogLarge : function (title, context){
			//set width
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subSubSubDialog').find('.modal-dialog').addClass('modal-lg');
			ShaDialog.dialogs._subSubSubDialog(40, title, context);
		},
		
		subSubSubDialogSmallCenter : function (title, context){
			//set width
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubSubDialog').find('.modal-dialog').addClass('modal-sm');
			ShaDialog.dialogs._subSubSubDialog(-1, title, context);
		},
		
		subSubSubDialogMiddleCenter : function (title, context){
			//set width
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-sm');
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubSubDialog').find('.modal-dialog').addClass('modal-md');
			ShaDialog.dialogs._subSubSubDialog(-1, title, context);
		},

		subSubSubDialogLargeCenter : function (title, context){
			//set width
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-md');
			$('#subSubSubDialog').find('.modal-dialog').removeClass('modal-lg');
			$('#subSubSubDialog').find('.modal-dialog').addClass('modal-lg');
			ShaDialog.dialogs._subSubSubDialog(-1, title, context);
		},
		
		_subSubSubDialog : function (top, title, context){
			//set width
			ShaDialog.dialogs._initZindex();
			ShaDialog.dialogs._showOverlay('subSubSubDialog');
			$('#subSubSubDialog').find('#subSubSubDialogTitle').html(title);
			$('#subSubSubDialog').find('#subSubSubDialogBody').html(context);
			$btn_obj = $('#subSubSubDialog').find("[data-dismiss='modal']"); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
				ShaInput.sound.playSoundClick();
				ShaDialog.dialogs._closeOverlay('subSubSubDialog');
			});
			if(top == -1){
				ShaUtil.util.toAlignVerticalCenterDialog($('#subSubSubDialog'));
			}else{
				ShaUtil.util.toVerticalTopDialog(top, $('#subSubSubDialog'));
			}
			$('#subSubSubDialog').modal({backdrop: 'static',keyboard: false});
			$('#subSubSubDialog').modal('show');
		},
		
		subSubSubDialogClose : function () {
			ShaDialog.dialogs._closeOverlay('subSubSubDialog');
			$('#subSubSubDialog').modal('hide');
		},
	}
//*****************************************************************************
// common dialogs define
//*****************************************************************************
	
	if($shaDialog.tooltip) { 
		return $shaDialog.tooltip; 
	}	
	
	$shaDialog.tooltip = {
		
		boundleTooltip : function(obj, context){
			var tooltip = $('#tooltipNoTitle');
			$('#tooltipNoTitle').css('z-index', parseInt($('#progress').css('z-index'))+2);
			ShaInput.button.onMouseOver(obj, 
				function(event) {
					var offset = obj.offset();
					var tooltipLeft = offset.left;
					var tooltipTop = offset.top + obj.outerHeight();
					tooltip.find('#tooltipNoTitlePanel').css('width', 'auto');
					tooltip.find('#tooltipNoTitlePanel').css('overflow', 'hidden');
					tooltip.find('#tooltipNoTitleBody').html(context);
					tooltip.css('top', tooltipTop);
					tooltip.css('left', tooltipLeft);
					tooltip.css('display','table');
			    }
			);
			
			ShaInput.button.onMouseOut(obj, 
				function(event) {
					tooltip.css('display','none');
			    }
			);
		},
		
		boundleTooltips : function(form){
			form.find('[data-toggle="tooltip"]').each(function(i, elem){
				ShaDialog.tooltip.boundleTooltip($(elem), $(elem).attr('data-toolip'));
			});
		},
		
		boundleMouseOverTitleTooltip : function(obj, title, context){
			var tooltip = $('#tooltip');
			$('#tooltip').css('z-index', parseInt($('#progress').css('z-index'))+2);
			ShaInput.button.onMouseOver(obj, 
				function(event) {
					var offset = obj.offset();
					var tooltipLeft = offset.left;
					var tooltipTop = offset.top + obj.outerHeight();
					tooltip.find('#tooltipTitle').html(title);
					tooltip.find('#tooltipPanel').css('width', 'auto');
					tooltip.find('#tooltipPanel').css('overflow', 'hidden');
					tooltip.find('#tooltipBody').html(context);
					tooltip.css('top', tooltipTop);
					tooltip.css('left', tooltipLeft);
					tooltip.css('display','table');
			    }
			);
			
			ShaInput.button.onMouseOut(obj, 
				function(event) {
					tooltip.css('display','none');
			    }
			);
		},
		
		boundleClickTitleTooltip : function(obj, title, context){
			var tooltip = $('#tooltip');
			$('#tooltip').css('z-index', parseInt($('#progress').css('z-index'))+2);
			ShaInput.button.onClick(obj, 
				function(event) {
					var offset = obj.offset();
					var tooltipLeft = offset.left;
					var tooltipTop = offset.top + obj.outerHeight();
					
					tooltip.find('#tooltipTitle').html(title);
					tooltip.find('#tooltipPanel').css('width', 'auto');
					tooltip.find('#tooltipPanel').css('overflow', 'hidden');
					tooltip.find('#tooltipBody').html(context);
					tooltip.css('top', tooltipTop);
					tooltip.css('left', tooltipLeft);
					tooltip.css('display','table');
			    }
			);
			
			ShaInput.button.onMouseOut(obj, 
				function(event) {
					tooltip.css('display','none');
			    }
			);
		},
	}
	
	
})(ShaDialog);

//*****************************************************************************
//Excute initialize when page loading.

//*****************************************************************************

jQuery(function($){
	
	//*****************************************
	//step0: constants init when loading
	//*****************************************

});
