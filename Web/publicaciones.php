<?php
header('Content-Type: application/json');

// URL del backend Java
$apiUrl = 'http://192.168.56.1:8080/tema5maven/rest/publicacion/imagen';

// Inicializa cURL
$ch = curl_init($apiUrl);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Ejecuta la petición
$response = curl_exec($ch);
$httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

// Manejo de errores
if ($response === false || $httpCode != 200) {
    http_response_code(500);
    echo json_encode(['error' => 'No se pudo cargar las publicaciones']);
    exit;
}

// Devuelve la respuesta tal cual
echo $response;

curl_close($ch);
