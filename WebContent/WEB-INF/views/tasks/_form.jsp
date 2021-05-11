<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label>内容<br />
<input type="text"  name="content" value="${task.content}"/>
</label>
<br /><br />
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>