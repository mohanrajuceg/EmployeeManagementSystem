<%-- 
    Document   : list_employee
    Created on : Dec 5, 2020, 4:14:43 PM
    Author     : Sindhu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
        <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="employeelist" value="${employeeList}" />
        <div class= "navbar">
           <nav class="navbar navbar-default ">
            <div class="container-fluid bg-sucess">
<!--            <div class="navbar-header ">
            
                <a class="navbar-brand" href="${pageContext.request.contextPath}/diverthome.htm">Job Portal</a>
                </div>-->
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav bg-info">
                         <li class="active "><a href="#">Dashboard</a></li>
            
                  </ul>
                    <ul class="nav navbar-nav navbar-right">
                               <li><a href="${pageContext.request.contextPath}/list_employee.htm">View/Add Employee</a></li> 
                               <li><a href="${pageContext.request.contextPath}/list_leaves.htm">View/Add Leave</a></li>
                               <!--   <li><a href="${pageContext.request.contextPath}/jobseekerregister.htm">New Employees</a></li>
                                <li><a href="${pageContext.request.contextPath}/employerLogin.htm">Sign In</a></li> -->
                                <li class="logindd dropdown">
       
             </li>
                    </ul>
              </div>
            </div>
        </nav> 
        </div>
	<div align="center">
           <c:if test="${addedEmployee}">
                        <p style="color:green">Added Employee Successfully</p>
                    </c:if>
                        <a class="btn btn-primary" href="${contextPath}/add_employee.htm">Add New Employee</a><br>
		<table class="login" border="3">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Title</th>
				<th>Role</th>
				<th>Reporting Manager</th>
				<th>Update Employee</th>
			</tr>
			<c:forEach items="${employeelist}" var="employee">
				<form>
					<tr>
						<td>${employee.first_name}</td>
						<td>${employee.last_name}</td>
						<td>${employee.email}</td>
						<td>${employee.title}</td>
						<td>${employee.role}</td>
						<td>${employee.manager}</td>
						<td><input class="btn btn-primary" type="submit"
							name="action" value="Update"></td>
						<!-- <td><input type="submit" name="action" value="Delete"></td> -->
					</tr>
				</form>
			</c:forEach>
		</table>
	</div>
</body>
</html>
