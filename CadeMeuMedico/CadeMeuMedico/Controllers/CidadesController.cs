using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Data;
using CadeMeuMedico.Models;

namespace CadeMeuMedico.Controllers
{
    public class CidadesController : BaseController
    {
        private EntidadesCadeMeuMedicoBD db = new EntidadesCadeMeuMedicoBD();

        //
        // GET: /Cidades/

        public ActionResult Index()
        {
            var cidades = db.Cidades.ToList();
            return View(cidades);
        }

        public ActionResult Adicionar()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Adicionar(Cidades cidade)
        {
            if (ModelState.IsValid)
            {
                db.Cidades.Add(cidade);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(cidade);
        }

        public ActionResult Detalhes(long id = 0)
        {
            Cidades cidade = db.Cidades.Find(id);
            if (cidade == null)
            {
                return HttpNotFound();
            }

            return View(cidade);
        }

        public ActionResult Editar(long id)
        {
            Cidades cidade = db.Cidades.Find(id);
            if (cidade == null)
            {
                return HttpNotFound();
            }

            return View(cidade);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Editar(Cidades cidade)
        {
            if (ModelState.IsValid)
            {
                db.Entry(cidade).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(cidade);
        }

        public ActionResult Excluir(long id = 0)
        {
            Cidades cidade = db.Cidades.Find(id);
            if (cidade == null)
            {
                return HttpNotFound();
            }
            return View(cidade);
        }

        [HttpPost, ActionName("Excluir")]
        [ValidateAntiForgeryToken]
        public ActionResult ExclusaoConfirmada(long id)
        {
            Cidades cidade = db.Cidades.Find(id);
            db.Cidades.Remove(cidade);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }
    }
}
