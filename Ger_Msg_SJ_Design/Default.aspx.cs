using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    string[] hor_ini = new string[] { "08:00", "09:00", "10:00", "12:00", "14:00" };
    string[] hor_fim = new string[] { "09:00", "10:00", "12:00", "13:00", "18:00" }; 

    protected void Page_Load(object sender, EventArgs e)
    {
        for (int i = 0; i < hor_ini.Length; i++)
            Response.Write(hor_ini[i] + " " + hor_fim[i] + "<br />");
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        int contador = 0;

        for (int i = 0; i < hor_ini.Length; i++)
        {
            //Response.Write("comparando: " + txtIni.Text + " com " + hor_ini[i] + "<br/>");
            if( ( TimeSpan.Parse(hor_ini[i]) < TimeSpan.Parse(txtIni.Text) && TimeSpan.Parse(hor_fim[i]) > TimeSpan.Parse(txtIni.Text) ) ||
                ( TimeSpan.Parse(hor_ini[i]) < TimeSpan.Parse(txtFim.Text) && TimeSpan.Parse(hor_fim[i]) > TimeSpan.Parse(txtFim.Text) ) || 
                ( TimeSpan.Parse(hor_ini[i]) > TimeSpan.Parse(txtIni.Text) && TimeSpan.Parse(hor_fim[i]) < TimeSpan.Parse(txtFim.Text) ) || 
                ( TimeSpan.Parse(hor_ini[i]) == TimeSpan.Parse(txtIni.Text) && TimeSpan.Parse(hor_fim[i]) == TimeSpan.Parse(txtFim.Text) ) )
            {
                contador++;
                break;
            }
        }

        if (contador >= 1)
            Response.Write("Horário Ocupado");
        else
            Response.Write("Horário Livre");
    }
}