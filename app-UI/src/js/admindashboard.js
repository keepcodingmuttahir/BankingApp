var app = angular.module('adminApp', []);

app.controller('adminController', function($scope, $location) {
    // Function to navigate to the "Create User" page
    $scope.createUser = function() {
        $location.path('/create-user');
    };

    // Function to navigate to the "View All Users" page
    $scope.viewAllUsers = function() {
        $location.path('/view-all-users');
    };

    // Function to navigate to the "Edit User" page
    $scope.editUser = function() {
        $location.path('/edit-user');
    };

    // Function to navigate to the "Delete User" page
    $scope.deleteUser = function() {
        $location.path('/delete-user');
    };
});
