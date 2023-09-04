(function () {
    'use strict';
    angular.module("user-db", ['ngResource', 'ng']);

    function UserService($resource) {
        return $resource('api/v1/Users/1/all');
    }
    angular.module("user-db").factory('UserService', ['$resource', UserService]);
    function UserController(UserService) {
        var self = this;
        self.service = UserService;
        self.display = false;
        self.userDetails = {
            user: {},
            balance: {},
            transactions: []
        }; // UserDetailsDTO structure with separate objects
        self.getCurrentDateTime = function () {
                var currentDate = new Date();
                return currentDate.toISOString(); // or use any other desired date format
            }
        self.init = function () {
            self.search();
        }
         self.getCurrentDateTime = function () {
                        var currentDate = new Date();
                        return currentDate.toISOString(); // or use any other desired date format
        }
        self.navigateToTransactionForm = function () {
            window.location.href = 'transaction-form.html';
        };


        self.search = function () {
            self.service.get().$promise
                .then(function (response) {
                    // Assuming UserDetailsDTO is returned directly with separate objects
                    self.display = true;
                    self.userDetails.user = response.content.user;
                    self.userDetails.balance = response.content.balance;
                    self.userDetails.transactions = response.content.transactions;

                })
                .catch(function (error) {
                    console.error('Error:', error);

                });

        }
        self.init();
    }
    angular.module("user-db").controller('UserController', ['UserService', UserController]);
})();



