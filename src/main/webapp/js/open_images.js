function readURL(input, imageId, inputId) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#' + imageId)
                .css('visibility', 'visible');
            $('#' + imageId)
                .attr('src', e.target.result)
        };

        if (inputId != null){
            $('#'+inputId+'Label').css('visibility', 'visible');
            $('#'+inputId+'Input').css('visibility', 'visible');
        }

        reader.readAsDataURL(input.files[0]);
    }
}