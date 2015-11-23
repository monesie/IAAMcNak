<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:actionerror/>
<s:form>
  <s:label name="label.otp.enterOTP"/>
  <s:hidden name="examId"/>
  <s:password key="exam.oneTimePassword" name="enteredPassword" requiredLabel="true"/>
  <s:submit key="exam.otpValidate" action="validateOTP"/>
</s:form>

