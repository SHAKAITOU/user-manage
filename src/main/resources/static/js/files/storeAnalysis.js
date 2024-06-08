//-------------------------------------------------------------------------------------------
//-----------------storeAnalysis.html view js controll class define ----------------------------
//------------------------------------------------------------------------------------------[

//------------constructor define------------[
StoreAnalysis = function(dataMap){
	this.jsContext = Pos.constants.setInfo;
	this.dataMap = dataMap;
	this.form = $('#storeAnalysisForm');
	
};
ShaUtil.other.inherits(StoreAnalysis, BaseJsController);
//------------------------------------------]

//------------properties define-------------[
StoreAnalysis.prototype.ID = {
	BTN_SNAPSHOT					: 'btnSnapshot',
	
	ITEM_TAB						: 'storeAnalysisTab',
	
	ITEM_PANEL_TOTAL_PRICE_IN_ID	: 'storeAnalysis1Panel',
	ITEM_PANEL_TOTAL_QTY_ID			: 'storeAnalysis2Panel',
	ITEM_PANEL_TOTAL_TURN_ID		: 'storeAnalysis3Panel',
	ITEM_PANEL_TOTAL_PERIOD_ID		: 'storeAnalysis4Panel',

	ITEM_GRAPH_TOTAL_PRICE_IN_ID	: 'storeAnalysis1Graph_div',
	ITEM_GRAPH_TOTAL_QTY_ID			: 'storeAnalysis2Graph_div',
	ITEM_GRAPH_TOTAL_TURN_ID		: 'storeAnalysis3Graph_div',
	ITEM_GRAPH_TOTAL_PERIOD_ID		: 'storeAnalysis4Graph_div',
	
	GRAPH_TOTAL_PRICE_IN_TO_LEFT	: "storeAnalysis1ToLeft",
	GRAPH_TOTAL_PRICE_IN_TO_RIGHT	: "storeAnalysis1ToRight",		
	GRAPH_TOTAL_QTY_TO_LEFT			: "storeAnalysis2ToLeft",
	GRAPH_TOTAL_QTY_TO_RIGHT		: "storeAnalysis2ToRight",	
	GRAPH_TOTAL_TURN_TO_LEFT		: "storeAnalysis3ToLeft",
	GRAPH_TOTAL_TURN_TO_RIGHT		: "storeAnalysis3ToRight",	
	GRAPH_TOTAL_PERIOD_TO_LEFT		: "storeAnalysis4ToLeft",
	GRAPH_TOTAL_PERIOD_TO_RIGHT		: "storeAnalysis4ToRight",	
	
	IS_STORE_MAX_INDEX_ID			: 'isStoreMaxIndex',
	STORE_OFFSET					: 'storeOffset',
	IS_STORE_MAX_MONTH_ID			: 'isStoreMaxMonth',
	STORE_MAX_MONTH					: 'storeMaxMonth',

	SELECT_GRAPH_ID				: '',
};
//------------------------------------------]

//---------------method define--------------[
//init 
StoreAnalysis.prototype.init = function(){
	//keep self instance for call back
	var self = this;
	
	ShaInput.tab.initTab(self.getForm(), self.ID.ITEM_TAB);
	self.setLeftRightButtonSts();
	//init bond event to btn
	self.initEvent();
	self.showGraph1();
	self.showGraph2();
	self.showGraph3();
	self.showGraph4();
	
	
	ShaInput.tab.activeTab(self.getForm(), self.ID.ITEM_TAB, self.dataMap.analysisData.selectTabId);
	self.ID.SELECT_GRAPH_ID = self.dataMap.analysisData.selectTabId;
};

// init event
StoreAnalysis.prototype.initEvent = function(){
	var self = this;
	
	self.setLeftRightButtonEvent(self.getObject(self.ID.GRAPH_TOTAL_PRICE_IN_TO_LEFT), "minus");
	self.setLeftRightButtonEvent(self.getObject(self.ID.GRAPH_TOTAL_PRICE_IN_TO_RIGHT), "add");
	self.setLeftRightButtonEvent(self.getObject(self.ID.GRAPH_TOTAL_QTY_TO_LEFT), "minus");
	self.setLeftRightButtonEvent(self.getObject(self.ID.GRAPH_TOTAL_QTY_TO_RIGHT), "add");
	self.setLeftRightButtonEvent(self.getObject(self.ID.GRAPH_TOTAL_TURN_TO_LEFT), "minus");
	self.setLeftRightButtonEvent(self.getObject(self.ID.GRAPH_TOTAL_TURN_TO_RIGHT), "add");
	self.setLeftRightButtonEvent(self.getObject(self.ID.GRAPH_TOTAL_PERIOD_TO_LEFT), "minusMonth");
	self.setLeftRightButtonEvent(self.getObject(self.ID.GRAPH_TOTAL_PERIOD_TO_RIGHT), "addMonth");
	
	ShaInput.button.onClick(self.getObject(self.ID.BTN_SNAPSHOT), 
		function(event) {
			var selectTabId = ShaInput.tab.getActivedTab(self.getForm(), self.ID.ITEM_TAB);
			if (selectTabId === self.ID.ITEM_PANEL_TOTAL_QTY_ID) {
				self.ID.SELECT_GRAPH_ID = self.ID.ITEM_GRAPH_TOTAL_QTY_ID;
			} else if (selectTabId === self.ID.ITEM_PANEL_TOTAL_TURN_ID) {
				self.ID.SELECT_GRAPH_ID = self.ID.ITEM_GRAPH_TOTAL_TURN_ID;
			} else if (selectTabId === self.ID.ITEM_PANEL_TOTAL_PERIOD_ID) {
				self.ID.SELECT_GRAPH_ID = self.ID.ITEM_GRAPH_TOTAL_PERIOD_ID;
			} else {
				self.ID.SELECT_GRAPH_ID = self.ID.ITEM_GRAPH_TOTAL_PRICE_IN_ID;
			}
			html2canvas(document.querySelector("#"+self.ID.SELECT_GRAPH_ID)).then(canvas=>{
				var imgageData = canvas.toDataURL("image/png");
				// a id="download" に ダウンロード設定
				var link = document.createElement("a");
				if (link.download !== undefined) { // feature detection
					// Browsers that support HTML5 download attribute
					link.setAttribute("href", imgageData.replace(/^data:image\/png/, "data:application/octet-stream"));
					link.setAttribute("download", "storeAnalysis_SnapShot.png");
					link.style.visibility = "hidden";
					document.body.appendChild(link);
					link.click();
					document.body.removeChild(link);
				}
			});
		}
	);
};

StoreAnalysis.prototype.setLeftRightButtonEvent = function(buttonObj, add){
	//keep self instance for call back
	var self = this;
	ShaInput.button.onClick(buttonObj, 
		function(event) {
			var sendData = ShaUtil.json.create();
			ShaUtil.json.setJsonData(sendData, 'offset', 
					self.getObject(self.ID.STORE_OFFSET).val());
			ShaUtil.json.setJsonData(sendData, 'add', add);
			ShaUtil.json.setJsonData(sendData, 'storeMaxMonth', 
					self.getObject(self.ID.STORE_MAX_MONTH).val());
			var selectTabId = ShaInput.tab.getActivedTab(self.getForm(), self.ID.ITEM_TAB);
			ShaUtil.json.setJsonData(sendData, 'selectTabId', selectTabId);
			ShaAjax.pop.postDialogMiddleCenter(
				self.getJsContext().jsView.analysis.store_analysis_title,
				self.getJsContext().jsView.analysis.url_store_analysis_init, 
				sendData
	        );
		}
	);
};

StoreAnalysis.prototype.setLeftRightButtonSts = function(){
	//keep self instance for call back
	var self = this;
	if (self.getObject(self.ID.STORE_OFFSET).val() == '0') {
		ShaInput.obj.disabled(self.getObject(self.ID.GRAPH_TOTAL_PRICE_IN_TO_LEFT));
		ShaInput.obj.disabled(self.getObject(self.ID.GRAPH_TOTAL_QTY_TO_LEFT));
		ShaInput.obj.disabled(self.getObject(self.ID.GRAPH_TOTAL_TURN_TO_LEFT));
	}
	if (self.getObject(self.ID.IS_STORE_MAX_INDEX_ID).val() == 'true') {
		ShaInput.obj.disabled(self.getObject(self.ID.GRAPH_TOTAL_PRICE_IN_TO_RIGHT));
		ShaInput.obj.disabled(self.getObject(self.ID.GRAPH_TOTAL_QTY_TO_RIGHT));
		ShaInput.obj.disabled(self.getObject(self.ID.GRAPH_TOTAL_TURN_TO_RIGHT));
	}
	
	if (self.getObject(self.ID.IS_STORE_MAX_MONTH_ID).val() == 'true') {
		ShaInput.obj.disabled(self.getObject(self.ID.GRAPH_TOTAL_PERIOD_TO_RIGHT));
	}
};

StoreAnalysis.prototype.showGraph1 = function(){
	
	//keep self instance for call back
	var self = this;
	var storeName = self.dataMap.analysisData.storeName;
	var amount = self.dataMap.analysisData.totalPriceIn;

	self.showGraph(storeName, amount, 
			self.getJsContext().jsView.analysis.label_total_price_in,
			self.ID.ITEM_GRAPH_TOTAL_PRICE_IN_ID,
			self.getJsContext().jsView.analysis.store_analysis_title);
};

StoreAnalysis.prototype.showGraph2 = function(){
	
	//keep self instance for call back
	var self = this;
	var storeName = self.dataMap.analysisData.storeName;
	var amount = self.dataMap.analysisData.totalQty;

	self.showGraph(storeName, amount, 
			self.getJsContext().jsView.analysis.label_total_qty,
			self.ID.ITEM_GRAPH_TOTAL_QTY_ID,
			self.getJsContext().jsView.analysis.store_analysis_title);
};

StoreAnalysis.prototype.showGraph3 = function(){
	
	//keep self instance for call back
	var self = this;
	var storeName = self.dataMap.analysisData.storeName;
	var amount = self.dataMap.analysisData.totalTurn;

	self.showGraph(storeName, amount, 
			self.getJsContext().jsView.analysis.label_total_turn,
			self.ID.ITEM_GRAPH_TOTAL_TURN_ID,
			self.getJsContext().jsView.analysis.store_analysis_title);
};

StoreAnalysis.prototype.showGraph4 = function(){
	
	//keep self instance for call back
	var self = this;
	var dataLabel = [];
	var data = [];
	for(n=0; n<self.dataMap.analysisData.storeName.length; n++) {
		var set = {};
		set.label = self.dataMap.analysisData.storeName[n];
		set.data = [];
		set.backgroundColor = [];
		set.borderColor = [];
		set.borderWidth = 1;
		set.fill = false;
		for(m=0; m<self.dataMap.analysisData.totalPeriod.length; m++) {
			set.backgroundColor.push(ShaColor.htmlColor.categorys[n].rgbA);
			set.borderColor.push(ShaColor.htmlColor.categorys[n].rgbB);
			if (n == 0) {
				dataLabel.push(self.dataMap.analysisData.totalPeriod[m][n][0]);
			}
			set.data.push(self.dataMap.analysisData.totalPeriod[m][n][1]);
		}
		data.push(set);
	}

	self.showLine(dataLabel, data, 
			self.getJsContext().jsView.analysis.label_total_period,
			self.ID.ITEM_GRAPH_TOTAL_PERIOD_ID,
			self.getJsContext().jsView.analysis.store_analysis_title);
};

StoreAnalysis.prototype.showGraph = function(
		storeName, amount,
		dataLabel, graphId, graphTitle){

	var leftBgColors = [];
	var leftBColors = [];
	var rightBgColors = [];
	var rightBColors = [];
	for(n=0; n<storeName.length; n++) {
		leftBgColors.push(ShaColor.htmlColor.categorys[n].rgbA);
		leftBColors.push(ShaColor.htmlColor.categorys[n].rgbB);
		rightBgColors.push(ShaColor.htmlColor.categorys[n].rgbA);
		rightBColors.push(ShaColor.htmlColor.categorys[n].rgbB);
	}
	
	var substoreName = [];
	for(n=0; n<storeName.length; n++) {
		if (storeName[n].length > 3) {
			substoreName.push(storeName[n].substring(0, 3) + "...");
		} else {
			substoreName.push(storeName[n]);
		}
	}
	var horizontalBarChartData = {
		labels: substoreName,
		fullLabels:storeName,
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

StoreAnalysis.prototype.showLine = function(
		dataLabel, data, 
		subTitle, graphId, graphTitle){
	
	var horizontalBarChartData = {
		labels: dataLabel,
		datasets: data
	};
	

	var ctx = document.getElementById(graphId).getContext('2d');
	var chart = new Chart(ctx, {
		type: 'line', //'horizontalBar',
		data: horizontalBarChartData,
		width:600,
		options: {
			title: {
				display: true,
				text: graphTitle + '--' + subTitle
			},
			tooltips: {
				mode: 'point',
				intersect: false
			},
			stacked: false,
			responsive: true,
			legend: {
		        position: 'center',
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
					stacked: false,
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
