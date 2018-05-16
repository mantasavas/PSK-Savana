var importExportApp = angular.module("importExportApp", []);

importExportApp.controller("importExportController", function($scope, $http, $window) {
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");

    $scope.checkValue = "none.com";

    if (header.length > 0 && token.length > 0) {
        $http.defaults.headers.common[header] = token;
    }

    $scope.greeting = "VISA ESTONIA";

    $scope.downloadExcelFile = function(){
        $scope.checkValue = "mantasavas@gmail.com";
        $http.get('/admin/generateProductExcel/products/?type=xls').success(function (data){
            var data = "some data goes here <imput>lglkh</imput>",
                blob = new Blob([data], { type: 'text/plain' }),
                url = $window.URL || $window.webkitURL;
            $scope.fileUrl = url.createObjectURL(blob);
        });
    };


    $scope.checkAvailability = function() {
        $http.get('/admin/importProductExcel/isReadyFile').success(function (data){
            $scope.returnedData = data.response;
        });
    }
});


