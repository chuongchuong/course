document.addEventListener('DOMContentLoaded', function() {
    function updateCountdown() {
        const saleEndDateElements = document.querySelectorAll('.sale-end-date');
        const timeRemainingElements = document.querySelectorAll('.time-remaining');

        for (let i = 0; i < saleEndDateElements.length; i++) {
            const saleEndDate = new Date(saleEndDateElements[i].value).getTime();
            const now = new Date().getTime();
            const distance = saleEndDate - now;

            if (distance <= 0) {
                timeRemainingElements[i].innerText = 'Kết thúc giảm giá';
            } else {
                const days = Math.floor(distance / (1000 * 60 * 60 * 24));
                const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                const seconds = Math.floor((distance % (1000 * 60)) / 1000);

                timeRemainingElements[i].innerText = 'Giảm giá kết thúc sau: '+ `${days}d ${hours}h ${minutes}m ${seconds}s`;
            }
        }
    }

    setInterval(updateCountdown, 1000);
    updateCountdown();
});