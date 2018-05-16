$(document).ready(function(){
    angular.bootstrap(document.getElementById("importExcelis"), ['importExportApp']);

    var modalConfirm = function(callback) {

        // For confirmation if user is sure to download file!
        $("#btn-confirm").on("click", function () {
            $("#my-modal").modal('show');
        });

        $("#modal-btn-yes").on("click", function () {
            callback(true);
            $("#my-modal").modal('hide');
        });

        $("#modal-btn-no").on("click", function () {
            callback(false);
            $("#my-modal").modal('hide');
        });
    };

    modalConfirm(function(confirm){
        if(confirm){
            angular.element(document.getElementById('importExcelis')).scope().downloadExcelFile();
            doAsync();
            // If administrator confirmed!
            //angular.element(document.getElementById('myCtrl')).scope().$apply("submit()")
            //window.location = "/admin/generateProductExcel/products/?type=xls";
        }else{
            // If administrator canceled! Execute logic here...
        }
    });

    var modalNotification = function(callback) {
        // For download notification, that excel document is ready

        $("#download-btn-yes").on("click", function () {
            callback(true);

            $("#download-modal").modal('hide');
        });

        $("#download-btn-no").on("click", function () {
            callback(false);
            $("#download-modal").modal('hide');
        });
    };

    modalNotification(function(confirm){
        // If user now want to download file!
        if(confirm){
            setCookie("showDownloadNoti", "false", 2);
        }else{
            // If administrator want later download! Execute logic here...
            setCookie("showDownloadNoti", "false", 2);
        }
    });

});

$(document).ready(function(){
    if(getCookie("showDownloadNoti") == "true")
    {
        $("#download-modal").modal('show');
    }

    if(getCookie("finsihedFileDownload") == "false")
    {
        doAsync();
    }
});

function timeoutPromise (time) {
    return new Promise(function (resolve) {
        setTimeout(function () {
            resolve(Date.now());
        }, time)
    })
}

function doSomethingAsync () {
    return timeoutPromise(1000);
}

async function doAsync () {
    setCookie("finsihedFileDownload", "false", 2);
    var start = Date.now(), time;
    while(true) {
        time = await
        doSomethingAsync();
        angular.element(document.getElementById('importExcelis')).scope().checkAvailability();
        readyToDownload = angular.element(document.getElementById('importExcelis')).scope().returnedData;

        if (readyToDownload == "true") {
            $("#download-modal").modal('show');
            setCookie("showDownloadNoti", "true", 2);
            setCookie("finsihedFileDownload", "true", 2);
            break;
        }
    }
}
function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function eraseCookie(name) {
    document.cookie = name +'=; Max-Age=-99999999;';
}