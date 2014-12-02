<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset=”UTF-8”>
		<title>Login</title>
		<script type="text/javascript">
			function checa(){
				var senha = document.getElementById("inputPasswordNU").value;
				var cSenha = document.getElementById("inputPasswordCNU").value;
				var form = document.getElementById("formNovo");
				var span = document.getElementById("checagem");
				
				
				if(String(senha) !== String(cSenha)){
					alert(senha + " " + cSenha);
					span.style.display="";
				}else
					form.submit();
			}
		</script>
		<style type="text/css">
			article{
				float:left;
				margin:10px;
				padding:10px 30px 50px 15px;
				border:2px solid black;
				height:250px;
				width:400px;
			}
			
			article form label, article form input  {
				display:block;
			}
			
			article span{
				font-size:15px;
				color:red;
			}
		</style>
	</head>
	<body>
		<header>
			<hgroup>
				<h1>PIN WEB</h1>
			</hgroup>
		</header>
		<section>
			<article>
				<form method="post" action="${ pageContext.request.contextPath }/login">
				<!--  <form method="post" action="/PIN4-WEB/pages/login">-->
					<h2><label for="lbUsuarioCadastrado">Usuário Cadastrado</label></h2>
					<label for="inputEmail">E-mail</label>
					<input name="inputEmailUC" type="email" placeholder="Digite o seu e-mail..." />
					<label for="inputPassword">Senha</label>
					<input name="inputPasswordUC" type="password" placeholder="Digite a sua senha..." />
					<input type="submit" name="Entrar" value="Entrar"> 
					<c:if test="${ failed }">
							<span>Usuário ou senha incorretos</span>
					</c:if>
				</form>
			</article>
			<article>
				<form id="formNovo" name="form" method="post" action="${ pageContext.request.contextPath }/users/new"> <!--  action="${ pageContext.request.contextPath }/users/new">-->
					<h2><label for="lbUsuarioCadastrado">Novo Usuário</label></h2>
					<label for="inputEmail">E-mail</label>
					<input name="email" type="email" placeholder="Digite o seu e-mail..." />
					<label for="inputNomeCompleto">Nome Completo</label>
					<input name="nome" type="text" placeholder="Digite o seu nome completo... " />
					<label for="inputPassword">Senha</label>
					<input id="inputPasswordNU" name="senha" type="password" placeholder="Digite a sua senha..." />
					<label for="inputPassword">Confirmação da Senha</label>
					<input id="inputPasswordCNU" name="inputPasswordCNU" type="password" placeholder="Confirmação da senha..." />
					<span id="checagem" style="display:none">As senhas não conferem</span>
					<input type="hidden" name="Criar" value="Criar" />
				</form>
				<button onclick="checa()">Criar</button>
			</article>
		</section>
	</body>
</html>
