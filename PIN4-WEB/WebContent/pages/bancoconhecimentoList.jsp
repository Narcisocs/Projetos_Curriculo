<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Base de Conhecimento</title>
		<script type="text/javascript">
			function submitForm(act, id){
				document.getElementById("conhecimentoId").value = id;
				document.getElementById("theForm").action="${ pageContext.request.contextPath}/banco/" + act;
				form.submit();
				return true;
			}
		</script>
		<style type="text/css">		
			section{
				border:1px solid black;
				width:800px;
			}
			
			article{
				border:1px solid black;
				width:800px;
			}
			
			footer a{
				padding:20px 0px 30px 20px;
			}
			
			footer span{
				font-weight:bold;
				padding:10px 0px 30px 20px;
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
			
			.alinhaDireita{
				text-align:right;
			}
			
			#div1{
				float:left;
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
					<div class="info">A tarefa foi removida com sucesso.</div>
				</c:when>
				<c:when test="${ table.newItem ne null }">
					<div class="info">A Tarefa "${ table.newItem.nome }" foi
						adicionada com sucesso.</div>
				</c:when>
				<c:when test="${ table.updatedItem ne null }">
					<div class="info">A tarefa "${ table.updatedItem.nome }" foi
						modificada com sucesso.</div>
				</c:when>
			</c:choose>
			</hgroup>
		</header>
		<section>
			<article>
				<header>
					<div id="div1">
						<select name="cbTecnologia">
							<option value="0">Selecione...</option>
							<c:forEach var="tecs" items="${ table.listaDeTecnologias }">
								<option value="${ tecs.id }">${ tecs.nome }</option>
							</c:forEach>
						</select>
					</div>
					<div class="alinhaDireita">
						<a href="${ pageContext.request.contextPath}/banco/add"><button>Novo Registro</button></a>
					</div>
				</header>
				<form>
				<button id="pesquisarBase" type="button">Pesquisar</button>
				<table>
					<tr class="coloreTable">
						<th>
							Código
						</th>
						<th>
							Tecnologia
						</th>
						<th>
							Contribuidor
						</th>
						<th>
							Resumo
						</th>
						<th>
							Data
						</th>
					</tr>
					<c:forEach var="bc" items="${ table.items }">
					<tr>
						<td>
							${ bc.descricao }
						</td>
						<td>
							${ bc.tecnologia.nome }
						</td>
						<td>
							${ bc.usu_contribuidor.nome }
						</td>
						<td>
							${ bc.resumo }
						</td>
						<td>
							${ bc.cadastro }
						</td>
						<td>
							<input type="submit" name="visualizar" value="Visualizar" onclick="return submitForm('view', ${bc.id});" />
						</td>
					</tr>
					</c:forEach>
				</table>
				<footer>
					<input type="image" src="${pageContext.servletContext.contextPath}/pages/Imagens/retroceder_total_recortado.png" />
					<input type="image" src="${pageContext.servletContext.contextPath}/pages/Imagens/Retroceder_parcial_recortado.png" />
					Exibindo páginas ${table.currentPage } de ${ table.totalPages }
					<input type="image" src="${pageContext.servletContext.contextPath}/pages/Imagens/avancar_parcial.png" />
					<input type="image" src="${pageContext.servletContext.contextPath}/pages/Imagens/avancar_total.png" />
				</footer>
			<input id="conhecimentoID" type="hidden" />
			</form>
			</article>
		</section>
	</body>
</html>