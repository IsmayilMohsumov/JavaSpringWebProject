



$(document).ready(
    function() {


        // SUBMIT FORM
        $("#add-form").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = $("#skillName").val();

            alert(formData);

            // DO POST
            $.ajax({
                type : "POST",
                // contentType : "application/json",
                url : "addSkill",
                data : 'skillName='+formData,
                // dataType : 'json',
                success : function(name) {
                    // $("form").submit(function(e){
                        e.preventDefault();
                        // var name = $("input[name='skillName']").val();
                        // var email = $("input[name='email']").val();

                        $(".data-table tbody").append("<tr data-name='"+name+"'><td>"+name+"</td><td><button class='btn btn-info btn-xs btn-edit'>Edit</button><button class='btn btn-danger btn-xs btn-delete'>Delete</button></td></tr>");

                        $("input[name='skillName']").val('');
                        // $("input[name='email']").val('');
                    // });


                    if (name!=null) {
                        $("#success").html(
                            ""+name);
                    } else {
                        $("#success").html("<strong>Error</strong>");
                    }
                    console.log(name);
                },
                error : function(data) {
                    alert("Error!")
                    console.log("ERROR: ", data);
                }
            });

        }

    })