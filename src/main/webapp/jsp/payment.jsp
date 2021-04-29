<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: nicki
  Date: 4/15/2021
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale"/>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
<link href="https://bootstraptema.ru/snippets/form/2017/styles.css" rel="stylesheet">
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_to_balance">
    <div class="container">
        <div class="row">

            <div class="creditCardForm">
                <div class="heading">
                </div>
                <div class="payment">
                    <form>
                        <div class="form-group" id="money-amount">
                            <label for="moneyAmount"><fmt:message key="payment.money"/></label>
                            <input type="number" min="0.01" step="0.01" class="form-control" name="bid" id="moneyAmount"/>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <div class="form-group owner">
                            <label for="owner"><fmt:message key="payment.owner"/></label>
                            <input type="text" class="form-control" id="owner" required pattern="\w{2,}\s\w{2,}">
                        </div>
                        <div class="form-group CVV">
                            <label for="cvv">CVV</label>
                            <input type="text" class="form-control" id="cvv" required pattern="\d{3}">
                        </div>
                        <div class="form-group" id="card-number-field">
                            <label for="cardNumber"><fmt:message key="payment.card_number"/></label>
                            <input type="text" class="form-control" id="cardNumber" required pattern="\d{16}">
                        </div>
                        <div class="form-group" id="expiration-date">
                            <label><fmt:message key="payment.expiration"/></label>
                            <select>
                                <option value="01"><fmt:message key="payment.january"/></option>
                                <option value="02"><fmt:message key="payment.february"/></option>
                                <option value="03"><fmt:message key="payment.march"/></option>
                                <option value="04"><fmt:message key="payment.april"/></option>
                                <option value="05"><fmt:message key="payment.may"/></option>
                                <option value="06"><fmt:message key="payment.june"/></option>
                                <option value="07"><fmt:message key="payment.july"/></option>
                                <option value="08"><fmt:message key="payment.august"/></option>
                                <option value="09"><fmt:message key="payment.september"/></option>
                                <option value="10"><fmt:message key="payment.october"/></option>
                                <option value="11"><fmt:message key="payment.november"/></option>
                                <option value="12"><fmt:message key="payment.december"/></option>
                            </select>
                            <select>
                                <option value="21"> 2021</option>
                                <option value="19"> 2022</option>
                                <option value="20"> 2023</option>
                                <option value="21"> 2024</option>
                            </select>
                        </div>
                        <div class="form-group" id="credit_cards">
                            <img src="https://bootstraptema.ru/snippets/form/2017/visa.jpg" id="visa">
                            <img src="https://bootstraptema.ru/snippets/form/2017/mastercard.jpg" id="mastercard">
                            <img src="https://bootstraptema.ru/snippets/form/2017/amex.jpg" id="amex">
                        </div>
                        <div class="form-group" id="pay-now">
                            <button type="submit" class="btn btn-default" id="confirm-purchase"><fmt:message key="payment.submit"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
