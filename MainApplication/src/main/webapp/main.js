var blaubandApp = angular.module(
        'blaubandApp', [
            'angularFileUpload'
        ],
        function ($locationProvider) {
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });
        }
);

var blaubandServices = angular.module('blaubandServices', ['ngResource']);
blaubandServices.run(function ($http) {
    $http.defaults.withCredentials = true;
    $http.defaults.cache = true;
});

var blaubandControllers = angular.module('blaubandControllers', []);

blaubandServices.factory('Config', ['$resource',
    function ($resource) {
        return $resource('/api/config', {
            get: {
                method: 'GET'
            },
            save: {
                method: 'POST'
            }
        });
    }
]);

blaubandServices.factory('Xlsx', ['$resource',
    function ($resource) {
        return $resource('/api/pdf', {
            get: {
                method: 'GET'
            },
            upload: {
                method: 'POST'
            }
        });
    }
]);

blaubandControllers.controller('mainController', [
    '$scope', 'Config', 'Xlsx',
    function ($scope, Config, Xlsx) {
        $scope.config = Config.get();
    }
]);
