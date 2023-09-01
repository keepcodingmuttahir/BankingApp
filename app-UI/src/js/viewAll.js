(function () {
    'use strict';
    angular.module("bank-fe", ['ngResource', 'ng']);

    function UserService($resource) {
        return $resource('http://localhost:9080/api/v1/Users/:id');
    }

    angular.module('bank-fe').factory('UserService', ['$resource', UserService]);

    function UserController(UserService) {
        var self = this;

        self.service = UserService;
        self.user = [];
        self.userName = '';
        self.display = false;

        self.init = function () {
            self.search();
        }

        self.search = function () {
            self.display = false;
            var parameters = {};
            if (self.userName) {
                parameters.userName = "%" + self.userName + "%"; // Corrected this line
            } else {
                parameters.search = "%"; // Corrected this line
            }
            self.service.get(parameters).$promise.then(function (response) {
                self.display = true;
                self.news = response.content;
            });
        }

        self.init();
    }

    angular.module("bank-fe").controller('UserController', ['UserService', UserController]);
})();

