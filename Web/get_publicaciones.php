<?php
require_once "Usuario.php";
$api = new Usuario();
$publicaciones = $api->getPublicaciones();
echo json_encode($publicaciones);