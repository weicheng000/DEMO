<!doctype html>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Email using FreeMarker</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .email-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }

        .header {
            background-color: #f1f1f1;
            padding: 10px;
            text-align: center;
        }

        .content {
            margin-top: 20px;
        }

        .footer {
            margin-top: 20px;
            text-align: center;
            font-size: 12px;
            color: #888;
        }
    </style>
</head>
<body>
<div class="email-container">
    <div class="header">
        <img src="cid:image" alt="logo">
        <h1>測試郵件</h1>
    </div>
    <div class="content">
        <h3>尊敬的${userName}</h3>
        <p>&nbsp; &nbsp;你好！</p>
        <p>&nbsp; &nbsp;讓我們一起學習，共同進步！</p>
        <p>&nbsp; &nbsp;順祝時祈!</p>
        <p>&nbsp; &nbsp; &nbsp;RJF Jason</p>
    </div>
    <div class="footer">
        <p>此郵件是由系統自動生成，請勿回覆。</p>
    </div>
</div>
</body>
</html>