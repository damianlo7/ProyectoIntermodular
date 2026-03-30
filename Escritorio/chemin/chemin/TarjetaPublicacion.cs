using System;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Windows.Forms;

namespace chemin
{
    public partial class TarjetaPublicacion : UserControl
    {
        private Api api = new Api();
        private Publicacion publicacion;

        public TarjetaPublicacion(Publicacion p)
        {
            InitializeComponent();
            this.publicacion = p;
            this.BackColor = Color.White;
            CargarDatos();
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            base.OnPaint(e);
            int radio = 15;
            GraphicsPath path = new GraphicsPath();
            path.AddArc(0, 0, radio, radio, 180, 90);
            path.AddArc(this.Width - radio, 0, radio, radio, 270, 90);
            path.AddArc(this.Width - radio, this.Height - radio, radio, radio, 0, 90);
            path.AddArc(0, this.Height - radio, radio, radio, 90, 90);
            path.CloseFigure();
            this.Region = new Region(path);
            e.Graphics.SmoothingMode = SmoothingMode.AntiAlias;
            e.Graphics.DrawPath(new Pen(Color.LightGray, 1), path);
        }

        private void CargarDatos()
        {
            lblUsername.Text = "@" + publicacion.Username;
            lblUsername.Font = new Font("Segoe UI", 10, FontStyle.Bold);
            lblUsername.ForeColor = Color.DimGray;

            bool tieneTexto = !string.IsNullOrEmpty(publicacion.Texto) && publicacion.Texto != "null";
            bool tieneImagen = !string.IsNullOrEmpty(publicacion.Imagen) && publicacion.Imagen != "null";

            if (tieneTexto)
            {
                lblTexto.Text = publicacion.Texto;
                lblTexto.Font = new Font("Segoe UI", 11);
                lblTexto.Location = new Point(10, lblUsername.Bottom + 5);
                pbImagen.Location = new Point(10, lblTexto.Bottom + 10);
            }
            else
            {
                lblTexto.Visible = false;
                pbImagen.Location = new Point(10, lblUsername.Bottom + 10);
            }

            if (tieneImagen)
            {
                pbImagen.Image = api.Base64ToImage(publicacion.Imagen);
                pbImagen.SizeMode = PictureBoxSizeMode.Zoom;
                pbImagen.Width = 380;
                pbImagen.Height = 250;
                this.Height = pbImagen.Bottom + 15;
            }
            else
            {
                pbImagen.Visible = false;
                this.Height = (tieneTexto ? lblTexto.Bottom : lblUsername.Bottom) + 15;
            }

            this.Width = 400;
        }

        private void TarjetaPublicacion_Load(object sender, EventArgs e)
        {
        }
    }
}