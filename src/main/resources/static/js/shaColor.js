try{
	ShaColor;
}catch(e){
	ShaColor = {};
}

(function($color) {
	
	//*****************************************************************************
	// html color define
	//*****************************************************************************
	
	if($color.htmlColor) { 
		return $color.htmlColor; 
	}	
	
	
	$color.htmlColor = {
			
		randomColor : function (size, colorFactory) {
			var randomColor = [];
			var min = 0;
			var max = colorFactory.length;
			var usedNumber = [];
			for(i=0; i<size; i++) {
				var rdNum;
				do {
					rdNum = ShaCommon.util.getRandomInt(min, max);
					var existFlg = false;
					for(j=0; j<usedNumber.length; j++) {
						if(usedNumber[j] == rdNum) {
							existFlg = true;
							break;
						}
					}
				}while(existFlg);
				usedNumber.push(rdNum);
				randomColor.push(colorFactory[rdNum]);
			}
			return randomColor;
		},
			
		jpColor : [
			"#fef4f4",
			"#96514d",
			"#e6b422",
			"#006e54",
			"#895b8a",
			"#fdeff2",
			"#8d6449",
			"#d9a62e",
			"#00a381",
			"#824880",
			"#e9dfe5",
			"#deb068",
			"#d3a243",
			"#38b48b",
			"#915c8b",
			"#e4d2d8",
			"#bf794e",
			"#c89932",
			"#00a497",
			"#9d5b8b",
			"#f6bfbc",
			"#bc763c",
			"#d0af4c",
			"#80aba9",
			"#7a4171",
			"#f5b1aa",
			"#b98c46",
			"#8b968d",
			"#5c9291",
			"#bc64a4",
			"#f5b199",
			"#b79b5b",
			"#6e7955",
			"#478384",
			"#b44c97",
			"#efab93",
			"#b77b57",
			"#767c6b",
			"#43676b",
			"#aa4c8f",
			"#e5abbe",
			"#956f29",
			"#726250",
			"#4c6473",
			"#a69abd",
			"#e597b2",
			"#8c7042",
			"#9d896c",
			"#455765",
			"#a89dac",
			"#e198b4",
			"#7b6c3e",
			"#94846a",
			"#44617b",
			"#9790a4",
			"#e4ab9b",
			"#d8a373",
			"#897858",
			"#393f4c",
			"#9e8b8e",
			"#e09e87",
			"#cd8c5c",
			"#716246",
			"#393e4f",
			"#95859c",
			"#d69090",
			"#cd5e3c",
			"#cbb994",
			"#203744",
			"#95949a"
		],
		
		categorys : [
			
			{hex:"#ff6384", rgbB:"rgba(255, 99, 132, 1)", rgbA:"rgba(255, 99, 132, 0.2)"},
			{hex:"#36a2eb", rgbB:"rgba(54, 162, 235, 1)", rgbA:"rgba(54, 162, 235, 0.2)"},
			{hex:"#ffce56", rgbB:"rgba(255, 206, 86, 1)", rgbA:"rgba(255, 206, 86, 0.2)"},
			{hex:"#4bc0c0", rgbB:"rgba(75, 192, 192, 1)", rgbA:"rgba(75, 192, 192, 0.2)"},
			{hex:"#fffb40", rgbB:"rgba(255, 251, 64, 1)", rgbA:"rgba(255, 251, 64, 0.2)"},
			{hex:"#9966ff", rgbB:"rgba(153, 102, 255, 1)", rgbA:"rgba(153, 102, 255, 0.2)"},
			{hex:"#ff9f40", rgbB:"rgba(255, 159, 64, 1)", rgbA:"rgba(255, 159, 64, 0.2)"},
			
			{hex:"#b2455c", rgbB:"rgba(178, 69, 92, 1)", rgbA:"rgba(178, 69, 92, 0.2)"},
			{hex:"#2674a8", rgbB:"rgba(38, 116, 168, 1)", rgbA:"rgba(38, 116, 168, 0.2)"},
			{hex:"#bc9840", rgbB:"rgba(188, 152, 64, 1)", rgbA:"rgba(188, 152, 64, 0.2)"},
			{hex:"#328282", rgbB:"rgba(50, 130, 130, 1)", rgbA:"rgba(50, 130, 130, 0.2)"},
			{hex:"#b7b42d", rgbB:"rgba(183, 180, 45, 1)", rgbA:"rgba(183, 180, 45, 0.2)"},
			{hex:"#6b47b2", rgbB:"rgba(107, 71, 178, 1)", rgbA:"rgba(107, 71, 178, 0.2)"},
			{hex:"#b7722d", rgbB:"rgba(183, 114, 45, 1)", rgbA:"rgba(183, 114, 45, 0.2)"},
			
			{hex:"#682836", rgbB:"rgba(104, 40, 54, 1)", rgbA:"rgba(104, 40, 54, 0.2)"},
			{hex:"#14405e", rgbB:"rgba(20, 64, 94, 1)", rgbA:"rgba(20, 64, 94, 0.2)"},
			{hex:"#755e27", rgbB:"rgba(117, 94, 39, 1)", rgbA:"rgba(117, 94, 39, 0.2)"},
			{hex:"#173d3d", rgbB:"rgba(23, 61, 61, 1)", rgbA:"rgba(23, 61, 61, 0.2)"},
			{hex:"#666419", rgbB:"rgba(102, 100, 25, 1)", rgbA:"rgba(102, 100, 25, 0.2)"},
			{hex:"#402a6b", rgbB:"rgba(64, 42, 107, 1)", rgbA:"rgba(64, 42, 107, 0.2)"},
			{hex:"#75491d", rgbB:"rgba(117, 73, 29, 1)", rgbA:"rgba(117, 73, 29, 0.2)"},
				
			{hex:"#ff6384", rgbB:"rgba(255, 99, 132, 1)", rgbA:"rgba(255, 99, 132, 0.4)"},
			{hex:"#36a2eb", rgbB:"rgba(54, 162, 235, 1)", rgbA:"rgba(54, 162, 235, 0.4)"},
			{hex:"#ffce56", rgbB:"rgba(255, 206, 86, 1)", rgbA:"rgba(255, 206, 86, 0.4)"},
			{hex:"#4bc0c0", rgbB:"rgba(75, 192, 192, 1)", rgbA:"rgba(75, 192, 192, 0.4)"},
			{hex:"#fffb40", rgbB:"rgba(255, 251, 64, 1)", rgbA:"rgba(255, 251, 64, 0.4)"},
			{hex:"#9966ff", rgbB:"rgba(153, 102, 255, 1)", rgbA:"rgba(153, 102, 255, 0.4)"},
			{hex:"#ff9f40", rgbB:"rgba(255, 159, 64, 1)", rgbA:"rgba(255, 159, 64, 0.4)"},

			{hex:"#b2455c", rgbB:"rgba(178, 69, 92, 1)", rgbA:"rgba(178, 69, 92, 0.4)"},
			{hex:"#2674a8", rgbB:"rgba(38, 116, 168, 1)", rgbA:"rgba(38, 116, 168, 0.4)"},
			{hex:"#bc9840", rgbB:"rgba(188, 152, 64, 1)", rgbA:"rgba(188, 152, 64, 0.4)"},
			{hex:"#328282", rgbB:"rgba(50, 130, 130, 1)", rgbA:"rgba(50, 130, 130, 0.4)"},
			{hex:"#b7b42d", rgbB:"rgba(183, 180, 45, 1)", rgbA:"rgba(183, 180, 45, 0.4)"},
			{hex:"#6b47b2", rgbB:"rgba(107, 71, 178, 1)", rgbA:"rgba(107, 71, 178, 0.4)"},
			{hex:"#b7722d", rgbB:"rgba(183, 114, 45, 1)", rgbA:"rgba(183, 114, 45, 0.4)"},

			{hex:"#682836", rgbB:"rgba(104, 40, 54, 1)", rgbA:"rgba(104, 40, 54, 0.4)"},
			{hex:"#14405e", rgbB:"rgba(20, 64, 94, 1)", rgbA:"rgba(20, 64, 94, 0.4)"},
			{hex:"#755e27", rgbB:"rgba(117, 94, 39, 1)", rgbA:"rgba(117, 94, 39, 0.4)"},
			{hex:"#173d3d", rgbB:"rgba(23, 61, 61, 1)", rgbA:"rgba(23, 61, 61, 0.4)"},
			{hex:"#666419", rgbB:"rgba(102, 100, 25, 1)", rgbA:"rgba(102, 100, 25, 0.4)"},
			{hex:"#402a6b", rgbB:"rgba(64, 42, 107, 1)", rgbA:"rgba(64, 42, 107, 0.4)"},
			{hex:"#75491d", rgbB:"rgba(117, 73, 29, 1)", rgbA:"rgba(117, 73, 29, 0.4)"},
			
			{hex:"#ff6384", rgbB:"rgba(255, 99, 132, 1)", rgbA:"rgba(255, 99, 132, 0.6)"},
			{hex:"#36a2eb", rgbB:"rgba(54, 162, 235, 1)", rgbA:"rgba(54, 162, 235, 0.6)"},
			{hex:"#ffce56", rgbB:"rgba(255, 206, 86, 1)", rgbA:"rgba(255, 206, 86, 0.6)"},
			{hex:"#4bc0c0", rgbB:"rgba(75, 192, 192, 1)", rgbA:"rgba(75, 192, 192, 0.6)"},
			{hex:"#fffb40", rgbB:"rgba(255, 251, 64, 1)", rgbA:"rgba(255, 251, 64, 0.6)"},
			{hex:"#9966ff", rgbB:"rgba(153, 102, 255, 1)", rgbA:"rgba(153, 102, 255, 0.6)"},
			{hex:"#ff9f40", rgbB:"rgba(255, 159, 64, 1)", rgbA:"rgba(255, 159, 64, 0.6)"},

			{hex:"#b2455c", rgbB:"rgba(178, 69, 92, 1)", rgbA:"rgba(178, 69, 92, 0.6)"},
			{hex:"#2674a8", rgbB:"rgba(38, 116, 168, 1)", rgbA:"rgba(38, 116, 168, 0.6)"},
			{hex:"#bc9840", rgbB:"rgba(188, 152, 64, 1)", rgbA:"rgba(188, 152, 64, 0.6)"},
			{hex:"#328282", rgbB:"rgba(50, 130, 130, 1)", rgbA:"rgba(50, 130, 130, 0.6)"},
			{hex:"#b7b42d", rgbB:"rgba(183, 180, 45, 1)", rgbA:"rgba(183, 180, 45, 0.6)"},
			{hex:"#6b47b2", rgbB:"rgba(107, 71, 178, 1)", rgbA:"rgba(107, 71, 178, 0.6)"},
			{hex:"#b7722d", rgbB:"rgba(183, 114, 45, 1)", rgbA:"rgba(183, 114, 45, 0.6)"},

			{hex:"#682836", rgbB:"rgba(104, 40, 54, 1)", rgbA:"rgba(104, 40, 54, 0.6)"},
			{hex:"#14405e", rgbB:"rgba(20, 64, 94, 1)", rgbA:"rgba(20, 64, 94, 0.6)"},
			{hex:"#755e27", rgbB:"rgba(117, 94, 39, 1)", rgbA:"rgba(117, 94, 39, 0.6)"},
			{hex:"#173d3d", rgbB:"rgba(23, 61, 61, 1)", rgbA:"rgba(23, 61, 61, 0.6)"},
			{hex:"#666419", rgbB:"rgba(102, 100, 25, 1)", rgbA:"rgba(102, 100, 25, 0.6)"},
			{hex:"#402a6b", rgbB:"rgba(64, 42, 107, 1)", rgbA:"rgba(64, 42, 107, 0.6)"},
			{hex:"#75491d", rgbB:"rgba(117, 73, 29, 1)", rgbA:"rgba(117, 73, 29, 0.6)"},
		],
		
		annotations : [
			{hex:"#c9171e", rgbB:"rgba(201, 23, 30, 1)", rgbA:"rgba(201, 23, 30, 0.6)"},
		],
		
		grids : [
			{hex:"#808080", rgbB:"rgba(128, 128, 128, 1)", rgbA:"rgba(128, 128, 128, 0.6)"},
		],
		
		ganttClass : [
			'gtaskred',
			'gtaskpurple',
			'gtaskpink',
			'gtaskyellow',
			'gtaskblue',
			'gtaskgreen',
			'ggroupblack',
			'gmilestone'
		],
	}
	
})(ShaColor);