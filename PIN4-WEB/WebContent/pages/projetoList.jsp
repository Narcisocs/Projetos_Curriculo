<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Projetos</title>
		<script type="text/javascript">
			function submitForm(act, id) {
				document.getElementById("projetoID").value = id;
				document.getElementById("theForm").action = "${ pageContext.request.contextPath}/projs/" + act;
				form.submit();
				return true;
			}
		</script>
		<style type="text/css">
			section{
				border:1px solid black;
				width:600px;
			}
			
			article{
				border:1px solid black;
				width:600px;
			}
			
			article a{
				float:right;
			}
			
			.coloreTable{
				color:White;
				background-color:Black;
				font-weight:bold;
			}
			
			article table{
				width:600px;
				color:Black;
				background-color:White;
				border-color:#999999;
				border-width:1px;
				border-style:Solid;
				font-family:arial;
				font-size:8pt;
				border-collapse:collapse;
			}
		</style>
	</head>
	<body>
		<header>
			<hgroup>
				<h1>PIN4 WEB - ${ userLogado.tipo_usuario eq "A" ? "Administrador" : "Usuário" }</h1>
			<jsp:include page="menu.jsp"></jsp:include>
			<c:choose>
				<c:when test="${ table.itemDeleted }">
					<div class="info">O Projeto foi removido com sucesso.</div>
				</c:when>
				<c:when test="${ table.newItem ne null }">
					<div class="info">O Projeto "${ table.newItem.nome}" foi
						adicionado com sucesso.</div>
				</c:when>
				<c:when test="${ table.updatedItem ne null }">
					<div class="info">O Projeto "${ table.updatedItem.nome }"
						foi modificado com sucesso.</div>
				</c:when>
			</c:choose>
			</hgroup>
		</header>
		<section>
			<article>
			<a href="${ pageContext.request.contextPath}/projs/add"><button>Novo Projeto</button></a>
			<form id="theForm" method="post">
				<header>
					<!--  <input type="submit" value="Novo Projeto" />-->
				</header>
				<table>
					<tr class="coloreTable">
						<th>
							Código
						</th>
						<th>
							Título
						</th>
						<th>
							Responsável
						</th>
						<th>
							Tecnologia
						</th>
						<th>
							Orçamento
						</th>
					</tr>
					<c:forEach var="projs" items="${ table.items }">
					<tr>
						<td>
							${ projs.titulo }
						</td>
						<td>
							${ projs.nome }
						</td>
						<td>
							${ projs.usu_resp.nome }
						</td>
						<td>
							${ projs.tecnologia.nome }
						</td>
						<td>
							${ projs.orcamento }
						</td>
						<td>
							<input type="submit" name="Editar" value="Editar" onclick="return submitForm('edit', ${projs.id});"/><!--  onclick="return prepareForEdit(${ user.id });"/>-->   
							<input type="submit" name="Excluir" value="Excluir" onclick="return submitForm('delete', ${projs.id});"/><!-- onclick="return prepareForDelete(${ user.id });"/>-->
						</td>
					</tr>
					</c:forEach>
				</table>
				<input id="projetoID" name="id" type="hidden" />
			</form>
			</article>
		</section>
	</body>
</html>