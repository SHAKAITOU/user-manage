try{
	ShaChart;
}catch(e){
	ShaChart = {};
}

(function($shaChart) {
	
	//*****************************************************************************
	// common define
	//*****************************************************************************
	
	if($shaChart.common) { 
		return $shaChart.common; 
	}	
	
	
	$shaChart.common = {
		initD3Tip : function() {
			d3.select(".d3-tip").remove();
		},
		createD3Tip : function(getHtml, offSet) {
			return d3.tip().attr("class", "d3-tip")
						.offset(offSet)
						.html(function(d) {
							return getHtml(d);
						});
		}
	}
	
	//*****************************************************************************
	// barChart define
	//*****************************************************************************
	
	if($shaChart.chart) {
		return $shaChart.chart;
	}
	
	$shaChart.chart = {

		//------------------------------------------------------------------------------
		// draw a simple bar chart
		//------------------------------------------------------------------------------
		simpleBarChart : function (divCanvasId, svgWidth, svgHeight, data, 
									getName, getValue, numberTip, barColor,
									title, xTitle) {
			
			d3.select(divCanvasId).select("svg").remove();
			var svg = d3.select(divCanvasId).append("svg");

			var margin = {top: 20, right: 30, bottom: 40, left: 30};
			var width = svgWidth - margin.left - margin.right;
			var height = svgHeight - margin.top - margin.bottom;
			
			var x = d3.scaleBand().rangeRound([0, width]).paddingInner(0.1);
			
			var y = d3.scaleLinear().range([height, 0]);
			
			var chart = svg.attr("width", svgWidth)
			    .attr("height", svgHeight)
			  	.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			
			//add title
			
			chart.append("text")
			.attr("x", (width / 2))
			.attr("y", (margin.top / 2))
			.attr("text-anchor", "middle")  
			.style("font-size", "16px") 
			.style("font-weight", "bold")
			.text(title);
			
			//bottom comment
			chart.append("text")
			.attr("x", width)
			.attr("y", height + margin.top + 10)
			.attr("text-anchor", "end")  
			.style("font-size", "12px") 
			.text(xTitle);
			
			var chartTopMargin = 5;
			x.domain(data.map(function(d) { return getName(d); }));
			y.domain([0, d3.max(data, function(d) { return getValue(d); })+chartTopMargin]);
			
			chart.call(numberTip);
			
			var bar = chart.selectAll("g")
				.data(data)
				.enter().append("g")
				.attr("transform", function(d) { return "translate(" + x(getName(d)) + ",0)"; });
			
			bar.append("rect")
			.attr("y", function(d) { return y(getValue(d)); })
			.attr("width",x.bandwidth())
			.attr("class", "bar")
			.attr("fill", 
					(barColor instanceof Array) ? function(d, i) { return barColor[i]; } : barColor)
			.on('mouseover', numberTip.show)
			.on('mouseout', numberTip.hide)
			.attr("height", 0)
					.transition()
					.duration(400)
					.delay(function(d) { return height - y(getValue(d)); })
			.attr("height", function(d) { return height - y(getValue(d)); });
			
			bar.append("text")
			.attr("x", x.bandwidth() / 2)
			.attr("y", function(d) { return y(getValue(d)) + 3; })
			.attr("dy", ".71em")
			.text(function(d) { return ""; });
			
			var xAxis = d3.axisBottom().scale(x);
			var yAxis = d3.axisLeft().scale(y);
			
			chart.append("g")
			.attr("class", "axis")
			.attr("transform", "translate(0," + height + ")")
			.call(xAxis);
			
			chart.append("g")
			.attr("class", "axis")
			.call(yAxis)
			.append("text")
			.attr("transform", "rotate(90)")
			.attr("x", height-10)
			.attr("y", y(y.ticks().pop()) + 25)
			.attr("dy", "0.32em")
			.attr("fill", "#000")
			.attr("font-weight", "bold")
			.attr("text-anchor", "start")
			//.text("回数")
			;
		},
	
		//------------------------------------------------------------------------------
		// draw a group bar chart
		//------------------------------------------------------------------------------
		groupBarChart : function(divCanvasId, svgWidth, svgHeight, data, 
								rangeColor, keys, names, numberTip,
								title, xTitle) {
			
			d3.select(divCanvasId).select("svg").remove();
			var svg = d3.select(divCanvasId).append("svg");
			
		    var margin = {top: 30, right: 30, bottom: 40, left: 40};
		    var width = svgWidth - margin.left - margin.right;
		    var height = svgHeight - margin.top - margin.bottom;
			var legendTopMargin = 20;
			
			var x0 = d3.scaleBand().rangeRound([0, width]).paddingInner(0.1);
		
			var x1 = d3.scaleBand().padding(0.05);
		
			var y = d3.scaleLinear().range([height, 0]);
			
			var z = d3.scaleOrdinal().range(rangeColor);
		
			data.forEach(function(d) {
				d.numbers = keys.map(function(name) { return {name: name, value: +d[name]}; });
			});
			

			x0.domain(data.map(function(d) { return d.numberName; }));
			x1.domain(keys).rangeRound([0, x0.bandwidth()]);
			y.domain([0, d3.max(data, function(d) { return d3.max(d.numbers, function(d) { return d.value; }); })+legendTopMargin]);
		
			//get a chart
			var chart =svg.attr("width", svgWidth)
							.attr("height", svgHeight)
							.append("g")
					  			.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			
			//add title
			
			chart.append("text")
					.attr("x", (width / 2))
					.attr("y", (margin.top / 2))
					.attr("text-anchor", "middle")  
					.style("font-size", "16px") 
					.style("font-weight", "bold")
					.text(title);
			
			//bottom comment
			chart.append("text")
					.attr("x", width)
					.attr("y", height + margin.top + 5)
					.attr("text-anchor", "end")  
					.style("font-size", "12px") 
					.text(xTitle);
			
			//append bar in chart
			var bar =
				chart.append("g")
					.selectAll("g")
						//bond data
						.data(data)
						.enter().append("g")
							.attr("transform", function(d) { return "translate(" + x0(d.numberName) + ",0)"; });
			
			//active tooptip

			chart.call(numberTip);
			
			bar.selectAll("rect")
				.data(function(d) { return d.numbers; })
				.enter().append("rect")
					.attr("class", "bar")
					.attr("x", function(d) { return x1(d.name); })
					.attr("y", function(d) { return y(d.value); })
					.attr("width", x1.bandwidth())
					.attr("fill", function(d) { return z(d.name); })
					.on('mouseover', numberTip.show)
					.on('mouseout', numberTip.hide)
					.attr("height", 0)
					.transition()
					.duration(400)
					.delay(function(d) { return height - y(d.value); })
					.attr("height", function(d) { return height - y(d.value); });
			
			
			
			//append x axis
			chart.append("g")
				.attr("class", "axis")
				.attr("transform", "translate(0," + height + ")")
				.call(d3.axisBottom(x0));
		
			//append y axis
			chart.append("g")
				.attr("class", "axis")
				.call(d3.axisLeft(y).ticks(null, "s"))
				.append("text")
				    .attr("x", 2)
				    .attr("y", y(y.ticks().pop()) + 0.5)
				    .attr("dy", "0.32em")
				    .attr("fill", "#000")
				    .attr("font-weight", "bold")
				    .attr("text-anchor", "start")
				    .text("");
			
			//append legend
			var legend = 
				chart.append("g")
					.attr("font-family", "sans-serif")
					.attr("font-size", 10)
					.attr("text-anchor", "end")
					.selectAll("g")
						.data(names.slice())
						.enter().append("g")
							.attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
		
			var yLegend = 0;
		
			legend.append("rect")
					.attr("x", width - 19)
					.attr("y", yLegend)
					.attr("width", 19)
					.attr("height", 19)
					.attr("fill", z);
		
			legend.append("text")
					.attr("x", width - 24)
					.attr("y", yLegend+9.5)
					.attr("dy", "0.32em")
					.text(function(d) { return d; });	
		},
		
		//------------------------------------------------------------------------------
		// draw a pie chart
		//------------------------------------------------------------------------------
		pieChart : function(divCanvasId, svgWidth, svgHeight, data,
								getName, getValue, numberTip, rangeColor, 
								title) {
			
			var margin = {top: 20, right: 30, bottom: 40, left: 30};
			
			var canvas = d3.select(divCanvasId);
			canvas.select("svg").remove();
			var svg = canvas.append("svg");
			
			//draw pie
			var outerRadius = (svgHeight - margin.top - margin.bottom)/2;

			// This will create <path> elements for us using arc data...
		    var arc = d3.arc()
		    			.innerRadius(0)
		    			.outerRadius(outerRadius);

		    var pie = d3.pie() //this will create arc data for us given a list of values
						      .value(function(d, i) { return getValue(data[i]); }) // Binding each value to the pie
						      .sort( function(a, b) { if(a.index > b.index) {return true;} else {return false;} });

			
			var chart = svg.attr("width", svgWidth)
						    .attr("height", svgHeight)
						  	.append("g").attr("transform", "translate(" + outerRadius*1.1 + "," + outerRadius*1.1 + ")");
			
			//active tooptip
			chart.call(numberTip);
			
			var arcs = chart.selectAll("g")
		      				.data(pie(data))
		      				.enter()
		      				.append("g");
			
			arcs.append("path")
					.attr("class", "bar")
		      		.attr("fill", function(d, i) { return rangeColor[i]; } )
		      		.attr("d", arc)
		      		.on('mouseover', numberTip.show)
					.on('mouseout', numberTip.hide);

		},
		
		//------------------------------------------------------------------------------
		// draw a legend table chart
		//------------------------------------------------------------------------------
		legendTableChart : function(divCanvasId, svgWidth, data,
									colSetting, numberTip, rangeColor) {
			// create table for legend.
			d3.select(divCanvasId).select("table").remove();
			var legend = d3.select(divCanvasId).append("table").attr('class','legend');
	       
			var trh = legend.append("tHeader").append("tr");
			trh.append("td")
					.attr("class", "td-t2s text-center")
					.attr("width", 40)
					.text("色");
			for(i=0; i<colSetting.length; i++) {
				trh.append("td")
					.attr("class", "td-t2s "+colSetting[i][3])
					.attr("width", colSetting[i][2])
					.text(colSetting[i][1]);
			}
			// create one row per segment.
			var tr = legend.append("tbody").selectAll("tr").data(data).enter().append("tr");
	            
			// create the first column for each segment.
			tr.append("td")
				.attr("width", 40)
				.attr("class", "text-center")
				.append("svg").attr("width", '16').attr("height", '16').append("rect")
				.attr("class", "bar")
				.attr("width", '16').attr("height", '16')
				.attr("fill",function(d, i){ return rangeColor[i]; });
	            
	        // create the second column for each segment.
			for(i=0; i<colSetting.length; i++) {
				tr.append("td")
					.attr("width", colSetting[i][2])
					.attr("class", colSetting[i][3])
					.text(function(d){ return colSetting[i][0](d);});
			}
		},
		
		ganttChart : function(divCanvasId, tasks, taskTypes, taskStatus, tickFormat){
			  var FIT_TIME_DOMAIN_MODE = "fit";
			  var FIXED_TIME_DOMAIN_MODE = "fixed";

			  var margin = {
			    top : 20,
			    right : 40,
			    bottom : 20,
			    left : 150
			  };
			  var timeDomainStart = d3.timeDay.offset(new Date(),-3);
			  var timeDomainEnd = d3.timeHour.offset(new Date(),+3);
			  var timeDomainMode = FIT_TIME_DOMAIN_MODE;// fixed or fit
			  var height = 400 - margin.top - margin.bottom-5;
			  var width = 600 - margin.right - margin.left-5;

			  var keyFunction = function(d) {
			    return d.startDate + d.taskName + d.endDate;
			  };

			  var rectTransform = function(d) {
			    return "translate(" + x(d.startDate) + "," + y(d.taskName) + ")";
			  };

			  var x,y,xAxis,yAxis;
			  
			  // initAxis
			  x = d3.scaleTime().domain([ timeDomainStart, timeDomainEnd ]).range([ 0, width ]).clamp(true);

			    y = d3.scaleBand().domain(taskTypes).rangeRound([ 0, height - margin.top - margin.bottom ], .1);

			    xAxis = d3.axisBottom().scale(x).tickFormat(d3.timeFormat(tickFormat))
			      .tickSize(8).tickPadding(8);

			    yAxis = d3.axisLeft().scale(y).tickSize(0);
			// initTimeDomain
		    if (timeDomainMode === FIT_TIME_DOMAIN_MODE) {
		        if (tasks === undefined || tasks.length < 1) {
		          timeDomainStart = d3.time.day.offset(new Date(), -3);
		          timeDomainEnd = d3.time.hour.offset(new Date(), +3);
		          return;
		        }
		        tasks.sort(function(a, b) {
		          return a.endDate - b.endDate;
		        });
		        timeDomainEnd = tasks[tasks.length - 1].endDate;
		        tasks.sort(function(a, b) {
		          return a.startDate - b.startDate;
		        });
		        timeDomainStart = tasks[0].startDate;
		      }
		    //
		    
		    var svg = d3.select(divCanvasId)
		      .append("svg")
		      .attr("class", "chart")
		      .attr("width", width + margin.left + margin.right)
		      .attr("height", height + margin.top + margin.bottom)
		      .append("g")
		      .attr("class", "gantt-chart")
		      .attr("width", width + margin.left + margin.right)
		      .attr("height", height + margin.top + margin.bottom)
		      .attr("transform", "translate(" + margin.left + ", " + margin.top + ")");

		    svg.selectAll(".chart")
		      .data(tasks, keyFunction).enter()
		      .append("rect")
		      .attr("rx", 5)
		      .attr("ry", 5)
		      .attr("class", function(d){ 
		        if(taskStatus[d.status] == null){ return "bar";}
		        return taskStatus[d.status];
		      }) 
		      .attr("y", 0)
		      .attr("transform", rectTransform)
		      .attr("height", function(d) { return 70; })
		      .attr("width", function(d) { 
		        return (x(d.endDate) - x(d.startDate)); 
		      });

		      svg.append("g")
		        .attr("class", "x axis")
		        .attr("transform", "translate(0, " + (height - margin.top - margin.bottom) + ")")
		        .transition()
		        .call(xAxis);

		      svg.append("g").attr("class", "y axis").transition().call(yAxis);
		},
	}
	
	
})(ShaChart);
