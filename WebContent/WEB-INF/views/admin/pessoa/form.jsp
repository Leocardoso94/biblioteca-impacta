<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content-header">
	<h1>
		Pessoas <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Lista de Pessoas</h3>
					<button class="btn btn-primary pull-right" data-toggle="modal"
						data-target="#inserir${pessoa.idpessoa}">
						<i class="fa fa-plus" aria-hidden="true"></i>
					</button>
					<hr>
					<form action="buscaPessoa" method="get" class="">
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
							<th>Tipo de Pessoa</th>
							<th>Empréstimos</th>
							<th>Alterar</th>
							<th>Excluir</th>
						</tr>
						<c:forEach items="${pessoas}" var="pessoa">
							<tr>
								<td>${pessoa.idpessoa}</td>
								<td>${pessoa.nome}</td>
								<td>${pessoa.email}</td>
								<td>${pessoa.telefone}</td>
								<td>${pessoa.cpf}</td>
								<c:choose>
									<c:when test="${pessoa.inadmin}">
										<td>Sim</td>
									</c:when>
									<c:otherwise>
										<td>Não</td>
									</c:otherwise>
								</c:choose>
								<td>${pessoa.tipopessoa}</td>
								<td>${pessoa.contagemDeEmprestimosPorPessoa()}</td>
								<td><button class="btn btn-warning" data-toggle="modal"
										data-target="#modal${pessoa.idpessoa}">
										<i class="fa fa-pencil" aria-hidden="true"></i>
									</button></td>
								<td><button class="btn btn-danger" data-toggle="modal"
										data-target="#delete${pessoa.idpessoa}">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button></td>
							</tr>
							<!-- Modal -->
							<div class="modal fade modal-warning"
								id="modal${pessoa.idpessoa}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Alterar</h4>
										</div>
										<form role="form" method="post" class="form-horizontal"
											action="alterarPessoa">
											<input value="${pessoa.idpessoa}" name="idpessoa"
												type="hidden" readonly>
											<div class="modal-body">
												<div class="box-body">
													<div class="form-group">
														<label for="inputNome3" class="col-sm-2 control-label">Nome</label>

														<div class="col-sm-10">
															<input type="text" name="nome" class="form-control"
																id="inputNome3" value="${pessoa.nome}"
																placeholder="Nome" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">Email</label>

														<div class="col-sm-10">
															<input type="email" name="email" class="form-control"
																id="inputEmail3" value="${pessoa.email}"
																placeholder="Email" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputPassword3" class="col-sm-2 control-label">Senha</label>

														<div class="col-sm-10">
															<input type="password" name="senha" class="form-control"
																id="inputPassword3" value="${pessoa.senha}"
																placeholder="Password" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputTelefone2" class="col-sm-2 control-label">Celular</label>

														<div class="col-sm-10">
															<input type="text" name="telefone" class="form-control"
																id="inputTelefone2" class="inputTelefone"
																value="${pessoa.telefone}" pattern=".{13,}"
																placeholder="Telefone" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputCpf2" class="col-sm-2 control-label">CPF</label>

														<div class="col-sm-10">
															<input type="text" name="cpf" class="form-control"
																pattern=".{14,}" class="inputCpf" value="${pessoa.cpf}"
																id="inputCpf2" placeholder="CPF" required>
														</div>
													</div>
													<div class="form-group">
														<label for="inputAdmin3" class="col-sm-2 control-label">Administrador</label>
														<div class="col-sm-10">
															<select id="inputAdmin3" class="form-control"
																name="inadmin">
																<c:choose>
																	<c:when test="${pessoa.inadmin}">
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
															Pessoa</label>
														<div class="col-sm-10">
															<select id="inputTipo3" class="form-control"
																name="idtipo_pessoa">
																<option value="${pessoa.idtipo_pessoa}">${pessoa.tipopessoa}</option>
																<c:forEach items="${tiposPessoa}" var="entry">
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
								id="delete${pessoa.idpessoa}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Excluir</h4>
										</div>
										<form role="form" method="post" action="excluirPessoa">
											<div class="modal-body">
												<div class="box-body">
													<div class="form-group">
														<p>
															Tem certeza que deseja excluir <b> ${pessoa.nome}?</b>
														</p>
														<h6>Caso o usuário não seja deletado é necessário
															finalizar os empréstimos que estão vinculados a ele</h6>
														<div class="col-sm-12">
															<input type="hidden" name="idpessoa"
																value="${pessoa.idpessoa}" readonly> <input
																type="hidden" value="${pessoa.nome}" name="nome"
																class="form-control" id="nomePessoa"
																placeholder="Nome da Pessoa" required readonly>
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
				<div class="modal fade modal-primary" id="inserir${pessoa.idpessoa}"
					role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Inserir</h4>
							</div>
							<form role="form" method="post" class="form-horizontal"
								action="adicionarPessoa">
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
												Pessoa</label>
											<div class="col-sm-10">
												<select id="inputTipo3" class="form-control"
													name="idtipo_pessoa" required>
													<option></option>
													<c:forEach items="${tiposPessoa}" var="entry">
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
