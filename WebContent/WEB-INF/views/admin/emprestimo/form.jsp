<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content-header">
	<h1>
		Emprestimos <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Lista de Emprestimos</h3>
					<button class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#inserir${emprestimo.idemprestimo}">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button>
					<hr>
					<form action="buscaEmprestimo" method="get" class="">
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
							<th>Nome</th>
							<th>E-mail</th>
							<th>Telefone</th>
							<th>CPF</th>
							<th>Administrador</th>
							<th>Tipo de Emprestimo</th>
							<th>Empréstimos</th>
							<th>Alterar</th>
							<th>Excluir</th>
						</tr>
						<c:forEach items="${emprestimos}" var="emprestimo">
							<tr>
								<td>${emprestimo.idemprestimo}</td>
								<td>${emprestimo.nome}</td>
								<td>${emprestimo.email}</td>
								<td>${emprestimo.telefone}</td>
								<td>${emprestimo.cpf}</td>
								<c:choose>
									<c:when test="${emprestimo.inadmin}">
										<td>Sim</td>
									</c:when>
									<c:otherwise>
										<td>Não</td>
									</c:otherwise>
								</c:choose>
								<td>${emprestimo.tipoemprestimo}</td>
								<td>${emprestimo.contagemDeEmprestimosPorEmprestimo()}</td>
								<td><button class="btn btn-warning" data-toggle="modal"
										data-target="#modal${emprestimo.idemprestimo}">
										<i class="fa fa-pencil" aria-hidden="true"></i>
									</button></td>
								<td><button class="btn btn-danger" data-toggle="modal"
										data-target="#delete${emprestimo.idemprestimo}">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button></td>
							</tr>
							<!-- Modal -->
							<div class="modal fade modal-warning"
								id="modal${emprestimo.idemprestimo}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Alterar</h4>
										</div>
										<form role="form" method="post" class="form-horizontal"
											action="alterarEmprestimo">
											<input value="${emprestimo.idemprestimo}" name="idemprestimo"
												type="hidden" readonly>
											<div class="modal-body">
												<div class="box-body">
													<div class="form-group">
														<label for="inputNome3" class="col-sm-2 control-label">Nome</label>

														<div class="col-sm-10">
															<input type="text" name="nome" class="form-control"
																id="inputNome3" value="${emprestimo.nome}"
																placeholder="Nome" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">Email</label>

														<div class="col-sm-10">
															<input type="email" name="email" class="form-control"
																id="inputEmail3" value="${emprestimo.email}"
																placeholder="Email" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">Senha</label>

														<div class="col-sm-10">
															<input type="password" name="senha" class="form-control"
																id="inputPassword3" value="${emprestimo.senha}"
																placeholder="Password" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputTelefone2" class="col-sm-2 control-label">Celular</label>

														<div class="col-sm-10">
															<input type="text" name="telefone" class="form-control"
																id="inputTelefone2" class="inputTelefone"
																value="${emprestimo.telefone}" pattern=".{13,}"
																placeholder="Telefone" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputCpf2" class="col-sm-2 control-label">CPF</label>

														<div class="col-sm-10">
															<input type="text" name="cpf" class="form-control"
																pattern=".{14,}" class="inputCpf" value="${emprestimo.cpf}"
																id="inputCpf2" placeholder="CPF" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputAdmin3" class="col-sm-2 control-label">Administrador</label>
														<div class="col-sm-10">
															<select id="inputAdmin3" class="form-control"
																name="inadmin">
																<c:choose>
																	<c:when test="${emprestimo.inadmin}">
																		<option value="true">Sim</option>
																	</c:when>
																</c:choose>
																<option value="false">Não</option>
																<option value="true">Sim</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label for="inputTipo3" class="col-sm-2 control-label">Tipo
															Emprestimo</label>
														<div class="col-sm-10">
															<select id="inputTipo3" class="form-control"
																name="idtipo_emprestimo">
																<option value="${emprestimo.idtipo_emprestimo}">${emprestimo.tipoemprestimo}</option>
																<c:forEach items="${tiposEmprestimo}" var="entry">
																	<option value="${entry.key}">${entry.value}</option>
																</c:forEach>
															</select>
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
							<div class="modal fade modal-danger"
								id="delete${emprestimo.idemprestimo}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Excluir</h4>
										</div>
										<form role="form" method="post" action="excluirEmprestimo">
											<div class="modal-body">
												<div class="box-body">
													<div class="form-group">
														<p>
															Tem certeza que deseja excluir <b> ${emprestimo.nome}?</b>
														</p>
														<h6>Caso o usuário não seja deletado é necessário
															finalizar os empréstimos que estão vinculados a ele</h6>
														<div class="col-sm-12">
															<input type="hidden" name="idemprestimo"
																value="${emprestimo.idemprestimo}" readonly> <input
																type="hidden" value="${emprestimo.nome}" name="nome"
																class="form-control" id="nomeEmprestimo"
																placeholder="Nome da Emprestimo" required readonly>
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
				<div class="modal fade modal-primary" id="inserir${emprestimo.idemprestimo}"
					role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Inserir</h4>
							</div>
							<form role="form" method="post" class="form-horizontal"
								action="adicionarEmprestimo">
								<div class="modal-body">
									<div class="box-body">
										<div class="form-group">
											<label for="inputNome3" class="col-sm-2 control-label">Nome</label>

											<div class="col-sm-10">
												<input type="text" name="nome" class="form-control"
													id="inputNome3" placeholder="Nome" required>
											</div>
										</div>
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-2 control-label">Email</label>

											<div class="col-sm-10">
												<input type="email" name="email" class="form-control"
													id="inputEmail3" placeholder="Email" required>
											</div>
										</div>
										<div class="form-group">
											<label for="inputPassword3" class="col-sm-2 control-label">Senha</label>

											<div class="col-sm-10">
												<input type="password" name="senha" class="form-control"
													id="inputPassword3" placeholder="Password" required>
											</div>
										</div>
										<div class="form-group">
											<label for="inputTelefone3" class="col-sm-2 control-label">Celular</label>

											<div class="col-sm-10">
												<input type="text" name="telefone" class="form-control"
													id="inputTelefone3" pattern=".{13,}" placeholder="Telefone"
													required>
											</div>
										</div>
										<div class="form-group">
											<label for="inputCpf3" class="col-sm-2 control-label">CPF</label>

											<div class="col-sm-10">
												<input type="text" name="cpf" class="form-control"
													pattern=".{14,}" id="inputCpf3" placeholder="CPF" required>
											</div>
										</div>
										<div class="form-group">
											<label for="inputTipo3" class="col-sm-2 control-label">Tipo
												Emprestimo</label>
											<div class="col-sm-10">
												<select id="inputTipo3" class="form-control"
													name="idtipo_emprestimo" required>
													<option></option>
													<c:forEach items="${tiposEmprestimo}" var="entry">
														<option value="${entry.key}">${entry.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="inputAdmin3" class="col-sm-2 control-label">Liberar
												Administrador</label>
											<div class="col-sm-10">
												<select id="inputAdmin3" class="form-control" name="inadmin">
													<option value="false">Não</option>
													<option value="true">Sim</option>
												</select>
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
