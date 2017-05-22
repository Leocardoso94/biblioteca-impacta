<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content-header">
	<h1>
		Pessoas <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-6">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">Lista de Pessoas</h3>
					<hr>					
					<form action="buscaPessoa" method="get" class="">
						<div class="input-group">
							<input type="text" name="search" class="form-control"
								placeholder="Buscar..."> <span class="input-group-btn">
								<button type="submit" id="search-btn"
									class="btn btn-flat">
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
							<th>Alterar</th>
							<th>Excluir</th>
						</tr>
						<c:forEach items="${pessoas}" var="pessoa">
							<tr>
								<td>${pessoa.idpessoa}.</td>
								<td>${pessoa.nome}</td>
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
							<div class="modal fade modal-warning" id="modal${pessoa.idpessoa}"
								role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Alterar</h4>
										</div>
										<form role="form" method="post" action="alterarPessoa">
											<div class="modal-body">
												<div class="box-body">
													<div class="form-group">
														<label for="nomePessoa" class="col-sm-12 control-label">Nome
															da Pessoa:</label>
														<div class="col-sm-12">
															<input type="hidden" name="idpessoa"
																value="${pessoa.idpessoa}" readonly> <input
																type="text" value="${pessoa.nome}"
																name="nome" class="form-control"
																id="nomePessoa" placeholder="Nome da Pessoa" required>
														</div>
													</div>
												</div>
												<!-- /.box-body -->

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
							<div class="modal fade modal-danger" id="delete${pessoa.idpessoa}" role="dialog">
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
														<p>Tem certeza que deseja excluir <b>
															${pessoa.nome}?</b></p>
														<div class="col-sm-12">
															<input type="hidden" name="idpessoa"
																value="${pessoa.idpessoa}" readonly> <input
																type="hidden" value="${pessoa.nome}"
																name="nome" class="form-control" id="nomePessoa"
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

		<div class="col-md-6">
			<!-- Horizontal Form -->
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">Adicionar Pessoa</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form class="form-horizontal" method="post"
					action="adicionarPessoa">
					<div class="box-body">
						<div class="form-group">
							${erroPessoaExiste} <label for="inputEmail3"
								class="col-sm-2 control-label">Nome</label>
							<div class="col-sm-10">
								<input type="text" name="nome" class="form-control"
									id="inputEmail3" placeholder="Nome da Pessoa" required>
							</div>
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="submit" class="btn btn-primary center-block">Inserir</button>
					</div>
					<!-- /.box-footer -->
				</form>
			</div>
		</div>
	</div>
</section>