'use strict';

/**
 * Document view scores controller. 
 * Adapted from DocumentView.js
 */
angular.module('docs').controller('DocumentViewScores', function ($scope, $stateParams, Restangular) {

    // Load scores from server
    Restangular.one('scores', $stateParams.id).get().then(function (data) {
      $scope.scores = data.scores;
      console.log(data.scores)

      $scope.skills = data.scores[0].skills
      $scope.experience = data.scores[0].experience
      $scope.transcriptGPA = data.scores[0].transcriptGPA
      $scope.match = data.scores[0].match
      


      console.log($scope.skills)
    });

    $scope.options = [0,1,2,3,4,5]
  /**
   * Add the entered scores 
   */
  $scope.addScores = function () {

    Restangular.one('scores').post('scores', {
      documentId: $stateParams.id,
      skills: $scope.skills,
      experience: $scope.experience,
      transcriptGPA: $scope.transcriptGPA,
      match: $scope.match,
      createDateStr: $scope.createDateStr
    })

  };

});