'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
	.controller('chatroomController', [ '$scope', 'ngstomp', function($scope, ngstomp) {
			$scope.name = "";
			$scope.messages = new Array();
			$scope.participants = new Array();
			
			$scope.client = ngstomp('/spring-chatroom/chatroom');
			//$scope.client.connect('loginid', 'passcode',
			$scope.client.connect('guest', 'guest',
				function(frames) {
					// successful connection
					$scope.name = frames[0].headers['user-name'];
					var queueSuffix = frames[0].headers['queue-suffix'];
					
					$scope.client.subscribe("/app/messageHistory", function(message) {
						$scope.messages = JSON.parse(message.body);
					});
					$scope.client.subscribe("/app/participants", function(message) {
						$scope.participants = JSON.parse(message.body);
					});
					
					$scope.client.subscribe("/topic/message-updates", function(message) {
						$scope.messages.push(JSON.parse(message.body));
					});					
					$scope.client.subscribe("/topic/participant-updates", function(message) {
						$scope.participants = JSON.parse(message.body);
					});
					$scope.client.subscribe("/queue/errors"+queueSuffix, function(message) {
						$scope.error = JSON.parse(message.body);
					});
					
				},
				function(frames){
					console.log(frames);					
				}
			);
			 
			$scope.sendMessage = function() {
				if($scope.message == '')
					return;
				$scope.client.send('/app/newmessage', {}, $scope.message);
				$scope.message = '';
			};
		    
		} ]);