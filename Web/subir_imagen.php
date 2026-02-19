<?php
session_start();
require_once "Usuario.php";

$data = json_decode(file_get_contents("php://input"), true);
$api = new Usuario();
$resultado = $api->subirImagen($_SESSION["id"], $data["nombre"], $data["imagen"]);
echo json_encode($resultado);