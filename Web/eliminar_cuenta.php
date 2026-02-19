<?php
session_start();
require_once "Usuario.php";

$api = new Usuario();
$api->eliminarCuenta($_SESSION["id"]);
session_destroy();
echo json_encode(["ok" => true]);