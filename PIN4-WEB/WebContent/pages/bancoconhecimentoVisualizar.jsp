<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Visualização de Conecimento</title>
		<script type="text/javascript">
			function submitForm(act, id){
				document.getElementById("tarefaId").value = id;
				document.getElementById("theForm").action="${ pageContext.request.contextPath}/banco/" + act;
				form.submit();
				return true;
			}
		</script>
		<style type="text/css">
			section{
				border:1px solid black;
				width:500px;
			}
			
			article{
				border:1px solid black;
				width:500px;
			}
			
			.style1{
				padding:0px 0px 0px 100px;
			}
			
			.style1 button{
				margin:0px 0px 0px 300px;
			}
		</style>
	</head>
	<body>
		<header>
			<hgroup>
				<h1>PIN WEB - ${TP_USUARIO}</h1>
				<h1>PIN4 WEB - ${ userLogado.tipo_usuario eq "A" ? "Administrador" : "Usuário" }</h1>
				<jsp:include page="menu.jsp"></jsp:include>
			</hgroup>
		</header>
		<section>
			<article>
				<form>
				<dl>
					<dt>
						<label>Tecnologia: ${table.updatedItem.tecnologia.nome}</label>
						<label class="style1">Código: ${table.updatedItem.id}</label>
					</dt>
					<dt>
						<label>${table.updatedItem.resumo}</label>
					</dt>
					<dt>
							<label>Por ${table.updatedItem.usu_contribuidor.nome} em ${table.updatedItem.cadastro} </label>
					</dt>
					<hr />
				</dl>
				</form>
				<a href="${ pageContext.request.contextPath}/banco/"><button>Fechar</button></a>
			</article>
		</section>
	</body>
</html>