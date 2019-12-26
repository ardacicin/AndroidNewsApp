using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data;
using MySql.Data.MySqlClient;

namespace yazlab2
{
    public partial class Form1 : Form
    {
        sqlbaglantisi bag = new sqlbaglantisi();
        DataTable table = new DataTable();
       
        public Form1()
        {
            InitializeComponent();
        }

        private void Label1_Click(object sender, EventArgs e)
        {

        }

        public void haber()//Müşterileri Listeleme fonksiyonu
        {
           // tb_haberid.Text = "";//textboxları doluysalar bi temizliyoruz
            tb_haberturu.Text = "";
            tb_haberbasligi.Text = "";
            tb_habericerigi.Text = "";
            tb_yayinlanmatarihi.Text = "";          
            table.Clear();
            MySqlDataAdapter adtr = new MySqlDataAdapter(" Select * from test22", bag.baglan());
            adtr.Fill(table);
            DataGridView1.DataSource = table;//gridwiewe de doldurdumuz tabloya aktardık
            DataGridView1.RowHeadersVisible = false;
            DataGridView1.MultiSelect = false;
            DataGridView1.SelectionMode = DataGridViewSelectionMode.FullRowSelect;          
        }
        
        private void Button_ekle_Click(object sender, EventArgs e)
        {
            MySqlCommand kmt = new MySqlCommand();
            kmt.Connection = bag.baglan();
            kmt.CommandText = "INSERT INTO test22(haberid,haberturu,haberbasligi,habericerigi,yayinlanmatarihi) VALUES ('" + this.tb_haberid.Text + "','" + this.tb_haberturu.Text + "','" + this.tb_haberbasligi.Text + "','" + this.tb_habericerigi.Text + "','" + this.tb_yayinlanmatarihi.Text + "');";
            kmt.Parameters.AddWithValue("@p1", tb_haberid);
            kmt.Parameters.AddWithValue("@p2", tb_haberturu);
            kmt.Parameters.AddWithValue("@p3", tb_haberbasligi);
            kmt.Parameters.AddWithValue("@p4", tb_habericerigi);
            kmt.Parameters.AddWithValue("@p5", tb_yayinlanmatarihi);
           

            kmt.ExecuteNonQuery();               
            MessageBox.Show("Kayıt İşlemi Başarılı");           
            haber();          
        }

        private void DataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
