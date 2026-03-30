namespace chemin
{
    partial class FormRegistro
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormRegistro));
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.txtCorreo = new System.Windows.Forms.TextBox();
            this.txtContraseña = new System.Windows.Forms.TextBox();
            this.txtNombreUsuario = new System.Windows.Forms.TextBox();
            this.rbH = new System.Windows.Forms.RadioButton();
            this.rbO = new System.Windows.Forms.RadioButton();
            this.rbM = new System.Windows.Forms.RadioButton();
            this.btnRegistrar = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(154, 35);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(73, 25);
            this.label1.TabIndex = 0;
            this.label1.Text = "correo";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(154, 96);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(119, 25);
            this.label2.TabIndex = 1;
            this.label2.Text = "contraseña";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(154, 162);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(191, 25);
            this.label3.TabIndex = 2;
            this.label3.Text = "nombre de usuario";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(154, 226);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(79, 25);
            this.label4.TabIndex = 3;
            this.label4.Text = "genero";
            // 
            // txtCorreo
            // 
            this.txtCorreo.Location = new System.Drawing.Point(375, 35);
            this.txtCorreo.Name = "txtCorreo";
            this.txtCorreo.Size = new System.Drawing.Size(228, 31);
            this.txtCorreo.TabIndex = 4;
            // 
            // txtContraseña
            // 
            this.txtContraseña.Location = new System.Drawing.Point(375, 96);
            this.txtContraseña.Name = "txtContraseña";
            this.txtContraseña.Size = new System.Drawing.Size(228, 31);
            this.txtContraseña.TabIndex = 5;
            // 
            // txtNombreUsuario
            // 
            this.txtNombreUsuario.Location = new System.Drawing.Point(375, 162);
            this.txtNombreUsuario.Name = "txtNombreUsuario";
            this.txtNombreUsuario.Size = new System.Drawing.Size(228, 31);
            this.txtNombreUsuario.TabIndex = 6;
            // 
            // rbH
            // 
            this.rbH.AutoSize = true;
            this.rbH.Location = new System.Drawing.Point(375, 226);
            this.rbH.Name = "rbH";
            this.rbH.Size = new System.Drawing.Size(115, 29);
            this.rbH.TabIndex = 7;
            this.rbH.TabStop = true;
            this.rbH.Text = "hombre";
            this.rbH.UseVisualStyleBackColor = true;
            // 
            // rbO
            // 
            this.rbO.AutoSize = true;
            this.rbO.Location = new System.Drawing.Point(598, 226);
            this.rbO.Name = "rbO";
            this.rbO.Size = new System.Drawing.Size(80, 29);
            this.rbO.TabIndex = 8;
            this.rbO.TabStop = true;
            this.rbO.Text = "otro";
            this.rbO.UseVisualStyleBackColor = true;
            // 
            // rbM
            // 
            this.rbM.AutoSize = true;
            this.rbM.Location = new System.Drawing.Point(496, 226);
            this.rbM.Name = "rbM";
            this.rbM.Size = new System.Drawing.Size(96, 29);
            this.rbM.TabIndex = 9;
            this.rbM.TabStop = true;
            this.rbM.Text = "mujer";
            this.rbM.UseVisualStyleBackColor = true;
            // 
            // btnRegistrar
            // 
            this.btnRegistrar.Location = new System.Drawing.Point(375, 311);
            this.btnRegistrar.Name = "btnRegistrar";
            this.btnRegistrar.Size = new System.Drawing.Size(151, 47);
            this.btnRegistrar.TabIndex = 10;
            this.btnRegistrar.Text = "registrar";
            this.btnRegistrar.UseVisualStyleBackColor = true;
            this.btnRegistrar.Click += new System.EventHandler(this.btnRegistrar_Click);
            // 
            // FormRegistro
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.btnRegistrar);
            this.Controls.Add(this.rbM);
            this.Controls.Add(this.rbO);
            this.Controls.Add(this.rbH);
            this.Controls.Add(this.txtNombreUsuario);
            this.Controls.Add(this.txtContraseña);
            this.Controls.Add(this.txtCorreo);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FormRegistro";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Registro";
            this.Load += new System.EventHandler(this.FormRegistro_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtCorreo;
        private System.Windows.Forms.TextBox txtContraseña;
        private System.Windows.Forms.TextBox txtNombreUsuario;
        private System.Windows.Forms.RadioButton rbH;
        private System.Windows.Forms.RadioButton rbO;
        private System.Windows.Forms.RadioButton rbM;
        private System.Windows.Forms.Button btnRegistrar;
    }
}