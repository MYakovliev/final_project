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
                            <label for="moneyAmount">Money</label>
                            <input type="number" min="0.01" step="0.01" class="form-control" name="bid" id="moneyAmount"/>
                        </div>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <div class="form-group owner">
                            <label for="owner">Owner</label>
                            <input type="text" class="form-control" id="owner" required pattern="\w{2,}\s\w{2,}">
                        </div>
                        <div class="form-group CVV">
                            <label for="cvv">CVV</label>
                            <input type="text" class="form-control" id="cvv" required pattern="\d{3}">
                        </div>
                        <div class="form-group" id="card-number-field">
                            <label for="cardNumber">Card Number</label>
                            <input type="text" class="form-control" id="cardNumber" required pattern="\d{16}">
                        </div>
                        <div class="form-group" id="expiration-date">
                            <label>Expiration Date</label>
                            <select>
                                <option value="01">January</option>
                                <option value="02">February</option>
                                <option value="03">March</option>
                                <option value="04">April</option>
                                <option value="05">May</option>
                                <option value="06">June</option>
                                <option value="07">July</option>
                                <option value="08">August</option>
                                <option value="09">September</option>
                                <option value="10">October</option>
                                <option value="11">November</option>
                                <option value="12">December</option>
                            </select>
                            <select>
                                <option value="20"> 2020</option>
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
                            <button type="submit" class="btn btn-default" id="confirm-purchase">Confirm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
