<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="content-header">
	<h1>
		500 Error Page <small></small>
	</h1>
</section>

<!-- Main content -->
<section class="content">
	<div class="error-page">
		<h2 class="headline text-red">500</h2>

		<div class="error-content">
			<h3>
				<i class="fa fa-warning text-red"></i> Oops! Alguma coisa está errada.
			</h3>

			<p>
				Nós vamos consertar este problema. Enquanto isso, você pode <a
					href="<c:url value="/admin"/>">retornar ao dashboard</a>
			</p>

		</div>
		<!-- /.error-content -->
	</div>
	<!-- /.error-page -->
</section>