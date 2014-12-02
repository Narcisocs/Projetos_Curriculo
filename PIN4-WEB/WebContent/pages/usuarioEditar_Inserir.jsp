<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<title>Tela de Inserção/Edição de Usuários</title>
		<style type="text/css">
			section{
				display:block;
			}
			
			article{
				display:inline-block;
				border:1px solid black;
				width:400px;
				padding:20px;
				text-align:left;
			}
			
			article form label, article form input, article form select{
				display:block;
			}
			
			input[type='checkbox']{
				float:left;
			}
			
			input[type='submit']{
				float:right;
			}
			
			input[type='checkbox'] + label{
				clear:right;
			}
		</style>
	</head>
	<body>
		<header>
			<hgroup>
				<h1>PIN4 WEB - ${ userLogado.tipo_usuario eq "A" ? "Administrador" : "Usuário" }</h1>
				<jsp:include page="menu.jsp"></jsp:include>
			</hgroup>
		</header>
		<section>
				<article>
					<header>
						<hgroup>
					<c:if test="${ table eq null }">
						<h1>Novo Usuário</h1>

						<c:set var="action" scope="page" value="new" />

					</c:if>

					<c:if test="${ table ne null }">
						<h1>Editando Usuário: ${ table.items[0].nome }</h1>

						<c:set var="action" scope="page" value="update" />
					</c:if>
					</hgroup>
				</header>
			<form method="post" action="${ pageContext.request.contextPath }/users/${ action }">
				<label for="inputEmail">E-mail</label>
				<input id="inputEmail" name="email" type="email" placeholder="Digite o e-mail..." value="${ table ne null ? table.items[0].email : '' }"/>
				<label for="inputNomeCompleto">Nome Completo</label>
				<input id="inputNomeCompleto" name="nome" type="text" placeholder="Digite o nome completo..." value="${ table ne null ? table.items[0].nome : '' }"/>
				<label for="inputPassword">Senha</label>
				<input id="inputPassword" name="senha" type="password" placeholder="Digite a senha..." value="${ table ne null ? table.items[0].senha : ''}"/>
				<label id="inputPerfil">Perfil</label>
				<select name="cbPerfil">
					<option value="0">Selecione...</option>
					<c:choose>
					<c:when test="${ table ne null }">
						<option value="A" ${ table.items[0].tipo_usuario eq "A" ? "selected" : "" }>Administrador</option>
						<option value="U" ${ table.items[0].tipo_usuario eq "U" ? "selected" : "" }>Usuário</option>
					</c:when>
					<c:otherwise>
						<option value="A" >Administrador</option>
						<option value="U" >Usuário</option>
					</c:otherwise>
					</c:choose>
				</select>
				<c:choose>
					<c:when test="${ table ne null }">
						<input type="checkbox" name="ativo" value="S" ${ table.items[0].ativo eq 'S' ? 'checked' : '' }/><label>Ativo?</label>
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="ativo" value="S"/><label>Ativo?</label>
					</c:otherwise>
				</c:choose>
				<input type="submit" value="Salvar" />
				<!-- <input type="submit" value="Cancelar"/> -->
				<c:if test="${ table ne null }">
					<input type="hidden" name="id" value="${ table.items[0].id }"/>
				</c:if>
			</form>
				<a href="${ pageContext.request.contextPath}/users/"><button>Cancelar</button></a>
			</article>
		</section>
	</body>
</html>