(function() {
	console.log('cart continue page loaded');

})();

$("#discard-cart").click(function() {
	var custId=getUrlParameter('custId');
	$.ajax({
		type : "GET",
		url : "/masterdata/deleteCartByCustId?custId="+custId,
		success : function(result) {
			console.log(result);
			window.location.href = "/Cart/getStateMachineStatus?custId="+custId;
		}
	});
});

$("#continue-cart").click(function() {
		var custId = getUrlParameter('custId');
		$.ajax({
			type : "GET",
			url : "/masterdata/getCart?custId=" + custId,
			dataType : "json",
			success : function(result) {
				console.log(result.cartId);
				console.log(result.orderId);
			window.location.href = "/Cart/fetchCartPage?cartId="
						+ result.cartId + "&orderId=" + result.orderId
						+ "&custId=" + custId+"&status="+result.status;
			},
			error : function(error) {
				console.log(error);
			}
		});

	});

