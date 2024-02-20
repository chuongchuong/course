var app = angular.module('myApp', []);
app.controller('myCtrl11', function($scope, $http) {
    $scope.createquiz = function(){
        $scope.host = "http://localhost:8080";
        $scope.data = {};

        $scope.form.coursedetail = {
            "id" : document.getElementById("coursedetail_id").value,

        }
        var id = document.getElementById("coursedetail_id").value;

        if (!$scope.form.question || !$scope.form.answer_a || !$scope.form.answer_b || !$scope.form.answer_c || !$scope.form.answer_d || !$scope.form.answer) {
            $scope.findAll();
            $scope.newform();

        }else{
            console.log($scope.form);
            $http.post("http://localhost:8080/rest/quiz/creates" , $scope.form)
                .then(function(response) {
                    console.log(response.data);
                    $scope.findAll();
                    $scope.newform();
                });
        }




    }

    $scope.findAll = function(){
        var id = document.getElementById("coursedetail_id").value;
        $http.get("http://localhost:8080/rest/quiz/" +id)
            .then(function(response) {
                $scope.data = {};
                $scope.data= response.data;
                $scope.newform();
            });


    }
    $scope.findAll();

    $scope.delete = function(id){
        $http.delete("http://localhost:8080/rest/quiz/delete/" + id)
            .then(function(response) {

                alert("delete okkk");
                $scope.findAll();
            });
    }

    $scope.deletev2 = function(){
        $http.delete("http://localhost:8080/rest/quiz/delete/" + $scope.form.id)
            .then(function(response) {

                alert("delete okkk");
                $scope.findAll();
                $scope.newform();
            });
    }


    $scope.edit = function(id){
        $http.get("http://localhost:8080/rest/quiz/edit/" + id)
            .then(function(response) {
                $scope.form = response.data;
                console.log(response.data)

            });
    }


    $scope.update = function(){
        $http.put("http://localhost:8080/rest/quiz/update",$scope.form)
            .then(function(response) {

                alert("update okkk");
                $scope.findAll();
                $scope.setdatatoform($scope.form.id)
                $scope.newform();
            });
    }


    $scope.setdatatoform = function(id){

        let obj = $scope.data.find(item => item.id == id);
        console.log(obj);
    }

    $scope.newform = function(){
        $scope.form = {};




        $scope.submitForm = function() {
            if($scope.form.answer_a == $scope.form.answer_b &&
                $scope.form.answer_a == $scope.form.answer_c &&
                $scope.form.answer_b == $scope.form.answer_c){
            }
        }
    };
});