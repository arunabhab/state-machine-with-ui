(function() {
	console.log('cart creationpage loaded');

})();

$("#yes-new-cart").click(
		function() {
			var custId = getUrlParameter('custId');
			$.ajax({
				type : "POST",
				url : "/masterdata/createCart?custId=" + custId,
				dataType : "json",
				success : function(result) {
					console.log(result.cartId);
					console.log(result.orderId);

					window.location.href = "/Cart/fetchCartPage?cartId="
							+ result.cartId + "&orderId=" + result.orderId
							+ "&custId=" + custId+"&status=";
				},
				error : function(error) {
					console.log(error);
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