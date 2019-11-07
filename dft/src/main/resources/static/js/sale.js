function OnSelectionChange (select) {
    var selectedOption = select.options[select.selectedIndex];
    $("#finalPriceSpan").text(Number($("#priceSpan").text()) + Number(selectedOption.value));
}
/*
$(function(){
    $("#delivery").on("select", function(){
        $("#finalPriceSpan").text( $("#priceSpan").text() * $("#delivery").value());
    })
})*/
