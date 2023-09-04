angular.module("user-tr").controller('TransactionController', ['$http', function ($http) {
    var self = this;
    self.transaction = {}; // Object to store user input

    self.saveTransaction = function () {
        // Send the transaction data to your Spring Boot application
        $http.post('api/v1/transactions', self.transaction)
            .then(function (response) {
                // Handle success, e.g., show a confirmation message
                alert('Transaction saved successfully.');
            })
            .catch(function (error) {
                // Handle error, e.g., display an error message
                console.error('Error:', error);
            });
    self.navigateToDashboard() = function () {
                window.location.href = 'dashboard.html';
            };
    };
}]);
