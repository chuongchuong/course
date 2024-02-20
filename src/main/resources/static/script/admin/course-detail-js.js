
    var app = angular.module('myApp', []);
app.controller('myCtrl2', function($scope, $http) {
    $scope.rest = "http://localhost:8080/rest/course-detail";
    $scope.restupload = "/rest/files/files";

    $scope.detailcourses = [];
    $scope.form= {};
    $scope.form.exercise={};

    $scope.editstatus = false;

    $scope.currentElement = {};
    var path = window.location.pathname;
        var parts = path.split('/');
        var idIndex = parts.indexOf('detail') + 1;

        if (idIndex < parts.length) {
            var courseId = parts[idIndex];
            console.log('Course ID:', courseId);

            // Bạn có thể sử dụng giá trị id trong controller của bạn
            // Ví dụ: $scope.loadCourseDetails(courseId);
        } else {
            console.error('No course ID found in the URL.');
        }


    $scope.loadCourseDetails = function(){

         $http.get($scope.rest +"/course"+ "/" + courseId)
                    .then(function(response) {
                        $scope.detailcourses =response.data;
                        console.log(response.data)
                   })
                   .catch(function(error) {
                               console.error('Error loading course details:', error);
                                console.log(courseId)
                           });
    }
    $scope.loadCourseDetails();

//
$scope.getForm = function(){

 let data = {
                        "name": $scope.form.coursename,
                        "chapter": $scope.form.chapter,

                        "url": $scope.form.url,
                        "course": {
                            "id":document.getElementById("courseidhidden").value
                        }
                    };


                    return data;

}
$scope.create = function(){
    let data = $scope.getForm();
      var ajaxData = new FormData();
        ajaxData.append("files", document.getElementById("cc").files[0]);
         ajaxData.append("files", document.getElementById("ccc").files[0]);

         let namefile = null;
                 $.when(
                            $http.post( $scope.restupload , ajaxData, {
                                                        transformRequest: angular.identity,
                                                        headers: { 'Content-Type': undefined }
                                                    }).then(resp => {
                                                        namefile = resp.data;
                                                    }).catch(error => {
                                                        console.log("Errors", error);
                                                    })


                    ).then(function () {
                        data.exercise = namefile[0];
                        data.exercisesolution = namefile[1];
                         $http.post($scope.rest , data)
                                        .then(function(response) {
                                            $scope.detailcourses.push(response.data);
                                            console.log("Themmmmmmmmmmmmmmmm")
                            });

                    });
}

        $scope.deletecoursedetail = function(id){
                        $http.delete($scope.rest + "/" + id)
                                           .then(function(response) {
                                               $scope.loadCourseDetails();

                               });
        }

        $scope.editcoursedetail = function(id){
            $http.get($scope.rest + "/" + id).then(function(response) {
                $scope.currentElement = response.data;
                console.log($scope.currentElement);
                $scope.editstatus = true;
               $scope.form.coursename =response.data.name;
               $scope.form.chapter  = response.data.chapter;
               $scope.form.url = response.data.url;

           });

        }

        $scope.removeImage = function(image){
           $.when(


                                   $http.delete("http://localhost:8080/rest/files/files/" + image)
                                  .then(function (response) {
                                           alert("Delete success")
                                     }, function (error) {
                                            alert("Delete falil");
                                        })

                       ).then(function () {
                           $http.post("http://localhost:8080/rest/course-detail/" + $scope.currentElement.id +  "/"+image)
                               .then(function (response) {
                                        $scope.loadCourseDetails();
                                     }, function (error) {
                                        alert("faild")
                                    })

                               });

        }

        $scope.reset = function(){// button reset

                 document.getElementById("courseform").reset();
                 $scope.editstatus = false;
            }
            $scope.deletecoursedetail = function (id){
                            $http.delete("http://localhost:8080/rest/course-detail/"+id)
                                    .then(function(response) {
                                        location.reload();
                                   });
            }
            $scope.updatecourse = function(){
                let data = $scope.getForm();


            }


});
