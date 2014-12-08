using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Data;
using CadeMeuMedico.Models;

namespace CadeMeuMedico.Controllers
{
    public class EspecialidadesController : BaseController
    {
        private EntidadesCadeMeuMedicoBD db = new EntidadesCadeMeuMedicoBD();
        //
        // GET: /Especialidades/

        public ActionResult Index()
        {
            var especialidades = db.Especialidades.ToList();
            return View(especialidades);
        }

        public ActionResult Adicionar()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Adicionar(Especialidades especialiade)
        {
            if (ModelState.IsValid)
            {
                db.Especialidades.Add(especialiade);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(especialiade);
        }

        public ActionResult Detalhes(long id = 0)
        {
            Especialidades especialidade = db.Especialidades.Find(id);
            if (especialidade == null)
            {
                return HttpNotFound();
            }

            return View(especialidade);
        }

        public ActionResult Editar(long id)
        {
            Especialidades especialidade = db.Especialidades.Find(id);
            if (especialidade == null)
            {
                return HttpNotFound();
            }

            return View(especialidade);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Editar(Especialidades especialidade)
        {
            if (ModelState.IsValid)
            {
                db.Entry(especialidade).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(especialidade);
        }

        public ActionResult Excluir(long id = 0)
        {
            Especialidades especialidade = db.Especialidades.Find(id);
            if (especialidade == null)
            {
                return HttpNotFound();
            }
            return View(especialidade);
        }

        [HttpPost, ActionName("Excluir")]
        [ValidateAntiForgeryToken]
        public ActionResult ExclusaoConfirmada(long id)
        {
            Especialidades especialidade = db.Especialidades.Find(id);
            db.Especialidades.Remove(especialidade);
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
