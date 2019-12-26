using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data;
using MySql.Data.MySqlClient;

namespace yazlab2
{
class sqlbaglantisi
    {
public MySqlConnection baglan()
{
  MySqlConnection baglanti = new MySqlConnection("Server=localhost;Database=test2;Uid=root;Pwd=;");
  baglanti.Open();
  MySqlConnection.ClearPool(baglanti);
  MySqlConnection.ClearAllPools();
  return (baglanti);
        }
    }
}
