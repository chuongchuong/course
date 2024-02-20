const app = angular.module('myApp', []);

app.controller('myCtrl', function($scope, $http) {
    $scope.categoryId = 0; // Initialize categoryId
    $scope.updateCategoryId = function(selectedCategoryId) {
        $scope.categoryId = selectedCategoryId; // Update categoryId when the user selects a category
    };
});
