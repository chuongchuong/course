var app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {

    $scope.export = function () {
        // Tạo workbook và worksheet
        var workbook = new ExcelJS.Workbook();
        var worksheet = workbook.addWorksheet('Sheet1');

        // Thêm header cho worksheet và định dạng
        var headerRow = worksheet.addRow(['Username', 'Password', 'Fullname', 'Email', 'Phonenumber']);
        headerRow.font = { bold: true }; // Tô đậm chữ
        headerRow.alignment = { horizontal: 'center' }; // Căn giữa

        // Thiết lập chiều rộng của cột (tùy chọn)
        worksheet.getColumn(1).width = 20;
        worksheet.getColumn(2).width = 20;
        worksheet.getColumn(3).width = 20;
        worksheet.getColumn(4).width = 20;
        worksheet.getColumn(5).width = 20;




     // Thêm dữ liệu từ $scope.students vào worksheet
     angular.forEach($scope.students, function (student) {
         var dataRow = worksheet.addRow([student.username, student.password, student.fullname, student.email, student.phonenumber]);
         dataRow.alignment = { horizontal: 'center' }; // Căn giữa
         dataRow.border = {
             top: { style: 'thin' },
             left: { style: 'thin' },
             bottom: { style: 'thin' },
             right: { style: 'thin' }
         }; // Định dạng đường viền
     });


        // Tạo buffer để lưu trữ file Excel
        workbook.xlsx.writeBuffer().then(function (data) {
            var blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

        worksheet.columns.forEach(function (column) {
            column.width = Math.max(column.width, 15); // Có thể điều chỉnh giá trị 15 theo ý muốn
        });

            // Sử dụng FileSaver.js để tải file xuống và xác định thư mục
          saveAs(blob, 'exported_data_User_' + new Date().toISOString() + '.xlsx', { autoBom: true, type: 'application/octet-stream' });
        });
    }


    // Lấy dữ liệu từ server
    $scope.getDataFromServer = function () {
        var url = "/rest/user";
        $http.get(url).then(function (resp) {
            $scope.students = resp.data;
        }).catch(function (error) {
            console.log("Error", error);
        });
    }

    // Gọi hàm để lấy dữ liệu từ server khi trang được load
    $scope.getDataFromServer();
});
