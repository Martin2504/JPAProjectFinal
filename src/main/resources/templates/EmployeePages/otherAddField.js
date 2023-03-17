$(document).ready(function() {
    $('#titleInput').hide();
    $("#titleName").change(function() {
        let val = $(this).val();
        if (val == 'Other') {
            $('#titleInput').show();
        } else {
            $('#titleInput').hide();
        }
    }).change();
});

function showAlert() {
    alert("The button was clicked!");
}