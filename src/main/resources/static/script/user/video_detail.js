var app = angular.module('myApp', []);
function preventDefault(event) {
    event.preventDefault();
}
app.filter('timeAgo', function() {
    return function(input) {
        var currentTime = new Date();
        var createdTime = new Date(input);
        var timeDifference = Math.abs(currentTime - createdTime);
        var daysDifference = Math.floor(timeDifference / (1000 * 3600 * 24));

        if (daysDifference === 1) {
            return '1 ngày trước';
        } else if (daysDifference > 1) {
            return daysDifference + ' ngày trước';
        } else {
            return 'Hôm nay';
        }
    };
});

app.controller('User-ctrl', function($scope, $http,$sce) {
    $scope.trustedVideoUrl = '';

    $scope.videoUrl ="";
    $scope.videos = [];
    $scope.videoCount =0;

    $scope.data = [];
    $scope.currentQuestionIndex = 0;
    $scope.results = [];
    $scope.courseData =[];
    $scope.scores = 0;
    $scope.courseName ="";
    $scope.btnshowsubmit = false;
    $scope.btnshownext = true;
    $scope.btnshowback = true;
    $scope.showResultForm = false;
    $scope.showQuizForm = true;
    $scope.messageThongBao = false;

    $scope.youtube = true;
    $scope.btnYou= true;
    //load comment
    $scope.loadComment =function (){
        $scope.comment = '';
        $http.get('/api/comment/display/' + courseId)
            .then(function(response) {
                $scope.comments = response.data;
                $scope.getCurrentUser();
            })
            .catch(function(error) {
                console.error('Error fetching comments:', error);
                alert(error)
            });
    }
    $scope.loadVideos = function() {

        $http.get('/api/library/videos/' + courseId)
            .then(function(response)   {
                $scope.videos = response.data; // Assuming the API response is an array of video objects

                if ($scope.videos.length > 0) {
                    $scope.changeVideo($scope.videos[0].url, $scope.videos[0].chapter,$scope.videos[0].name,$scope.videos[0].id,0);
                    $scope.videoCount = $scope.videos.length;
                }


            })
            .catch(function(error) {
                console.error('Error loading videos:', error);
            });


    };
    $scope.changeVideo = function(urll, chapterr,namee,idd,index) {
    // neu quiz truoc status = true moi duoc xem bai tiep theo


        $scope.quiz = null;

        $scope.trustedVideoUrl = $sce.trustAsResourceUrl('https://www.youtube.com/embed/' + urll);
        $scope.currentChapter = chapterr;
        $scope.currentName = namee;
        $scope.currentId = idd;
        $scope.findQuiz(idd);
// Kiem tra dieu kien cua index va hien thi video
if ($scope.videos && $scope.videos.length > 0) {
if(index ==0){
 var videoId = $scope.videos[index].id;
}else{
 var videoId = $scope.videos[index-1].id;
}
  $http.get("http://localhost:8080/rest/studyresult/find/" + videoId)
                    .then(function(response){
                     $scope.quiz1 = response.data;
                       if($scope.quiz1.status == true){


                      //tao ng-show = true cho btn quiz,div video
                          $scope.btnYou= true;
                        $scope.youtube = true;
                       }
                       else{
                       if(index ==0){
                       //tao ng-show = true cho btn quiz,div video
                           $scope.btnYou= true;
                       $scope.youtube = true;
                       }else{
                       alert("Phải làm quiz trước đó và kết quả bằng hoặc trên 50% để học video tiếp theo")

                        //tao ng-show = false cho btn quiz,div video

                             $scope.btnYou= false;
                         $scope.youtube = false;
                       }


                       }
                    })
}

    };
$scope.clickVideo = function(index){
if(index !=0 ){
     var clickedVideo = $scope.videos[index-1];
      console.log(clickedVideo);
      // tim index-- check status
      $http.get("http://localhost:8080/rest/studyresult/find/" + clickedVideo.id)
                  .then(function(response){
                   $scope.quiz1 = response.data;
                     if($scope.quiz1.status == true){
                     return true;
                     }
                     else{
                     return false;
                     }
                  })
}

}


  $scope.findQuiz = function (id){
             $http.get("http://localhost:8080/rest/studyresult/find/" + id)
             .then(function(response){
              $scope.quiz = response.data;

            $scope.sai = $scope.quiz.wrong;
            $scope.dung = $scope.quiz.correct;
            $scope.tong = $scope.sai + $scope.dung;

             })
            }


// var app = angular.module('myApp', []);




    //Lấy người dùng hiện tại
    $scope.getCurrentUser = function() {
        $http.get('/api/comment/comment')
            .then(function(response) {
                $scope.currentUser = response.data;
// Check if the logged-in user is the owner of each comment
                angular.forEach($scope.comments, function(comment) {
                    comment.isOwner = (comment.user.id === $scope.currentUser.id);
                });
            })
            .catch(function(error) {
                console.error('Error fetching current user:', error);
            });
    };
    //Comment
    $scope.submitComment = function() {
        // Check if the comment text is not empty

        if ($scope.comment.trim() === '') {

            return;
        }


        var userid = $scope.currentUser.id;
        // var courseid = courseId;

        $scope.currentDate = new Date();

        $scope.newComment = {
            contents: $scope.comment,
            userid: userid,
            courseid: courseId,
            createddate: $scope.currentDate
        };
        console.log($scope.newComment);
        $http.post('/api/comment/save/'+ courseId, $scope.newComment)

            .then(function(response) {
                $scope.comment = '';
                $scope.loadComment();
            })
            .catch(function(error) {
                console.error('Error posting comment:', error);
                // Handle error, show a user-friendly message to the user
            });
    };
    //Delete comment(Đổi status)
    $scope.deleteCommentStatus = function(commentId, comment) {
        $http.post('/api/comment/deleteStatus/' + commentId, comment)
            .then(function(response) {
                // Handle success
                console.log('Comment status deleted:', response.data);
                $scope.loadComment();
                // Optionally, update UI or perform other actions upon success
            })
            .catch(function(error) {
                // Handle error
                console.error('Error deleting comment status:', error);
                // Optionally, show an error message or perform other actions upon error
            });
    };

    
    //Quiz
    // tim cau hoi cua khoa hoc nao va chapter nao???????????????????????????????????????????????????
    // "http://localhost:8080/rest/quiz/20/1"
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

    $scope.findDoneQuiz = function () {
        $http.get("http://localhost:8080/rest/studyresult/getDoneQuiz/" + courseId)
            .then(function (response) {
                $scope.total = response.data;
                $scope.resultQuiz = $scope.total.length;
                $http.get("http://localhost:8080/api/library/course/" + courseId)
                    .then(function (responses) {
                        $scope.courseData = responses.data;
                       $scope.courseName = $scope.courseData.coursename;

                        if ($scope.resultQuiz === $scope.videoCount) {
                            $http.get("http://localhost:8080/api/library/course/course/" + courseId).then(function (response) {
                                $scope.coursess = response.data;
                                if($scope.coursess.statusmail !==true){
                                    let dynamicString = "Chúc mừng bạn đã hoàn thành khóa học " + $scope.courseName; // Modify this line to generate your dynamic string
                                    let requestData = {
                                        dynamicString: dynamicString
                                    };
                                    $http.post("http://localhost:8080/api/library/setMail/"+courseId) .then(function (postMailResponse) {
                                        // Handle success of the POST request



                                    })
                                        .catch(function (postMailError) {
                                            // Handle error of the POST request
                                            console.error('Error sending email:', postMailError);
                                        });
                                    $http.post("http://localhost:8080/rest/studyresult/sendmail", requestData)
                                        .then(function (postResponse) {
                                            // Handle success of the POST request

                                            console.log('Email sent successfully:', postResponse.data);

                                        })
                                        .catch(function (postError) {
                                            // Handle error of the POST request
                                            console.error('Error sending email:', postError);
                                        });
                                }else{
                                    console.log("Đã gửi mail rồi");
                                }
                            })
                                .catch(function (error) {
                                    console.error('Error loading data:', error);
                                });

                        }
                    })


            })
            .catch(function (error) {
                // Handle error of the GET request
                console.error('Error loading data:', error);
            });
    };

    $scope.findAll1 = function (id) {
        $http.get("http://localhost:8080/rest/quiz/" + id)
            .then(function (response) {
            $scope.resetQuiz();
                $scope.data = response.data;
                 if($scope.currentQuestionIndex === 0){
                         $scope.btnshowback = false;
                 }

                if($scope.currentQuestionIndex === $scope.data.length-1 ){
                                $scope.btnshowsubmit = true;
                                $scope.btnshownext = false;
                                $scope.btnshowback = false;
                  }
                 if (!$scope.data || $scope.data.length === 0) {

                      $scope.messageThongBao = true;
                       $scope.showResultForm = false;
                       $scope.showQuizForm = false;

                 }
                 else {

                        $scope.loadQuestion();
                 }
            })
            .catch(function (error) {
                console.error('Error loading data:', error);

                alert('Không thể tải dữ liệu câu hỏi.');
            });
    };

    $scope.loadQuestion = function () {
        $scope.cauhoi = $scope.data[$scope.currentQuestionIndex];
    };

    $scope.nextQuestion = function () {
        $scope.removeselected();


        if (
                $scope.selectedAnswer_a == undefined &&
                $scope.selectedAnswer_b == undefined &&
                $scope.selectedAnswer_c == undefined &&
                $scope.selectedAnswer_d == undefined
        ) {
                alert("Mời chọn đáp án.");
                return;
            } else{

            $scope.currentQuestionIndex
            console.log($scope.results);
            $scope.currentQuestionIndex ++;
            if ($scope.currentQuestionIndex < $scope.data.length) {
//                        $scope.cauhoi = $scope.data[$scope.currentQuestionIndex];
                        $scope.loadQuestion();

                    }
//            $scope.cauhoi = $scope.data[$scope.currentQuestionIndex];
//            $scope.loadQuestion();
            if($scope.currentQuestionIndex > 0){
             $scope.btnshowback = true;
            }
            if($scope.currentQuestionIndex === $scope.data.length-1 ){
                $scope.btnshowsubmit = true;
                $scope.btnshownext = false;
            }


        };
         $scope.removeUndefined();
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
            $scope.btnshowsubmit = false;
            $scope.btnshownext = true;
            $scope.loadQuestion();
        }
if($scope.currentQuestionIndex === 0 ){
                $scope.btnshowback = false;
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
                    $scope.scores++;

                }
            }

        }
        console.log($scope.scores);
    };


$scope.resetQuiz = function () {
    $scope.data = [];
    $scope.currentQuestionIndex = 0;
    $scope.results = [];
    $scope.scores = 0;
    $scope.btnshowsubmit = false;
    $scope.btnshownext = true;
    $scope.showResultForm = false;
    $scope.showQuizForm = true;
    $scope.btnshowback = true;
    //$scope.findAll1(id); // Load lại câu hỏi cho quiz mới
};



    $scope.submitQuiz = function (id) {
        // Tính phần trăm đúng
if (
                $scope.selectedAnswer_a === undefined &&
                $scope.selectedAnswer_b === undefined &&
                $scope.selectedAnswer_c === undefined &&
                $scope.selectedAnswer_d === undefined
        ) {
                alert("Mời chọn đáp án.");
                return;
            }
        $scope.checkAnswer();
        $scope.showResultForm = true;
        $scope.showQuizForm = false;

        let data = {
            "correct" :$scope.scores,
            "wrong": $scope.data.length - $scope.scores,

        }


            $http.post("http://localhost:8080/rest/studyresult/creates/"+id , data)
                    .then(function(response) {
                        console.log(response.data);
                         $scope.removeUndefined();
                         $scope.removeselected();
                        $scope.findQuiz(id);

                        $scope.findDoneQuiz();

                    });


    }

     $scope.removeUndefined = function (){
         $scope.selectedAnswer_a = undefined
         $scope.selectedAnswer_b = undefined
         $scope.selectedAnswer_c = undefined
         $scope.selectedAnswer_d = undefined
     }


    $scope.changeVideo();
    $scope.loadVideos();
    $scope.loadComment();
    $scope.getCurrentUser();
});


