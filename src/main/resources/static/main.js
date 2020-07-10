(function() {
	console.log('page loaded');
})();

$("#sm-query").click(function() {
	var custId=$("#custIdInput").val();
	console.log("Changing window");
	window.location.href = "/Cart/getStateMachineStatus?custId="+custId;
});





//$.ajax({
//	type : "POST",
//	url : "/Cart/getStateMachineStatus?custId=10",
//	dataType: "jsonp",
//	success : function(result) {
//		console.log(result);
//		 $("body").html(result);
//		
//	}
//});