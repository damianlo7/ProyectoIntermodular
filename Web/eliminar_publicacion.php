<?php
session_start();
require_once "Usuario.php";

$data = json_decode(file_get_contents("php://input"), true);
$api = new Usuario();
$resultado = $api->eliminarPublicacion($data["id"]);
echo json_encode($resultado);