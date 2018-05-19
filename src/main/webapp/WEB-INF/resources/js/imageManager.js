var imageCount;

function updateImageCount () {
    if (imageCount <= 0) {
        $("#uploader-label").text("No file chosen");
        $("#images-table").hide();
    }
    else if (imageCount == 1) {
        $("#uploader-label").text("Chosen 1 file");
        $("#images-table").show();
    }
    else {
        $("#uploader-label").text("Chosen " + imageCount + " files");
        $("#images-table").show();
    }
}

function removeImage (element) {
    //handle featured image
    if(element.parents(".newRow").find("#featuredImage").is(':checked')){
        $("input:radio[name='featuredImage']").each(function(){
            if(!$(this).is(':checked')){
                $(this).prop("checked", true);
                return false;
            }
        });
    }
    this.
        imageCount--;
    updateImageCount();
    element.parents(".newRow").remove();
}

$(document).ready(function () {
    //init image count
    imageCount = 0;
    updateImageCount();

    //display selected images
    document.getElementById('productFiles').onchange = function () {
        $("#images-table > tbody").html("");
        imageCount = 0;

        if (this.files != null && this.files.length > 0) {
            for (var i = 0; i < this.files.length; i++) {
                var markup = "<tr class='newRow'>" +
                    "<td><img id='" + i +"' src='#' alt='Image' style='width: 100px; height: 100px; object-fit: cover;'></td>" +
                    "<td><p>" + this.files.item(i).name + "</p></td>" +
                    "<td><p>" + this.files.item(i).size + "</p></td>" +
                    "<td><label class='checkbox-inline'><input type='radio' name='featuredImage' id='featuredImage' value='" + i + "'/></label></td>" +
                    "<td><button class='btn btn-danger' type='button' onclick='removeImage($(this));'>Remove</button></td>" +
                    "</tr>";

                $("#images-table > tbody").append(markup);
                imageCount++;

                var reader = new FileReader();
                const imgId = i;

                reader.onload = function (e) {
                    $("#images-table img[id='"+imgId.toString()+"']").attr('src', e.target.result);
                };

                reader.readAsDataURL(this.files[i]);
            }

            $("input:radio[name='featuredImage']").filter("[value='0']").prop("checked", true);
        }
        updateImageCount();
    };
});
