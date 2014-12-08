$(document).ready(function () {
    $("#status").hide();
    $("#botao-email").click(function () {
        $.ajax({
            url: "/Usuarios/RecuperaSenha",
            data: {
                Email: $("#txtEmail").val()
            },
            dataType: "json",
            type: "GET",
            async: true,
            beforeSend: function () {
                $("#status").html("Estamos pesquisando. Só um instante...");
                $("#status").show();
            },
            success: function (dados) {
                if (dados.OK) {
                    $("#status").html(dados.Mensagem);
                    setTimeout(function () {
                        window.location.href = "/Home/Index"
                    }, 5000);
                }
                else {
                    $("#status").html(dados.Mensagem);
                    $("#status").show();
                }
            },
            error: function (dados) {
                $("#status").html(dados.Mensagem);
                $("#status").show();
            }
        });
    });
});