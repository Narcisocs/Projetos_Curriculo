<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Tarefas</title>
		<script type="text/javascript">
			function submitForm(act, id){
				document.getElementById("tarefaId").value = id;
				document.getElementById("theForm").action="${ pageContext.request.contextPath}/tars/" + act;
				form.submit();
				return true;
			}
		</script>
		
		<style type="text/css">
			section{
				border:1px solid black;
				width:700px;
			}
			
			article{
				border:1px solid black;
				width:700px;
			}
			
			article a{
				float:right;
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
			
			.div1{
				float:left;
			}
			
			.div2{
				float:left;
				/*padding:0px 0px 0px 50px;*/
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
					<c:if test="${ table.projResp }">
					<a href="${ pageContext.request.contextPath}/tars/add"><button>Nova Tarefa</button></a>
					</c:if>
				<form id="theForm" method="post">
				<header>
					<div class="div1">
						<c:if test="${ userLogado.tipo_usuario eq 'A'}">
						<select name="cbProjeto">
							<option value="0">Selecione...</option>
							<c:forEach var="projs" items="${ table.listaDeProjetos }">
								<option value="${ projs.id }">${ projs.nome }</option>
							</c:forEach>
						</select>
						</c:if>
						<c:if test="${ table.projResp }">
						<select name="cbResponsável">
							<option value="0">Selecione...</option>
							<c:forEach var="users" items="${ table.listaDeUsuarios }" >
								<option value="${ users.id }">${ users.nome }</option>
							</c:forEach>
						</select>
						</c:if>
					</div>
					<div class="div2">
						<input type="submit" value="Pesquisar" />
					</div>
					<!-- <input type="submit" value="Nova Tarefa" /> -->
				</header>
				<table>
					<tr class="coloreTable">
						<th>
							Número
						</th>
						<th>
							Resumo
						</th>
						<c:if test="${ table.projResp }">
						<th>
							Responsável
						</th>
						</c:if>
						<th>
							Situação
						</th>
						<th>
							Tempo Gasto
						</th>
						<th>
							Previsto
						</th>
					</tr>
					<c:forEach var="tars" items="${ table.items }">
					<tr>
						<td>
							${ table.items.id }
						</td>
						<td>
							${ table.items.resumo }
						</td>
						<c:if test="${ table.projResp }">
						<td>
							${ table.items.usu_resp.nome }
						</td>
						</c:if>
						<td>
							${ table.items.status }
						</td>
						<td>
							${ table.items.tempo_gasto }
						</td>
						<td>
							${ table.items.tempo_previsto }
						</td>
						<td>
							<input type="submit" name="visualizar" value="Visualizar" onclick="return submitForm('view', ${table.items.id});" /> 
						</td>
					</tr>
					</c:forEach>
				</table>
				<footer>
					<input type="image" name="retroTotal" src="${pageContext.servletContext.contextPath}/pages/Imagens/retroceder_total_recortado.png" />
					<input type="image" name="retroParcial" src="${pageContext.servletContext.contextPath}/pages/Imagens/Retroceder_parcial_recortado.png" />
					Exibindo páginas ${ table.currentPage } de ${ table.totalPages }
					<input type="image" name="avancParcial" src="${pageContext.servletContext.contextPath}/pages/Imagens/avancar_parcial.png" />
					<input type="image" name="avancTotal" src="${pageContext.servletContext.contextPath}/pages/Imagens/avancar_total.png" />
				</footer>
				<input id="tarefaID" name="id" type="hidden" />
				<input type="hidden" name="pageIndex" value="${ table.currentPage }"/>
				</form>
			</article>	
			<footer>
			</footer>
		</section>
	</body>
</html>