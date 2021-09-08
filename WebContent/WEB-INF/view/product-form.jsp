<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product form</title>
</head>
<body>

 Product - form
 <br><br><br>
 
 <form:form action="product-save" modelAttribute="product">
      
      <form:hidden path="id"/>
      Product title: <form:input path="title"/>
      <br>
      Product description: <form:input path="description"/>
      <br>
      Product image: <form:input path="image"/>
      <br>
      Product price: <form:input path="price"/>
      <br>
      Product quantity: <form:input path="quantity"/>
      <br>
      Product category: <form:select path="category.id" itemLabel="name" itemValue="id" items="${categories}" />
      <br><br>
      Product stickers: <form:checkboxes path="stickers" itemLabel="title" itemValue="id" items="${stickers}"/>
      
      <br><br>
       <input type="submit" value="Save product">
 </form:form>
 <br><br>


</body>
</html-->

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>Cubes school - Restaurant project</title>


  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

 <jsp:include page="include-admin-header-profile.jsp"/> 

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Product Logo -->
    

    <!-- Sidebar -->
   
      <jsp:include page="include-admin-menu.jsp"/>
   
    <!-- /.sidebar -->
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Products Form</h1>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Product Form</h3>
              </div>
              <!-- /.card-header -->
            
              
              <!-- form start -->
              <form:form action="product-save" modelAttribute="product" role="form">
              <form:hidden path="id"/>
                <div class="card-body">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label>Title</label>
                        <form:input path="title" type="text" class="form-control" placeholder="Enter title"/>
                      </div>
                      <div class="form-group">
                        <label>Description</label>
                        <form:textarea path="description" class="form-control" placeholder="Enter Description"/>
                      </div>
                       <div class="form-group">
                        <label>Manual Description</label>
                        <form:textarea path="manual.description" class="form-control" placeholder="Enter Description"/>
                      </div>
					
                      <div class="form-group">
                        <label>Price</label>
                        <div class="input-group">
                          <form:input path="price" type="number" class="form-control" placeholder="Enter price"/>
                          <div class="input-group-append">
                            <span class="input-group-text">din</span>
                          </div>
                        </div>
                      </div>
						<div class="form-group">
                        <label>Image</label>
                        <form:input path="image" type="text" class="form-control" placeholder="Enter image url"/>
                      </div>
                      <div class="form-group">
                        <label>Quantity</label>
                        <form:input path="quantity" type="text" class="form-control" placeholder="Enter quanitity"/>
                      </div>
                      <div class="form-group">
                        <label>Product category</label>
                        <form:select path="category.id" itemLabel="name" itemValue="id" items="${categories}" type="text" class="form-control" placeholder="Choose category"/>
                      </div>
                      
                       <div class="form-group">
                        <label>Product stickers</label>
                        <form:select path="stickers" itemLabel="title" itemValue="id" items="${stickers}" type="text" class="form-control" placeholder="Choose stickers"/>
                      </div>
                      <div class="form-group">
                        <label>Is product on Mainpage</label>
                        <form:checkbox path="homepage" class="form-control" />
                      </div>
                      
                      
                    

                      
                     
                    </div>
                    
                  </div>
                  
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Save</button>
                  <a href="product-list" class="btn btn-outline-secondary">Cancel</a>
                </div>
              </form:form>
              
              
              
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Java
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2019 <a href="https://cubes.edu.rs">Cubes School</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/admin/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/admin/dist/js/adminlte.min.js"></script>
</body>
</html>
