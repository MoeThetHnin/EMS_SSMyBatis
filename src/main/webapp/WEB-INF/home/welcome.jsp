<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

	<div style="width: 100%;">
		<div style="text-align: center;">
			<h2>株式会社アンスール</h2>
			<h3>従業員管理システムアドミン側</h3>
		</div>
		<div style="width: 80%; margin: 1% auto;">
			<a style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="logout.action"/>"> ログアウト</a>
		</div>
		<div
			style="width: 80%; margin: 1% auto; border: 1px solid gray; clear: right;">
			<a href="<s:url action="empManage.action"/>"
				style="text-decoration: none; color: gray;">
				<div
					style="width: 21%; margin: 1% 1% 1% 3%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
					<h4>社員管理</h4>
				</div>
			</a> <a href="<s:url action="departmentManage.action"/>"
				style="text-decoration: none; color: gray;">
				<div
					style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
					<h4>部門管理と役職管理</h4>
				</div>
			</a> <a href="<s:url action="ekiManage.action"/>"
				style="text-decoration: none; color: gray;">
				<div
					style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
					<h4>駅管理</h4>
				</div>
			</a> <a href="#" style="text-decoration: none; color: gray;">
				<div
					style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
					<h4>OO管理</h4>
				</div>
			</a>


		</div>
		<div style="width: 80%; margin: 1% auto; clear: left;">
			<div style="width: 50%; float: left;">

				<h4 style="text-align: center;">コンバータ</h4>
				<div
					style="clear: left; width: 90%; margin: 1% auto; border: 1px solid gray;">

					<h5 style="text-align: center;">エクセルファイルをPDFファイルに変更する機能 (.xls
						to .pdf)</h5>
					<div style="width: 50%; margin: 1% auto;">
						<s:form action="excelToPdf" enctype="multipart/form-data">
							<s:file name="function.uploadImage" multiple="multiple"></s:file>
							<s:submit value="PDFファイルに変更"></s:submit>
						</s:form>
					</div>
				</div>
				<div
					style="clear: left; width: 90%; margin: 1% auto; border: 1px solid gray;">
					<h5 style="text-align: center;">エクセルファイルをSQLファイルに変更する機能 (.xls
						to .sql)</h5>
					<div style="width: 50%; margin: 1% auto;">
						<s:form action="excelToSql" enctype="multipart/form-data">
							<s:file name="function.uploadImage" multiple="multiple"></s:file>
							<s:submit value="SQLファイルに変更"></s:submit>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<p>
		<s:property value="result" />
	</p>
</body>
</html>