namespace chemin
{
    partial class TarjetaPublicacion
    {
        /// <summary> 
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de componentes

        /// <summary> 
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.lblUsername = new System.Windows.Forms.Label();
            this.lblTexto = new System.Windows.Forms.Label();
            this.pbImagen = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pbImagen)).BeginInit();
            this.SuspendLayout();
            // 
            // lblUsername
            // 
            this.lblUsername.AutoSize = true;
            this.lblUsername.Location = new System.Drawing.Point(3, 14);
            this.lblUsername.Name = "lblUsername";
            this.lblUsername.Size = new System.Drawing.Size(0, 25);
            this.lblUsername.TabIndex = 1;
            // 
            // lblTexto
            // 
            this.lblTexto.AutoSize = true;
            this.lblTexto.Location = new System.Drawing.Point(39, 156);
            this.lblTexto.Name = "lblTexto";
            this.lblTexto.Size = new System.Drawing.Size(70, 25);
            this.lblTexto.TabIndex = 2;
            this.lblTexto.Text = "label2";
            // 
            // pbImagen
            // 
            this.pbImagen.Location = new System.Drawing.Point(35, 53);
            this.pbImagen.Name = "pbImagen";
            this.pbImagen.Size = new System.Drawing.Size(126, 76);
            this.pbImagen.TabIndex = 0;
            this.pbImagen.TabStop = false;
            // 
            // TarjetaPublicacion
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.lblTexto);
            this.Controls.Add(this.lblUsername);
            this.Controls.Add(this.pbImagen);
            this.Name = "TarjetaPublicacion";
            this.Size = new System.Drawing.Size(203, 225);
            this.Load += new System.EventHandler(this.TarjetaPublicacion_Load);
            ((System.ComponentModel.ISupportInitialize)(this.pbImagen)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pbImagen;
        private System.Windows.Forms.Label lblUsername;
        private System.Windows.Forms.Label lblTexto;
    }
}
