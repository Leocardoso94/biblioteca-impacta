<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section class="content-header">
	<h1>
		Empréstimos <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Lista de Empréstimos</h3>
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
							<th>Exemplar</th>
							<th>Usuário</th>
							<th>Data do Empréstimo</th>
							<th>Data prevista da devolução</th>
							<th>Situação</th>
							<th>Multa</th>
							<th>Efetuar Devolução</th>
						</tr>
						<c:forEach items="${emprestimos}" var="emprestimo">
							<tr>
								<td>${emprestimo.idemprestimo}</td>
								<td>#${emprestimo.num_exemplar} -
									${emprestimo.getNomeExemplar()}</td>
								<td>${emprestimo.getNomePessoa()}</td>
								<td><fmt:formatDate value="${emprestimo.data_emprestimo}"
										type="date" dateStyle="long" /></td>
								<td><fmt:formatDate
										value="${emprestimo.data_prevista_retorno}" type="date"
										dateStyle="long" /></td>
								<td>${emprestimo.situacao()}</td>
								<td><fmt:formatNumber value="${emprestimo.valorDaMulta()}"
										type="currency" /></td>
								<c:choose>
									<c:when test="${emprestimo.finalizado}">
										<td>Devolvido</td>
									</c:when>
									<c:otherwise>
										<td><button class="btn btn-success" data-toggle="modal"
												data-target="#modal${emprestimo.idemprestimo}">
												<i class="fa fa-check-square" aria-hidden="true"></i>
												Devolver
											</button></td>
									</c:otherwise>
								</c:choose>

							</tr>
							<!-- Modal -->
							<div class="modal fade modal-success"
								id="modal${emprestimo.idemprestimo}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Devolver</h4>
										</div>
										<form role="form" method="post" class="form-horizontal"
											action="devolver">
											<div class="modal-body">

												<input value="${emprestimo.idemprestimo}"
													name="idemprestimo" type="hidden" readonly>
												<div class="box-body">
													<div class="form-group">
														<p>
															Tem certeza que deseja efetuar a devolução deste
															exemplar?<br>Após isso o processo não poderá ser
															desfeito.<br> <br>Lembre-se o usuário deve
															pagar
															<fmt:formatNumber value="${emprestimo.valorDaMulta()}"
																type="currency" />
															de multa.
														</p>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Cancelar</button>
												<button type="submit" class="btn btn-outline">Devolver</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>
					</table>
				</div>


				<!-- Modal -->
				<div class="modal fade modal-primary"
					id="inserir${emprestimo.idemprestimo}" role="dialog">
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
											<label for="inputUsuario" class="col-sm-2 control-label">Exemplar</label>
											<div class="col-sm-10">
												<select id="inputUsuario" class="form-control"
													name="num_exemplar" required>
													<option></option>
													<c:forEach items="${exemplares}" var="exemplar">
														<option value="${exemplar.num_exemplar}">#${exemplar.num_exemplar}
															- ${exemplar.nomeobra}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label for="inputUsuario" class="col-sm-2 control-label">Usuário</label>
											<div class="col-sm-10">
												<select id="inputUsuario" class="form-control" name="pessoa"
													required>
													<option></option>
													<c:forEach items="${pessoas}" var="pessoa">
														<option value="${pessoa.idpessoa}">${pessoa.nome}</option>
													</c:forEach>
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
