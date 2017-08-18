// app.js
'use strict';

// create invoiceRevenueApp app
var app = angular.module('invoiceRevenueApp', []);
var rootUrl = "http://localhost:8090/InvoiceAndRevenue/";

// create login controller
app.controller('loginController', function($scope, $window, $http) {

    // function to submit the form after all validation has occurred            
    $scope.submitForm = function() {

        // check to make sure the form is completely valid
        if ($scope.loginForm.$valid) {

            var data = {
                "userName": $scope.username,
                "password": $scope.password
            };

            $http({
                url: rootUrl + "login",
                method: "POST",
                data: data,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function successCallback(response) {
                localStorage.userid = response.data.data.userid;
                $window.location.href = rootUrl+"invoices";
            }, function errorCallback(response) {
                $scope.loginError = response.data.message;
            });
        } else {
            console.log("Error");
        }

    };
});

// create invoices controller
app.controller('invoicesController', function($scope, $window, $http) {

    $scope.navigateInvoice = function(type) {
        if (type == "Month-to-Date") {
            localStorage.timePeriod = "month";
        } else if (type == "Year-to-Date") {
            localStorage.timePeriod = "year";
        } else if (type == "Quarter-to-Date") {
            localStorage.timePeriod = "quarter";
        }
        $window.location.href = rootUrl+"listOfInvoice";
    };

    $scope.loadData = function() {
        var data = {
            "userid": localStorage.userid
        };

        $http({
            url: rootUrl + "revenue",
            method: "POST",
            data: data,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function successCallback(response) {
            $scope.revenues = response.data.data;
        }, function errorCallback(response) {
            console.log("error");
        });
    }

    //call loadData when controller initialized
    $scope.loadData();

});

// create invoiceController controller
app.controller('invoiceController', function($scope, $window, $http) {

    $scope.letterArray = [];

    for (var i = 0; i < 26; i++) {
        $scope.letterArray.push(String.fromCharCode(65 + i));
    }

    $scope.loadRevenueData = function(api) {
        var data = {
            "userid": localStorage.userid
        };

        $http({
            url: rootUrl + api,
            method: "POST",
            data: data,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function successCallback(response) {
            $scope.customers = response.data.data;
            $scope.drawChart($scope.customers);
        }, function errorCallback(response) {
            console.log("error");
        });
    };

    $scope.chartData = function(label, aliasLabel, count) {
        this.label = label;
    }
    
    $scope.resetFilter = function() {
        $scope.filtersStatus = {};
    }

    $scope.drawChart = function(data) {
        var dataset = [];
        for (var i=0; i<data.type.length; i++) {
            dataset.push({
                label: data.type[i].type,
                aliasLabel: String.fromCharCode(65 + i),
                count: data.type[i].revenue
            });
        }

        var width = 140;
        var height = 140;
        var radius = Math.min(width, height) / 2;
        var color =
            d3.scaleOrdinal().range(["#3366cc", "#990099", "#109618", "#ff9900"]);

        //var color = d3.scaleOrdinal(d3.schemeCategory20b);

        var svg = d3.select('#chart')
            .append('svg')
            .attr('width', width)
            .attr('height', height)
            .append('g')
            .attr('transform', 'translate(' + (width / 2) +
                ',' + (height / 2) + ')');

        var arc = d3.arc()
            .innerRadius(0)
            .outerRadius(radius);

        var label = d3.arc()
            .outerRadius(radius - 10)
            .innerRadius(radius - 30);

        var pie = d3.pie()
            .value(function(d) {
                return d.count;
            })
            .sort(null);

        var g =
            svg.selectAll('arc')
            .data(pie(dataset))
            .enter()
            .append('g')
            .attr("class", "arc")
            .attr('d', arc)
            .attr('fill', function(d) {
                return color(d.data.label);
            });

        g.append("path").attr("d", arc).style("fill", function(d) {
            return color(d.data.label);
        });

        g.append("text").attr("transform", function(d) {
            return "translate(" + label.centroid(d) + ")";
        }).text(function(d) {
            return d.data.aliasLabel;
        }).style("fill", "#FFF");
    };

    //call loadData when controller initialized
    if (localStorage.timePeriod == "month") {
        $scope.timePeriodMonth = true;
        $scope.loadRevenueData('revenueMonthToDate');
        $scope.title = "Revenue Month-to-Date";
    } else if (localStorage.timePeriod == "year") {
        $scope.timePeriodYear = true;
        $scope.loadRevenueData('revenueYearToDate');
        $scope.title = "Revenue Year-to-Date";
    }

    $scope.selected = 99;

    $scope.select = function(index) {
        $scope.selected = index;
        $scope.showDiv = false;
    };
});

// create invoicesCustomerController controller
app.controller('invoicesCustomerController', function($scope, $window) {

    $scope.letterArray = [];

    for (var i = 0; i < 26; i++) {
        $scope.letterArray.push(String.fromCharCode(65 + i));
    }

    $scope.customers = {
        "user": "admin",
        "currency": "USD",
        "revenue": {
            "title": "Month-to-Date",
            "amount": "10,000.00",
            "types": [{
                    "type": "Sales",
                    "amount": "4,000.00"
                },
                {
                    "type": "Rental",
                    "amount": "2,600.00"
                },
                {
                    "type": "Parts",
                    "amount": "1,000.00"
                },
                {
                    "type": "Labor",
                    "amount": "2,400.00"
                }
            ],
            "invoices": [{
                    "customerName": "Customer Name 1",
                    "amount": "1,000.00",
                    "type": "Rental",
                    "billedDate": "01/01/2017",
                    "status": "BILLED"
                },
                {
                    "customerName": "Customer Name 2",
                    "amount": "20,000.00",
                    "type": "Rental",
                    "billedDate": "01/01/2017",
                    "status": "BILLED"
                },
                {
                    "customerName": "Customer Name 3",
                    "amount": "1,000.21",
                    "type": "Rental",
                    "billedDate": "01/01/2017",
                    "status": "BILLED"
                },
                {
                    "customerName": "Customer Name 4",
                    "amount": "21,000.32",
                    "type": "Parts",
                    "billedDate": "01/02/2017",
                    "status": "UNPAID"
                },
                {
                    "customerName": "Customer Name 5",
                    "amount": "32,000.00",
                    "type": "Maintenance",
                    "billedDate": "01/03/2017",
                    "status": "PAID"
                }
            ]
        }
    };

    $scope.selected = 99;

    $scope.select = function(index) {
        $scope.selected = index;
        $scope.showDiv = false;
    };
});

app.filter('unique', function() {
    return function(collection, keyname) {
        var output = [],
            keys = [];

        angular.forEach(collection, function(item) {
            var key = item[keyname];
            if (keys.indexOf(key) === -1) {
                keys.push(key);
                output.push(item);
            }
        });
        return output;
    };
});