// var app = angular.module('myApp', []);
//
// app.controller('User-ctrl', function($scope, $http, $sce) {
//     $scope.trustedVideoUrl = '';
//     $scope.currentName = '';
//     $scope.currentChapter = '';
//     $scope.videos = [];
//
//     $scope.changeVideo = function(url, chapter, name) {
//         $scope.trustedVideoUrl = $sce.trustAsResourceUrl('https://www.youtube.com/embed/' + url);
//         $scope.currentName = name;
//         $scope.currentChapter = chapter;
//         console.log('Chapter:', chapter);
//         console.log('Name:', name);
//     };
//
//     $scope.loadVideos = function() {
//         $http.get('/api/library/videos/' + courseId)
//             .then(function(response) {
//                 $scope.videos = response.data; // Assuming the API response is an array of video objects
//                 if ($scope.videos.length > 0) {
//                     $scope.changeVideo($scope.videos[0].url, $scope.videos[0].chapter, $scope.videos[0].name);
//                 }
//                 console.log($scope.videos);
//             })
//             .catch(function(error) {
//                 console.error('Error loading videos:', error);
//             });
//     };
//
//     // Call functions after defining them
//     $scope.changeVideo();
//
//     // $scope.loadVideos();
// });
