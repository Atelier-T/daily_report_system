<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>フォロワー日報　一覧</h2>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                </tr>
                <c:forEach var="check_follow" items="${check_follow}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${check_follow.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value='${check_follow.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title"><c:out value="${check_follow.title}" /></td>
                        <td class="report_action"><a href="<c:url value='/reports/show?id=${check_follow.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${check_follow_count} 件)<br />
        </div>
    </c:param>
</c:import>