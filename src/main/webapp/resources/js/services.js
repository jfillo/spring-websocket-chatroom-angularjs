'use strict';

/* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', []).value('version', '0.1')
	.factory('ngstomp', function($rootScope) {
		var self = this;
		
		self.stompClient = {};

        function NGStomp(sock) {
        	self.stompClient = Stomp.over(sock);
        }

        NGStomp.prototype.subscribe = function(queue, callback) {
            self.stompClient.subscribe(queue, function() {
                var args = arguments;
                $rootScope.$apply(function() {
                    callback(args[0]);
                });
            });
        };

        NGStomp.prototype.send = function(queue, headers, data) {
        	self.stompClient.send(queue, headers, JSON.stringify(data));
        };

        NGStomp.prototype.connect = function(user, password, on_connect, on_error) {
        	self.stompClient.connect(user, password,
                function() {
        			var args = arguments;
                    $rootScope.$apply(function() {
                        on_connect.call(undefined,args);
                    });
                },
                function() {
                	var args = arguments;
                    $rootScope.$apply(function() {                    	
                        on_error.apply(undefined,args);
                    });
                });
        };

        NGStomp.prototype.disconnect = function(callback) {
        	self.stompClient.disconnect(function() {
                var args = arguments;
                $rootScope.$apply(function() {
                    callback.apply(args);
                });
            });
        };

        return function(url) {
        	var sock = new SockJS(url);
            return new NGStomp(sock);
        };
    });;
