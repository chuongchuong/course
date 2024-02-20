
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
app.controller('User-ctrl', function($scope, $http) {
    $scope.comment = '';
    //load comment
    $scope.loadComment =function (){
        $http.get('/api/comment/display/' + courseId)
            .then(function(response) {
                $scope.comments = response.data;
            })
            .catch(function(error) {
                console.error('Error fetching comments:', error);
            });
    }


    //Lấy người dùng hiện tại
    $scope.getCurrentUser = function() {
        $http.get('/api/comment/comment')
            .then(function(response) {
                $scope.currentUser = response.data;

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

    $scope.getCurrentUser();
    $scope.loadComment();

});

