<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul>
	<a href="<s:url action="empManage"/>">
		<li class="current">社員管理</li>
	</a>
	<a href="<s:url action="departmentManage"/>"
		style="text-decoration: none; color: gray;">
		<li>部門管理と役職管理</li>
	</a>
	<a href="<s:url action="ekiManage"/>"
		style="text-decoration: none; color: gray;">
		<li>駅管理</li>
	</a>
	<a href="#" style="text-decoration: none; color: gray;">
		<li>OO管理</li>
	</a>
</ul>