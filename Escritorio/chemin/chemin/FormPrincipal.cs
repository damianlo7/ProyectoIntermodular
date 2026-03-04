using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace chemin
{
    public partial class FormPrincipal : Form
    {

        public FormPrincipal()
        {
            InitializeComponent();
            btnPerfil.Image = new Bitmap(Image.FromFile("C:/Users/damia/Desktop/proyectoCHEMIN/imagenes/usuario.png"), new Size(20, 20));
            btnImagen.Image = new Bitmap(Image.FromFile("C:/Users/damia/Desktop/proyectoCHEMIN/imagenes/anadir.png"), new Size(20, 20));
            btnTexto.Image = new Bitmap(Image.FromFile("C:/Users/damia/Desktop/proyectoCHEMIN/imagenes/burbujachat.png"), new Size(20, 20));

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
        }

        private void btnImagen_Click(object sender, EventArgs e)
        {
            //formulario modal (mirar como poder subir imagenes)
            FormEnviarImagen formEnviarImagen = new FormEnviarImagen();
            formEnviarImagen.ShowDialog();
        }

        private void FormPrincipal_Load(object sender, EventArgs e)
        {

        }
    }
}
