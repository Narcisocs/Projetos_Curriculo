<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Design.aspx.cs" Inherits="Design" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Gerenciador de Mensagens SJ</title>
    <style>
        #usersContainer
        {
            width:465px;
            overflow-x:auto;
        }
        #messages
        {
            list-style:none;
        }
        /*Começo do CSS dos da lista de usuário*/
        .usuario
        {
            position:relative;
            left:10px;
            height:45px;
            width:140px;
        }
        .usuario_img
        {
            position:absolute;
        }
        .usuario_nome
        {
            position:absolute;
            top:5px;
            left:40px;
            font-weight:bold;
        }
        .usuario_ult_msg
        {
            font-size:8px;
            position:absolute;
            top:15px;
            left:40px;
        }
        .usuario_data
        {
            font-size:8px;
            position:absolute;
            top:5px;
            right:5px;
        }
        .selecionado
        {
            color:white;
            background-color:#6699FF !important;
        }
        /*Fim do CSS do usuário*/
        .imagem
        {
            padding: 5px 10px 5px 5px;
        }
        #box
        {
            position: absolute;
            top: 80px;
            width: 465px;
            height: 100px;
            font: normal 2 verdana;
            border: solid;
            display: none;
            overflow-y: auto;
        }
        /*Começo do CSS das mensagens*/
        .mensagem_container
        {
            position:absolute;
            top:10px;
            left:10px;
        }
        .msg_usuario
        {   
	        position:relative;
            height:40px;
        }
        .msg_imagem
        {
            position:relative;
            /*border-radius: 50%;
            overflow:hidden;*/
        }
        .msg_nome
        {
            position:absolute;
            top:-2px;
            left:35px;
            color:darkblue;
            font-weight:bold;
            clear:right;
            font-family:Arial;
            font-size:10px;
            width:400px;
        }
        .msg_resposta
        {
	        position:absolute;
	        top:10px;
            left:35px;
            height:30px;
            width:400px;
            font-family:Arial;
            font-size:8px;
            word-wrap: break-word;
        }
        .msg_data
        {
            position:absolute;
            top:-1px;
            left:385px;
            font-family:Arial;
            font-size:8px;
        }
        .msg_resposta > span > p
        {
            margin:0px;
            padding:0px;
        }
        #msg
        {
            position:absolute;
        }
        #restantes
        {
            font-family:Arial;
            font-size:10px;
        }
        .msg_para
        {
            position:relative;
            width:auto;
            height:10px;
        }
        /*Fim do CSS das mensagens*/
        /*Css da caixa de texto dos destinatários*/
        #destinatarios
        {
            width:auto;
        }
        #destinatarios .usuario_destino
        {
            float:left;
            margin: 0px 2px 0px 2px;
            font-family:Arial;
            font-size:8px;
            width:auto;
            padding:5px;
            background-color:#94f2e1;
            border-radius:2px;
        }
        .selected {
            background-color: #17d9cd;   
        }
        .invisivel
        {
            display:none !important;
        }
        /*Fim do Css da caixa de texto dos destinatários*/
        /*Começo do CSS da lista de usuario pesquisa*/
        #usuario_search
        {
            font-family:Arial, sans-serif;
            font-size:8px;
            width:135px;
        }
        #usuario_list
        {
            position:relative;
            list-style: none;
            list-style-position: inside; 
            padding: 0; 
            margin-top: 0px; 
            display: block; 
        }
        #usuario_list li
        {
            position:relative;
            left:5px;
        }
        #usuario_list li:hover, li.active
        {
            background: #17d9cd;
        }
        #usuario_list .usuario_pesq
        {
            position:relative;
            left:-5px;
            height:45px;
            width:120px;
        }
        #usuario_list .usuario_pesq_img
        {
            position:absolute;
            left:5px;
        }
        #usuario_list .usuario_pesq_nome
        {
            position:absolute;
            top:10px;
            left:45px;
        }
        /*Fim do CSS da lista de usuario pesquisa*/
    </style>
    <script src="js/jquery-1.11.0.js" type="text/javascript"></script>
    <script src="js/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
    <script src="js/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            //declaração das variáveis necessárias
            var id = 0;
            var id_user = 0;
            var texto;
            var linhas;
            var $restantes = $('#restantes');
            //var $usuario_list_li = $('#usuario_list li');
            var $usuario_list = $('#usuario_list');
            var $usuario_search = $('#usuario_search');
            //fim das declarações

            //Começo do evento para "cortar" as mensagens
            $('#listaUsuario .usuario .usuario_ult_msg').each(function () {
                if ($(this).html().length > 20)
                    $(this).html($(this).html().toString().substr(0, 30) + "...");
            });
            //Fim do evento para "cortar" as mensagens

            //Começo do evento de clique nas divs dos usuarios que mandaram as mensagens
            $('#listaUsuario').on("click", ".usuario", function () {
                $('.usuario').removeClass("selecionado");
                $(this).addClass("selecionado");

                $('#box').css("display", "block");

                PageMethods.retornaMensagens($(this).attr("data-user-id"), OnSucceeded, OnFailed);
                id = $(this).attr("chat-id");
                id_user = $(this).attr("data-user-id");
                $("#box").animate({ scrollTop: $("#box")[0].scrollHeight }, 1000);
                $('#msg').animate({ top: '200px' }, 500);
            });

            function OnSucceeded(result) {
                $('#box').html("");
                $('#box').append(result);
            }

            function OnFailed(error) {
                alert("Um erro ocorreu no primeiro PageMethod!!!");
            }
            //Fim do evento de clique nas divs dos usuarios que mandaram as mensagens

            //Começo dos eventos de movimentação do mouse
            $('#listaUsuario').on("mouseover", ".usuario", function () {
                $(this).attr("style", "background-color:#87CEFA");
            }).on("mouseleave", ".usuario", function () {
                $(this).attr("style", "background-color:white");
            });

            $('#destinatarios').on("mouseover", ".remove_tag", function () {
                $(this).attr("style", "color:white");
            }).on("mouseleave", ".remove_tag", function () {
                $(this).attr("style", "color:black");
            });
            //Fim dos eventos de movimentação do mouse

            //Começo do evento de enviar mensagem
            $('#btEnviar').click(function () {

                var today = new Date();
                var months = new Array('Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dec');

                var message = document.form1.txtMensagem.value;
                if (message.length > 20)
                    message = message.substr(0, 20) + "...";

                /*if (id === 0)
                    alert('Você deve selecionar uma conversa primeiro');
                else {*/
                if ($('#destinatarios .usuario_destino').length == 0) {
                    //alert("sem destino");
                    texto = document.form1.txtMensagem.value.split(/\s+/);
                    linhas = document.form1.txtMensagem.value.split("\n");
                    //alert($('.mensagem_container .msg_usuario:last').attr("data-user-id"));
                    PageMethods.adicionaMensagem($('.mensagem_container .msg_usuario:last').attr("data-user-id"), document.form1.txtMensagem.value, OnSucceededAdd, OnFailedAdd);

                    $("#box").animate({ scrollTop: $("#box")[0].scrollHeight }, 1000);

                    $('#listaUsuario .usuario[chat-id=' + id + '] .usuario_data').html(today.getDate() + " " + months[today.getMonth()]);

                    $('#listaUsuario .usuario[chat-id=' + id + '] .usuario_ult_msg').html(message);
                } else if ($('#destinatarios .usuario_destino').length === 1) {
                    //alert("com um destino");
                    PageMethods.adicionaMensagem($('#destinatarios .usuario_destino').attr("data-user-id"), document.form1.txtMensagem.value, OnSucceededAdd, OnFailedAdd);
                    $('#listaUsuario .usuario[data-user-id=' + $('#destinatarios .usuario_destino').attr("data-user-id") + ']').animate({ backgroundColor: '#CCFF66' }, 1000);

                    $('#listaUsuario .usuario[data-user-id=' + $('#destinatarios .usuario_destino').attr("data-user-id") + '] .usuario_ult_msg').html(message);
                    $('#listaUsuario .usuario[data-user-id=' + $('#destinatarios .usuario_destino').attr("data-user-id") + '] .usuario_data').html(today.getDate() + " " + months[today.getMonth()]);
                } else {
                    //alert("Com mais de um destino");
                    var destinatarios_id = "";
                    var destinatarios_nome = "";

                    $('#destinatarios .usuario_destino').each(function () {
                        destinatarios_id += $(this).attr("data-user-id") + ",";
                        destinatarios_nome += $(this).text().trim().replace("x","").trim() + ",";
                    });

                    destinatarios_id = destinatarios_id.substr(0, destinatarios_id.length - 1);
                    destinatarios_nome = destinatarios_nome.substr(0, destinatarios_nome.length - 1);

                    if (destinatarios_nome.length > 10)
                        destinatarios_nome = destinatarios_nome.substr(0, 10) + "...";

                    var destinatarios_div = "   <td> " +
                    "<div class='usuario' data-user-id='" + destinatarios_id + "' chat-id='" + ($('#usersContainer #listaUsuario .usuario:last').attr("chat-id") + 1) + "'> " +
                    "             <div class='usuario_img'><image class='imagem' src='../Imagens/mais_de_um.jpg' height='32px' width='32px'/></div> " +
                    "             <div class='usuario_nome'><span>" + destinatarios_nome + "</span></div> " +
                    "             <div class='usuario_ult_msg'>" + message + " </div> " +
                    "             <div class='usuario_data'>" + today.getDate() + " " + months[today.getMonth()] + "</div> " +
                    "     </div>" +
                    "   </td>";

                    $('#usersContainer #listaUsuario tr').prepend(destinatarios_div).hide().show('slow');
                }

                $('#destinatarios .usuario_destino').each(function () {
                    $(this).remove();
                });

                /*$('#listaDeUsuarios li').each(function () {
                    if ($(this).hasClass('invisivel'))
                        $(this).removeClass('invisivel');
                });*/

                $restantes.html("1500 caracteres restantes");
                document.form1.txtMensagem.value = "";
                $usuario_search.focus();
                //}
            });

            function OnSucceededAdd(result) {
                if (id_user === $('.mensagem_container .msg_usuario:last').attr("data-user-id"))
                    $('.mensagem_container').append(result);
                else
                    $('.msg_usuario:last .msg_resposta span').append(result);

                var $mensagem = $('.mensagem_container .msg_nome:last, .msg_resposta:last');

                $mensagem.animate({ backgroundColor: '#00FFFF' }, 1000).animate({ backgroundColor: 'white' }, 1000);

                var $msg_texto = $('.mensagem_container .msg_usuario:last');

                if (texto.length > 20|| linhas.length >= 2)
                    $msg_texto.css("height", "+=10px");
                else if(texto.length > 30 || linhas.length > 3)
                    $msg_texto.css("height", "+=15px");
                else if(texto.length > 40 || linhas.length > 4)
                    $msg_texto.css("height", "+=20px");
                else if (texto.length > 50 || linhas.length > 5)
                    $msg_texto.css("height", "+=25px");
            }

            function OnFailedAdd(error) {
                alert("Um erro ocorreu no segundo PageMethod!!!!!!");
            }
            //Fim do evento de enviar mensagem

            //Começo do contador de caracteres
            $("[id$=txtMensagem]").keyup(function () {
                var chars = this.value.length,
            messages = Math.ceil(chars / 1500),
            remaining = messages * 1500 - (chars % (messages * 1500) || messages * 1500);
                if (remaining != 0)
                    $restantes.html(remaining + ' caracteres restantes');
                else
                    $restantes.html(1500 + ' caracteres restantes');
            });
            //Fim do contador de caracteres

            /*Começo do código para filtragem da pesquisa*/
            //$usuario_list_li.hide();
            $usuario_list.hide();
            $usuario_search.focus();
            
            $usuario_search.on('keyup click input', function (e) {

                if ((e.keyCode >= 65 && e.keyCode <= 90) || (e.keyCode >= 97 && e.keyCode <= 122) || e.keyCode == 8) {

                    /*$usuario_list_li.each(function () {
                        if ($(this).hasClass('selected'))
                            $(this).removeClass('selected');
                    });*/

                    if (this.value.length > 0) {
                        PageMethods.buscaUsuario($usuario_search.val().toLowerCase(), OnSucceededBusca, OnFailedBusca);

                        /*$usuario_list_li.hide().filter(function () {
                            return $(this).text().toLowerCase().indexOf($usuario_search.val().toLowerCase()) != -1;
                        }).show().not('.invisivel').first().addClass('selected');*/
                    }
                    else {
                        $usuario_list.hide();
                        //$usuario_list_li.hide();
                    }
                }
            }).on('keydown', function (e) {

                if (e.keyCode == 27) {// esc
                    if (this.value.length > 0)
                        $usuario_search.val("");
                    else
                        $('#destinatarios .usuario_destino:last').remove();
                    /*$usuario_list.each(function () {
                        if ($usuario_list.hasClass('selected'))
                            $usuario_list.removeClass('selected');
                    });*/

                    //$usuario_list_li.hide();
                    $usuario_list.hide();
                }
                else if (e.keyCode == 13) { // enter
                    $("<div class='usuario_destino' data-user-id=" + $('.selected .usuario_pesq').attr('data-user-id') + "><span>" + $('.selected .usuario_pesq').text() + "</span><span class='remove_tag'>  x</span></div>").insertBefore('#destinatarios #usuario_search');

                    $usuario_search.val("");
                    //$('.selected').addClass('invisivel');

                    /*$usuario_list.each(function () {
                        if ($usuario_list_li.hasClass('selected'))
                            $usuario_list_li.removeClass('selected');
                    });*/

                    $usuario_list.hide();
                }
                else if (e.keyCode == 38) { // up
                    var selected = $(".selected");
                    $("#listaDeUsuarios li").removeClass("selected");
                    if (selected.prev().length == 0) {
                        selected.siblings().last().addClass("selected");
                    } else {
                        //if (!selected.hasClass('invisivel'))
                            selected.prev().addClass("selected");
                        //else
                          //  selected = selected.prev();
                    }
                }
                else if (e.keyCode == 40) { // down
                    var selected = $(".selected");
                    $("#listaDeUsuarios li").removeClass("selected");
                    if (selected.next().length == 0) {
                        selected.siblings().first().addClass("selected");
                    } else {
                        //if (!selected.hasClass('invisivel'))
                            selected.next().addClass("selected");
                        //else
                          //  selected = selected.next();
                    }
                }
            });

            function OnSucceededBusca(result) {
                //alert(result);
                $usuario_list.empty();
                $usuario_list.append(result);
                $usuario_list.show();
                //$usuario_list_li.first().addClass('selected');
            }

            function OnFailedBusca(error) {
                alert("Um erro ocorreu no terceiro PageMethod!!!!!!");
            }
            /*Fim do código para filtragem da pesquisa*/

            /*Começo do código para escolha de para quem será mandada a mensagem*/
            $('#listaDeUsuarios').on('click', 'li .usuario_pesq', function () {
                //alert($(this).attr('data-user-id'));
                $("<div class='usuario_destino' data-user-id=" + $(this).attr('data-user-id') + "><span>" + $(this).text() + "</span><span class='remove_tag'>  x</span></div>").insertBefore('#destinatarios #usuario_search');
                document.form1.usuario_search.value = "";
                $usuario_list.hide();
                //$(this).parent().addClass('invisivel');
                //$('#usuario_list li').hide();
            });
            /*Fim do código para escolha de para quem será mandada a mensagem*/

            /*Começo do código para apagar os destinatarios*/
            $('#destinatarios').on("click", ".remove_tag", function () {
                $('#destinatarios .usuario_destino[data-user-id=' + $(this).parent().attr("data-user-id") + ']').remove();
                //$('#listaDeUsuarios li .usuario_pesq[data-user-id=' + $(this).parent().attr("data-user-id") + ']').parent().removeClass('invisivel');
            });
            /*Fim do código para apagar os destinatarios*/

            //Começo do tratamento para evitar o submit da página
            $('#form1').bind("keyup keypress", function (e) {
                var code = e.keyCode || e.which;
                if (code == 13) {
                    e.preventDefault();
                    return false;
                }
            });
            //Fim do tratamento para evitar o submit da página
        });
    </script>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:ScriptManager ID="ScriptManager1" runat="server" EnablePageMethods="true">
        </asp:ScriptManager>
        <asp:Label ID="Label1" runat="server" Font-Bold="True" Font-Names="arial" Font-Size="8pt"></asp:Label>
    </div>
    <div id="box"></div>
    <div id="msg">
        <table>
            <tr>
                <td style="vertical-align: top">
                    <asp:Label ID="Label3" runat="server" Text="Para:" Font-Names="arial" Font-Size="8pt"></asp:Label>
                </td>
                <td>
                    <div id='destinatarios'>
                        <input type="text" id="usuario_search" placeholder='Digite para quem enviar mensagem' autocomplete="off"/>
                    </div>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <td>
                    <div id='listaDeUsuarios'>
                        <ul id='usuario_list'></ul>
                        <asp:Label ID="Label2" runat="server"></asp:Label>
                    </div>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <td>
                    <span id="restantes">1500 caracteres restantes</span>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:TextBox ID="txtMensagem" runat="server" TextMode="MultiLine" Font-Names="arial" Font-Size="8pt" MaxLength="1500"></asp:TextBox>
                </td>
                <td>
                    <input type="button" name="btn1" id="btEnviar" value="Enviar" />
                </td>
            </tr>
        </table>
    </div>
    </form>
</body>
</html>
