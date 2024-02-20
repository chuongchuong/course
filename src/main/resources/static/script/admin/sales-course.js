$(document).ready(function() {

    // Event delegation for handling the click event on "EDIT" buttons
    $('#datatable-buttons').on('click', '.edit-btn', function() {
        var row = $(this).closest('tr'); // Get the parent row of the clicked button

        // Retrieve data from the clicked row
        var courseName = row.find('td:eq(0)').text();
        var salePercent = row.find('td:eq(5)').text().replace('%', '');
        var saleEndDate = row.find('td:eq(6)').text();
        var courseidhidden = row.find('td:eq(7)').text();
        var saleCourseId = row.find('td:eq(8)').text();
        // Set the values in the form fields
        $('#courseName').val(courseName);
        $('#salePercent').val(salePercent);
        $('#saleEndDate').val(formatDate(saleEndDate)); // Call formatDate function


        document.getElementById("createForm").formAction = "/admin/sales/create/" +courseidhidden;
        document.getElementById("updateForm").formAction = "/admin/sales/update/" +saleCourseId;
    });


    function formatDate(dateString) {
        var parts = dateString.split('/');
        if (parts.length === 3) {
            return parts[2] + '-' + parts[1] + '-' + parts[0]; // Convert to 'YYYY-MM-DD' format
        }
        return dateString; // Return original if not in expected format
    }
    var app = angular.module('myApp', []);
    app.controller('saleCtrl', function ($scope) {





    });
});

