package controllers

import play.api._
import play.api.mvc._

object Home extends Controller {
  
  def index = Action { implicit request => 
    Ok(views.html.index("Your new application is ready."))
  }
  
}