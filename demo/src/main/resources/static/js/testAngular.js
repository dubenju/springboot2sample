var app=angular.module("myApp",[]);
app.controller("myCon",function ($scope,$http) {
    $http.get("/testAngular").then(function (response) {
        console.log(response.data.userName);
        $scope.hello=response.data.userName
    })
})
