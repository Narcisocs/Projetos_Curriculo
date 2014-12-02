<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Usuários</title>
		
		<script type="text/javascript">
			function submitForm(act, id){
				document.getElementById("usuarioId").value = id;
				document.getElementById("theForm").action="${ pageContext.request.contextPath}/users/" + act;
				form.submit();
				return true;
			}
			/*function prepareForAdd(){
				//
				document.getElementById("theForm").action="usuarioEditar_Inserir.jsp";
				return true;
			}		
			function prepareForDelete(usuarioId) {
				document.getElementById("usuarioId").value = usuarioId;
				document.getElementById("theForm").action = "users/delete";
				return true;
			}
			function prepareForEdit(usuarioId) {
				document.getElementById("usuarioId").value = usuarioId;
				document.getElementById("theForm").action = "users/editar";
				return true;
			}*/
		</script>
		
		<style type="text/css">
			section{
				border:1px solid black;
				width:700px;
				display:block;
			}
			
			article{
				border:1px solid black;
				width:700px;
			}
			
			article header{
				text-align:right;
			}
			
			.coloreTable{
				color:White;
				background-color:Black;
				font-weight:bold;
			}
			
			/*article th{
				font-weigth:bold;
				border:1px solid black;
				width:160px;
				background-color:silver;
			}*/
			
			article table{
				width:700px;
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
					<div class="info">O Usuário foi removido com sucesso.</div>
				</c:when>
				<c:when test="${ table.newItem ne null }">
					<div class="info">O Usuário "${ table.newItem.nome}" foi
						adicionado com sucesso.</div>
				</c:when>
				<c:when test="${ table.updatedItem ne null }">
					<div class="info">O Usuário "${ table.updatedItem.nome }"
						foi modificado com sucesso.</div>
				</c:when>
			</c:choose>
			</hgroup>
		</header>
		<section>
				<article>
					<form id="theForm" method="post">
					<header>
						<!--  <input type="submit" name="NovoUsuario" value="NovoUsuario" onClick="return submitForm('new','');"/>-->
						<a href="users/add">Novo Usuário</a>
					</header>
					<table border="1" >
						<tr class="coloreTable">
							<th>
								Email
							</th>
							<th>
								Nome
							</th>
							<th>
								Perfil
							</th>
							<th>
								Ativo
							</th>
							<th>
							</th>
						</tr>
						<c:forEach var="user" items="${ table.items }">
						<tr>
							<td>${ user.email }</td>
							<td>${ user.nome }</td>
							<td>${ user.tipo_usuario eq "A" ? "Administrador" : "Usuário" }</td>
							<td>${ user.ativo eq "S" ? "Sim" : "Não" }</td>
							<td>
								<input type="submit" name="Editar" value="Editar" onclick="return submitForm('edit', ${user.id});"/><!--  onclick="return prepareForEdit(${ user.id });"/>-->   
								<input type="submit" name="Excluir" value="Excluir" onclick="return submitForm('delete', ${user.id});"/><!-- onclick="return prepareForDelete(${ user.id });"/>-->
							</td>
						</tr>
						</c:forEach>
					</table>
					<input id="usuarioId" type="hidden" name="id" />
				</form>
			</article>
		</section>
	</body>
</html>