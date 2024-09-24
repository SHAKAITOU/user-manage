package cn.caam.gs.common.enums;

public enum OnInputMode {
	/*
	 * ‌oninput事件主要支持Firefox、Chrome、Safari等非IE浏览器。‌
	oninput是HTML5的标准事件，对于检测textarea、input、input和input
	这几个元素通过用户界面发生的内容变化非常有用，在内容修改后立即被触发，与onchange事件需要在失去焦点时才触发不同。oninput事件在主流浏览器中的兼容情况良好，特别是在非IE浏览器中，如Firefox、Chrome、Safari等，提供了对oninput事件的支持。
	然而，需要注意的是，oninput事件并不被所有浏览器支持。特别是在早期的IE浏览器版本（如IE6、IE7、IE8）中，这些浏览器支持的是onpropertychange事件，而不是oninput。因此，在开发过程中，如果需要确保代码在各种浏览器中的兼容性，
	可能需要结合使用oninput和onpropertychange事件，或者采用其他方法来实现类似的功能。
	总的来说，虽然oninput主要在非IE浏览器中得到支持，但在实际开发中，开发者需要注意浏览器的兼容性问题，以确保用户体验的一致性和功能的可用性
	
	只能输入中文：

	οnkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5\w]/g,'')"
	
	只能输入数字：
	
	οnkeyup="value=value.replace(/[^\d]/g,'') " 
	
	只能输入数字和字母：
	
	οnkeyup="value=value.replace(/[\W]/g,'') " 
	
	JS控制只能输入小写英文和数字
	οnkeyup="value=value.replace(/[^\a-z\0-9]/g,'') "
	
	禁止输入特殊字符：
	
	onkeyup="value=value.replace(/[\W]/g,'')"
	
	οnkeyup="value=value.replace(/[^u4e00-u9fa5w]/g,'')"
	
	
	 JS 控制文本框只能输入数字
	input οnkeyup="value=value.replace(/[^0-9]/g,'')" οnpaste="value=value.replace(/[^0-9]/g,'')" oncontextmenu = "value=value.replace(/[^0-9]/g,'')"
	
	JS 控制文本框只能输入数字、小数点
	input οnkeyup="value=value.replace(/[^\0-9\.]/g,'')" οnpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')">
	
	JS 控制文本框只能输入英文
	input οnkeyup="value=value.replace(/[^\a-\z\A-\Z]/g,'')" οnpaste="value=value.replace(/[^\a-\z\A-\Z]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z]/g,'')">
	
	JS 控制文本框只能输入英文、数字
	input οnkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" οnpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')">
	
	JS 控制文本框只能输入中文
	input οnkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" οnpaste="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" oncontextmenu = "value=value.replace(/[^\u4E00-\u9FA5]/g,'')">
	
	JS 控制文本框只能输入中文、英文、数字
	input οnkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" οnpaste="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5]/g,'')">
	
	JS 控制文本框只能输入中文、英文、数字、空格
	input οnkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5\ ]/g,'')" οnpaste="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5\ ]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5\ ]/g,'')">
	
	JS 控制文本框只能输入中文、英文、数字、小数点
	input οnkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5\.]/g,'')" οnpaste="value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5\.]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9\u4E00-\u9FA5\.]/g,'')">
	
	总而言之:先在input标签里输入οnkeyup="value=value.replace(/[^\X]/g,'')" 然后在(/[\X]/g,'')里的X换成你想输入的代码就可以了,中文u4E00-u9FA5，数字0-9，英文a-z\A-Z，其它符号@、点或其它符号。也可以多个，用\隔开就行了。‌
	 */
	NUMBERS_ONLY(EnumIndex.startIndex(1), "/[^0-9]/g"),
	LETTERS_ONLY (EnumIndex.getNext(), "/[^\\a-\\z\\A-\\Z]/g"),
	NUMBERS_LETTERS (EnumIndex.getNext(), "/[^\\a-\\z\\A-\\Z0-9]/g"),
	NUMBERS_UPPERLETTERS (EnumIndex.getNext(), "/[^\\A-\\Z0-9]/g"),
	EMAIL (EnumIndex.getNext(), "/[^\\a-\\z0-9_\\.@-]/g"),
	;

    /** type. */
    private int id;
    
    private String regex;

    private OnInputMode(int id, String regex) {
        this.id = id;
        this.regex = regex;
    }
    
    public int getId() {
        return id;
    }
    
    public String getRegex() {
        return regex;
    }
    
    public OnInputMode valueOf(int id) {
    	for(OnInputMode type : OnInputMode.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}

