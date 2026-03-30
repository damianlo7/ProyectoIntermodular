using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace chemin
{
    public partial class FormPrincipal : Form
    {
        private Api api = new Api();
        public FormPrincipal()
        {
            InitializeComponent();
            btnPerfil.Image = new Bitmap(Image.FromFile("C:/Users/damia/Desktop/proyectoCHEMIN/imagenes/usuario.png"), new Size(35, 35));
            btnImagen.Image = new Bitmap(Image.FromFile("C:/Users/damia/Desktop/proyectoCHEMIN/imagenes/anadir.png"), new Size(35, 35));
            btnTexto.Image = new Bitmap(Image.FromFile("C:/Users/damia/Desktop/proyectoCHEMIN/imagenes/burbujachat.png"), new Size(35, 35));

        }

        private void button1_Click(object sender, EventArgs e)
        {
            //formulario modal editar usuario
            FormPerfil formPerfil = new FormPerfil();
            formPerfil.ShowDialog();
        }

        private void btnTexto_Click(object sender, EventArgs e)
        {
            //formulario modal para enviar mensaje
            FormEnviarTexto formEnviarTexto = new FormEnviarTexto();
            formEnviarTexto.ShowDialog();
            CargarPublicaciones();
        }

        private void btnImagen_Click(object sender, EventArgs e)
        {
            //formulario modal (mirar como poder subir imagenes)
            FormEnviarImagen formEnviarImagen = new FormEnviarImagen();
            formEnviarImagen.ShowDialog();
            CargarPublicaciones();
        }

        private async void CargarPublicaciones()
        {
            flowPublicaciones.Controls.Clear();
            List<Publicacion> publicaciones = await api.GetPublicacionesAsync();
            foreach (Publicacion p in publicaciones)
            {
                TarjetaPublicacion tarjeta = new TarjetaPublicacion(p);
                tarjeta.Width = 400;
                tarjeta.Margin = new Padding((flowPublicaciones.Width - 400) / 2, 10, 0, 10);
                flowPublicaciones.Controls.Add(tarjeta);
            }
        }
        private void FormPrincipal_Load(object sender, EventArgs e)
        {
            ColocarBotones();
            CargarPublicaciones();
            this.Resize += (s, e2) => {
                ColocarBotones();
                foreach (Control c in flowPublicaciones.Controls)
                {
                    c.Margin = new Padding((flowPublicaciones.Width - 400) / 2, 10, 0, 10);
                }
            };
            flowPublicaciones.BackColor = Color.Transparent;
            //pnToolbar.BackColor = Color.Transparent;
        }
        private void ColocarBotones()
        {
            int margenDerecha = 10;
            int y = (pnToolbar.Height - btnPerfil.Height) / 2;

            btnPerfil.Location = new Point(pnToolbar.Width - btnPerfil.Width - margenDerecha, y);
            btnTexto.Location = new Point(btnPerfil.Left - btnTexto.Width - 10, y);
            btnImagen.Location = new Point(btnTexto.Left - btnImagen.Width - 10, y);
        }
        private void FormPrincipal_Resize(object sender, EventArgs e)
        {
            ColocarBotones();
        }
        private void flowPublicaciones_Paint(object sender, PaintEventArgs e)
        {

        }
        protected override void OnPaintBackground(PaintEventArgs e)
        {
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
    }
}
