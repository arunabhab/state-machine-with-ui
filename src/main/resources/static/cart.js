(function() {
	console.log('cart creationpage loaded');
	var cartId = getUrlParameter('cartId');
	var orderId = getUrlParameter('orderId');

	if (getUrlParameter('status') == "CART_SAVED") {
		$.ajax({
			type : "GET",
			url : "/masterdata/getCartItems?cartId=" + cartId + "&orderId="
					+ orderId,
			dataType : "text",
			success : function(result) {
				console.log(result);
				 $("#itemList").val(result);
			}
		});
	}

})();

$("#checkout").click(
		function() {
			var custId = getUrlParameter('custId');
			var cartId = getUrlParameter('cartId');
			var orderId = getUrlParameter('orderId');

			$.ajax({
				type : "POST",
				url : "/masterdata/addItems?cartId=" + cartId + "&items="
						+ $("#itemList").val() + "&orderId=" + orderId,
				success : function(result) {
					console.log(result);
				}
			});
		});

var getUrlParameter = function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1), sURLVariables = sPageURL
			.split('&'), sParameterName, i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true
					: decodeURIComponent(sParameterName[1]);
		}
	}
};