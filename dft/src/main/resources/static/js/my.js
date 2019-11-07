$(function() {
    $("#myInput").on("input", function () {
        $("#mySpan").text($("#myInput").val() );
    })
})