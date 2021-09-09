<?php
//var_dump($_GET);
session_start();
$timeStart = (float)microtime();
$tz = 'Europe/Moscow';
$timestamp = time();
$dt = new DateTime("now", new DateTimeZone($tz)); //first argument "must" be a string
$dt->setTimestamp($timestamp); //adjust the object to correct timestamp

$X = "not set";
$Y = "not set";

$RIsNotSet = true;

if (isset($_GET['X']) and trim($_GET['X']) !== "" and is_numeric($_GET['X']) and abs((float)($_GET['X'])) <= 5) {
    $X = (float)$_GET['X'];
} else {
    echo "WRONG X VALUE <br>";
    exit("ERROR");
}

if (isset($_GET['Y']) and trim($_GET['Y']) !== "" and is_numeric($_GET['Y']) and ((float)($_GET['Y']) == -5 or (float)($_GET['Y']) == -4
        or (float)($_GET['Y']) == -3 or (float)($_GET['Y']) == -2 or (float)($_GET['Y']) == -1 or (float)($_GET['Y']) == -0
        or (float)($_GET['Y']) == 1 or (float)($_GET['Y']) == 2 or (float)($_GET['Y']) == 3)) {
    $Y = (float)$_GET['Y'];
} else {
    echo "WRONG Y VALUE <br>";
    exit("ERROR");
}

$R = $_GET['R'];

if (empty($R)) {
    $RIsNotSet = true;
}

for ($i = 0; $i <= count($R)-1; $i++) {
    if ( trim($R[$i]) !== "" and is_numeric($R[$i]) and ((float)($R[$i]) == 1 or (float)($R[$i]) == 1.5 or (float)($R[$i]) == 2 or (float)($R[$i]) == 2.5 or (float)($R[$i]) == 3)) {
        $RIsNotSet = false;
    }
}

//if (isset($_GET['R1']) and trim($_GET['R1']) !== "" and (float)($_GET['R1']) == 1) {
//    $R[1] = (float)$_GET['R1'];
//    $RIsNotSet = false;
//}
//
//if (isset($_GET['R2']) and trim($_GET['R2']) !== "" and (float)($_GET['R2']) == 1.5) {
//    $R[2] = (float)$_GET['R2'];
//    $RIsNotSet = false;
//}
//
//if (isset($_GET['R3']) and trim($_GET['R3']) !== "" and (float)($_GET['R3']) == 2) {
//    $R[3] = (float)$_GET['R3'];
//    $RIsNotSet = false;
//}
//
//if (isset($_GET['R4']) and trim($_GET['R4']) !== "" and (float)($_GET['R4']) == 2.5) {
//    $R[4] = (float)$_GET['R4'];
//    $RIsNotSet = false;
//}
//
//if (isset($_GET['R5']) and trim($_GET['R5']) !== "" and (float)($_GET['R5']) == 3) {
//    $R[5] = (float)$_GET['R5'];
//    $RIsNotSet = false;
//}

if ($RIsNotSet) {
    echo "R IS NOT SET <br>";
    exit("ERROR");
}
//echo "Done1";
//phpinfo();
//$connection = pg_connect("host=localhost port=5432 dbname=postgres user=postgres password=vb484732");
//$connection = pg_connect("host=pg dbname=studs user=s312421 password=sdn516 sslmode=disable") or die("Не удалось");

//echo $connection;

//if (!$connection) {
//    echo "Connection is not established";
//}
//echo "Done";

for ($i = 0; $i <= count($R)-1; $i++) {
    if ($R[$i] == 0) {
        continue;
    }
    $state = true;
//    echo $X . " " . $Y . " " . -$R[$i] . " " . (pow($Y, 2) + pow($X, 2));
    if ($X < -$R[$i] / 2 or $X > $R[$i]) {
        $state = false;
    } else if (($X <= 0 and $Y < -$R[$i]) or ($X <= 0 and ((pow($Y, 2) + pow($X, 2)) > $R[$i] / 2))) {
        $state = false;
    } else if ($X > 0 and ($Y > 0 or $Y < ($X / 2 - $R[$i] / 2))) {
        $state = false;
    }

    if ($state) {
        $file = file_get_contents("results.txt");
        $file .= $X . " " . $Y . " " . $R[$i] . " " . "TRUE" . " " . $dt->format('d.m.Y, H:i:s') . "\n";
//        $file .= "<tr><td>" . $X . "</td><td>" . $Y . "</td><td>" . $R[$i] . "</td><td>" . "TRUE" . "</td><td>" . $dt->format('d.m.Y, H:i:s') . "</td></tr>\n";
        file_put_contents("results.txt", $file);
//        pg_query_params($connection, 'INSERT INTO results(x, y, r, result, date) VALUES ($1, $2, $3, $4, $5)', array($X, $Y, $R[$i], "TRUE", date('Y-m-d H:i:s')));
//    function printTable($i, $state) {
//        echo"<tr>\n<td>".$_SESSION[$i."X"].
//            "</td>\n<td>".$_SESSION[$i."Y"].
////                "</td>\n<td>".$_SESSION[$i."R[$i]"].
////                "</td>\n<td>".$_SESSION[$i."res"].
////                "</td>\n<td>".$_SESSION[$i."runtime"].
//            "</td>\n<td>".$_SESSION[$i."dt"]."</td>\n</tr>\n";;
//    }
    } else {
        $file = file_get_contents("results.txt");
        $file .= $X . " " . $Y . " " . $R[$i] . " " . "FALSE" . " " . $dt->format('d.m.Y, H:i:s') . "\n";

//        $file .= "<tr><td>" . $X . "</td><td>" . $Y . "</td><td>" . $R[$i] . "</td><td>" . "FALSE" . "</td><td>" . $dt->format('d.m.Y, H:i:s') . "</td></tr>\n";

        file_put_contents("results.txt", $file);
//        pg_query_params($connection, 'INSERT INTO results(x, y, r, result, date) VALUES ($1, $2, $3, $4, $5)', array($X, $Y, $R[$i], "FALSE", date('Y-m-d H:i:s')));

    }
//    echo "DONE FOR " . $R[$i];
}
echo "<table border='2'><tr><td>X</td><td>Y</td><td>R</td><td>Result</td><td>Timestamp</td></tr>";

$handle = @fopen("results.txt", "r");
while (($buffer = fgets($handle, 4096)) !== false) {
    $stats = explode(" ", $buffer);
    echo "<tr><td>".$stats[0]."</td><td>".$stats[1]."</td><td>".$stats[2]."</td><td>".$stats[3]."</td><td>".$stats[4].$stats[5]."</td></tr>\n";
}
echo("</table>");

//$fileLines = file("results.txt");
//$result = pg_query($connection, "SELECT * FROM results");

//echo "<table border='2'><tr><td>X</td><td>Y</td><td>R</td><td>Result</td><td>Timestamp</td></tr>";
////while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
////    echo("<tr><td>".$line["x"]."</td><td>".$line["y"]."</td><td>".$line["r"]."</td><td>".$line["result"]."</td><td>".$line["date"]."</td></tr>\n");
////}
//foreach ($fileLines as $value) {
//    echo $value;
//}
//echo("</table>");

echo "Сейчас " . $dt->format('d.m.Y, H:i:s') . "<br>";

//pg_close($connection);

$timeEnd = (float)microtime();
$workTime = $timeEnd - $timeStart;
echo "Время работы скрипта - " . $workTime . " с";