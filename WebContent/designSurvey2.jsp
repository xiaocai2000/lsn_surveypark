<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:include value="/header.jsp"></s:include>

<!-- 调查标题 -->
<s:property value="title"/>
<!-- 输出页面集合 -->
<s:iterator value="pages" var="p">
	<!-- 页面标题 -->
	<s:property value="#p.title"/>
	<!-- 迭代问题集合 -->
	<s:iterator value="#p.questions" var="q">
		<!-- 问题标题 -->
		<s:property value="#q.title"/>
		
		<!-- 处理选项输出 -->
		<s:set var="qt" value="#q.questionType"></s:set>
		
		<!-- 0,1,2,3 -->
		<s:if test="#qt < 4">
			<s:iterator value="#p.optionArr">
				<input type='<s:property value="#qt < 2 ? 'radio' : 'checkbox'"/>'/>
				<s:property/>
				<!-- 是否携带br -->
				<s:if test="#qt == 1 || #qt == 3"><br/></s:if>
			</s:iterator>
			<!-- 是否带其他 -->
			<s:if test="#q.other">
				<input type='<s:property value="#qt < 2 ? 'radio' : 'checkbox'"/>'/>其他
				<!-- 其他样式 -->
				<s:if test="#q.otherStyle == 1">
					<input type="text"/>
				</s:if>
				<s:elseif test="q.otherStyle == 2">
					<select>
						<s:iterator value="#q.otherSelectOptionArr">
							<option><s:property/></option>
						</s:iterator>
					</select>
				</s:elseif>
			</s:if>
		</s:if>
		
		<!-- 4,5 -->
		<s:elseif test="#qt == 4 || #qt == t">
			<!-- 下拉列表 -->
			<s:if test="#qt == 4">
				<select>
					<s:iterator value="#q.optionArr">
						<option><s:property/></option>
					</s:iterator>
				</select>
			</s:if>
			<s:elseif test="#qt == 5">
				<input type="text"/>
			</s:elseif>
		</s:elseif>
		
		<!-- 6,7,8 矩阵式问题 -->
		<s:elseif test="#qt > 5">
			<table>
				<!-- 表头 -->
				<tr>
					<td></td>
					<s:iterator value="#q.matrixColTitleArr">
						<td><s:property/></td>
					</s:iterator>
				</tr>
				<!-- 输出n多行 -->
				<s:iterator value="#q.matrixRowTitleArr">
					<tr>
						<s:property/>
						<s:iterator value="#q.matrixColTitleArr">
							<td>
								<s:if test="#qt == 6"><input type="radio"/></s:if>
								<s:if test="#qt == 7"><input type="checkbox"/></s:if>
								<s:if test="#qt == 8">
									<select>
										<s:iterator value="#q.optionArr">
											<option><s:property/></option>
										</s:iterator>
									</select>
								</s:if>
							</td>
						</s:iterator>
					</tr>
				</s:iterator>
			</table>
		</s:elseif>
	</s:iterator>
</s:iterator>
</body>
</html>