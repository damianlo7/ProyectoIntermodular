namespace chemin
{
    partial class FormPrincipal
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormPrincipal));
            this.flowPublicaciones = new System.Windows.Forms.FlowLayoutPanel();
            this.btnPerfil = new System.Windows.Forms.Button();
            this.btnImagen = new System.Windows.Forms.Button();
            this.btnTexto = new System.Windows.Forms.Button();
            this.pnToolbar = new System.Windows.Forms.Panel();
            this.pnToolbar.SuspendLayout();
            this.SuspendLayout();
            // 
            // flowPublicaciones
            // 
            this.flowPublicaciones.AutoScroll = true;
            this.flowPublicaciones.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flowPublicaciones.FlowDirection = System.Windows.Forms.FlowDirection.TopDown;
            this.flowPublicaciones.Location = new System.Drawing.Point(0, 0);
            this.flowPublicaciones.Name = "flowPublicaciones";
            this.flowPublicaciones.Padding = new System.Windows.Forms.Padding(0, 90, 0, 0);
            this.flowPublicaciones.Size = new System.Drawing.Size(800, 450);
            this.flowPublicaciones.TabIndex = 3;
            this.flowPublicaciones.WrapContents = false;
            this.flowPublicaciones.Paint += new System.Windows.Forms.PaintEventHandler(this.flowPublicaciones_Paint);
            // 
            // btnPerfil
            // 
            this.btnPerfil.FlatAppearance.BorderSize = 0;
            this.btnPerfil.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnPerfil.Image = global::chemin.Properties.Resources.usuario;
            this.btnPerfil.Location = new System.Drawing.Point(697, 3);
            this.btnPerfil.Name = "btnPerfil";
            this.btnPerfil.Size = new System.Drawing.Size(80, 80);
            this.btnPerfil.TabIndex = 0;
            this.btnPerfil.UseVisualStyleBackColor = true;
            this.btnPerfil.Click += new System.EventHandler(this.button1_Click);
            // 
            // btnImagen
            // 
            this.btnImagen.FlatAppearance.BorderSize = 0;
            this.btnImagen.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnImagen.Image = global::chemin.Properties.Resources.anadir;
            this.btnImagen.Location = new System.Drawing.Point(611, 3);
            this.btnImagen.Name = "btnImagen";
            this.btnImagen.Size = new System.Drawing.Size(80, 80);
            this.btnImagen.TabIndex = 1;
            this.btnImagen.UseVisualStyleBackColor = true;
            this.btnImagen.Click += new System.EventHandler(this.btnImagen_Click);
            // 
            // btnTexto
            // 
            this.btnTexto.FlatAppearance.BorderSize = 0;
            this.btnTexto.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnTexto.Image = global::chemin.Properties.Resources.burbujachat;
            this.btnTexto.Location = new System.Drawing.Point(525, 3);
            this.btnTexto.Name = "btnTexto";
            this.btnTexto.Size = new System.Drawing.Size(80, 80);
            this.btnTexto.TabIndex = 2;
            this.btnTexto.UseVisualStyleBackColor = true;
            this.btnTexto.Click += new System.EventHandler(this.btnTexto_Click);
            // 
            // pnToolbar
            // 
            this.pnToolbar.Controls.Add(this.btnTexto);
            this.pnToolbar.Controls.Add(this.btnPerfil);
            this.pnToolbar.Controls.Add(this.btnImagen);
            this.pnToolbar.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnToolbar.Location = new System.Drawing.Point(0, 0);
            this.pnToolbar.Name = "pnToolbar";
            this.pnToolbar.Padding = new System.Windows.Forms.Padding(0, 90, 0, 0);
            this.pnToolbar.Size = new System.Drawing.Size(800, 100);
            this.pnToolbar.TabIndex = 4;
            // 
            // FormPrincipal
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.pnToolbar);
            this.Controls.Add(this.flowPublicaciones);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FormPrincipal";
            this.Text = "Chemin";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.FormPrincipal_Load);
            this.pnToolbar.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.FlowLayoutPanel flowPublicaciones;
        private System.Windows.Forms.Button btnPerfil;
        private System.Windows.Forms.Button btnImagen;
        private System.Windows.Forms.Button btnTexto;
        private System.Windows.Forms.Panel pnToolbar;
    }
}