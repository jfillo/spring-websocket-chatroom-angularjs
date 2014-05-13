'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', ['myApp.filters', 'myApp.services', 'myApp.directives', 'myApp.controllers','luegg.directives']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/chatroom', {templateUrl: 'resources/partials/chatroom.html', controller: 'chatroomController'});
    $routeProvider.otherwise({redirectTo: '/chatroom'});
  }]);
