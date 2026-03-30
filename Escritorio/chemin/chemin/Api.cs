using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace chemin
{
    public class Api
    {
        private static readonly HttpClient client = new HttpClient();
        private const string BASE_URL = "http://localhost:8080/tema5maven/rest";

        public async Task<List<Publicacion>> GetPublicacionesAsync()
        {
            try
            {
                var response = await client.GetAsync($"{BASE_URL}/publicacion/lista");
                if (response.IsSuccessStatusCode)
                {
                    string json = await response.Content.ReadAsStringAsync();
                    return JsonConvert.DeserializeObject<List<Publicacion>>(json);
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine("Error: " + ex.Message);
            }
            return new List<Publicacion>();
        }

        public async Task<bool> EliminarPublicacionAsync(int id)
        {
            try
            {
                var response = await client.DeleteAsync($"{BASE_URL}/publicacion/eliminar/{id}");
                return response.IsSuccessStatusCode;
            }
            catch (Exception) { return false; }
        }
        public async Task<bool> RegistrarAsync(string nombre, string username, string email, string password, string genero)
        {
            try
            {
                string json = "{\"nombreCompleto\":\"" + nombre + "\",\"username\":\"" + username + "\",\"email\":\"" + email + "\",\"contrasenha\":\"" + password + "\",\"genero\":\"" + genero + "\"}";
                var content = new StringContent(json, Encoding.UTF8, "application/json");
                var response = await client.PostAsync($"{BASE_URL}/usuario/registro", content);
                return response.IsSuccessStatusCode;
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error: " + ex.Message);
                return false;
            }
        }

        public Image Base64ToImage(string base64)
        {
            if (string.IsNullOrEmpty(base64)) return null;
            try
            {
                byte[] bytes = Convert.FromBase64String(base64);
                MemoryStream ms = new MemoryStream(bytes);
                return Image.FromStream(ms);
            }
            catch
            {
                return null;
            }
        }
        public async Task<bool> SubirImagenAsync(int idUsuario, string nombre, string base64)
        {
            try
            {
                string json = "{\"idUsuario\":" + idUsuario + ",\"nombre\":\"" + nombre + "\",\"imagen\":\"" + base64 + "\"}";
                var content = new StringContent(json, Encoding.UTF8, "application/json");
                var response = await client.PostAsync($"{BASE_URL}/publicacion/imagen", content);
                return response.IsSuccessStatusCode;
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error: " + ex.Message);
                return false;
            }
        }
        public async Task<bool> PublicarTextoAsync(int idUsuario, string texto)
        {
            try
            {
                string json = "{\"idUsuario\":" + idUsuario + ",\"texto\":\"" + texto + "\"}";
                var content = new StringContent(json, Encoding.UTF8, "application/json");
                var response = await client.PostAsync($"{BASE_URL}/publicacion/texto", content);
                return response.IsSuccessStatusCode;
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error: " + ex.Message);
                return false;
            }
        }
        public async Task<bool> EliminarCuentaAsync(int idUsuario)
        {
            try
            {
                var response = await client.DeleteAsync($"{BASE_URL}/usuario/eliminar/{idUsuario}");
                return response.IsSuccessStatusCode;
            }
            catch (Exception ex)
            {
                Debug.WriteLine("Error: " + ex.Message);
                return false;
            }
        }

        public string ImageToBase64(Image image)
        {
            if (image == null) return null;
            using (MemoryStream ms = new MemoryStream())
            {
                image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);
                return Convert.ToBase64String(ms.ToArray());
            }
        }
    }
}