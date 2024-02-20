  var app = angular.module('myApp', []);

            app.controller('myCtrl1', function ($scope, $http) {
                $scope.data = [];
                $scope.currentQuestionIndex = 0;
                $scope.results = [];

                $scope.scores = 0;


            $scope.btnshowsubmit = false;
            $scope.btnshownext = true;
            $scope.showResultForm = false;
            $scope.showQuizForm = true;
                 $scope.findAll = function () {
                    $http.get("http://localhost:8080/rest/quiz/findAll")
                        .then(function (response) {
                            $scope.data = response.data;
                            $scope.loadQuestion();
                        })
                        .catch(function (error) {
                            console.error('Error loading data:', error);
                        });
                };

                $scope.loadQuestion = function () {
                    $scope.cauhoi = $scope.data[$scope.currentQuestionIndex];

                };

                $scope.nextQuestion = function () {
                $scope.removeselected();




                if (!$scope.selectedAnswer) {
                                                    alert("Mời chọn đáp án trước khi chuyển đến câu tiếp theo.");
                                                    return;
                }else{


                                                    console.log($scope.results);
                                                  $scope.currentQuestionIndex ++;
                                                 $scope.cauhoi = $scope.data[$scope.currentQuestionIndex];
                                                  $scope.loadQuestion();
                                                                    if($scope.currentQuestionIndex == $scope.data.length-1){
                                                                          $scope.btnshowsubmit = true;
                                                                          $scope.btnshownext = false;
                                                                    }


                                                                };



                }

                $scope.addToArray = function(x){
                    let index = $scope.results.findIndex( item => item.id == $scope.cauhoi.id );
                    if(index != -1){
                        $scope.results[index].kq = x;
                    }else{
                        $scope.results.push({"id":$scope.cauhoi.id,
                                                             "kq": x});
                    }

                }

                $scope.back = function () {
                    if ($scope.currentQuestionIndex >= 0) {
                        $scope.currentQuestionIndex--;
                        $scope.loadQuestion();
                    }
                    console.log($scope.results);

                };

                $scope.removeselected = function(){
                 const collection = document.getElementsByClassName("options");
                            for (let i = 0; i < collection.length; i++) {
                                        collection[i].checked = false;
                                }
                }

$scope.checkAnswer = function () {
   for(let i= 0 ; i < $scope.data.length ; i++){
   if($scope.data[i].id == $scope.results[i].id){

              if($scope.data[i].answer === $scope.results[i].kq){
                   ++$scope.scores;
              }
           }

   }
   console.log($scope.scores);
};






$scope.submitQuiz = function () {
    // Tính phần trăm đúng
    alert("hihihihi")
$scope.checkAnswer();
$scope.showResultForm = true;
$scope.showQuizForm = false;

let data = {
    "correct" :$scope.scores,
    "wrong": $scope.data.length - $scope.scores,
    "quiz":{
        "id":document.getElementById("quiz_id").value
    }
}
console.log(data)
 $http.post("http://localhost:8080/rest/studyresult/creates" , data)
                                        .then(function(response) {
                                            console.log(response.data);

    });
}

                $scope.findAll();
            });