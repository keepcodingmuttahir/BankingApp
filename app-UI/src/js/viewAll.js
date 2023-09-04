(function () {
    'use strict';
    angular.module("bank-fe", ['ngResource', 'ng']);

    function UserService($resource) {
        return $resource('api/v1/Users/:id', { id: '@id' });
    }

    angular.module("bank-fe").factory('UserService', ['$resource', UserService]);

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
//            if (self.userName) {
//                parameters.search = "%" + self.userName + "%"; // Corrected this line
//            } else {
//                parameters.search = "%"; // Corrected this line
//            }

            self.service.get().$promise
                .then(function (response) {
                    self.display = true;
                    self.user = response.content;
                })
                .catch(function (error) {
                    // Handle the error here, e.g., by logging it or showing an error message to the user.
                    console.error('Error:', error);
                });
        }

        self.init();
    }

    angular.module("bank-fe").controller('UserController', ['UserService', UserController]);
})();
