<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="content-header">
	<h1>
		Obras <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Lista de Obras</h3>
					<button class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#inserir${obra.idobra}">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button>
					<hr>
					<form action="buscaObra" method="get" class="">
						<div class="input-group">
							<input type="text" name="search" class="form-control"
								placeholder="Buscar..."> <span class="input-group-btn">
								<button type="submit" id="search-btn" class="btn btn-flat">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
				</div>

				<!-- /.box-header -->
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">#</th>
							<th>Título</th>
							<th>Autor</th>
							<th>Editora</th>
							<th>Assunto</th>
							<th>Ano de Publicação</th>
							<th>Exemplares</th>
							<th>Alterar</th>
							<th>Excluir</th>
						</tr>
						<c:forEach items="${obras}" var="obra">
							<tr>
								<td>${obra.idobra}</td>
								<td>${obra.titulo}</td>
								<td>${obra.autor}</td>
								<td>${obra.editora}</td>
								<td>${obra.assunto}</td>
								<td><fmt:formatDate pattern="yyyy"
										value="${obra.ano_publicacao}" /></td>
								<td>${obra.contagemDeExemplaresPorObras()}</td>
								<td><button class="btn btn-warning" data-toggle="modal"
										data-target="#modal${obra.idobra}">
										<i class="fa fa-pencil" aria-hidden="true"></i>
									</button></td>
								<td><button class="btn btn-danger" data-toggle="modal"
										data-target="#delete${obra.idobra}">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button></td>
							</tr>

							<!-- Modal -->
							<div class="modal fade modal-warning" id="modal${obra.idobra}"
								role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Alterar</h4>
										</div>
										<form role="form" method="post" class="form-horizontal"
											action="alterarObra">
											<input type="hidden" name="idobra" value="${obra.idobra}"
												readonly>
											<div class="modal-body">
												<div class="box-body">
													<div class="form-group">
														<label for="inputTitulo${obra.idobra}"
															class="col-sm-12 control-label">Título</label>

														<div class="col-sm-12">
															<input type="text" name="titulo" class="form-control"
																id="inputTitulo${obra.idobra}" value="${obra.titulo}"
																placeholder="Título" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputAutor${obra.idobra}"
															class="col-sm-12 control-label">Autor</label>
														<div class="col-sm-12">
															<select id="inputAutor${obra.idobra}"
																class="form-control" name="idautor" required>
																<option value="${obra.idautor}">${obra.autor}</option>
																<c:forEach items="${autores}" var="autor">
																	<option value="${autor.idautor}">${autor.nome_autor}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="inputEditora${obra.idobra}"
															class="col-sm-12 control-label">Editora</label>
														<div class="col-sm-12">
															<select id="inputEditora${obra.idobra}"
																class="form-control" name="ideditora" required>
																<option value="${obra.ideditora}">${obra.editora}</option>
																<c:forEach items="${editoras}" var="editora">
																	<option value="${editora.ideditora}">${editora.nome_editora}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="inputAssunto${obra.idobra}"
															class="col-sm-12 control-label">Assunto</label>
														<div class="col-sm-12">
															<select id="inputAssunto${obra.idobra}"
																class="form-control" name="idassunto" required>
																<option value="${obra.idautor}">${obra.autor}</option>
																<c:forEach items="${assuntos}" var="assunto">
																	<option value="${assunto.idassunto}">${assunto.nome_assunto}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="inputAno${obra.idobra}"
															class="col-sm-12 control-label">Ano de publicação</label>

														<div class="col-sm-12">
															<input type="number" name="data" class="form-control"
																id="inputAno${obra.idobra}"
																value="<fmt:formatDate pattern="yyyy"
										value="${obra.ano_publicacao}" />"
																min="1500" max="2099" step="1"
																placeholder="Ano de Publicação" required>
														</div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Cancelar</button>
												<button type="submit" class="btn btn-outline">Alterar</button>
											</div>
										</form>
									</div>
								</div>
							</div>
							<!-- Modal -->
							<div class="modal fade modal-danger" id="delete${obra.idobra}"
								role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Excluir</h4>
										</div>
										<form role="form" method="post" action="excluirObra">
											<div class="modal-body">
												<div class="box-body">
													<div class="form-group">
														<p>
															Tem certeza que deseja excluir <b>${obra.titulo} ?</b>
														</p>
														<h6>Caso a obra não seja deletada é necessário
															deletar os exemplares vinculadas a ela</h6>
														<div class="col-sm-12">
															<input type="hidden" name="idobra" value="${obra.idobra}"
																readonly>
														</div>
													</div>
												</div>
												<!-- /.box-body -->

											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Cancelar</button>
												<button type="submit" class="btn btn-outline">Excluir</button>
											</div>
										</form>
									</div>

								</div>
							</div>
						</c:forEach>
					</table>
				</div>


				<!-- Modal -->
				<div class="modal fade modal-primary" id="inserir${obra.idobra}"
					role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Inserir</h4>
							</div>
							<form role="form" method="post" class="form-horizontal"
								action="adicionarObra">
								<div class="modal-body">
									<div class="box-body">
										<div class="form-group">
											<label for="inputTitulo" class="col-sm-2 control-label">Título</label>

											<div class="col-sm-10">
												<input type="text" name="titulo" class="form-control"
													id="inputTitulo" placeholder="Título" required>
											</div>
										</div>
										<div class="form-group">
											<label for="inputAutor" class="col-sm-2 control-label">Autor</label>
											<div class="col-sm-10">
												<select id="inputAutor" class="form-control" name="idautor"
													required>
													<option></option>
													<c:forEach items="${autores}" var="autor">
														<option value="${autor.idautor}">${autor.nome_autor}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="inputEditora" class="col-sm-2 control-label">Editora</label>
											<div class="col-sm-10">
												<select id="inputEditora" class="form-control"
													name="ideditora" required>
													<option></option>
													<c:forEach items="${editoras}" var="editora">
														<option value="${editora.ideditora}">${editora.nome_editora}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="inputAssunto" class="col-sm-2 control-label">Assunto</label>
											<div class="col-sm-10">
												<select id="inputAssunto" class="form-control"
													name="idassunto" required>
													<option></option>
													<c:forEach items="${assuntos}" var="assunto">
														<option value="${assunto.idassunto}">${assunto.nome_assunto}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="inputAno" class="col-sm-2 control-label">Ano
												de publicação</label>

											<div class="col-sm-10">
												<input type="number" name="data" class="form-control"
													id="inputAno" min="1500" max="2099" step="1"
													placeholder="Ano de Publicação" required>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancelar</button>
									<button type="submit" class="btn btn-outline">Inserir</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- /.box-body -->
				<div class="box-footer clearfix">
					<ul class="pagination pagination-sm no-margin pull-right">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
			<!-- /.box -->
		</div>

	</div>
</section>
