<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="org.apache.commons.fileupload.FileItem" %> 
<%@ page import="nation.web.tool.*" %>

 
<%@ page import="nation.web.devcategory.*" %>
<%@ page import="nation.web.devdiary.*" %>


<%
if (session.getAttribute("act") == null) {
  session.setAttribute("act", "G"); // 손님 권한 적용
}
%>
 
 
<% 
String root = request.getContextPath();
request.setCharacterEncoding("utf-8"); 
%>
 
<%
DevcategoryProc devcategoryProc = new DevcategoryProc();
DevcategoryVO devcategoryVO = new DevcategoryVO();

ArrayList<DevdiaryVO> list = new ArrayList<DevdiaryVO>();

DevdiaryProc devdiaryProc = new DevdiaryProc();

String col = Tool.checkNull(request.getParameter("col"));
String word = Tool.checkNull(request.getParameter("word"));


//페이지당 출력할 레코드 갯수
int recordPerPage =  0;
if (request.getParameter("recordPerPage") == null) {
recordPerPage = 5;
} else {
recordPerPage = Integer.parseInt(request.getParameter("recordPerPage"));
}
 
//현재 페이지
int nowPage =  0;
if (request.getParameter("nowPage") == null) {
nowPage = 1; // 현재 페이지 1부터 시작
} else {
nowPage = Integer.parseInt(request.getParameter("nowPage"));
}

//목록 파일
String listFile = "";
if (request.getParameter("listFile") == null) {
listFile = "list.jsp";
} else {
listFile = request.getParameter("listFile");
};
%>
