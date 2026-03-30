using System;
using System.Drawing;
using System.Net.Http;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace chemin
{
    public partial class Form1 : Form
    {
        private static readonly HttpClient client = new HttpClient();
        private string BASE_URL = "http://localhost:8080/tema5maven/rest";

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            panel1.Location = new Point(
                (this.ClientSize.Width - panel1.Width) / 2,
                (this.ClientSize.Height - panel1.Height) / 2);
        }
        private void Form1_Resize(object sender, EventArgs e)
        {
            panel1.Location = new Point(
            (this.ClientSize.Width - panel1.Width) / 2,
            (this.ClientSize.Height - panel1.Height) / 2);
        }

        private async void btnEntrar_Click(object sender, EventArgs e)
        {
            string username = txtNombreUsuario.Text.Trim();
            string password = txtContrasenha.Text.Trim();

            if (string.IsNullOrEmpty(username) || string.IsNullOrEmpty(password))
            {
                MessageBox.Show("Rellena todos los campos");
                return;
            }

            try
            {
                string json = "{\"username\":\"" + username + "\",\"contrasenha\":\"" + password + "\"}";
                var content = new StringContent(json, Encoding.UTF8, "application/json");
                var response = await client.PostAsync($"{BASE_URL}/usuario/login", content);

                if (response.IsSuccessStatusCode)
                {
                    string responseBody = await response.Content.ReadAsStringAsync();

                    string id = Regex.Match(responseBody, "\"id\":(\\d+)").Groups[1].Value;
                    string nombre = Regex.Match(responseBody, "\"nombreCompleto\":\"([^\"]+)\"").Groups[1].Value;

                    Sesion.Id = int.Parse(id);
                    Sesion.Username = username;
                    Sesion.Nombre = nombre;

                    FormPrincipal formPrincipal = new FormPrincipal();
                    formPrincipal.Show();
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Usuario o contraseña incorrectos");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error de conexión: " + ex.Message);
            }
        }
        protected override void OnPaintBackground(PaintEventArgs e)
        {
            if (this.ClientSize.Width == 0 || this.ClientSize.Height == 0) return;

            using (System.Drawing.Drawing2D.LinearGradientBrush brush =
                new System.Drawing.Drawing2D.LinearGradientBrush(
                    this.ClientRectangle,
                    Color.FromArgb(0xF8, 0xFB, 0xFF),
                    Color.FromArgb(0xEA, 0xF2, 0xFF),
                    System.Drawing.Drawing2D.LinearGradientMode.Vertical))
            {
                e.Graphics.FillRectangle(brush, this.ClientRectangle);
            }
        }
        private void btnRegistro_Click(object sender, EventArgs e)
        {
            FormRegistro formRegistro = new FormRegistro();
            formRegistro.Show();
            this.Hide();
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}