'use strict';

/**
 * Document view scores controller. 
 * Adapted from DocumentView.js
 */
angular.module('docs').controller('DocumentViewScores', function ($scope, $stateParams, Restangular) {

    // Load comments from server
    Restangular.one('scores', $stateParams.id).get().then(function (data) {
      $scope.scores = data.scores;
    });

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