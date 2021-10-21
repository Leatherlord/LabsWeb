<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <meta charset="utf-8" content="text/html"
          http-equiv="Content-Type">
    <title>First Lab Web</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link href="gray-black_square_cut.jpg" rel="shortcut icon" type="image/jpg">
</head>
<body>
<div id="container">
    <div id="header">
        <h1>Бойко Владислав Алексеевич, Р3212, ВАРИАНТ: 12001</h1>

    </div>
    <div id="page">
        <div id="content">
            <div id="input-field">
                <h2></h2>
                <h3>Заполните форму ниже, пожалуйста:</h3>
                <section>
                    <form method="post" action="controller-servlet" id="toSend">
                        <div>
                            Координата Х:<br>
                            <label for="check1">-3:</label>
                            <input class="checkbox" id="check1" name="X[]" type="checkbox" value=-3>
                            <label for="check2">-2:</label>
                            <input class="checkbox" id="check2" name="X[]" type="checkbox" value=-2>
                            <label for="check3">-1:</label>
                            <input class="checkbox" id="check3" name="X[]" type="checkbox" value=-1>
                            <label for="check4">0:</label>
                            <input class="checkbox" id="check4" name="X[]" type="checkbox" value=0>
                            <label for="check5">1:</label>
                            <input class="checkbox" id="check5" name="X[]" type="checkbox" value=1>
                            <label for="check6">2:</label>
                            <input class="checkbox" id="check6" name="X[]" type="checkbox" value=2>
                            <label for="check7">3:</label>
                            <input class="checkbox" id="check7" name="X[]" type="checkbox" value=3>
                            <label for="check8">4:</label>
                            <input class="checkbox" id="check8" name="X[]" type="checkbox" value=4>
                            <label for="check9">5:</label>
                            <input class="checkbox" id="check9" name="X[]" type="checkbox" value=5>
                        </div>
                        <div>
                            <label for="Y">Координата Y:<br></label>

                            <input id="Y" name="Y" placeholder="Введите Y от -5 до 5" type="text" value="">
                        </div>
                        <div>
                            Параметр R:<br>
                            <label for="radio1">1:</label>
                            <input checked="checked" class="radio" id="radio1" name="R" type="radio" value=1>
                            <label for="radio2">1.5:</label>
                            <input class="radio" id="radio2" name="R" type="radio" value=1.5>
                            <label for="radio3">2:</label>
                            <input class="radio" id="radio3" name="R" type="radio" value=2>
                            <label for="radio4">2.5:</label>
                            <input class="radio" id="radio4" name="R" type="radio" value=2.5>
                            <label for="radio5">3:</label>
                            <input class="radio" id="radio5" name="R" type="radio" value=3>
                        </div>
                        <div class="span"></div>
                        <div><input class="button" type="submit" value="Отправить"></div>
                    </form>
                </section>
            </div>
            <div id="content-separator"></div>
            <div id="ajax">
                <table id="the-only-table">
                    <tr>
                        <td>X</td>
                        <td>Y</td>
                        <td>R</td>
                        <td>Result</td>
                        <td>Date</td>
                    </tr>
                </table>
            </div>
        </div>

        <div id="sidebar">
            <img id="duck" src="blue_rotate.jpg">
            <div id="field">
                <img id="zone" src="red_field.png">
                <img id="pointer" src="gray-black_square_cut-removebg-preview(1).png">
            </div>

        </div>
    </div>
    <div class="clear">
    </div>
</div>
<script src="script.js" type="text/javascript"></script>
</body>
</html>