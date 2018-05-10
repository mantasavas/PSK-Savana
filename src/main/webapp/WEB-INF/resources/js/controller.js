var cartApp = angular.module ("cartApp", []);

cartApp.controller("cartCtrl", function($scope, $http){

    //add csrf token
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $http.defaults.headers.common[header] = token;

    $scope.refreshCart = function(){
        $http.get('/rest/cart/' + $scope.cartId).success(function (data){
            $scope.cart = data;
        });
    };

    $scope.clearCart = function(){
        $http.delete('/rest/cart/' + $scope.cartId).success(function (){
            $scope.refreshCart();
        });
    };

    $scope.initCartId = function(cartId){
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };

    $scope.addToCart = function(productId){
        $http.put('/rest/cart/add/' + productId).success(function (){
            alert('Product successfully added to the cart!');
        });
    };

    $scope.removeFromCart = function(productId){
        $http.put('/rest/cart/remove/' + productId).success(function(data){
            $scope.refreshCart();
        });
    };

    $scope.calGrandTotal = function(){
        var grandTotal = 0;

        for (var i = 0; i < $scope.cart.cartItems.length; i++){
            grandTotal += $scope.cart.cartItems[i].totalPrice;
        }

        return grandTotal;
    }
});