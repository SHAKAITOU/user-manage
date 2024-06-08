//-------------------------------------------------------------------------------------------
//-----------------buyProductAnalysis.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
BuyProductAnalysis = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#buyProductAnalysisForm');
	
};
ShaUtil.other.inherits(BuyProductAnalysis, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
BuyProductAnalysis.prototype.ID = {
	BTN_SNAPSHOT					: 'btnSnapshot',
	
	ITEM_TAB						: 'buyProductAnalysisTab',
	
	ITEM_PANEL_TOTAL_PRICE_IN_ID	: 'buyProductAnalysis1Panel',
	ITEM_PANEL_TOTAL_QTY_ID			: 'buyProductAnalysis2Panel',

	
	ITEM_GRAPH_TOTAL_PRICE_IN_ID	: 'buyProductAnalysis1Graph_div',
	ITEM_GRAPH_TOTAL_QTY_ID			: 'buyProductAnalysis2Graph_div',

	
	BUY_PRODUCT_GRAPH_TOTAL_PRICE_IN_TO_LEFT		: 'buyProductAnalysis1ToLeft',
	BUY_PRODUCT_GRAPH_TOTAL_PRICE_IN_TO_RIGHT	: 'buyProductAnalysis1ToRight',
	BUY_PRODUCT_GRAPH_TOTAL_QTY_TO_LEFT		: 'buyProductAnalysis2ToLeft',
	BUY_PRODUCT_GRAPH_TOTAL_QTY_TO_RIGHT	: 'buyProductAnalysis2ToRight',
	
	IS_BUY_PRODUCT_MAX_INDEX_ID			: 'isBuyProductMaxIndex',
	BUY_PRODUCT_OFFSET					: 'buyProductOffset',

	SELECT_GRAPH_ID				: '',
};
//------------------------------------------]

//---------------method define--------------[
//init 
BuyProductAnalysis.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	self.setLeftRightButtonEvent(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_PRICE_IN_TO_LEFT), "minus");
	self.setLeftRightButtonEvent(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_PRICE_IN_TO_RIGHT), "add");
	self.setLeftRightButtonEvent(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_QTY_TO_LEFT), "minus");
	self.setLeftRightButtonEvent(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_QTY_TO_RIGHT), "add");
	
	ShaInput.tab.initTab(self.getForm(), self.ID.ITEM_TAB);
	self.setLeftRightButtonSts();
	//init bond event to btn
	self.initEvent();
	self.showGraph1();
	self.showGraph2();
	
	
	ShaInput.tab.activeTab(self.getForm(), self.ID.ITEM_TAB, self.dataMap.analysisData.selectTabId);
	self.ID.SELECT_GRAPH_ID = self.dataMap.analysisData.selectTabId;
};

// init event
BuyProductAnalysis.prototype.initEvent = function(){
	var self = this;
	
	self.setLeftRightButtonEvent(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_PRICE_IN_TO_LEFT), "minus");
	self.setLeftRightButtonEvent(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_PRICE_IN_TO_RIGHT), "add");
	self.setLeftRightButtonEvent(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_QTY_TO_LEFT), "minus");
	self.setLeftRightButtonEvent(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_QTY_TO_RIGHT), "add");
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SNAPSHOT), 
		function(event) {
			var selectTabId = ShaInput.tab.getActivedTab(self.getForm(), self.ID.ITEM_TAB);
			if (selectTabId === self.ID.ITEM_PANEL_TOTAL_QTY_ID) {
				self.ID.SELECT_GRAPH_ID = self.ID.ITEM_GRAPH_TOTAL_QTY_ID;
			} else {
				self.ID.SELECT_GRAPH_ID = self.ID.ITEM_PANEL_TOTAL_PRICE_IN_ID;
			}
			html2canvas(document.querySelector("#"+self.ID.SELECT_GRAPH_ID)).then(canvas=>{
				var imgageData = canvas.toDataURL("image/png");
				// a id="download" に ダウンロード設定
				var link = document.createElement("a");
				if (link.download !== undefined) { // feature detection
					// Browsers that support HTML5 download attribute
					link.setAttribute("href", imgageData.replace(/^data:image\/png/, "data:application/octet-stream"));
					link.setAttribute("download", "buyProductAnalysis_SnapShot.png");
					link.style.visibility = "hidden";
					document.body.appendChild(link);
					link.click();
					document.body.removeChild(link);
				}
			});
		}
	);
};

BuyProductAnalysis.prototype.setLeftRightButtonSts = function(){
	//keep self instance for call back
	var self = this;
	if (self.getObject(self.ID.BUY_PRODUCT_OFFSET).val() == '0') {
		ShaInput.obj.disabled(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_PRICE_IN_TO_LEFT));
		ShaInput.obj.disabled(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_QTY_TO_LEFT));
	}
	if (self.getObject(self.ID.IS_BUY_PRODUCT_MAX_INDEX_ID).val() == 'true') {
		ShaInput.obj.disabled(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_PRICE_IN_TO_RIGHT));
		ShaInput.obj.disabled(self.getObject(self.ID.BUY_PRODUCT_GRAPH_TOTAL_QTY_TO_RIGHT));
	}
	
};

BuyProductAnalysis.prototype.setLeftRightButtonEvent = function(buttonObj, add){
	//keep self instance for call back
	var self = this;
	ShaInput.button.onClick(buttonObj, 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'offset', 
					self.getObject(self.ID.BUY_PRODUCT_OFFSET).val());
			ShaUtil.json.setJsonData(sendData, 'add', add);
			var selectTabId = ShaInput.tab.getActivedTab(self.getForm(), self.ID.ITEM_TAB);
			ShaUtil.json.setJsonData(sendData, 'selectTabId', selectTabId);
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.analysis.buyProduct_analysis_title,
				self.getJsContext().jsView.analysis.url_buyProduct_analysis_init, 
				sendData
	        );
		}
	);
};

BuyProductAnalysis.prototype.showGraph1 = function(){
	
	//keep self instance for call back
	var self = this;
	var productName = self.dataMap.analysisData.productName;
	var amount = self.dataMap.analysisData.totalPriceIn;

	self.showGraph(productName, amount, 
			self.getJsContext().jsView.analysis.label_buyProduct_total_price_in,
			self.ID.ITEM_GRAPH_TOTAL_PRICE_IN_ID,
			self.getJsContext().jsView.analysis.buyProduct_analysis_title);
};

BuyProductAnalysis.prototype.showGraph2 = function(){
	
	//keep self instance for call back
	var self = this;
	var productName = self.dataMap.analysisData.productName;
	var amount = self.dataMap.analysisData.totalQty;

	self.showGraph(productName, amount, 
			self.getJsContext().jsView.analysis.label_buyProduct_total_qty,
			self.ID.ITEM_GRAPH_TOTAL_QTY_ID,
			self.getJsContext().jsView.analysis.buyProduct_analysis_title);
};

BuyProductAnalysis.prototype.showGraph = function(
		productName, amount,
		dataLabel, graphId, graphTitle){

	var leftBgColors = [];
	var leftBColors = [];
	var rightBgColors = [];
	var rightBColors = [];
	for(n=0; n<productName.length; n++) {
		leftBgColors.push(ShaColor.htmlColor.categorys[n%ShaColor.htmlColor.categorys.length].rgbA);
		leftBColors.push(ShaColor.htmlColor.categorys[n%ShaColor.htmlColor.categorys.length].rgbB);
		rightBgColors.push(ShaColor.htmlColor.categorys[n%ShaColor.htmlColor.categorys.length].rgbA);
		rightBColors.push(ShaColor.htmlColor.categorys[n%ShaColor.htmlColor.categorys.length].rgbB);
	}
	
	var subproductName = [];
	for(n=0; n<productName.length; n++) {
		if (productName[n].length > 3) {
			subproductName.push(productName[n].substring(0, 3) + "...");
		} else {
			subproductName.push(productName[n]);
		}
	}
	var horizontalBarChartData = {
		labels: subproductName,
		fullLabels:productName,
		datasets: [{
			label: dataLabel,
			backgroundColor: leftBgColors,
			borderColor: leftBColors,
			borderWidth: 1,
			data: amount
		}]

	};
	

	var ctx = document.getElementById(graphId).getContext('2d');
	var chart = new Chart(ctx, {
		type: 'bar', //'horizontalBar',
		data: horizontalBarChartData,
		width:600,
		options: {
			title: {
				display: true,
				text: graphTitle + '--' + dataLabel
			},
			tooltips: {
				mode: 'index',
				intersect: false,
				callbacks: {
					title: function(tooltipItem, data) {
						return data.fullLabels[tooltipItem[0].index];
					}
				}
			},
			responsive: true,
			legend: {
		        position: 'right',
		        display: false
		    },
			maintainAspectRatio: false,
			scales: {
				xAxes: [{
					gridLines: {
	        			display: true,
	        			color: ShaColor.htmlColor.grids[0].rgbB,
	                    borderDash: [1, 5],
	        		},
	        		ticks: {
	                    beginAtZero:true,
	                    min: 0,
	                    stepSize: 5,
	                },
	        		scaleLabel: {
	        			display: false,
	        			labelString: dataLabel,
	        			//fontColor: "red"
	        		}
				}],
				yAxes: [{
					stacked: true,
					scaleLabel: {
	        			display: false,
	        			labelString: "店舗",
	        			//fontColor: "red"
	        		}
				}]
			}
		}
	});
};

//----------------------------------------------------------------------------]
