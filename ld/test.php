<?php
	require 'dbconnect.php';
    $result = mysql_query("SELECT MAX(STT) FROM bantin");
    $row = mysql_fetch_row($result);
    $highest_id = $row[0];
    echo $highest_id;
	mysql_close($connect);
?>