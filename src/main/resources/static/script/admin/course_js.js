
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
$scope.idcurrentCourse = -1;
$scope.image = true;
$scope.href = "http://localhost:8080/rest/courses"
$scope.imagelink = "http://localhost:8080/rest/files/files/"
    $scope.getInfo = function( id ){ // button edit
    $scope.image = false;
         $http.get($scope.href + "/admin/" + id)
          .then(function(response) {
                $scope.idcurrentCourse = response.data.id;
               document.getElementById("course_name").value =response.data.coursename;
               document.getElementById("course_price").value =response.data.price;
               document.getElementById("course_desc").value =response.data.descriptions;
               document.getElementById("course_cate").value =response.data.category.categoryname;
               $scope.imagecurent = response.data.image ;
               //               document.getElementById("buttonUpdate").formaction = "/admin/update/28";
                                   console.log(document.getElementById("buttonUpdate").formAction);
                                   document.getElementById("buttonUpdate").formAction = "/admin/update/"+$scope.idcurrentCourse;
                                   console.log(document.getElementById("buttonUpdate").formAction);

          });

    }

    $scope.reset = function(){// button reset
    $scope.idcurrentCourse = -1;
         $scope.image = true;
         $scope.imagecurent = null;
         document.getElementById("courseform").reset();
    }
    $scope.deletecourse = function (){
        if($scope.idcurrentCourse !== -1){
            $http.delete($scope.href + "/" + $scope.idcurrentCourse)
            .then(function(response) {
                location.reload();
           });
        }
    }


    $scope.removeImageCourse = function  (){
                      $.when(


                        $http.delete( $scope.imagelink + $scope.imagecurent )
                       .then(function (response) {
                                alert("Delete success");

                                $scope.imagecurent = null;
                          }, function (error) {
                                 alert("Delete fail");
                                 alert($scope.imagelink + $scope.imagecurent)
                             })

                                 ).then(function () {
                                                             $http.post($scope.href+"/"+ $scope.idcurrentCourse + "/" + 1 )
                                                                            .then(function (response) {

                                                                            }, function (error) {

                                                                            })

                                 });

      }



});