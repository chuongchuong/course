app.controller("cart-ctrl", function($scope, $http){
	// quản lý giỏ hàng

	var $cart = $scope.cart = {
        items: [],
        add(id){ // thêm sản phẩm vào giỏ hàng
        	var item = this.items.find(item => item.id == id);
                    if(item){
                        alert("Khoá học đã có trong giỏ hàng");
                    }
                    else{
                    	$http.get(`/rest/courses/${id}`).then(resp => {
                            if(resp.data!==null && resp.data !== ""){
                                this.items.push(resp.data);
                                this.saveToLocalStorage();
                                alert("Đã thêm khoá học vào giỏ hàng");
                            }else{
                                alert("Bạn đã mua khoá học này rồi");
                            }
                    	}).catch(error => {
                               console.error("Error fetching data:", error);
                               alert("Có lỗi xảy ra khi tải dữ liệu");
                           });
                    }
        },
        remove(id){ // xóa sản phẩm khỏi giỏ hàng
        var index = this.items.findIndex(item => item.id == id);
                             this.items.splice(index, 1);
                             this.saveToLocalStorage();

        },
        clear(){ // Xóa sạch các mặt hàng trong giỏ

                this.items = []
                this.saveToLocalStorage();

        },
        amt_of(item){ // tính thành tiền của 1 sản phẩm
            return item.price;
        },
        get count(){ // tính tổng số lượng các mặt hàng trong giỏ
            return this.items.length;
        },
        get amount(){ // tổng thành tiền các mặt hàng trong giỏ
            return this.items.reduce((total, item) => total += this.amt_of(item), 0);
        },
        addVoucher(){ // tổng thành tiền các mặt hàng trong giỏ
            // Tính tổng số tiền từ các mặt hàng trong giỏ
            const totalAmount = this.items.reduce((total, item) => total += this.amt_of(item), 0);

            if(voucher !=0){
                const finalAmount = totalAmount -  voucher;


                // Trả về tổng số tiền sau khi trừ giảm giá
                return finalAmount;
            }else{
                // Trừ đi số lượng giảm giá (giam) từ tổng số tiền
                const finalAmount = totalAmount - (totalAmount*percent/100) ;


                // Trả về tổng số tiền sau khi trừ giảm giá

                return finalAmount;
            }


        },
        saveToLocalStorage(){ // lưu giỏ hàng vào local storage
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        loadFromLocalStorage(){ // đọc giỏ hàng từ local storage
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },

    }

    $cart.loadFromLocalStorage();
    	// Đặt hàng
    	$scope.order = {
    			get orderDetails(){
    				return $cart.items.map(item => {
    					return {
    						course:{id: item.id},
    						price: item.price
    					}
    				});
    			},
    			purchase(tien){
    				var order = angular.copy(this);
                    console.log(idVoucher);
                    if(idVoucher==null){
                        idVoucher=0;
                    }
                    console.log(idVoucher);
                    console.log(tien);
    				// Thực hiện đặt hàng
    				$http.post(`/rest/orders/`+tien+`/`+idVoucher,order).then(resp => {
    					alert("Chuyển đến trang thanh toán!");
    					//$cart.clear();
    					location.href = "/api_payment/create_payment";
    				}).catch(error => {
    					alert("Đặt hàng lỗi!");
    					console.log(error)
    				})

    			}
    	}


});
