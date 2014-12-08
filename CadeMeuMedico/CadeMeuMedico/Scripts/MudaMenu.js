$(document).ready(function () {
    $('#Menu').on('click', 'li', function () {
        $(this).addClass('active').siblings().removeClass('active');
    });
});